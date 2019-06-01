package hr.fer.zemris.java.astar.gui;

import hr.fer.zemris.java.seminar.statespace.algorithm.Node;
import hr.fer.zemris.java.seminar.statespace.algorithm.SearchAlgorithms;
import hr.fer.zemris.java.seminar.statespace.grid.Coordinate;
import hr.fer.zemris.java.seminar.statespace.grid.RectangularGrid;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiFunction;

public class AlgorithmsPanel extends JPanel {

    public AlgorithmsPanel(AStarFrame aStarFrame) {
        JComboBox<Algorithm> algorithmsBox = new JComboBox();

        algorithmsBox.addItem(new Algorithm("BFS", (grid, mapPanel) -> SearchAlgorithms.bfs(grid, grid, grid, mapPanel)));
        algorithmsBox.addItem(new Algorithm("UCS", (grid, mapPanel) -> SearchAlgorithms.ucs(grid, grid, grid, mapPanel)));
        algorithmsBox.addItem(new Algorithm("GBFS", (grid, mapPanel) -> SearchAlgorithms.prioritisedSearch(grid, grid, grid, grid.DISTANCE_TO_GOAL, mapPanel)));
        algorithmsBox.addItem(new Algorithm("A*", (grid, mapPanel) -> SearchAlgorithms.prioritisedSearch(grid, grid, grid, grid.DISTANCE_THROUGH_COORDINATE, mapPanel)));

        JButton run = new JButton("Run");
        JButton reset = new JButton("Reset");

        setLayout(new BorderLayout());
        add(algorithmsBox, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));

        add(buttonsPanel, BorderLayout.SOUTH);

        buttonsPanel.add(run);
        buttonsPanel.add(reset);

        setBorder(BorderFactory.createTitledBorder("Algorithms"));

        run.addActionListener(e -> {
            new Thread (() -> {
                RectangularGrid grid = new RectangularGrid(aStarFrame.grid);

                Algorithm algorithm = (Algorithm) algorithmsBox.getSelectedItem();
                Node<Coordinate> goal = algorithm.algorithm.apply(grid, aStarFrame.mapPanel);

                for (; goal.getParent() != null; goal = goal.getParent()) {
                    aStarFrame.mapPanel.setBackground(goal.getState(), Color.GREEN.darker());
                }

                aStarFrame.mapPanel.setBackground(goal.getState(), Color.GREEN.darker());
            }).start();
        });

        reset.addActionListener(e -> aStarFrame.mapPanel.resetGrid());
    }

    private static class Algorithm {

        private final String name;
        private final BiFunction<RectangularGrid, MapPanel, Node<Coordinate>> algorithm;


        private Algorithm(String name, BiFunction<RectangularGrid, MapPanel, Node<Coordinate>> algorithm) {
            this.name = name;
            this.algorithm = algorithm;
        }

        @Override
        public String toString() {
            return name;
        }

    }
}
