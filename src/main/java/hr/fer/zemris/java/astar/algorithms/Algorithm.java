package hr.fer.zemris.java.astar.algorithms;

import hr.fer.zemris.java.astar.gui.CellState;

import java.util.Objects;

public abstract class Algorithm {

    protected final CellState[][] map;
    protected Coordinate start;
    protected Coordinate end;

    protected Algorithm(CellState[][] map) {
        this.map = Objects.requireNonNull(map, "Map cannot be null.");
        findStartAndEnd();
    }

    protected Algorithm(char[][] map) {
        Objects.requireNonNull(map, "Map cannot be null.");

        this.map = new CellState[map.length][map[0].length];

        translateCharactersToStates(map);
        findStartAndEnd();
    }

    private void findStartAndEnd() {
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[x].length; ++y) {

                if (map[x][y] == CellState.START) {
                    start = new Coordinate(x, y);
                }
                else if (map[x][y] == CellState.END) {
                    end = new Coordinate(x, y);
                }

            }
        }

        Objects.requireNonNull(start, "Every map has to contain one start tile.");
        Objects.requireNonNull(end, "Every map has to contain one end tile.");
    }

    private void translateCharactersToStates(char[][] map) {
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[x].length; ++y) {
                this.map[x][y] = CellState.getStateFromCharacter(map[x][y]);
            }
        }
    }

    protected boolean canVisit(Coordinate coordinate) {
        int x = coordinate.x;
        int y = coordinate.y;

        if (x < 0 || x >= map.length) {
            return false;
        }

        if (y < 0 || y >= map[x].length) {
            return false;
        }

        return map[x][y] == CellState.EMPTY || map[x][y] == CellState.END;
    }

    protected void markAsVisited(Coordinate coordinate) {
        // TODO
    }

    protected void markAsFrontier(Coordinate coordinate) {
        // TODO
    }

    public abstract void iterate();
}
