package myPackage;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class TreeNode {
    public int value;
    public TreeNode left, right, parent;

    public TreeNode(int value) {
        this.value = value;
        this.left = this.right = this.parent = null;
    }

    public void print() {
        StringBuilder buffer = new StringBuilder(50);
        dfsHelper(buffer, "", "", "");
        System.out.println(buffer.toString());
    }

    private void dfsHelper(StringBuilder buffer, String prefix, String childrenPrefix, String pos) {
        buffer.append(prefix);
        buffer.append(String.valueOf(this.value) + pos);
        buffer.append('\n');

        if (this.left != null) {
            if (this.right == null) {
                this.left.dfsHelper(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", "(L)");
            } else {
                this.left.dfsHelper(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", "(L)");
            }
        }
        if (this.right != null) {
            this.right.dfsHelper(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", "(R)");
        }
    }
}