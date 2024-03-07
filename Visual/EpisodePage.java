import javax.swing.*;
import java.awt.*;

public class EpisodePage {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Split Pane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        frame.setContentPane(getSplitPane17());
        frame.revalidate();
        frame.repaint();
        frame.pack();

        frame.setVisible(true);
    }

    public static JSplitPane getSplitPane1() {

        JLabel leftLabel = new JLabel("Genre");
        leftLabel.setBackground(Color.BLUE);
        leftLabel.setOpaque(true);

        leftLabel.setHorizontalAlignment(JLabel.CENTER);
        leftLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel rightLabel = new JLabel("Add");
        rightLabel.setBackground(Color.RED);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftLabel, rightLabel);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane2() {

        JLabel rightLabel = new JLabel("Aired");
        rightLabel.setBackground(Color.PINK);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane1(), rightLabel);
        splitPane.setResizeWeight(0.6);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane3() {

        JLabel rightLabel = new JLabel("Duration");
        rightLabel.setBackground(Color.ORANGE);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane2(), rightLabel);
        splitPane.setResizeWeight(0.75);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane4() {

        JLabel rightLabel = new JLabel("Reviews");
        rightLabel.setBackground(Color.GREEN);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane3(), rightLabel);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane5() {

        JTextArea textArea = new JTextArea("Description");
        textArea.setBackground(Color.YELLOW);
        textArea.setOpaque(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane4(), textArea);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane6() {

        ImageIcon imageIcon = new ImageIcon("path/to/your/image.jpg");
        JLabel imageLabel = new JLabel(imageIcon);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imageLabel, getSplitPane5());
        splitPane.setResizeWeight(0.35);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane7() {

        JTextArea textArea = new JTextArea("Title");
        textArea.setBackground(Color.gray);
        textArea.setOpaque(true);

        JLabel rightLabel = new JLabel("Rating");
        rightLabel.setBackground(Color.cyan);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, textArea,rightLabel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane8() {

        JLabel rightLabel = new JLabel("Rate");
        rightLabel.setBackground(Color.cyan);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane7(),rightLabel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane9() {

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

    public static JSplitPane getSplitPane10() {

        JLabel rightLabel = new JLabel("Gallery");
        rightLabel.setBackground(Color.green);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane9(),rightLabel);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane11() {

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane10(),getSplitPane6());
        splitPane.setResizeWeight(0.17);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane12() {

        JLabel leftLabel = new JLabel("Menu");
        leftLabel.setBackground(Color.blue);
        leftLabel.setOpaque(true);

        leftLabel.setHorizontalAlignment(JLabel.CENTER);
        leftLabel.setVerticalAlignment(JLabel.CENTER);

        JTextField textField = new JTextField("Search");

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftLabel, textField);
        splitPane.setResizeWeight(0.1);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane13() {

        JLabel rightLabel = new JLabel("Sign In");
        rightLabel.setBackground(Color.pink);
        rightLabel.setOpaque(true);

        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getSplitPane12(), rightLabel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane14() {

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane13(), getSplitPane11());
        splitPane.setResizeWeight(0.045);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane15() {

        JLabel bottomLabel = new JLabel("Director");
        bottomLabel.setBackground(Color.gray);
        bottomLabel.setOpaque(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane14(), bottomLabel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    }

    public static JSplitPane getSplitPane16() {

        JLabel bottomLabel = new JLabel("Writers");
        bottomLabel.setBackground(Color.orange);
        bottomLabel.setOpaque(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane15(), bottomLabel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);

        return splitPane;

    } public static JSplitPane getSplitPane17() {

        JLabel bottomLabel = new JLabel("Stars");
        bottomLabel.setBackground(Color.cyan);
        bottomLabel.setOpaque(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getSplitPane16(), bottomLabel);
        splitPane.setResizeWeight(0.92);
        splitPane.setDividerSize(0);

        return splitPane;

    }

}

