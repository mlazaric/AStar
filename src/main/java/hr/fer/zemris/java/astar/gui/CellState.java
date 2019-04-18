package hr.fer.zemris.java.astar.gui;

import java.awt.*;

public enum CellState {
    EMPTY('.', Color.WHITE),
    WALL('#', Color.GRAY),
    VISITED('*', Color.RED),
    START('A', Color.YELLOW),
    END('B', Color.ORANGE);

    public final char character;
    public final Color color;

    CellState(char character, Color color) {
        this.character = character;
        this.color = color;
    }

    public static CellState getStateFromCharacter(char character) {
        for (CellState state: CellState.values()) {
            if (state.character == character) {
                return state;
            }
        }

        return null;
    }
}
