package JavaFile;

import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class UpdateTime {
    JLabel topLabel = new JLabel("Type the information of the code that you want to update");
    JTextField starDayField = new JTextField();
    JTextField codeField = new JTextField();
    JTextField endDayField = new JTextField();
    JLabel codeLabel = new JLabel("Type code of the discount: ");
    JLabel starDayLabel = new JLabel("Start date(YYYY-MM-DD): ");
    JLabel endDayLabel = new JLabel("End Date(YYYY-MM-DD): ");
    JButton summitButtion = new JButton("Submit");
    JFrame frame = new JFrame();
    JButton backButton = new JButton("Back");
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    UpdateTime() {
        codeLabel.setBounds(20,100,200,25);
        starDayLabel.setBounds(20,150,200,25);
        endDayLabel.setBounds(20, 200, 200, 25);
        topLabel.setBounds(20, 50, 400, 25);
        summitButtion.setBounds(170, 250, 75, 25);

        codeField.setBounds(200,100,200,25);
        starDayField.setBounds(200,150,200,25);
        endDayField.setBounds(200, 200, 200, 25);
        backButton.setBounds(10, 350, 75, 25);

        frame.add(backButton);
        frame.add(summitButtion);
        frame.add(topLabel);
        frame.add(starDayField);
        frame.add(codeField);
        frame.add(endDayField);
        frame.add(codeLabel);
        frame.add(starDayLabel);
        frame.add(endDayLabel);

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
        PreparedStatement statement = con.prepareStatement("UPDATE SALE_PERIOD SET S_STDATE = ?, S_ENDDATE = ? WHERE D_CODE = ?");
        statement.setDate(1, Date.valueOf((starDayField.getText())));
        statement.setDate(2, Date.valueOf((endDayField.getText())));
        statement.setInt(3, Integer.parseInt(codeField.getText()));
        int rowsUpdated1 = statement.executeUpdate();
        if (rowsUpdated1 > 0) {
            JOptionPane.showMessageDialog(null, "An existing sale_period was updated successfully!", "Update Price", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("An existing discount was updated successfully!");
        }
    }
}
