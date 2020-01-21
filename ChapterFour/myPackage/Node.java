package myPackage;
import java.util.ArrayList;

public class Node {
    public int value;
    public ArrayList<Node> adjacents;

    public Node(int value) {
        this.value = value;
        this.adjacents = new ArrayList<>();
    }
    public void addAdjacent(Node node) {
        this.adjacents.add(node);
    }
}