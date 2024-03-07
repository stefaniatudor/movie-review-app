package org.example.Visual;

import org.example.Rating;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RatingListWindow extends JFrame {

    public RatingListWindow(List<Rating> ratings,JFrame parentFrame) {
        setTitle("Rating Panel Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelFrame = createPanelWithRatings(ratings);

        getContentPane().add(panelFrame);

        setSize(300, 200);
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private JPanel createPanelWithRatings(List<Rating> ratings) {
        JPanel panelFrame = new JPanel();
        panelFrame.setLayout(new BoxLayout(panelFrame, BoxLayout.Y_AXIS));

        for (Rating rating : ratings) {
            JPanel ratingPanel = new JPanel();
            ratingPanel.setBorder(BorderFactory.createEtchedBorder());
            ratingPanel.setLayout(new GridLayout(3, 1));

            JLabel usernameLabel = new JLabel("Username: " + rating.getUsername());
            JLabel valueLabel = new JLabel("Rating: " + rating.getValue());
            JLabel commentsLabel = new JLabel("Comments: " + rating.getComments());

            ratingPanel.add(usernameLabel);
            ratingPanel.add(valueLabel);
            ratingPanel.add(commentsLabel);

            panelFrame.add(ratingPanel);
        }

        return panelFrame;
    }
}