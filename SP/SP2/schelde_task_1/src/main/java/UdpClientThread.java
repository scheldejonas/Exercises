import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <h2>Task 2</h2>
 * <p>
 * Now that you implemented a server it's time to run it! Create a client which sends the bytes from the
 * {@link #MESSAGE} constant to the server.
 * </p>
 * <p>
 * First try to create a UDP socket with the {@link InetSocketAddress} to the server as a constructor argument
 * to the {@link DatagramSocket}. Before doing anything else, try to run this code. Does it give an exception?
 * Why/Why not? And what would a TCP socket do?
 * </p>
 * <p>
 * Second, send the text message from the {@link #MESSAGE} constant to the server and check to see what is actually
 * received.
 * </p>
 */
public class UdpClientThread implements Runnable {

    // This is horrifyingly hilariously bad movie
    private final byte[] MESSAGE =
            "We are all interested in the future, for that is where you and I will spend the rest of our lives!".getBytes(); // Plan 9 from Outer Space"

    private final ReentrantLock reentrantLock;

    public UdpClientThread(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        sendMessageToUdpServer();
    }

    public void sendMessageToUdpServer() {
        // TODO: 1. Create a socket and connect it to the server

        try {
            reentrantLock.lock();

            DatagramPacket datagramPacket = new DatagramPacket(MESSAGE,MESSAGE.length);

            try {
                DatagramSocket datagramSocketClient = new DatagramSocket();
                datagramSocketClient.connect(new InetSocketAddress("localhost",8080));
                datagramSocketClient.send(datagramPacket);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } finally {
            reentrantLock.unlock();
        }

    }

}
