package hr.fer.zemris.java.seminar.statespace.algorithm;

public class Node<S> implements Comparable<Node> {

    private final S state;
    private final Node<S> parent;
    private final double cost;

    public Node(S state, Node<S> parent, double cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    public Node<S> getParent() {
        return parent;
    }

    public double getCost() {
        return cost;
    }

    public S getState() {
        return state;
    }

    @Override
    public int compareTo(Node node) {
        return Double.compare(cost, node.cost);
    }

    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                ", parent=" + parent +
                ", cost=" + cost +
                '}';
    }
}
