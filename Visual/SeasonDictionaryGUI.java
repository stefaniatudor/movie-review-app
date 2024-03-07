import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Episode {
    private String title;
    private int episodeNumber;

    public Episode(String title, int episodeNumber) {
        this.title = title;
        this.episodeNumber = episodeNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }
}

public class SeasonDictionaryGUI extends JFrame {
    private Map<String, List<Episode>> seasonDictionary;
    private DefaultTableModel tableModel;
    private JTable table;

    public SeasonDictionaryGUI() {
        // Initialize the JFrame
        setTitle("Season Dictionary GUI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create sample data
        seasonDictionary = new HashMap<>();
        List<Episode> season1Episodes = new ArrayList<>();
        season1Episodes.add(new Episode("Episode 1", 1));
        season1Episodes.add(new Episode("Episode 2", 2));
        seasonDictionary.put("Season 1", season1Episodes);

        List<Episode> season2Episodes = new ArrayList<>();
        season2Episodes.add(new Episode("Episode 1", 1));
        season2Episodes.add(new Episode("Episode 2", 2));
        seasonDictionary.put("Season 2", season2Episodes);

        // Create a table model
        tableModel = new DefaultTableModel(new Object[]{"Season", "Title", "Episode Number"}, 0);

        // Populate the table model with the dictionary data
        for (Map.Entry<String, List<Episode>> entry : seasonDictionary.entrySet()) {
            String seasonName = entry.getKey();
            for (Episode episode : entry.getValue()) {
                tableModel.addRow(new Object[]{seasonName, episode.getTitle(), episode.getEpisodeNumber()});
            }
        }

        // Create a JTable with the table model
        table = new JTable(tableModel);

        // Add the table to a JScrollPane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the JScrollPane to the JFrame
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeasonDictionaryGUI dictionaryGUI = new SeasonDictionaryGUI();
            dictionaryGUI.setVisible(true);
        });
    }
}
