package JavaFile;

import javax.swing.*;
import java.awt.*;

public class StartPage {
    JFrame frame = new JFrame("Discount Management");
    JLabel label = new JLabel("Hi users, are you customer or salesperson?");
    JCheckBox checkBox1 = new JCheckBox();
    JCheckBox checkBox2 = new JCheckBox();
    JButton button = new JButton();

    public int ok = 0;
    public StartPage(){
        JCheckBox checkBox1 = new JCheckBox();
        JCheckBox checkBox2 = new JCheckBox();
        JButton button = new JButton();
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        checkBox1.setText("Customer");
        checkBox2.setText("Salesperson");
        button.setText("Submit");
        button.addActionListener(e ->{
            frame.dispose();
            if(checkBox1.isSelected()) {
                ok = 1;
                RegisterPageForCustomer registerPageForCustomer = new RegisterPageForCustomer();
            }
            else {
                ok = 2;
                CompanySide companySide = new CompanySide();
            }
        });

        frame.add(label);
        frame.add(checkBox1);
        frame.add(checkBox2);
        frame.add(button);
        frame.setLocation(450, 450);
        frame.pack();
        frame.setVisible(true);
    }



}
