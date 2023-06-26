package org.example.TCPClient;


import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame f = new CustomFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.setVisible(true);
    }


}

