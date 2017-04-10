package threadsObserver;

import randomperson.RandomUser;
import threadsObserver.synchron_gui_actions.StartFecthingNewRandomUser_ToObservers;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class RandomUserFormOne {
    private JTextField textFirstName;
    private JTextField textLastName;
    private JTextField textStreet;
    private JTextField textCity;
    private JTextField textEmail;
    private JButton addNewTestUserButton;
    private JButton saveUserDOESNOTHINGButton;
    private JPanel RandomUserFormOne;
    private JLabel addNewUserStatusLine;

    private RandomUserControl randomUserControl = new RandomUserControl();

    public void startGUI() {
        JFrame frame = new JFrame("RandomUserFormOne");
        frame.setContentPane(new RandomUserFormOne().RandomUserFormOne);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public RandomUserFormOne() {
        saveUserDOESNOTHINGButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    consoleActionOut(e);
                    informUserOfNotImplemented();
                }
            }
        );
        addNewTestUserButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    consoleActionOut(e);
                    startUpdateWithNewUserSeparatly();
                }
            }
        );
    }

    private void startUpdateWithNewUserSeparatly() {
        StartFecthingNewRandomUser_ToObservers observableFetcher = new StartFecthingNewRandomUser_ToObservers();
        observableFetcher.addObserver(
            new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    System.out.println("...GUI recieved Random User from fetcher.");
                    RandomUser randomUser = (RandomUser) arg;
                    setInputFieldsWithUser(randomUser);
                    System.out.println("...GUI is done updating with recieved Random User.");
                    addNewUserStatusLine.setText("Status: DONE, New random user fetched.");
                }
            }
        );
        new Thread(observableFetcher).start();
        addNewUserStatusLine.setText("Status: WAIT, Getting Random User.");
    }

    private void consoleActionOut(ActionEvent e) {
        System.out.println("...User pushed: " + e.getActionCommand());
    }

    private void setInputFieldsWithUser(RandomUser randomUser) {
        textFirstName.setText(randomUser.getFirstName());
        textLastName.setText(randomUser.getLastName());
        textCity.setText(randomUser.getCity());
        textEmail.setText(randomUser.getEmail());
        textStreet.setText(randomUser.getStreet());
    }

    private void informUserOfNotImplemented() {
        JOptionPane.showMessageDialog(null,"This function is not yet implemented");
    }


}
