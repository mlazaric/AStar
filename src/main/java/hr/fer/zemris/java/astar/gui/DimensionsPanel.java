package hr.fer.zemris.java.astar.gui;

import javax.swing.*;
import java.awt.*;

public class DimensionsPanel extends JPanel {

    private JButton btnSetDimensions;
    private JSpinner spinnerHeight, spinnerWidth;

    public DimensionsPanel(AStarFrame aStarFrame) {
        spinnerHeight = new JSpinner(new SpinnerNumberModel(25, 10, 50, 1));
        spinnerWidth = new JSpinner(new SpinnerNumberModel(25, 10, 50, 1));
        btnSetDimensions = new JButton("Set dimensions");

        JPanel innerPanel = new JPanel();

        innerPanel.setLayout(new GridLayout(2, 2));

        innerPanel.add(new JLabel("Height:"));
        innerPanel.add(spinnerHeight);
        innerPanel.add(new JLabel("Width:"));
        innerPanel.add(spinnerWidth);

        setLayout(new BorderLayout());

        add(innerPanel, BorderLayout.NORTH);
        add(btnSetDimensions, BorderLayout.CENTER);

        setBorder(BorderFactory.createTitledBorder("Dimensions"));

        btnSetDimensions.addActionListener(event -> {
            //aStarFrame.setDimensions((Integer) spinnerHeight.getValue(), (Integer) spinnerWidth.getValue());
        });
    }
}
