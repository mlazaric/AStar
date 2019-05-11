package hr.fer.zemris.java.seminar.statespace.algorithm;

public interface AlgorithmObserver<S> {

    void markFound(Node<S> node);
    void markVisited(Node<S> node);

}
