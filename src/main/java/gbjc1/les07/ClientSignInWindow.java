package gbjc1.les07;

import javax.swing.*;

public class ClientSignInWindow extends JFrame {
    private final JLabel error;

    interface Callback {
        void onLoginClick(String login, String password);
    }

    //ClientSignInWindow(Function<ClientSignInResult, Void> onLoginClick) {        // внутри может быть не function, а собственный интерфейс - 2-й вариант
    ClientSignInWindow(Callback callback) {
        setBounds(200, 200, 300, 150);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Пожалуйста, авторизуйтесь"));
        JTextField loginField = new JTextField();
        loginField.setName("Логин");
        JTextField passwordField = new JTextField();
        passwordField.setName("Пароль");
        JButton signInButton = new JButton("Войти");
        signInButton.addActionListener((e) -> {
            callback.onLoginClick(loginField.getText(), passwordField.getText());
        });
        error = new JLabel();
        add(loginField);
        add(passwordField);
        add(signInButton);
        add(error);
    }

    void showError(String errorText) {
        error.setText(errorText);
    }

}

