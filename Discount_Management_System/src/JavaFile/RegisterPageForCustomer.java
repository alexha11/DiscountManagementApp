package JavaFile;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class RegisterPageForCustomer implements ActionListener{

    JFrame frame = new JFrame();
    JButton registerButton = new JButton("Register");
    JButton loginButton = new JButton("Click here");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JTextField userAgeField = new JTextField();
    JLabel userIDLabel = new JLabel("UserName:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel userAgeLabel = new JLabel("Age");
    JLabel messageLabel = new JLabel("You already have an account?");

    Connection con;
    PreparedStatement st;
    ResultSet rs;
    Scanner sc = new Scanner(System.in);

    RegisterPageForCustomer(){

        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);
        userAgeLabel.setBounds(50, 200, 75, 25);

        messageLabel.setBounds(100,300,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,15));

        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);
        userAgeField.setBounds(125, 200, 200, 25);

        registerButton.setBounds(150,250,100,25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        loginButton.setBounds(150,340,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(registerButton);
        frame.add(loginButton);
        frame.add(userAgeField);
        frame.add(userAgeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setLocation(650, 350);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            frame.dispose();
            LoginPageForCustomer loginPageForCustomer = new LoginPageForCustomer();
        } else if (e.getSource() == registerButton) {
            try {
                frame.dispose();
                connectDatabase();
                new ShowTablePage();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void connectDatabase() throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TEST123", "postgres", "hacker123");
        st = con.prepareStatement("INSERT INTO CUSTOMER (C_USER_NAME, C_PASSWORD, C_AGE) VALUES (?, ?, ?)");
        st.setString(1, userIDField.getText());
        st.setString(2, userPasswordField.getText());
        st.setInt(3, Integer.parseInt(userAgeField.getText()));
        int rowsInserted = st.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "The account is created successfully!", "Update Price", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}