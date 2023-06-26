package org.example.TCPClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class CustomFrame extends JFrame {

    Socket socket;
    private JTextArea textArea;


    public CustomFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set 3 buttons
        JButton expensiveButton = new JButton("More expensive");
        JButton allButton = new JButton("All");
        JButton sortedButton = new JButton("Sorted by name");

        // Set container
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Set container panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(expensiveButton);
        buttonPanel.add(allButton);
        buttonPanel.add(sortedButton);
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        container.add(buttonPanel, BorderLayout.PAGE_START);
        container.add(scrollPane, BorderLayout.CENTER);

        allButton.addActionListener(e -> useButton("all"));
        sortedButton.addActionListener(e -> useButton("all_sorted"));
        expensiveButton.addActionListener(e -> useButton("more_expensive"));
    }

    void useButton(String query) {
        {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    getJSON(query);
                    return null;
                }
            };
            worker.execute();
        }
    }

    void getJSON(String s) {
        try {
            socket = new Socket("127.0.0.1", 1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (
                IOException e) {
            System.out.println("impossibile scrivere sul socket");
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("impossibile allocare nel bufferedReader");
        }

        try {
            out.println(s);
            out.flush();
            StringBuilder response = new StringBuilder();
            String line;

            while (true) {
                try {
                    if ((line = in.readLine()) == null && line.length() == 0) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    } else {
                        response.append(line);
                        response.append("\n");
                        textArea.setText(response.toString());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}