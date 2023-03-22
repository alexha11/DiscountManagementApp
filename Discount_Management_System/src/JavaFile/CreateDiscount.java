package JavaFile;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CreateDiscount extends JFrame{
    JPanel panel = new JPanel();
    JLabel topLabel = new JLabel("Type the information to create new discount");
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    JTextField[] textFields = new JTextField[11];

    CreateDiscount() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(topLabel, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(11, 2));
        String[] labels = {"Your Company Name: ", "Way to customer contact: ", "Your adrress (optional): ", "Your City: ", "Your Product: ",
                "Category: ", "Discount Password(16 characters): ", "Discount(%): ", "Initial Price(euros): ", "Start date(YYYY-MM-DD): ", "End Date(YYYY-MM-DD): "};
        for (int i = 0; i < 11; i++) {
            JLabel label = new JLabel(labels[i]);
            fieldsPanel.add(label);
            JTextField textField = new JTextField();
            fieldsPanel.add(textField);
            textFields[i] = textField;
        }
        panel.add(fieldsPanel, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(e -> {
            this.dispose();
            new CompanySide();
        });

        submitButton.addActionListener(e -> {
            try {
                connectDatabase();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.setLocation(650, 350);
        this.add(panel);
        this.setSize(420, 420);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void connectDatabase() throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TEST123", "postgres", "hacker123");
        st = con.prepareStatement("INSERT INTO DISCOUNT (D_PRODUCT, D_PASSWORD) VALUES (?, ?) RETURNING D_CODE;");
        st.setString(1, textFields[4].getText());
        st.setString(2, textFields[6].getText());
        st.execute();

        ResultSet last_updated_person = st.getResultSet();
        last_updated_person.next();
        int last_updated_person_id = last_updated_person.getInt(1);

        //System.out.println(last_updated_person_id);
        st = con.prepareStatement("INSERT INTO CATEGORY (D_PRODUCT, D_CATEGORY) VALUES (?, ?)");
        st.setString(1, textFields[4].getText());
        st.setString(2, textFields[5].getText());
        int rowsInserted2 = st.executeUpdate();

        st = con.prepareStatement("INSERT INTO PRICE (D_CODE, P_DISCOUNT, P_INPRICE) VALUES (?, ?, ?)");
        st.setInt(1, last_updated_person_id);
        st.setDouble(2, Double.parseDouble(textFields[7].getText()));
        st.setDouble(3, Double.parseDouble(textFields[8].getText()));
        int rowsInserted3 = st.executeUpdate();

        st = con.prepareStatement("INSERT INTO STORE (D_CODE, ST_NAME, ST_PHONE, ST_STREET, ST_CITY) VALUES (?, ?, ?, ?, ?)");
        st.setInt(1, last_updated_person_id);
        st.setString(2, textFields[0].getText());
        st.setString(3, textFields[1].getText());
        st.setString(4, textFields[2].getText());
        st.setString(5, textFields[3].getText());
        int rowsInserted5 = st.executeUpdate();

        st = con.prepareStatement("INSERT INTO SALE_PERIOD (D_CODE, S_STDATE, S_ENDDATE) VALUES (?, ?, ?)");
        st.setInt(1, last_updated_person_id);
        st.setDate(2, Date.valueOf(textFields[9].getText()));
        st.setDate(3, Date.valueOf(textFields[10].getText()));
        int rowsInserted4 = st.executeUpdate();


        if(rowsInserted2 > 0 && rowsInserted3 > 0 && rowsInserted4 > 0 && rowsInserted5 > 0) {
            JOptionPane.showMessageDialog(null, "Congrates, your infor upload succesfully", "Information", JOptionPane.PLAIN_MESSAGE);

            System.out.println("Congrates, your infor upload succesfully");
        }
    }
}
