package JavaFile;

import javax.swing.*;
import java.awt.*;

public class UpdateDiscount {
    JFrame frame = new JFrame("Discount Management");
    JLabel label = new JLabel("Do you want to update price or time");
    JCheckBox checkBox1 = new JCheckBox("Update price");
    JCheckBox checkBox2 = new JCheckBox("Update time");
    JButton button = new JButton();
    UpdateDiscount() {
        JButton button = new JButton();
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        button.setText("Submit");
        button.addActionListener(e ->{
            frame.dispose();
            if(checkBox1.isSelected()) {
                new UpdatePrice();
            }
            else {
                new UpdateTime();
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
