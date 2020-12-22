package gbjc1.les07;

// 1. Сервер - обрабатывающий запросы клиентов
// 1.1. Авторизация - для разграничения доступа
// 1.2. Шириковещательные сообщения
// 2.3. Лог чата

// 2. Клиент чата
// 2.1. Авторизация
// 2.2. Отправка широковещательного сообщения
// 2.3. Лог чата

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Server {

    List<ClientHandler> clients = new ArrayList<>();
//    List<Message> messages = new ArrayList<>();
    Map<String, List<Message>> chats = new HashMap<>();

    Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            AuthService authService = new AuthService();
            // Обработчик клиентов
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    new ClientHandler(authService, this, socket);
                }).start();
            }
        } catch (IOException e) {
            System.out.println("Сервер прекратил работу с ошибкой");
            e.printStackTrace();
        }
    }

//    synchronized void onNewMessage(Client client, String message) {
    synchronized void sendBroadCastMessage(Client sender, String textMessage) { // переименовали onNewMessage
        for (int i = 0; i < clients.size(); i++) {
            String recipientLogin = clients.get(i).client.login;
            sendMessageTo(sender, recipientLogin, textMessage);
        }
//        messages.add(new Message(client, message));
//        // Рассылаем сообщения всем
//        for (int i = 0; i < clients.size(); i++) {
//            ClientHandler recipient = clients.get(i);
//            if (!recipient.client.login.equals(client.login)) {
//                recipient.sendMessage(client, message);
//            }
//        }
    }

    synchronized void sendMessageTo(Client sender, String recipientLogin, String messageText) {
        // Получаем логин получателя для поиска
        String sendLogin = sender.login;
        // Генерируем ключ чата
        String key;
        if (sender.login.compareTo(recipientLogin) > 0) {   // проверить compareTo
            key = sendLogin + recipientLogin;
        } else {
            key = recipientLogin + sendLogin;
        }
        // Проверяем есть ли такой чат и, если нет, создаем новый
        if (!chats.containsKey(key)) {
            // Создаем список сообщений для чата
            chats.put(key, new ArrayList<>());
        }
        // Сохраняем сообщение в чат
        chats.get(key).add(new Message(sender, messageText));
        // Ищем получателей среди клиентов
        ClientHandler recipient = null;
        for (int i = 0; i < clients.size(); i++) {
            ClientHandler client = clients.get(i);
            if (client.client.login.equals(recipientLogin)) {
                recipient = client;
            }
        }
        // Если получатель онлайн, то отправляем ему сообщение
        if (recipient != null) {
        //if (recipient != null && recipient.equals(sender.login)) {
            recipient.sendMessage(sender, messageText);
            System.out.println("Отправлено сообщение для " + recipientLogin);
        } else {
            System.out.println("Получатель не найден! " + recipientLogin);
        }
    }

    synchronized void onNewClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
//        for (int i = 0; i < messages.size(); i++) {
//            Message message = messages.get(i);
//            clientHandler.sendMessage(message.client, message.text);
//        }
        sendBroadCastMessage(clientHandler.client, "Вошел в чат");
    }

    synchronized void onClientDisconnected(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        sendBroadCastMessage(clientHandler.client, "Покинул чат");
    }

    public static void main(String[] args) {
        new Server();
    }
}