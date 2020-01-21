package myPackage;
import java.util.LinkedList;

public class TopoNode {
    public char value;
    public int numDependencies;
    public LinkedList<TopoNode> children;

    public TopoNode(char value) {
        this.value = value;
        this.numDependencies = 0;
        this.children = new LinkedList<>();
    }

    public void addChild(TopoNode node) {
        this.children.add(node);
        node.numDependencies += 1;
    }
}