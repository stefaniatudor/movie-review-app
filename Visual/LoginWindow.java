package org.example.Visual;

import org.example.Credentials;
import org.example.IMDB;
import org.example.User;
import org.example.UsersManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginWindow() {
        super("Login Window");
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");

        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLoginButtonClick();
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        setContentPane(panel);
    }

    private void onLoginButtonClick() {
        String email = emailField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        Credentials credentials = new Credentials(email, password);
        User u = UsersManager.getUserByCredentials(credentials);
        // Check login credentials (dummy check)
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            IMDB.loggedUser = u;
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.");
        }

        // Clear password field for security
        passwordField.setText("");
    }
}