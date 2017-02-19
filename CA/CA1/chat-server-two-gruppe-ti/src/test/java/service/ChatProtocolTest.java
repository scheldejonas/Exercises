package service;

import controller.ChatServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.Socket;

/**
 * Created by scheldejonas on 18/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatProtocolTest {

    @Mock
    private ChatServer chatServer;

    @Mock
    private Socket socket;

    @InjectMocks
    private ClientThread clientThread = new ClientThread(socket);

}
