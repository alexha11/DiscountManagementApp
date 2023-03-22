package JavaFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageForCustomer implements ActionListener {
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("UserName:");
    JLabel userPasswordLabel = new JLabel("Password:");
    LoginPageForCustomer() {
        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        loginButton.setBounds(150,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);

        frame.add(userIDField);
        frame.add(userPasswordField);

        frame.add(loginButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setLocation(650, 350);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        JOptionPane.showMessageDialog(null, "Login successfully!", "Login", JOptionPane.INFORMATION_MESSAGE);
        new ShowTablePage();
    }
}
