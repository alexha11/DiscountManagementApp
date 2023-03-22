package JavaFile;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class GivePassword{
    JLabel label;
    JTextField textField;
    JButton submitButton;
    JButton backButton;
    JFrame frame = new JFrame("Password Page");
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    GivePassword() {
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Enter D_CODE of product that you want to get discount");
        textField = new JTextField(20);
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");

        label.setBounds(30, 100, 400, 25);
        textField.setBounds(50, 150, 300, 25);
        submitButton.setBounds(170, 200, 75, 25);
        backButton.setBounds(10, 350, 75, 25);

        submitButton.addActionListener(e -> {
            try {
                connectDatabase();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new ShowTablePage();
        });

        frame.add(label);
        frame.add(textField);
        frame.add(submitButton);
        frame.add(backButton);
        frame.setLocation(650, 350);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void connectDatabase() throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TEST123", "postgres", "hacker123");
        st = con.prepareStatement("SELECT D_PASSWORD FROM DISCOUNT WHERE D_CODE = ?");
        st.setInt(1, Integer.parseInt(textField.getText()));
        rs = st.executeQuery();
        while(rs.next()) {
            JOptionPane.showMessageDialog(null,  rs.getString("D_PASSWORD"), "Password", JOptionPane.INFORMATION_MESSAGE);
        }
        st = con.prepareStatement("DELETE FROM DISCOUNT WHERE D_CODE = ?");
        st.setInt(1, Integer.parseInt(textField.getText()));

        int rowsDeleted = st.executeUpdate();
        if(rowsDeleted > 0) {
            System.out.println("THE DISCOUNT PASSWORD HAS BEEN USED");
        }
    }


}
