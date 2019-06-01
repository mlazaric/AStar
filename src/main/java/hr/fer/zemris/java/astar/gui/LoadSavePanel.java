package hr.fer.zemris.java.astar.gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LoadSavePanel extends JPanel {

    public LoadSavePanel(AStarFrame aStarFrame) {
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");


        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));

        add(buttonsPanel);

        buttonsPanel.add(save);
        buttonsPanel.add(load);

        save.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(".");

            if (jfc.showSaveDialog(aStarFrame) == JFileChooser.APPROVE_OPTION) {
                Path path = jfc.getSelectedFile().toPath();

                try (BufferedWriter fw = Files.newBufferedWriter(path)) {

                    for (int y = 0; y < aStarFrame.grid.length; ++y) {
                        for (int x = 0; x < aStarFrame.grid[0].length; ++x) {
                            fw.write(aStarFrame.grid[y][x]);
                        }

                        fw.write('\n');
                    }
                }
                catch (IOException exc) {}
            }
        });

        load.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(".");

            if (jfc.showOpenDialog(aStarFrame) == JFileChooser.APPROVE_OPTION) {
                Path path = jfc.getSelectedFile().toPath();

                try {
                    List<String> lines = Files.readAllLines(path);

                    int height = lines.size();
                    int width = lines.get(0).length();

                    char[][] grid = new char[height][width];

                    for (int y = 0; y < height; ++y) {
                        grid[y] = lines.get(y).toCharArray();
                    }

                    aStarFrame.replaceGrid(grid);
                }
                catch (IOException exc) {}
            }
        });
    }
}
