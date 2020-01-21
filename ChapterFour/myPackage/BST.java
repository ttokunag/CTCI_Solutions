package myPackage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.lang.StringBuilder;

import myPackage.TreeNode;

public class BST {
    int size; // the numer of nodes
    TreeNode root;

    public BST() {
        this.size = 0;
        root = null;
    }

    public BST(int[] values) {
        this.size = values.length;
        for (int value: values) {
            this.add(value);
        }
    }

    public TreeNode getRoot() {
        return this.root;
    }
    public int getSize() {
        return this.size;
    }

    public void add(int value) {
        // increment the size of this BST
        this.size++;
        // when this BST is empty
        if (this.root == null) {
            this.root = new TreeNode(value);
            return;
        }
        this.addHelper(this.root, value);
    }

    private boolean addHelper(TreeNode node, int value) {
        // base case
        if (node == null) {
            return true;
        }
        // recursive phase
        if (node.value > value) {
            if (addHelper(node.left, value)) {
                node.left = new TreeNode(value);
            }
        } else {
            if (addHelper(node.right, value)) {
                node.right = new TreeNode(value);
            }
        }
        // when finished adding
        return false;
    }

    public boolean find(int target) {
        if (this.root == null) {
            return false;
        }
        return getHelper(this.root, target) != null;
    }

    public TreeNode get(int target) {
        if (this.root == null) {
            return null;
        }
        return getHelper(this.root, target);
    }

    private TreeNode getHelper(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        if (node.value == target) {
            return node;
        }
        return getHelper((target <= node.value ? node.left: node.right), target);
    }

    public boolean remove(int target) {
        if (this.root == null) {
            return false;
        }
        return removeHelper(null, this.root, target);
    }

    private boolean removeHelper(TreeNode parent, TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        if (node.value == target) {
            TreeNode right = node.right;
            if (parent.left == node) {
                parent.left = right;
            } else {
                parent.right = right;
            }
            leftmost(right).left = node.left;

            this.size--;
            return true;
        }
        // recursive phase
        return removeHelper(node, (target <= node.value ? node.left: node.right), target); 
    }

    private TreeNode leftmost(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return leftmost(root.left);
    }

    public int getRandomValue() {
        if (this.root == null) {
            return Integer.MIN_VALUE;
        }
        Random random = new Random();
        int randInt = random.nextInt(this.size); // exclusive
        return getRandomValueHelper(this.root, new Counter(randInt));
    }

    private static class Counter {
        int value;
        Counter(int value) {
            this.value = value;
        }
    }

    private int getRandomValueHelper(TreeNode node, Counter counter) {
        // avoid code duplication in recursive phase
        counter.value--;
        // base case
        if (counter.value + 1 == 0) {
            return node.value;
        }
        // recursive phase
        if (node.left != null) {
            int leftResult = getRandomValueHelper(node.left, counter);
            if (leftResult != -1) { // if (recursion) then ~~ pattern
                return leftResult;
            }
        }
        if (node.right != null) {
            int rightResult = getRandomValueHelper(node.right, counter);
            if (rightResult != -1) {
                return rightResult;
            }
        }
        // leaf instruction
        return -1;
    }

    public void minimize() {
        ArrayList<Integer> increment = this.inOrder();
        TreeNode newRoot = minimizeHelper(increment, 0, increment.size() - 1);
        this.root = newRoot;
    }

    private TreeNode minimizeHelper(ArrayList<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        // build a node
        int median = (left + right) / 2;
        TreeNode node = new TreeNode(list.get(median));
        node.left = minimizeHelper(list, left, median - 1);
        node.right = minimizeHelper(list, median + 1, right);
        
        return node;
    }

    public ArrayList<Integer> inOrder() {
        return inOrderHelper(this.root, new ArrayList<>());
    }

    private ArrayList<Integer> inOrderHelper(TreeNode node, ArrayList<Integer> result) {
        if (node == null) {
            return result;
        }
        inOrderHelper(node.left, result);
        result.add(node.value);
        inOrderHelper(node.right, result);
        return result;
    }

    public String serialize() {
        StringBuilder str = serializeHelper(this.root, new StringBuilder());
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
    private StringBuilder serializeHelper(TreeNode node, StringBuilder str) {
        if (node == null) {
            str.append("null,");
        } else {
            str.append(String.valueOf(node.value) + ",");
            serializeHelper(node.left, str);
            serializeHelper(node.right, str);
        }
        return str;
    }

    public static TreeNode deserialize(String str) {
        String[] strList = str.split(",");
        return deserializeHelper(strList, new Counter(0));
    }

    private static TreeNode deserializeHelper(String[] words, Counter counter) {
        // base case
        if (counter.value > words.length - 1) {
            return null;
        }
        // build a tree recursively
        String word = words[counter.value++];
        TreeNode root;
        if (word.equals("null")) {
            root = null;
        } else {
            root = new TreeNode(Integer.valueOf(word));
            root.left = deserializeHelper(words, counter);
            root.right = deserializeHelper(words, counter);
        }

        return root;
    }
}