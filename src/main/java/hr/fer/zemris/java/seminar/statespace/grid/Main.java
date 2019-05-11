package hr.fer.zemris.java.seminar.statespace.grid;

import hr.fer.zemris.java.seminar.statespace.algorithm.AlgorithmObserver;
import hr.fer.zemris.java.seminar.statespace.algorithm.Node;
import hr.fer.zemris.java.seminar.statespace.algorithm.SearchAlgorithms;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main implements AlgorithmObserver<Coordinate> {

    static char[][] grid = {
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '#', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'.', ',', ',', ',', ',', ',', ',', ',', ',', ',', ',', ',', ',', ',', ',', ',', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'A', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', 'B'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'},
            {'.', ',', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            /*{'.', '.', '#', 'B'},
            {'#', '.', '#', '.'},
            {'.', '.', '.', '.'},
            {'A', '#', '.', '.'},*/
    };

    public static void main(String[] args) {
        Main main = new Main();
        RectangularGrid rectGrid = new RectangularGrid(grid);

        //System.out.println(SearchAlgorithms.bfs(rectGrid, rectGrid, rectGrid, main));
        Node<Coordinate> goal = SearchAlgorithms.prioritisedSearch(rectGrid, rectGrid, rectGrid, rectGrid.DISTANCE_THROUGH_COORDINATE, main);

        for (Node<Coordinate> parent = goal; parent.getParent() != null; parent = parent.getParent()) grid[parent.getState().getY()][parent.getState().getX()] = '$';

        Stream.of(grid).map(Arrays::toString).forEach(System.out::println);
    }

    @Override
    public void markFound(Node<Coordinate> node) {
        Coordinate state = node.getState();

        if (grid[state.getY()][state.getX()] != '*')
            grid[state.getY()][state.getX()] = 'o';
        //Stream.of(grid).map(Arrays::toString).forEach(System.out::println);
        //System.out.println();
    }

    @Override
    public void markVisited(Node<Coordinate> node) {
        Coordinate state = node.getState();

        grid[state.getY()][state.getX()] = '*';
        //Stream.of(grid).map(Arrays::toString).forEach(System.out::println);
        //System.out.println();
    }
}
