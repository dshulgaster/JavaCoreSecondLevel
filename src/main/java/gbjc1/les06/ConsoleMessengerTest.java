package gbjc1.les06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConsoleMessengerTest extends Thread {
    Socket socket;

    Collection<String> history = new ConcurrentLinkedQueue<String>();

    ConsoleMessengerTest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            int clineNumber = Server.clientCount.incrementAndGet();
            System.out.println("Новый клиент №" + clineNumber);
            // Создаём обёртки над потоками данных
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            // Обрабатываем входяще данные
            while (true) {
                // Читаем текщий текст
                String text = dataInputStream.readUTF();
                history.add(text);
                System.out.println("Новое сообщние от " + clineNumber);
                // Отправлет ответ
                dataOutputStream.writeUTF("Echo: " + text);
            }
        } catch (EOFException e) {
            // Поймали ошибку конца файла по этому прекращаем обработку
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Server.clientCount.decrementAndGet();
            System.out.println("Клиент отключился " + Server.clientCount.get());
        }
    }

}
