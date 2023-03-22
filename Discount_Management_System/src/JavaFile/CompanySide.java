package JavaFile;

import javax.swing.*;
import java.awt.*;

public class CompanySide {
    JFrame frame = new JFrame("Discount Management");
    JLabel label = new JLabel("Hi staff, do you want to update your discount or want to create a new one?");
    JCheckBox checkBox1 = new JCheckBox("Update discount");
    JCheckBox checkBox2 = new JCheckBox("Create discount");
    JButton button = new JButton();

    CompanySide() {

        JButton button = new JButton();
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        button.setText("Submit");
        button.addActionListener(e ->{
            frame.dispose();
            if(checkBox1.isSelected()) {
                frame.dispose();
                new UpdateDiscount();
            }
            else {
                frame.dispose();
                new CreateDiscount();
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
