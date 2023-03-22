package JavaFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ShowTablePage {
    JFrame frame = new JFrame();

//    JComboBox comboBox1 = new JComboBox(category);
//    JComboBox comboBox2 = new JComboBox(city);
    JComboBox<String> comboBox1;
    JComboBox<String> comboBox2;
    JLabel label;
    JButton button;
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"D_CODE", "D_PRODUCT", "P_DISCOUNT", "P_INPRICE", "S_STDATE", "S_ENDDATE","ST_NAME", "ST_PHONE", "ST_STREET"});
    JButton nextButton;
    JButton backButton;

    ShowTablePage() {
        nextButton = new JButton("Next");
        backButton = new JButton("Back");
        comboBox1 = new JComboBox<>(new String[]{"Electronics", "Travelling & Transportation", "Food", "Clothes", "Sports & Training", "Home & Family", "Grocery", "Leisure"});
        comboBox2 = new JComboBox<>(new String[]{"Tempere", "Helsinki", "Espoo", "Lahti", "Seinajoki"});
        label = new JLabel("Please choose category and city you want to get the discount");
        button = new JButton("Submit");


        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        button.addActionListener(e -> {
            try {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                connectDatabase();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        nextButton.addActionListener(e -> {
            frame.dispose();
            new GivePassword();
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new StartPage();
        });

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(label)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBox1)
                        .addComponent(comboBox2))
                .addComponent(button, GroupLayout.Alignment.CENTER)
                .addComponent(scrollPane, GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBox1)
                        .addComponent(comboBox2))
                .addComponent(button)
                .addComponent(scrollPane)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton)
                        .addComponent(nextButton))
        );



        frame.setTitle("Discount Management");
        frame.setSize(700, 420);
        frame.setLocation(650, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void connectDatabase () throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TEST123", "postgres", "hacker123");
        String sqlJoinTables = "JOIN STORE ON STORE.D_CODE = DISCOUNT.D_CODE JOIN PRICE ON PRICE.D_CODE = DISCOUNT.D_CODE JOIN SALE_PERIOD ON SALE_PERIOD.D_CODE = DISCOUNT.D_CODE JOIN CATEGORY ON CATEGORY.D_PRODUCT = DISCOUNT.D_PRODUCT";
        st = con.prepareStatement("SELECT * FROM DISCOUNT "+ sqlJoinTables +" WHERE D_CATEGORY = ? AND ST_CITY = ?");
        st.setString(1, (String) comboBox1.getSelectedItem());
        st.setString(2, (String) comboBox2.getSelectedItem());
        rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("D_CODE"));
            System.out.println(rs.getString("D_PRODUCT"));
            System.out.println(rs.getString("P_DISCOUNT"));
            System.out.println(rs.getString("P_INPRICE"));
            System.out.println(rs.getString("S_STDATE"));
            System.out.println(rs.getString("S_ENDDATE"));
            System.out.println(rs.getString("ST_NAME"));
            System.out.println(rs.getString("ST_PHONE"));
            System.out.println(rs.getString("ST_STREET"));
            System.out.println("---------------------------------------");
            model.addRow(new Object[]{rs.getString("D_CODE"), rs.getString("D_PRODUCT"), rs.getString("P_DISCOUNT"), rs.getString("P_INPRICE")
            , rs.getString("S_STDATE"), rs.getString("S_ENDDATE"), rs.getString("ST_NAME"),rs.getString("ST_PHONE"), rs.getString("ST_STREET")});
        }

    }

}
