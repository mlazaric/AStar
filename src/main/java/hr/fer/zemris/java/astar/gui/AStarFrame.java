package hr.fer.zemris.java.astar.gui;

import javax.swing.*;
import java.awt.*;

public class AStarFrame extends JFrame {

    static final char INITIAL_STATE = 'A';
    static final char GOAL_STATE = 'B';
    static final char SPACE = '.';
    static final char WALL = '#';

    MapPanel mapPanel;
    JScrollPane scrollPane;
    char[][] grid;

    public AStarFrame() {
        char[][] charGrid = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', 'B'},
                {'.', '#', '#', '#', '#', '#', '#', '.'},
                {'.', '.', '.', '.', '.', '.', '#', '.'},
                {'.', '.', '.', '.', '.', '.', '#', '.'},
                {'A', '.', '.', '.', '.', '.', '#', '.'},
                {'.', '.', '.', '.', '.', '.', '#', '.'},
                {'.', '#', '#', '#', '#', '#', '#', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
        };

        replaceGrid(charGrid);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JPanel helper = new JPanel();
        helper.add(optionsPanel);
        add(helper, BorderLayout.EAST);

        optionsPanel.add(new DimensionsPanel(this));
        optionsPanel.add(new AlgorithmsPanel(this));
        optionsPanel.add(new LoadSavePanel(this));
    }

    public void replaceGrid(char[][] charGrid) {
        if (scrollPane != null) {
            remove(scrollPane);
        }

        grid = charGrid;

        mapPanel = new MapPanel(charGrid);

        scrollPane = new JScrollPane(mapPanel);

        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        pack();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new AStarFrame();

            frame.setTitle("A Star");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

}
