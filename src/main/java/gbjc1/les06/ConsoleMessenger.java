package gbjc1.les06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConsoleMessenger {
    public static void run(Socket socket) {
        Messenger.Listener listener = new Messenger.Listener() {
            @Override
            public void onMessage(String incomingMessage) {
                System.out.println(incomingMessage);
            }

            @Override
            public void onIOException(IOException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        };
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Messenger messenger = new Messenger(socket, listener);
            messenger.start();
            while (true) {
                String message = reader.readLine();
                messenger.send(message);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
