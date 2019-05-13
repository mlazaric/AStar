package hr.fer.zemris.java.astar.gui;

import hr.fer.zemris.java.seminar.statespace.algorithm.Node;
import hr.fer.zemris.java.seminar.statespace.algorithm.SearchAlgorithms;
import hr.fer.zemris.java.seminar.statespace.grid.Coordinate;
import hr.fer.zemris.java.seminar.statespace.grid.RectangularGrid;

import javax.swing.*;
import java.awt.*;

public class AStarFrame extends JFrame {

    static final char INITIAL_STATE = 'A';
    static final char GOAL_STATE = 'B';
    static final char SPACE = '.';
    static final char WALL = '#';

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

        MapPanel mapPanel = new MapPanel(charGrid);

        JScrollPane scrollPane = new JScrollPane(mapPanel);

        add(scrollPane, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();

        add(optionsPanel, BorderLayout.EAST);

        optionsPanel.add(new DimensionsPanel(this));

        JButton test = new JButton("TEST");
        JButton reset = new JButton("RESET");

        optionsPanel.add(test);
        optionsPanel.add(reset);

        test.addActionListener(e -> {
            new Thread (() -> {
                RectangularGrid grid = new RectangularGrid(charGrid);

                Node<Coordinate> goal = SearchAlgorithms.prioritisedSearch(grid, grid, grid, grid.DISTANCE_THROUGH_COORDINATE, mapPanel);

                for (; goal.getParent() != null; goal = goal.getParent()) {
                    mapPanel.setBackground(goal.getState(), Color.GREEN.darker());
                }

                mapPanel.setBackground(goal.getState(), Color.GREEN.darker());
            }).start();
        });

        reset.addActionListener(e -> mapPanel.resetGrid());
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new AStarFrame();

            frame.setTitle("State space searching algorithms");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

}
