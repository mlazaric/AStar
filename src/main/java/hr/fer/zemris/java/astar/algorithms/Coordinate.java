package hr.fer.zemris.java.astar.algorithms;

public class Coordinate {

    public final int x, y;
    private int cost;
    private Coordinate parent;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Coordinate(int x, int y, Coordinate parent, int cost) {
        this(x, y);

        this.parent = parent;
        this.cost = cost;
    }

    public int manhattanDistance(Coordinate coordinate) {
        return Math.abs(coordinate.x - x) + Math.abs(coordinate.y - y);
    }

    public Coordinate[] getAdjacent() {
        Coordinate[] adjacentCoordinates = new Coordinate[4];

        adjacentCoordinates[0] = new Coordinate(x - 1, y, this, cost + 1);
        adjacentCoordinates[1] = new Coordinate(x + 1, y, this, cost + 1);
        adjacentCoordinates[2] = new Coordinate(x, y - 1, this, cost + 1);
        adjacentCoordinates[3] = new Coordinate(x, y + 1, this, cost + 1);

        return adjacentCoordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate coordinate = (Coordinate) obj;

        return coordinate.x == x && coordinate.y == y;
    }
}