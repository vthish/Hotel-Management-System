package view;

import controller.UserController;

import javax.swing.*;

public class LoginForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Login");

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel userLabel = new JLabel("Username");
        JLabel passLabel = new JLabel("Password");

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        userLabel.setBounds(50, 30, 200, 20);
        username.setBounds(50, 50, 200, 30);

        passLabel.setBounds(50, 90, 200, 20);
        password.setBounds(50, 110, 200, 30);

        loginBtn.setBounds(50, 160, 200, 30);

        UserController controller = new UserController();

        // LOGIN ACTION (DB BASED)
        loginBtn.addActionListener(e -> {

            String user = username.getText();
            String pass = new String(password.getPassword());

            if (controller.login(user, pass)) {

                JOptionPane.showMessageDialog(frame, "Login Success");
                frame.dispose();
                Dashboard.main(null);

            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
        });

        // ENTER KEY SUPPORT
        password.addActionListener(e -> loginBtn.doClick());

        frame.add(userLabel);
        frame.add(username);
        frame.add(passLabel);
        frame.add(password);
        frame.add(loginBtn);

        frame.setSize(300, 250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}