package hr.fer.zemris.java.astar.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapPanel extends JPanel {

    private final char[][] map;
    private final CellPane[][] cells;

    public MapPanel(int height, int width, char[][] map) {
        this.map = map.clone();
        cells = new CellPane[height][width];

        setLayout(new GridLayout(height, width));

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                add(cells[y][x] = new CellPane('.'));
            }
        }
    }

    private class CellPane extends JPanel {
        private CellState state;

        private void setState(CellState state) {
            this.state = state;
            setBackground(state.color);
        }

        private void toggleState() {
            if (state == CellState.EMPTY) setState(CellState.WALL);
            else if (state == CellState.WALL) setState(CellState.EMPTY);
        }

        public CellPane(char character) {
            setState(CellState.getStateFromCharacter(character));

            setPreferredSize(new Dimension(20, 20));
            setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter(), 1));

            addMouseListener(
                    new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            CellPane.this.toggleState();
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    }
            );
        }
    }
}
