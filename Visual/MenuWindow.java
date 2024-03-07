package org.example.Visual;

import org.example.AccountType;
import org.example.IMDB;
import org.example.Production;
import org.example.ProductionsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

public class MenuWindow extends JFrame {

    private static JButton productionButton = new JButton("Productions Menu");
    private static JButton actorsButton = new JButton("Actors Menu");
    private static JButton requestButton = new JButton("RequestS");

    public ProductionPage productionPage = new ProductionPage(ProductionsManager.getProductions().get (new Random().nextInt(ProductionsManager.getProductions().size())));
    private static final Map<AccountType, List<JButton>> buttonsForAccountType = Map.ofEntries(
            entry(AccountType.REGULAR, new LinkedList<>(java.util.List.of(
                    productionButton,
                    actorsButton
            ))),
            entry(AccountType.CONTRIBUTOR, new LinkedList<>(java.util.List.of(
                    productionButton,
                    actorsButton,
                    requestButton
            ))),
            entry(AccountType.ADMIN, new LinkedList<>(java.util.List.of(
                    productionButton,
                    actorsButton,
                    requestButton
            ))));

    public MenuWindow() {
        setTitle("Centered Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size
        int windowWidth = 200;
        int windowHeight = 300;

        // Calculate the vertical center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int yPosition = (screenSize.height - windowHeight) / 2;

        // Set the window size and position
        setBounds(screenSize.width / 2 - windowWidth / 2, yPosition, windowWidth, windowHeight);

        // Create four buttons centered vertically
        productionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Production randomProduction = ProductionsManager.getProductions().get (new Random().nextInt(ProductionsManager.getProductions().size()));
                productionPage.setProductionToDisplay(randomProduction);
                productionPage.setVisible(true);
                setVisible(false);
            }
        });
        actorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MoviePage.createAndShowGUI();
            }
        });
        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 10));

        // Add buttons to the panel
        for (JButton button : buttonsForAccountType.get(IMDB.loggedUser.getAccountType())) {
            buttonPanel.add(button);
        }

        // Add the panel to the content pane
        getContentPane().add(buttonPanel);
    }

}
