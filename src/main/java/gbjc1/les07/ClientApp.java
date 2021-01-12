package gbjc1.les07;

public class ClientApp implements ClientSignInWindow.Callback, ClientChatWindow.Callback, ChatApiHandler.CallBack {

    final ChatApiHandler api;
    final ClientSignInWindow clientSignInWindow;
    final ClientChatWindow clientChatWindow;

    public ClientApp() {
        api = new ChatApiHandler(this);
        clientSignInWindow = new ClientSignInWindow(this);
        clientChatWindow = new ClientChatWindow(this);
        showSignInWindow();
    }

    public static void main(String[] args) {
        new ClientApp();
    }

    @Override
    public void onLoginClick(String login, String password) {
        api.auth(login, password);
    }

    @Override
    public synchronized void onAuth(boolean isSuccess, String serverError) {
        System.out.println("login (onAuth в ClientApp), isSuccess" + isSuccess);
        if (isSuccess) {
            hideSignInWindow();
            showChatWindow();
            // Открываем окно чата
        } else {
            clientSignInWindow.showError(serverError);
        }
    }

    @Override
    public void sendMessage(String text) {
        api.sendMessage(text);
    }

    @Override
    public void onNewMessage(String message) {
        synchronized (clientChatWindow) {
            clientChatWindow.onNewMessage(message);
        }
        // вывести сообщение
    }

    private void showSignInWindow() {
//        clientSignInWindow = new ClientSignInWindow(this);
        clientSignInWindow.setVisible(true);
    }

    private void hideSignInWindow() {
        clientSignInWindow.setVisible(false);
    }

    private void showChatWindow() {
        clientChatWindow.setVisible(true);
    }

    private void hideChatWindow() {
        clientChatWindow.setVisible(false);
    }
}
