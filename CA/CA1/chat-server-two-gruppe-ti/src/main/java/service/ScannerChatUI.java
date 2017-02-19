package service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by scheldejonas on 19/02/2017.
 */
public class ScannerChatUI {
    public ScannerChatUI() {
        System.err.println("This is a server and not a client, therefore a UI is not intended for this program");
        throw new NotImplementedException();
    }
}
