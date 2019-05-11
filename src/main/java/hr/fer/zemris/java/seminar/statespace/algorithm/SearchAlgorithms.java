package hr.fer.zemris.java.seminar.statespace.algorithm;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SearchAlgorithms {

    public static <S> Node<S> bfs(Supplier<S> initialState,
                                  Function<S, List<Transition<S>>> transitions,
                                  Predicate<S> goalTest,
                                  AlgorithmObserver<S> observer) {
        List<Node<S>> frontier = new LinkedList<>();
        Set<S> visited = new HashSet<>();

        frontier.add(new Node<S>(initialState.get(), null, 0));
        visited.add(initialState.get());
        observer.markFound(frontier.get(0));

        while (!frontier.isEmpty()) {
            Node<S> current = frontier.get(0);
            frontier.remove(0);
            observer.markVisited(current);

            if (goalTest.test(current.getState())) {
                return current;
            }

            List<Transition<S>> possibleTransitions = transitions.apply(current.getState());

            possibleTransitions.stream()
                    .filter(trans -> !visited.contains(trans.getState()))
                    .forEach(trans -> {
                        visited.add(trans.getState());
                        frontier.add(new Node<>(trans.getState(), current, current.getCost() + trans.getCost()));
                        observer.markFound(frontier.get(frontier.size() - 1));
                    });
        }

        return null;
    }

    public static <S> Node<S> prioritisedSearch(Supplier<S> initialState,
                                                Function<S, List<Transition<S>>> transitions,
                                                Predicate<S> goalTest,
                                                Function<Node<S>, Double> estimationFunction,
                                                AlgorithmObserver<S> observer) {

        Comparator<Node<S>> heuristicComparator = Comparator.comparingDouble(
                (Node<S> node) -> estimationFunction.apply(node)
        );

        PriorityQueue<Node<S>> frontier = new PriorityQueue<>(heuristicComparator);
        HashMap<S, Node<S>> frontierSet = new HashMap<>();
        Set<S> visited = new HashSet<>();

        frontier.add(new Node<S>(initialState.get(), null, 0));
        frontierSet.put(initialState.get(), frontier.peek());
        observer.markFound(frontier.peek());

        while (!frontier.isEmpty()) {
            Node<S> current = frontier.poll();
            observer.markVisited(current);

            if (goalTest.test(current.getState())) {
                return current;
            }

            visited.add(current.getState());
            frontierSet.remove(current.getState());

            for (Transition<S> transition : transitions.apply(current.getState())) {
                if (!frontierSet.containsKey(transition.getState()) && !visited.contains(transition.getState())) {
                    Node<S> node = new Node<>(transition.getState(), current, current.getCost() + transition.getCost());

                    frontier.add(node);
                    observer.markFound(node);
                }

                Node<S> oldPath = frontierSet.get(transition.getState());
                Node<S> newPath = new Node<>(transition.getState(), current, current.getCost() + transition.getCost());

                if (oldPath != null && heuristicComparator.compare(oldPath, newPath) > 0) {
                    frontier.removeIf(n -> n.getState().equals(transition.getState()));
                    frontier.add(newPath);
                    frontierSet.put(newPath.getState(), newPath);
                    observer.markFound(newPath);
                }
            }
        }

        return null;
    }

    public static <S> Node<S> ucs(Supplier<S> initialState,
                                  Function<S, List<Transition<S>>> transitions,
                                  Predicate<S> goalTest,
                                  AlgorithmObserver<S> observer) {
        return prioritisedSearch(initialState, transitions, goalTest, Node::getCost, observer);
    }

}
