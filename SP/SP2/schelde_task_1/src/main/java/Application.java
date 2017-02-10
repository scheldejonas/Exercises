import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 07/02/17.
 */
public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {

        final ReentrantLock reentrantLock = new ReentrantLock();

        // TODO: Run the udp server in a thread here

        UdpServerThread udpServerThreadOne = new UdpServerThread(reentrantLock);

        // TODO: 2. Try to start the socket without the server. Do you get an exception? Why/Why not?

        // I excepted some error in the client send method, but didn't came.
        // This was of course because the ud protocol is not checking if the reciever got anything.

        // TODO: 3. Start the server and send the message from above to the server. What are you receiving?

        // This Server is not built for being started more then once. There is no secureness of race conditions here, but there is ofcourse for recieving udp packets inside the server.
        // The reason is just for this test case, so this main can continue down and start the client and send a hell of packets.
        udpServerThreadOne.start();



        // Waiting a bit for the server to bind on the port and reach the pausing recieve method.
        Thread.sleep(3*1000);

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ExecutorService executorService = Executors.newFixedThreadPool(128,threadFactory);

        int counter = 0;
        for (int i = 0; i < 20000; i++ ) {
            executorService.submit(new UdpClientThread(reentrantLock));
        }

        executorService.shutdown();

        udpServerThreadOne.shutdownServer();

    }
}
