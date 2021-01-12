package gbjc1.les04;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {
    private final JButton sendButton;
    private final JTextField messageField;
    private final JTextPane logPane;
    private final Document logDocument;

    public ChatWindow() {
        setTitle("Net chat");
        setBounds(200, 100, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sendButton = new JButton("send");
        messageField = new JTextField();
        logPane = new JTextPane();
        logPane.setPreferredSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(logPane);
        logPane.setEditable(false);
        JScrollPane scroll = new JScrollPane(logPane);
        add(scroll);
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.add(messageField);
        messagePanel.add(sendButton);
        add(messagePanel);
        logDocument = logPane.getDocument();
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        setVisible(true);
    }

    private void sendMessage() {
        String message = messageField.getText();
        messageField.setText("");
        try {
            logDocument.insertString(logDocument.getLength(), message + "\n", null);
        } catch (BadLocationException x) {
            System.out.println("Exception");
        }
    }
}