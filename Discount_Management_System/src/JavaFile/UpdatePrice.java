package JavaFile;

import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class UpdatePrice {
    JLabel topLabel = new JLabel("Type the information of the code that you want to update");
    JTextField discountField = new JTextField();
    JTextField codeField = new JTextField();
    JTextField newPriceField = new JTextField();
    JLabel codeLabel = new JLabel("Type code of the discount: ");
    JLabel discountPriceLabel = new JLabel("Discount(%): ");
    JLabel newPriceLabel = new JLabel("Initial Price(euros): ");
    JButton summitButtion = new JButton("Submit");
    JFrame frame = new JFrame();
    JButton backButton = new JButton("Back");

    Connection con;
    PreparedStatement st;
    ResultSet rs;
    UpdatePrice() {
        codeLabel.setBounds(20,100,150,25);
        discountPriceLabel.setBounds(20,150,150,25);
        newPriceLabel.setBounds(20, 200, 150, 25);
        topLabel.setBounds(20, 50, 400, 25);
        summitButtion.setBounds(170, 250, 75, 25);

        codeField.setBounds(175,100,200,25);
        discountField.setBounds(175,150,200,25);
        newPriceField.setBounds(175, 200, 200, 25);
        backButton.setBounds(10, 350, 75, 25);

        frame.add(backButton);
        frame.add(summitButtion);
        frame.add(topLabel);
        frame.add(discountField);
        frame.add(codeField);
        frame.add(newPriceField);
        frame.add(codeLabel);
        frame.add(discountPriceLabel);
        frame.add(newPriceLabel);

        backButton.addActionListener(e -> {
            frame.dispose();
            new StartPage();
        });

        summitButtion.addActionListener(e -> {
            try {
                connectDatabase();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setLocation(650, 350);
        frame.setVisible(true);

    }
    public void connectDatabase() throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TEST123", "postgres", "hacker123");
        PreparedStatement statement = con.prepareStatement("UPDATE PRICE SET P_DISCOUNT = ?, P_INPRICE = ? WHERE D_CODE = ?");
        statement.setDouble(1, Double.parseDouble(discountField.getText()));
        statement.setDouble(2, Double.parseDouble(newPriceField.getText()));
        statement.setInt(3, Integer.parseInt(codeField.getText()));
        int rowsUpdated1 = statement.executeUpdate();
        if (rowsUpdated1 > 0) {
            JOptionPane.showMessageDialog(null, "An existing price was updated successfully!", "Update Price", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("An existing discount was updated successfully!");
        }
    }
}
