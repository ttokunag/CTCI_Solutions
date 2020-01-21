package myPackage;

import java.util.*;

public class Graph {
    public static boolean routeBetweenNodes(Node s, Node d) {
        // return routeBetweenNodeDFS(s, d, new HashSet<Node>());
        return routeBetweenNodeBFS(s, d);
    }

    // O(N) time, O(max depth) space
    private static boolean routeBetweenNodeDFS(Node s, Node d, HashSet<Node> visited) {
        // base case
        if (s == null || d == null) {
            return false;
        } else if (s == d) {
            return true;
        }
        // recursive phase
        for (Node adjacent: s.adjacents) {
            // avoid infinite loop by a cycle
            if (!visited.contains(adjacent)) {
                visited.add(adjacent);
                if (routeBetweenNodeDFS(adjacent, d, visited)) {
                    return true;
                }
            }
        }
        // leaf instruction
        return false;
    }

    // O(N) time, O(N) space => root has N-1 children
    private static boolean routeBetweenNodeBFS(Node s, Node d) {
        // base case
        if (s == null || d == null) {
            return false;
        }
        // preparation for BFS
        HashSet<Node> visited = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        visited.add(s);
        queue.add(s);
        // iterative phase
        while (!queue.isEmpty()) {
            Node currNode = queue.removeFirst();
            if (currNode == d) {
                return true;
            } else {
                // enqueue adjacents
                for (Node adjacent: currNode.adjacents) {
                    if (!visited.contains(adjacent)) {
                        visited.add(adjacent);
                        queue.add(adjacent);
                    }
                }
            }
        }
        // after touching all nodes
        return false;
    }

    // O(N) time, O(log(N)) space
    public static TreeNode minimalBST(int[] arr) {
        return minimalBSTHelper(arr, 0, arr.length - 1);
    }

    private static TreeNode minimalBSTHelper(int[] arr, int left, int right) {
        // base case
        if (left > right) {
            return null;
        }
        // recursive phase
        int median = (right + left) / 2;
        TreeNode node = new TreeNode(arr[median]);
        node.left = minimalBSTHelper(arr, left, median - 1);
        node.right = minimalBSTHelper(arr, median + 1, right);

        return node;
    }

    // O(N) time, O(N) space
    public static LinkedList<LinkedList<TreeNode>> listOfDepths(TreeNode node) {
        LinkedList<LinkedList<TreeNode>> list = new LinkedList<>();
        listOfDepthsDFS(node, 0, list);
        // listOfDepthsBFS(node, list);
        for (LinkedList<TreeNode> level: list) {
            for (TreeNode n: level) {
                System.out.printf("%d ", n.value);
            }
            System.out.println();
        }
        return list;
    }

    private static void listOfDepthsDFS(TreeNode node, int depth, LinkedList<LinkedList<TreeNode>> list) {
        // base case
        if (node == null) {
            return;
        }
        // recursive phase
        LinkedList<TreeNode> currLevel;
        if (depth == list.size()) {
            currLevel = new LinkedList<>();
            list.add(currLevel);
        }
        else {
            currLevel = list.get(depth);
        }
        currLevel.add(node);
        listOfDepthsDFS(node.left, depth + 1, list);
        listOfDepthsDFS(node.right, depth + 1, list);
    }

    private static void listOfDepthsBFS(TreeNode node, LinkedList<LinkedList<TreeNode>> list) {
        if (node == null) {
            return;
        }

        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        nextLevel.add(node);
        while (!nextLevel.isEmpty()) {
            LinkedList<TreeNode> currLevel = (LinkedList<TreeNode>) nextLevel.clone();
            list.add(nextLevel);
            // clean up nextLevel
            nextLevel = new LinkedList<>();
            while (!currLevel.isEmpty()) {
                TreeNode currNode = currLevel.removeFirst();
                if (currNode.left != null) {
                    nextLevel.add(currNode.left);
                }
                if (currNode.right != null) {
                    nextLevel.add(currNode.right);
                }
            }
        }
    }

