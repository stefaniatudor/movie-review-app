package org.example.Visual;

import org.example.IMDB;
import org.example.Production;
import org.example.ProductionsManager;
import org.example.Rating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static org.example.IMDB.loggedUser;

public class ProductionPage extends JFrame{

    private Production productionToDisplay;

    static JTextArea genreTextArea = new JTextArea();
    static  JLabel releaseYearLabel = new JLabel();
    static  JLabel durationLabel = new JLabel();
    static JLabel directorLabel = new JLabel();

    static  JLabel ratingLabel = new JLabel();
    static JTextArea plotTextArea = new JTextArea();

    static JTextField searchTextField = new JTextField();

    static JLabel titleLabel = new JLabel();


    public ProductionPage(Production productionToDisplay) throws HeadlessException {
        this.productionToDisplay = productionToDisplay;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.setContentPane(getSplitPane15()); // Pass the reference to the current frame
        this.revalidate();
        this.repaint();
    }

    public void setProductionToDisplay(Production productionToDisplay) {
        this.productionToDisplay = productionToDisplay;
        this.setContentPane(getSplitPane15());
        this.revalidate();
        this.repaint();
        this.repaint();
    }

    @Override
    public void dispose(){
        super.dispose();
        this.setVisible(false);

        IMDB.getInstance().mainWindow.setVisible(true);
    }

    public ProductionPage getFrame(){
        return this;
    }
    public JSplitPane getSplitPane1() {

        genreTextArea = new JTextArea("Genre:\n   -"+productionToDisplay.getGenres().toString()
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(",","\n   -"));
        genreTextArea.setBackground(Color.BLUE);
        genreTextArea.setOpaque(true);

        JButton addToFavourites = new JButton("Add to favourites");
        addToFavourites.setBackground(Color.RED);
        addToFavourites.setOpaque(true);

        addToFavourites.setHorizontalAlignment(JLabel.CENTER);
        addToFavourites.setVerticalAlignment(JLabel.CENTER);

        addToFavourites.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Added to favourites!");
                loggedUser.addFavorite(productionToDisplay);
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, genreTextArea, addToFavourites);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane2() {

        releaseYearLabel = new JLabel("ReleaseYear:" + productionToDisplay.getReleaseYear().toString());
        releaseYearLabel.setBackground(Color.PINK);
        releaseYearLabel.setOpaque(true);

        releaseYearLabel.setHorizontalAlignment(JLabel.CENTER);
        releaseYearLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane1(), releaseYearLabel);
        splitPane.setResizeWeight(0.6);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane3() {

        durationLabel = new JLabel("Duration:");
        durationLabel.setBackground(Color.ORANGE);
        durationLabel.setOpaque(true);

        durationLabel.setHorizontalAlignment(JLabel.CENTER);
        durationLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane2(), durationLabel);
        splitPane.setResizeWeight(0.75);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane4() {

        JButton reviewsButton = new JButton("Reviews");
        reviewsButton.setBackground(Color.GREEN);
        reviewsButton.setOpaque(true);

        reviewsButton.setHorizontalAlignment(JLabel.CENTER);
        reviewsButton.setVerticalAlignment(JLabel.CENTER);

        reviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RatingListWindow ratingListWindow = new RatingListWindow(productionToDisplay.getRatings(),getFrame());
                ratingListWindow.setVisible(true);
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane3(), reviewsButton);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane5() {

        plotTextArea = new JTextArea("Description:\n"+productionToDisplay.getPlot());
        plotTextArea.setEditable(false);
        plotTextArea.setBackground(Color.YELLOW);
        plotTextArea.setOpaque(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane4(), plotTextArea);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane6() {

        ImageIcon imageIcon = new ImageIcon("path/to/your/image.jpg");
        JLabel imageLabel = new JLabel(imageIcon);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imageLabel, getSplitPane5());
        splitPane.setResizeWeight(0.35);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane7() {

        titleLabel = new JLabel(productionToDisplay.getTitle());
        titleLabel.setBackground(Color.gray);
        titleLabel.setOpaque(true);

        ratingLabel = new JLabel("Rating:"+productionToDisplay.getAverageRating()+"/10");
        ratingLabel.setBackground(Color.cyan);
        ratingLabel.setOpaque(true);

        ratingLabel.setHorizontalAlignment(JLabel.CENTER);
        ratingLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, titleLabel,ratingLabel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane8() {

        JButton rateButton = new JButton("Rate");
        rateButton.setBackground(Color.cyan);
        rateButton.setOpaque(true);

        rateButton.setHorizontalAlignment(JLabel.CENTER);
        rateButton.setVerticalAlignment(JLabel.CENTER);

        rateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RatingCreationFrame ratingCreationFrame = new RatingCreationFrame(productionToDisplay,getFrame());
                ratingCreationFrame.setVisible(true);
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane7(),rateButton);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane9() {

        JLabel rightLabel = new JLabel("Cast & Crew");
        rightLabel.setBackground(Color.red);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane8(),rightLabel);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane10() {

        JButton galleryButton = new JButton("Gallery");
        galleryButton.setBackground(Color.green);
        galleryButton.setOpaque(true);

        galleryButton.setHorizontalAlignment(JLabel.CENTER);
        galleryButton.setVerticalAlignment(JLabel.CENTER);
        
        galleryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"NO DB for photos");
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane9(),galleryButton);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane11() {

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane10(),getSplitPane6());
        splitPane.setResizeWeight(0.17);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane12() {
        JButton menuButton = new JButton("Menu");
        menuButton.setBackground(Color.blue);
        menuButton.setOpaque(true);

        menuButton.setHorizontalAlignment(JLabel.CENTER);
        menuButton.setVerticalAlignment(JLabel.CENTER);

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setVisible(false);

                IMDB.getInstance().mainWindow.setVisible(true);
            }
        });

        searchTextField = new JTextField("Search");

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuButton, searchTextField);
        splitPane.setResizeWeight(0.1);
        splitPane.setDividerSize(0);

        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchTextField.getText().trim();
                Production productionToLoad = ProductionsManager.getProductionByName(searchText);

                if(productionToLoad != null){
                    setProductionToDisplay(productionToLoad);
                }else {
                    JOptionPane.showMessageDialog(getFrame(),"Nu exista productia");
                }
            }
        });
        searchTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchTextField.getText().equals("Search")) {
                    searchTextField.setText("");
                    searchTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchTextField.getText().isEmpty()) {
                    searchTextField.setText("Search");
                    searchTextField.setForeground(Color.GRAY);
                }
            }
        });

        return splitPane;
    }

    public JSplitPane getSplitPane13() {

        JButton logOutButton = new JButton("Log out");
        logOutButton.setBackground(Color.pink);
        logOutButton.setOpaque(true);

        logOutButton.setHorizontalAlignment(JLabel.CENTER);
        logOutButton.setVerticalAlignment(JLabel.CENTER);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().dispose();
                IMDB.getInstance().reauthentificateUser();
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane12(), logOutButton);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane14() {

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane13(), getSplitPane11());
        splitPane.setResizeWeight(0.045);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public JSplitPane getSplitPane15() {

        directorLabel = new JLabel("Directors: " + productionToDisplay.getDirectors().toString()
                .replaceAll("\\[","")
                .replaceAll("\\]",""));
        directorLabel.setBackground(Color.gray);
        directorLabel.setOpaque(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane14(), directorLabel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

}

