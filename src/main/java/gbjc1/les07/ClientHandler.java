package gbjc1.les07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ClientHandler {
    AuthService authService;
    Server server;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Client client;
    private static int clientsCount = 0;
    private String nick;
    private static final Pattern QUIT_COMMAND = Pattern.compile("/exit\\s");
    private static final Pattern STR_COMMAND = Pattern.compile("/w\\s+(\\w+)\\s+(.*)");
    private static final String NEW_CLIENTS_MSG = "Новый участник! Теперь нас = ";
    private static final String EXIT_CLIENT_MSG = "Участник вышел! Теперь нас = ";

    ClientHandler(AuthService authService, Server server, Socket socket) {
        this.authService = authService;
        this.server = server;
        this.socket = socket;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            if (!auth(dataInputStream, dataOutputStream)) {
                // Удаляемся из сервера
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
                server.onClientDisconnected(this);
                return;
            }
            clientsCount++;
            nick = "user_" + clientsCount;
            server.onNewClient(this);
            messageListener(dataInputStream);
        } catch (IOException e) {
            // Удаляемся из сервера
            try {
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            server.onClientDisconnected(this);
            e.printStackTrace();
        }
    }

    void sendMessage(Client client, String text) {
        try {
            dataOutputStream.writeUTF("/nm " + client.name + ": " + text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean auth(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
//        dataOutputStream.writeUTF("Пожалуйста, введите логин и пароль через пробел!");
        // Цикл ожидания авторизации клиентов
        int tryCount = 0;
        int maxTryCount = 5;
        while (true) {
            // Читаем команду от клиента
            String newMessage = dataInputStream.readUTF();
            // Разбиваем сообщение на состовляющие комманды
            String[] messageData = newMessage.split("\\s");
            // Проверяем соответствует ли комманда комманде авторизации
            if (messageData.length == 3 && messageData[0].equals("/auth")) {
                tryCount++;
                //dataOutputStream.writeUTF("???");
                String login = messageData[1];
                String password = messageData[2];
                // Зарегистрирован ли данных пользователь
                client = authService.auth(login, password);
                if (client != null) {
                    // Если получилось авторизоваться то выходим из цикла
                    dataOutputStream.writeUTF("/auth ok");
                    System.out.println("login success");
                    break;
                } else {
                    dataOutputStream.writeUTF("Неправильные логин и пароль!");
                }
            } else {
                dataOutputStream.writeUTF("Ошибка авторизации!");
            }
            if (tryCount == maxTryCount) {
                dataOutputStream.writeUTF("Первышен лимит попыток!");
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
                return false;
            }
        }
        return true;
    }

    private void messageListener(DataInputStream dataInputStream) throws IOException {
        while (true) {

            // Читаем команду от клиента
            String newMessage = dataInputStream.readUTF();
            // Разбиваем сообщение на состовляющие комманды
            String[] messageData = newMessage.split("\\s");
            Matcher quitMatcher = QUIT_COMMAND.matcher(newMessage); // команда на выход пользователя
            //Matcher nickMatcher = NICK_COMMAND.matcher(newMessage); // команда на выход пользователя
            Matcher strMatcher = STR_COMMAND.matcher(newMessage); // команда на персональное сообщение
            if (messageData[0].equals("/exit")) {
//            if (quitMatcher.matches()) {
                System.out.println("Команда /exit ok");
                dataOutputStream.writeUTF("/exit ok");
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
                //server.onClientDisconnected(this);
            } else if (strMatcher.matches()) {        //персональное сообщение
                String nick = strMatcher.group(1);    // второе слово = логин
                String message = strMatcher.group(2); // все остальные слова после 2-го пробела = message
                dataOutputStream.writeUTF("/w ok");
                System.out.println("Команда /w ok");
                server.sendMessageTo(client, nick, message);
            } else {
                //общее сообщение для всех
                dataOutputStream.writeUTF("/b ok");
                System.out.println("Команда /b ok");
                server.sendBroadCastMessage(client, newMessage);
            }
        }
    }
}