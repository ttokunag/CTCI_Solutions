import myPackage.Node;
import myPackage.TreeNode;
import myPackage.BST;
import myPackage.Graph;
import myPackage.Graph.Dependency;

import java.util.*;

public class Main {

    public static void printList(LinkedList<LinkedList<Integer>> list) {
        for (LinkedList<Integer> l : list) {
            System.out.print("{");
            for (int i = 0; i < l.size(); i++) {
                if (i != l.size() - 1) {
                    System.out.printf("%d,", l.get(i));
                } else {
                    System.out.printf("%d}\n", l.get(i));
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\n----------------------------------------");
        
        /**** Directed graph with cycles ****/
        // Node dGraphWithCycle = new Node(1);
        // Node node2 = new Node(2); Node node3 = new Node(3); Node node4 = new Node(4);
        // Node node5 = new Node(5); Node node6 = new Node(6); Node node7 = new Node(7);
        // dGraphWithCycle.addAdjacent(node2); dGraphWithCycle.addAdjacent(node3);
        // node2.addAdjacent(node4); node2.addAdjacent(node3); 
        // node3.addAdjacent(node4); node3.addAdjacent(dGraphWithCycle);
        // node4.addAdjacent(node5);
        // node5.addAdjacent(dGraphWithCycle); node5.addAdjacent(node6);
        // node6.addAdjacent(node7); // without this, no route exists


        /**** BST ****/
        // TreeNode tree1 = new TreeNode(8);
        // TreeNode node3 = new TreeNode(3); TreeNode node10 = new TreeNode(10); 
        // TreeNode node1 = new TreeNode(1); TreeNode node6 = new TreeNode(6); 
        // TreeNode node14 = new TreeNode(14); TreeNode node4 = new TreeNode(4); 
        // TreeNode node7 = new TreeNode(7); TreeNode node13 = new TreeNode(13); 
        // tree1.left = node3; tree1.right = node10;
        // node3.left = node1; node3.right = node6;
        // node10.right = node14;
        // node6.left = node4; node6.right = node7; node14.left = node13;


        // // Route Between Nodes
        // System.out.printf("Route exists: %b\n",
        //     Graph.routeBetweenNodes(dGraphWithCycle, node7));


        // // Minimal Tree
        // int[] arr = {1,3,4,6,7,8,10,13,14};
        // TreeNode balancedBST = Graph.minimalBST(arr);
        // balancedBST.print();


        // // List of Depths
        // tree1.print();
        // Graph.listOfDepths(tree1);


        // // Check Balanced
        // System.out.println(Graph.checkBalanced(tree1));
        // System.out.println(Graph.checkBalanced(balancedBST));


        // // Check BST
        // System.out.println(Graph.checkBST(tree1));
        // System.out.println(Graph.checkBST(balancedBST));


        // // Build Order
        // char[] projects = {'a','b','c','d','e','f','g','h'};
        // Dependency[] dependencies1 = {
        //     new Dependency('f','a'), new Dependency('f','b'), new Dependency('a','d'),
        //     new Dependency('b','h'), new Dependency('h','d'), new Dependency('d','c'),
        //     new Dependency('e','g')
        // };
        // ArrayList<Dependency> dependencies = new ArrayList<>(Arrays.asList(dependencies1));
        // Graph.buildOrder(projects, dependencies);


        // // First Common Ancestor
        // tree1.print();
        // System.out.printf("First Common Ancestor: %d\n",
        //     Graph.firstCommonAncestor(tree1, node10, node13).value);


        int[] values = {8,3,10,1,6,14,4,7,13};
        BST bst = new BST(values);
        // bst.add(8); bst.add(3); bst.add(10); bst.add(1); bst.add(6); 
        // bst.add(14); bst.add(4); bst.add(7); bst.add(13); bst.add(9);
        // bst.getRoot().print();
        
        // // Rondom Node
        // HashMap<Integer, Integer> demog = new HashMap<>();
        // int numIter = 100000;
        // for (int i = 0; i < numIter; i++) {
        //     int res = bst.getRandomValue();
        //     demog.put(res, demog.getOrDefault(res, 0) + 1);
        // }
        // for (Integer key: demog.keySet()) {
        //     System.out.printf("%d: %f\n", key, (double)demog.get(key)/numIter);
        // }

        // // BST sequences
        // int[] values1 = {8,3,10,1,6,14};
        // BST bst1 = new BST(values1);
        // ArrayList<LinkedList<Integer>> result = Graph.allSequences(bst1.getRoot());
        // for (LinkedList<Integer> list : result) {
        //     System.out.print("{");
        //     for (int i = 0; i < list.size(); i++) {
        //         if (i != list.size() - 1) {
        //             System.out.printf("%d,", list.get(i));
        //         } else {
        //             System.out.printf("%d}\n", list.get(i));
        //         }
        //     }
        // }

        // // Check Subtree
        // int[] values2 = {10,14,13}; 
        // BST bst2 = new BST(values2);
        // bst2.getRoot().print();
        // bst.getRoot().print();
        // System.out.println(Graph.isSubtree(bst.getRoot(), bst2.getRoot()));

        // Paths of Sum
        bst.getRoot().print();
        System.out.println(Graph.pathSum(bst.getRoot(), 13));
        System.out.println(Graph.pathSumOpt(bst.getRoot(), 13));

        // // BST serialization & deserialization
        // String serialized = bst.serialize();
        // System.out.println(serialized);
        // TreeNode node = BST.deserialize(serialized);
        // node.print();

        
        System.out.println("----------------------------------------");
    }
}