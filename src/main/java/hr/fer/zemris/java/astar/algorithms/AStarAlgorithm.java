package hr.fer.zemris.java.astar.algorithms;

import hr.fer.zemris.java.astar.gui.CellState;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarAlgorithm extends Algorithm {

    private PriorityQueue<Coordinate> queue;

    public AStarAlgorithm(char[][] map) {
        super(map);

        this.queue = new PriorityQueue<>(Comparator.comparingInt(c -> c.manhattanDistance(end) + c.manhattanDistance(start)));
        this.queue.add(start);
    }


    @Override
    public void iterate() {
        Coordinate current = queue.remove();
        System.out.println(current.x  + " " + current.y);

        if (current.equals(end)) {
            System.out.println("DONE");
            throw new IllegalArgumentException();
        }

        markAsVisited(current);
        map[current.x][current.y] = CellState.VISITED;

        for (Coordinate coordinate: current.getAdjacent()) {
            if (canVisit(coordinate)) {
                markAsFrontier(coordinate);
                queue.add(coordinate);
            }
        }
    }

    public static void main(String[] args) {
        char[][] map = {
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '#', 'B', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '#', '#', '#', '#', '#', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', 'A', '.', '.', '.',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',},
        };

        AStarAlgorithm algorithm = new AStarAlgorithm(map);

        while (true) {
            algorithm.iterate();

            for (CellState[] row: algorithm.map) {
                for (CellState character: row) {
                    System.out.print(character.character);
                }

                System.out.println();
            }
            System.out.println();
        }
    }


}
