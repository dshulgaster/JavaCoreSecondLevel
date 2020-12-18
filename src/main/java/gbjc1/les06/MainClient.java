package gbjc1.les06;
import java.io.*;
import java.net.Socket;

public class MainClient {
    public static final String HOST = "localhost";
    public static final int PORT = 19000;
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Подключаемся к серверу
            socket = new Socket(HOST, PORT);

            ConsoleMessenger.run(socket);
            //new ConsoleMessengerTest(socket).start();

        } catch (EOFException e) {
            System.out.println("Сервер отключился!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Сервер отключился!");
    }
}