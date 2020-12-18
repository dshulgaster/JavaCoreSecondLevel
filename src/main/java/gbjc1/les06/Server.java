package gbjc1.les06;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    public static final int PORT = 19000;
    public static AtomicInteger clientCount = new AtomicInteger(0);
    public static void main (String [] args) throws IOException {
        int countClient = 0;
        ServerSocket server = new ServerSocket(PORT);

        while (true) {
            try {
                // ожидаем/получаем подключение клиента (проверяю: 127.0.0.1:19000
                System.out.println("Started, waiting for connection...");
                Socket socket = server.accept();
                System.out.println("Client accepted " + (countClient++) +": " + socket.getInetAddress() + "\n");

                ConsoleMessenger.run(socket);
//                new ConsoleMessengerTest(socket).start();
//                socket.close();
//                server.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}