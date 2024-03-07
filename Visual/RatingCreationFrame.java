package org.example.Visual;

import org.example.Production;
import org.example.Rating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RatingCreationFrame extends JFrame {

    private JTextField usernameField;
    private JTextField valueField;
    private JTextField commentsField;

    private Production production;

    public RatingCreationFrame(Production p,JFrame parentFrame) {
        setTitle("Create Rating");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.production = p;

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel valueLabel = new JLabel("Rating Value:");
        JLabel commentsLabel = new JLabel("Comments:");

        usernameField = new JTextField();
        valueField = new JTextField();
        commentsField = new JTextField();

        JButton createButton = new JButton("Create Rating");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRating();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(valueLabel);
        panel.add(valueField);
        panel.add(commentsLabel);
        panel.add(commentsField);
        panel.add(createButton);
        panel.add(cancelButton);

        getContentPane().add(panel);

        setSize(300, 150);
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void createRating() {
        String username = usernameField.getText();
        String valueText = valueField.getText();
        String comments = commentsField.getText();

        try {
            int value = Integer.parseInt(valueText);

            // You can use the entered values to create a Rating object or perform any other necessary actions
            // For demonstration purposes, let's just print the entered values for now
            Rating r = new Rating(username,value,comments);
            production.addRating(r);
            // You may want to close the frame after creating the rating
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric rating value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
