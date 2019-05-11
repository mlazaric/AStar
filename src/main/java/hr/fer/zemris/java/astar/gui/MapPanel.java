package hr.fer.zemris.java.astar.gui;

import hr.fer.zemris.java.seminar.statespace.algorithm.AlgorithmObserver;
import hr.fer.zemris.java.seminar.statespace.algorithm.Node;
import hr.fer.zemris.java.seminar.statespace.grid.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapPanel extends JPanel implements AlgorithmObserver<Coordinate> {

    private final char[][] grid;
    private final CellPane[][] cells;
    private final int height, width;

    public MapPanel(char[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;
        this.cells = new CellPane[height][width];

        setLayout(new GridLayout(height, width));

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                add(cells[y][x] = new CellPane(x, y));
            }
        }
    }

    public void resetGrid() {
        for (CellPane[] row : cells) {
            for (CellPane cell : row) {
                cell.resetState();
            }
        }
    }

    @Override
    public void markFound(Node<Coordinate> node) {
        /*SwingUtilities.invokeLater(() -> {
            Coordinate state = node.getState();
            CellPane cell = cells[state.getY()][state.getX()];

            cell.setBackground(Color.LIGHT_GRAY.brighter());
            cell.character.setText("" + (int) node.getCost());
        });*/
    }

    @Override
    public void markVisited(Node<Coordinate> node) {
        SwingUtilities.invokeLater(() -> {
            Coordinate state = node.getState();
            CellPane cell = cells[state.getY()][state.getX()];

            cell.setBackground(Color.LIGHT_GRAY);
            cell.character.setText("" + (int) node.getCost());
        });
    }

    public void setBackground(Coordinate state, Color darker) {
        SwingUtilities.invokeLater(() -> {
            cells[state.getY()][state.getX()].setBackground(darker);
        });
    }

    private class CellPane extends JPanel {
        private final int x, y;
        private final JLabel character;

        public CellPane(int x, int y) {
            this.x = x;
            this.y = y;
            this.character = new JLabel();

            add(character);
            setState(grid[y][x]);

            setPreferredSize(new Dimension(20, 20));
            setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter(), 1));

            addMouseListener(
                    new MouseListener() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                toggleWall();
                            }
                            else if (SwingUtilities.isRightMouseButton(e)) {
                                toggleStart();
                            }
                            else {
                                toggleGoal();
                            }
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {}

                        @Override
                        public void mouseReleased(MouseEvent e) {}

                        @Override
                        public void mouseEntered(MouseEvent e) {}

                        @Override
                        public void mouseExited(MouseEvent e) {}
                    }
            );
        }

        private void setState(char character) {
            if (character == AStarFrame.WALL) {
                setBackground(Color.GRAY);
            }
            else {
                setBackground(Color.WHITE);
            }

            grid[y][x] = character;

            if (character == AStarFrame.INITIAL_STATE || character == AStarFrame.GOAL_STATE) {
                this.character.setText("" + character);
            }
            else {
                this.character.setText("");
            }
        }

        private void toggleWall() {
            setState(grid[y][x] == AStarFrame.WALL ? AStarFrame.SPACE : AStarFrame.WALL);
        }

        private void toggleStart() {
            setState(grid[y][x] == AStarFrame.INITIAL_STATE ? AStarFrame.SPACE : AStarFrame.INITIAL_STATE);
        }

        private void toggleGoal() {
            setState(grid[y][x] == AStarFrame.GOAL_STATE ? AStarFrame.SPACE : AStarFrame.GOAL_STATE);
        }

        private void resetState() {
            setState(grid[y][x]);
        }
    }
}
