package hr.fer.zemris.java.seminar.statespace.algorithm;

public class Transition<S> {

    private final S state;
    private final double cost;

    public Transition(S state, double cost) {
        this.state = state;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public S getState() {
        return state;
    }
}
