package hr.fer.zemris.java.astar.gui;

import javax.swing.*;
import java.awt.*;

public class AStarFrame extends JFrame {

    public AStarFrame() {
        JScrollPane scrollPane = new JScrollPane(new MapPanel(25, 25, new char[][]{
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '#', '.', '.', '.', '.', '2'},
                {'.', '.', '#', '.', '1', '.', '.'},
                {'.', '.', '.', '#', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
        }));

        add(scrollPane, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();

        add(optionsPanel, BorderLayout.EAST);

        optionsPanel.add(new DimensionsPanel(this));
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new AStarFrame();

            frame.setTitle("AStar");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

}
