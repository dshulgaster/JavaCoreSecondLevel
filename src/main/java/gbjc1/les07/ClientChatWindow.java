package gbjc1.les07;

import javax.swing.*;

public class ClientChatWindow extends JFrame {
    final JLabel messagesList;

    interface Callback {
        void sendMessage(String text);
    }

    ClientChatWindow(Callback callback) {
        setBounds(300, 300, 500, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        // Список сообщений
        JScrollPane messagesScroll = new JScrollPane();
        messagesList = new JLabel();
        messagesScroll.add(messagesList);
        add(messagesList);
        // Панель нового сообщения
        JPanel sendMessagePanel = new JPanel();
        sendMessagePanel.setLayout(new BoxLayout(sendMessagePanel, BoxLayout.X_AXIS));
        JTextField messageField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendMessagePanel.add(messageField);
        sendMessagePanel.add(sendButton);
        add(sendMessagePanel);
        sendButton.addActionListener((e) -> {
            callback.sendMessage(messageField.getText());
        });
    }

    public void onNewMessage(String message) {
        messagesList.setText(messagesList.getText() + "\n" + message);
    }

}
