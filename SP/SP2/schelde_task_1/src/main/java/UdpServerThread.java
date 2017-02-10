import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <h2>Task 1</h2>
 * <p>
 *     Your first task is to write an UDP server instead of a TCP server. UDP splits your data into small data fragments
 *     and sends them off without any guarantee of order: you don't know which package comes first! (A bit like
 *     concurrency). Instead of the constantly open output- and input-streams from the TCP server, you receive and
 *     send things from/to byte arrays.
 * </p>
 * <p>
 *     So, each package receives a certain amount of bytes. In our (kinda artificial) case we only receive 10 bytes.
 *     Use the {@link #DATA_BUFFER} for storing and retrieving data in this exercise.
 * </p>
 * <p>
 *     Your job is to implement a UDP server that binds to a socket on your local machine and waits until a client
 *     sends some data. When the data arrives you print it out. That's it! When it's printed, notice how much data is
 *     sent (and how much isn't). That's the drawback of this approach. On the other hand it's fast!
 * </p>
 */
public class UdpServerThread extends Thread {

    private static final byte[] DATA_BUFFER = new byte[10];
    private final ReentrantLock reentrantLock;
    private ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private ExecutorService executorService = Executors.newFixedThreadPool(128,threadFactory);
    private DatagramSocket datagramSocket = null;

    public UdpServerThread(ReentrantLock reentrantLock) {
        if (reentrantLock == null) {
            this.reentrantLock = reentrantLock;
        } else {
            this.reentrantLock = new ReentrantLock();
        }
    }

    @Override
    public void run() {
        // TODO 1. Create a UDP server that binds (listens) to a port

        try {
            datagramSocket = new DatagramSocket( new InetSocketAddress(Inet4Address.getByName("192.168.1.37"),8080) );
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // TODO 2. Wait for some data to arrive

        byte[] buffer = new byte[255];

        DatagramPacket datagramRecievePacket = new DatagramPacket(buffer,buffer.length);

        AtomicLong connectionsCounter = new AtomicLong(0);

        System.out.printf("Server is listening on port: %s:%s \n",datagramSocket.getLocalAddress(),datagramSocket.getLocalPort() );

        try {
            while (true) {

                connectionsCounter.getAndAdd(1);

                datagramSocket.receive(datagramRecievePacket);

                executorService.execute(new ConnectionProcess(datagramSocket, reentrantLock, datagramRecievePacket.getData(), new AtomicLong(connectionsCounter.longValue())) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // TODO: Wait for the thread to finish before the program stops by Exceptions
            executorService.shutdown();
        }

    }

    public void shutdownServer() {
        executorService.shutdown();
        datagramSocket.close();
    }

    class ConnectionProcess implements Runnable {

        private byte[] datagramPacket;
        private DatagramSocket datagramSocketServer;
        private final ReentrantLock reentrantLock;
        private AtomicLong connectionCounter;

        public ConnectionProcess(DatagramSocket datagramSocketServer, ReentrantLock reentrantLock, byte[] datagramRecievePacket, AtomicLong connectionsCounter) {
            this.datagramSocketServer = datagramSocketServer;
            this.reentrantLock = reentrantLock;
            this.datagramPacket = datagramRecievePacket;
            this.connectionCounter = connectionsCounter;
        }

        @Override
        public void run() {

            try {
                reentrantLock.lock();

                String recieveText = new String(datagramPacket);

                // TODO 3. Print the data!
                System.out.printf("Printing data of Connection %s...\n",connectionCounter);

                for (char character : recieveText.toCharArray()
                     ) {
                    if ( character != '\0' ) {
                        System.out.printf("%s",character);
                    }
                }

                System.out.printf("\nDone printing data of Connection %s...\n",connectionCounter);


//                InetAddress sendBackInetIpAddress = datagramPacket.getAddress();
//
//                int sendBackPort = datagramPacket.getPort();
//
//                String confirmSentence = "I have recieved you message";
//
//                byte[] sendDataByteArray = new byte[confirmSentence.length()];
//
//                sendDataByteArray = confirmSentence.getBytes();
//
//                DatagramPacket sendDatagramPacket = new DatagramPacket(sendDataByteArray,sendDataByteArray.length,sendBackInetIpAddress,sendBackPort);
//
//                try {
//                    datagramSocketServer.send(sendDatagramPacket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            } finally {
                reentrantLock.unlock();
            }

        }
    }

}