    // O(N) time: touch every node once
    // O(log(N)) space
    public static boolean checkBalanced(TreeNode node) {
        // base case
        if (node == null) {
            return true;
        }
        return treeHeight(node) != Integer.MAX_VALUE;
    }

    // O(N) time, O(max depth) space
    private static int treeHeight(TreeNode node) {
        if (node == null)
            return -1;
        
        int leftHeight = treeHeight(node.left);
        int rightHeight = treeHeight(node.right);

        if (Math.abs(leftHeight - rightHeight) <= 1) {
            return Math.max(leftHeight, rightHeight) + 1;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public static boolean checkBST(TreeNode node) {
        if (node == null) {
            return false;
        }
        return checkBSTHelper(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // O(N) time, O(H) space
    private static boolean checkBSTHelper(TreeNode node, int lower, int upper) {
        // base case
        if (node == null) {
            return true;
        }
        // recursive phase (be careful of global restrictions)
        if (node.value > lower && node.value < upper) {
            return checkBSTHelper(node.left, lower, node.value)
                && checkBSTHelper(node.right, node.value, upper);
        }
        return false;
    }

    public static class Dependency {
        char dependee;
        char depender;
        public Dependency(char dependee, char depender) {
            this.dependee = dependee;
            this.depender = depender;
        }
    }

    // O(N) time, O(N) space
    public static ArrayList<Character> buildOrder(char[] projects, ArrayList<Dependency> dependencies) {
        LinkedList<TopoNode> independents = new LinkedList<>();
        HashMap<Character, TopoNode> nodes = new HashMap<>();
        // create nodes
        for (char project: projects) {
            nodes.put(project, new TopoNode(project));
        }
        // add dependencies
        for (Dependency curr: dependencies) {
            TopoNode currNode = nodes.get(curr.dependee);
            currNode.addChild(nodes.get(curr.depender));
        }
        // identify independent projects
        for (TopoNode node: nodes.values()) {
            if (node.numDependencies == 0) {
                independents.add(node);
            }
        }

        return buildOrderHelper(independents);
        // ArrayList<Character> res = new ArrayList<>();
        // for (TopoNode node: independents) {
        //     res.addAll(buildOrderHelperDFS(
        //         node, new HashSet<TopoNode>(), new LinkedList<Character>())
        //     );
        // }
        // System.out.print("Build Order: ");
        // for (Character project: res) {
        //     System.out.printf("%c ", project);
        // }
        // System.out.println();

        // return res;
    }

    private static ArrayList<Character> buildOrderHelper(LinkedList<TopoNode> independents) {
        ArrayList<Character> order = new ArrayList<>();

        while (!independents.isEmpty()) {
            TopoNode curr = independents.removeFirst();
            order.add(curr.value);
            // remove dependecies of it children
            for (TopoNode child: curr.children) {
                child.numDependencies -= 1;
                // add to independents if it gets independent
                if (child.numDependencies == 0) {
                    independents.add(child);
                }
            }
        }

        // print the result
        System.out.print("Build Order: ");
        for (Character project: order) {
            System.out.printf("%c ", project);
        }
        System.out.println();

        return order;
    }

    private static LinkedList<Character> buildOrderHelperDFS(TopoNode node, HashSet<TopoNode> visited, LinkedList<Character> list) {
        // base case
        if (node == null) {
            return list;
        }
        // recursive phase
        visited.add(node);
        for (TopoNode child: node.children) {
            if (!visited.contains(child)) {
                buildOrderHelperDFS(child, visited, list);
            }
        }
        list.addFirst(node.value);
        return list;
    } 

    public static TreeNode firstCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        // recursive phase
        boolean pOnLeft = cover(root.left, p);
        boolean qOnLeft = cover(root.left, q);
        if (pOnLeft != qOnLeft) {
            return root;
        } else { // if p and q are on the same side, recurse
            return firstCommonAncestor((pOnLeft ? root.left: root.right), p, q);
        }
    }

    private static boolean cover(TreeNode node, TreeNode target) {
        // base case
        if (node == null || target == null) {
            return false;
        }
        // recursive phase
        if (node == target) {
            return true;
        } else if (cover(node.left, target) || cover(node.right, target)) {
            return true;
        }
        // after subtrees are all traversed
        return false;
    }

    public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        return allSequencesHelper(node);
    }

    private static ArrayList<LinkedList<Integer>> allSequencesHelper(TreeNode node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        if (node == null) {
            result.add(new LinkedList<>());
            return result;
        }
        // ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        ArrayList<LinkedList<Integer>> leftSequences = allSequencesHelper(node.left);
        ArrayList<LinkedList<Integer>> rightSequences = allSequencesHelper(node.right);
        
        // if lists are empty, the following is never executed
        for (LinkedList<Integer> left : leftSequences) {
            for (LinkedList<Integer> right : rightSequences) {
                ArrayList<LinkedList<Integer>> weaved = 
                    weaveList(node.value, left, right, new ArrayList<>(), new LinkedList<>());
                result.addAll(weaved);
            }
        }

        return result;
    }

    public static ArrayList<LinkedList<Integer>> weaveList(
        int prefix, LinkedList<Integer> left, LinkedList<Integer> right, ArrayList<LinkedList<Integer>> list, LinkedList<Integer> curr) {
            curr.add(prefix);
            
            // Base case
            if (left.isEmpty() || right.isEmpty()) {
                LinkedList<Integer> result = new LinkedList<>(curr);
                result.addAll(left); 
                result.addAll(right);
                list.add(result);
                return list;
            }
        
            weaveList(left.removeFirst(), left, right, list, curr);
            left.addFirst(curr.removeLast()); // reverting

            weaveList(right.removeFirst(), left, right, list, curr);
            right.addFirst(curr.removeLast()); // reverting

            return list;
    }

    // public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
    //     ArrayList<LinkedList<Integer>> result = new ArrayList<>();
    //     // Base case
    //     if (node == null) {
    //         result.add(new LinkedList<>());
    //         return result;
    //     }
    //     // initialize a prefix set
    //     LinkedList<Integer> prefix = new LinkedList<>();
    //     prefix.add(node.value);
    //     // recursive phase
    //     ArrayList<LinkedList<Integer>> leftSequences = allSequences(node.left);
    //     ArrayList<LinkedList<Integer>> rightSequences = allSequences(node.right);
    //     for (LinkedList<Integer> left : leftSequences) {
    //         for (LinkedList<Integer> right : rightSequences) {
    //             ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
    //             weaveList(weaved, left, right, prefix);
    //             result.addAll(weaved);
    //         }
    //     }
    //     return result;
    // }

    // private static void weaveList(
    //     ArrayList<LinkedList<Integer>> results, LinkedList<Integer> left, 
    //     LinkedList<Integer> right, LinkedList<Integer> prefix) {
        
    //     if (left.isEmpty() || right.isEmpty()) {
    //         LinkedList<Integer> result = new LinkedList<>(prefix);
    //         result.addAll(left);
    //         result.addAll(right);
    //         results.add(result);
    //         return;
    //     }

    //     int leftHead = left.removeFirst();
    //     prefix.add(leftHead);
    //     weaveList(results, left, right, prefix);
    //     prefix.removeLast();
    //     left.addFirst(leftHead);

    //     int rightHead = right.removeFirst();
    //     prefix.add(rightHead);
    //     weaveList(results, left, right, prefix);
    //     prefix.removeLast();
    //     right.addFirst(rightHead);
    // }

    public static LinkedList<LinkedList<Integer>> permutation(LinkedList<Integer> nums, LinkedList<Integer> prefix) {
        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        // base case
        if (nums == null || nums.isEmpty()) {
            result.add(new LinkedList(prefix));
            return result;
        }
        // recursive phase
        for (int i = 0; i < nums.size(); i++) {
            prefix.add(nums.remove(i));
            result.addAll(permutation(nums, prefix));
            nums.add(i, prefix.removeLast());
        }

        return result;
    }

    // public static LinkedList<LinkedList<Integer>> allSequencesBST(TreeNode node) {
    //     LinkedList<LinkedList<Integer>> result = new LinkedList<>();
    //     if (node == null) {
    //         return result;
    //     }
    //     // prepare for BFS
    //     LinkedList<Integer> prefix = new LinkedList<>();
    //     prefix.add(node.value);
    //     result.add(prefix);
    //     LinkedList<TreeNode> currLevel = new LinkedList<>();
    //     LinkedList<TreeNode> nextLevel = new LinkedList<>();
    //     LinkedList<Integer> currLevelInt = new LinkedList<>();
    //     currLevel.add(node); 

    //     while (!currLevel.isEmpty()) {
    //         while (!currLevel.isEmpty()) {
    //             TreeNode currNode = currLevel.removeFirst();
    //             if (currNode.left != null) {
    //                 nextLevel.add(currNode.left);
    //                 currLevelInt.add(currNode.left.value);
    //             }
    //             if (currNode.right != null) {
    //                 nextLevel.add(currNode.right);
    //                 currLevelInt.add(currNode.right.value);
    //             }
    //         }
    //         currLevel = nextLevel;
    //         nextLevel = new LinkedList<>();
    //         int resultSize = result.size();
    //         for (int i = 0; i < resultSize; i++) {
    //             LinkedList<Integer> previous = result.removeFirst();
    //             LinkedList<LinkedList<Integer>> perm = permutation(currLevelInt, new LinkedList<>());
    //             for (LinkedList<Integer> p : perm) {
    //                 LinkedList<Integer> temp = new LinkedList<>(previous);
    //                 temp.addAll(p);
    //                 result.add(temp);
    //             }
    //             currLevelInt = new LinkedList<>();
    //         }    
    //     }
    //     return result;
    // }

    public static boolean isSubtree(TreeNode big, TreeNode small) {
        // base case
        if (big == null && small == null) {
            return true;
        } else if (big == null || small == null) {
            return false;
        }
        if (big.value == small.value) {
            if (isSubtreeHelper(big, small)) {
                return true;
            }
        }
        // recursive pahse
        return isSubtree(big.left, small) || isSubtree(big.right, small);
    }

    private static boolean isSubtreeHelper(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        } else if (p.value != q.value) {
            return false;
        } else { // two current nodes are valid
            return isSubtreeHelper(p.left, q.left) && isSubtreeHelper(p.right, q.right);
        }
    }

    public static int pathSum(TreeNode node, int target) {
        // base case
        if (node == null) {
            return 0;
        }
        // recursive phase
        int result = 0;
        result += pathSumHelper(node, target, 0);
        result += pathSum(node.left, target);
        result += pathSum(node.right, target);

        return result;
    }

    // a function all path(going downward) from a given node
    private static int pathSumHelper(TreeNode node, int target, int runningSum) {
        if (node == null) { // Base case
            return 0;
        }
        int numOfTarget = 0;
        runningSum += node.value;
        if (runningSum == target) {
            numOfTarget++;
        }
        // recursive phase
        numOfTarget += pathSumHelper(node.left, target, runningSum);
        numOfTarget += pathSumHelper(node.right, target, runningSum);
        return numOfTarget;
    }

    public static int pathSumOpt(TreeNode node, int target) {
        HashMap<Integer,Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1); // initial setting
        return pathSumOptHelper(node, target, 0, sumCount);
    }

    private static int pathSumOptHelper(TreeNode node, int target, int runningSum, HashMap<Integer, Integer> sumCount) {
        // Base case
        if (node == null) {
            return 0;
        }
        // check a running sum
        int numOfTarget = 0;
        runningSum += node.value;
        if (sumCount.containsKey(runningSum - target)) {
            numOfTarget += sumCount.get(runningSum - target);
        }
        sumCount.put(runningSum, sumCount.getOrDefault(runningSum, 0) + 1);
        // recursive phase
        numOfTarget += pathSumOptHelper(node.left, target, runningSum, sumCount);
        numOfTarget += pathSumOptHelper(node.right, target, runningSum, sumCount);
        sumCount.put(runningSum, sumCount.get(runningSum) - 1);

        return numOfTarget;
    }
}