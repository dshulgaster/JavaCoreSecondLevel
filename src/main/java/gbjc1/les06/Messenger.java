package gbjc1.les06;

import java.io.*;
import java.net.Socket;

public class Messenger {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private Thread readerThread;
    private Listener listener;

    public interface Listener {
        void onMessage(String serverMessage);
        void onIOException(IOException e);
    }

    public Messenger(Socket socket, Listener listener) throws IOException {
        this.listener = listener;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter((new OutputStreamWriter(socket.getOutputStream())));
        readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String incomingMessage = in.readLine(); // readUTF
//                        if (incomingMessage.equalsIgnoreCase("/end")) {
//                        if (incomingMessage.equalsIgnoreCase("qqq")) {
//                            break;
//                        }
                        listener.onMessage(incomingMessage);
                    }
                } catch (IOException e) {
                    listener.onIOException(e);
                }
            }
        });
    }

    public void start() {
        readerThread.start();
    }

    public void send(String message) throws IOException {
        out.write(message + "\n");
        out.flush();
    }
}
