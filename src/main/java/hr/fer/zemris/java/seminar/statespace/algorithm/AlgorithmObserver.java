package hr.fer.zemris.java.seminar.statespace.algorithm;

public interface AlgorithmObserver<S> {

    void markFound(S state);
    void markVisited(S state);

}
