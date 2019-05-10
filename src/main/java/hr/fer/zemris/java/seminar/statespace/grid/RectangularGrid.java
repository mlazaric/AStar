package hr.fer.zemris.java.seminar.statespace.grid;

import hr.fer.zemris.java.seminar.statespace.algorithm.Node;
import hr.fer.zemris.java.seminar.statespace.algorithm.Transition;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class RectangularGrid implements Supplier<Coordinate>,
        Function<Coordinate, List<Transition<Coordinate>>>,
        Predicate<Coordinate> {

    public final Function<Node<Coordinate>, Double> DISTANCE_TO_GOAL = n -> (double) manhattanDistanceToGoal(n.getState());
    public final Function<Node<Coordinate>, Double> DISTANCE_FROM_START = n -> n.getCost();
    public final Function<Node<Coordinate>, Double> DISTANCE_THROUGH_COORDINATE =
            n -> (double) n.getCost() + manhattanDistanceToGoal(n.getState());

    private final char[][] grid;
    private final Coordinate initialState;
    private final Coordinate goalState;

    public RectangularGrid(char[][] grid) {
        this.grid = cloneGrid(grid);

        initialState = findSpecificState('A');
        goalState = findSpecificState('B');
    }

    public static char[][] cloneGrid(char[][] grid) {
        char[][] clone = new char[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                clone[row][col] = grid[row][col];
            }
        }

        return clone;
    }

    private Coordinate findSpecificState(char character) {
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                if (grid[row][col] == character) {
                    return new Coordinate(col, row);
                }
            }
        }

        throw new IllegalArgumentException("Grid contains no '" + character + "' state.");
    }

    @Override
    public Coordinate get() {
        return initialState;
    }

    @Override
    public List<Transition<Coordinate>> apply(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        return Stream.of(
                new Coordinate(x - 1, y),
                new Coordinate(x + 1, y),
                new Coordinate(x, y - 1),
                new Coordinate(x, y + 1)
        ).filter(this::isValid).map(coord -> new Transition<>(coord, 1)).collect(Collectors.toList());
    }

    @Override
    public boolean test(Coordinate coordinate) {
        if (!isValid(coordinate)) return false;

        return grid[coordinate.getY()][coordinate.getX()] == 'B';
    }

    private boolean isValid(Coordinate coordinate) {
        if (coordinate.getY() < 0 || coordinate.getY() >= grid.length) {
            return false;
        }
        if (coordinate.getX() < 0 || coordinate.getX() >= grid[coordinate.getY()].length) {
            return false;
        }

        return grid[coordinate.getY()][coordinate.getX()] != '#';
    }

    private int manhattanDistanceToGoal(Coordinate coordinate) {
        return abs(coordinate.getX() - goalState.getX()) +
                abs(coordinate.getY() - goalState.getY());
    }

}
