package myPackage;

import java.util.*;

public class Recursion {

    // Consider top-down way: how do we calc f(n)?
    // Be carefule of overlaps: don't include f(n-1) and f(n-2)
    // while considering f(n-3)->f(n) way
    public int tripleStep(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0 || n == 1) {
            return 1;
        }
        // return tripleStep(n-1) + tripleStep(n-2) + tripleStep(n-3);
        return tripleStep(n, new int[n + 1]);
    }
    private int tripleStep(int n, int[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        if (memo[n] == 0) {
            memo[n] = tripleStep(n-1, memo) + tripleStep(n-2, memo) + tripleStep(n-3, memo);
        }
        return memo[n];
    }


    public class Point {
        public int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        private boolean equals(Point p) {
            return (this.row == p.row) && (this.col == p.col);
        }
    }
    // Grid contains two states: 0->okay to come in, 1->off limits
    public ArrayList<Point> robotInGrid(int[][] grid) {
        ArrayList<Point> path = new ArrayList<>();
        return robotInGrid(grid, 0, 0, path);
    }
    private ArrayList<Point> robotInGrid(int[][] grid, int row, int col, ArrayList<Point> path) {
        path.add(new Point(row, col));
        if (row == grid.length-1 && col == grid.length-1) {
            return path;
        }
        if (okayMove(grid, row+1, col)) {
            return robotInGrid(grid, row+1, col, path);
        }
        if (okayMove(grid, row, col+1)) {
            return robotInGrid(grid, row, col+1, path);
        }
        return null;
    }
    private boolean okayMove(int[][] grid, int row, int col) {
        return (row >= 0 && row < grid.length) 
            && (col >= 0 && col < grid[0].length) && (grid[row][col] != 1);
    }


    // O(log(N)) time, O(log(N)) space
    // Based on the binary search
    public int magicIndex(int[] arr) {
        // return magicIndex(arr, 0, arr.length-1);
        return magicIndexWithDups(arr, 0, arr.length-1);
    }
    private int magicIndex(int[] arr, int left, int right) {
        // base case
        if (left > right) {
            return -1;
        }
        // recursive phase
        int median = (left + right) / 2;
        if (median == arr[median]) {
            return median;
        } else if (median < arr[median]) {
            return magicIndex(arr, left, median - 1);
        } else {
            return magicIndex(arr, median + 1, right);
        }
    }
    private int magicIndexWithDups(int[] arr, int left, int right) {
        // base case
        if (left > right) {
            return -1;
        }
        int median = (left + right) / 2;
        int medianValue = arr[median];
        if (median == medianValue) {
            return median;
        }

        // left search
        int leftIdx = Math.min(median - 1, medianValue);
        int leftResult = magicIndexWithDups(arr, left, leftIdx);
        if (leftResult != -1) {
            return leftResult;
        }
        // right search
        int rightIdx = Math.max(median + 1, medianValue);
        return magicIndex(arr, rightIdx + 1, right);
    }
    // // the following is not sufficient
    // private int checkNeighbor(int[] arr, int idx, int curr) {
    //     int pointer = 0;
    //     // go right
    //     while (arr[idx + pointer] == curr) {
    //         System.out.printf("Curr: %d, idx: %d\n", curr, idx + pointer);
    //         if (idx + pointer == curr) {
    //             return curr;
    //         }
    //         pointer++;
    //     }
    //     // go left
    //     pointer = -1; // reset pointer
    //     while (arr[idx + pointer] == curr) {
    //         System.out.printf("Curr: %d, idx: %d\n", curr, idx + pointer);
    //         if (idx + pointer == curr) {
    //             return curr;
    //         }
    //         pointer--;
    //     }
    //     // not found
    //     return -1;
    // }


    // O(N*2^N) time, O(N*2^N) space, where N is the size of a given array
    public ArrayList<ArrayList<Character>> powerSet(char[] arr) {
        return powerSet(arr, arr.length - 1);
    }
    private ArrayList<ArrayList<Character>> powerSet(char[] arr, int i) {
        ArrayList<ArrayList<Character>> result = new ArrayList<>();
        // base case
        if (i == -1) {
            result.add(new ArrayList<>());
            return result;
        }
        // recursive phase
        result = powerSet(arr, i - 1);
        int size = result.size();
        for (int idx = 0; idx < size; idx++) {
            ArrayList<Character> currSet = new ArrayList(result.get(idx));
            currSet.add(arr[i]);
            result.add(currSet);
        }

        return result;
    }

    // O(log(N)) time, O(min(m,n)) space
    public int multiply(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int smaller = Math.min(m, n);
        int larger = Math.max(m, n);
        return multiply(smaller, larger, new int[smaller + 1]);
    }
    private int multiply(int m, int n, int[] memo) {
        // Base case
        if (m == 1) {
            return n;
        } 
        //(neg. case) else if (m == -1) {
        //(neg. case)     return (~n + 1);
        //(neg. case) }
        // recursive phase
        //(neg. case) int index = Math.abs(m);
        if (memo[m] == 0) {
            memo[m] = multiply((m >> 1), n, memo) + multiply((m >> 1), n, memo);
            if (m % 2 == 1) {
                memo[m] += multiply(1, n, memo);
            }
        }
        return memo[m];
    }


    // O(N!) time, O(N!) space
    public ArrayList<String> permWithoutDups(String str) {
        // add and remove repeatedly later, so linked-list is used
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i));
        }
        return permWithoutDups(list);
    }
    private ArrayList<String> permWithoutDups(LinkedList<Character> list) {
        ArrayList<String> result = new ArrayList<>();
        // Base case
        if (list.isEmpty()) {
            result.add("");
            return result;
        }
        // recursive phase
        for (int i = 0; i < list.size(); i++) {
            Character curr = list.remove(i);
            ArrayList<String> prev = permWithoutDups(list);
            for (String following : prev) {
                StringBuilder str = new StringBuilder(list.size());
                str.append(curr); // append a prefix
                str.append(following);
                result.add(str.toString());
            }
            // reverting a list structure
            list.add(i, curr);
        }
        return result;
    }


    public ArrayList<String> permWithDups(String str) {
        // add and remove repeatedly later, so linked-list is used
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i));
        }
        Collections.sort(list);
        return permWithDups(list);
    }
    private ArrayList<String> permWithDups(LinkedList<Character> list) {
        ArrayList<String> result = new ArrayList<>();
        // Base case
        if (list.isEmpty()) {
            result.add("");
            return result;
        }
        // recursive phase
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i).equals(list.get(i - 1))) {
                continue;
            }
            Character curr = list.remove(i);
            ArrayList<String> prev = permWithDups(list);
            for (String following : prev) {
                StringBuilder str = new StringBuilder(list.size());
                str.append(curr); // append a prefix
                str.append(following);
                result.add(str.toString());
            }
            // reverting a list structure
            list.add(i, curr);
        }
        return result;
    }

    
    public void parens(int n) {
        StringBuilder str = new StringBuilder(2*n);
        parens(n, 0, 0, str);
    }
    private void parens(int n, int numOpen, int numClose, StringBuilder parens) {
        // base case
        if (parens.length() == 2*n && (numOpen - numClose) == 0) {
            System.out.println(parens);
            return;
        }
        // recursive phase
        if (numOpen < n) { // add an open paren
            parens.append('(');
            parens(n, numOpen + 1, numClose, parens);
            // reverting
            parens.deleteCharAt(parens.length() - 1);
        }
        if (numClose < numOpen) { // add a close paren
            parens.append(')');
            parens(n, numOpen, numClose + 1, parens);
            // reverting
            parens.deleteCharAt(parens.length() - 1);
        }
    }


    public void paintFill(int[][] grid, int row, int col, int color) {
        // base case
        if (grid[row][col] == color) {
            return;
        }
        // recursive phase
        grid[row][col] = color;
        int[] rows = {-1,-1,-1,0,0,1,1,1};
        int[] cols = {-1,0,1,-1,1,-1,0,1};
        for (int r : rows) {
            for (int c : cols) {
                if (okayMove(grid, row + r, col + c)) {
                    paintFill(grid, row + r, col + c, color);
                }
            }
        }
    }


    public int coins(int n) {
        int[] coinBox = {1,5,10,25};
        return coins(n, 0, coinBox, 0);
    }
    // lots of extra works (exhaustive search)
    private int coins(int n, int runningSum, int[] coinBox, int idx) {
        int result = 0;
        // base case
        if (runningSum >= n) {
            return ++result;
        } 
        // recursive phase
        for (int i = 0; i + idx < coinBox.length; i++) {
            int remaining = n - runningSum;
            int coin = coinBox[idx + i];
            if (coin > remaining) { break;}
            result += coins(n, runningSum + coinBox[idx + i], coinBox, idx + i);
        }
        return result;
    }

    public int coinsOpt(int n) {
        int[] coins = {25,10,5,1};
        int[][] map = new int[n+1][coins.length];
        return coinsOptHelper(n, coins, 0, map);
        // return coinsOptHelper(n, coins, 0);
    }
    private int coinsOptHelper(int amount, int[] coins, int coinIndex) {
        // base case
        if (coinIndex >= coins.length - 1) {
            return 1;
        }
        // recursive phase
        int coin = coins[coinIndex];
        int result = 0;
        for (int i = 0; i * coin <= amount; i++) {
            result += coinsOptHelper((amount - i * coin), coins, coinIndex + 1);
        }
        return result;
    }
    private int coinsOptHelper(int amount, int[] coins, int coinIndex, int[][] map) {
        // base case
        if (map[amount][coinIndex] > 0) {
            return map[amount][coinIndex];
        }
        if (coinIndex >= coins.length - 1) {
            return 1;
        }
        // recursive phase
        int coin = coins[coinIndex];
        int result = 0;
        for (int i = 0; i * coin <= amount; i++) {
            int remaining = amount - i * coin;
            result += coinsOptHelper(remaining, coins, coinIndex + 1, map);
        }
        map[amount][coinIndex] = result;
        return result;
    }

    public ArrayList<Integer[]> queens(int size) {
        ArrayList<Integer[]> result = new ArrayList<>();
        Integer[] cols = new Integer[size];
        queensHelper(size, 0, cols, result);
        return result;
    }
    private void queensHelper(int size, int row, Integer[] cols, ArrayList<Integer[]> result) {
        // base case
        if (row >= size) {
            result.add(cols.clone());
            return;
        }

        for (int col = 0; col < size; col++) {
            if (validCell(row, col, cols)) {
                cols[row] = col;
                queensHelper(size, row + 1, cols, result);
                cols[row] = 0;
            }
        }
    }
    private boolean validCell(int row, int col, Integer[] cols) {
        for (int i = 0; i < row; i++) {
            if (col == cols[i]) {
                return false;
            }
            if ((row - i) == Math.abs(col - cols[i])) {
                return false;
            }
        }
        return true;
    }

    public int boolEval(String exp, boolean result, HashMap<String, Integer> memo) {
        // base case
        if (memo.containsKey(result + exp)) {
            return memo.get(result + exp);
        }
        if (exp.length() == 0) { return 0; }
        if (exp.length() == 1) { return charToBool(exp) == result ? 1 : 0; }

        // recursive phase
        int ways = 0;
        for (int i = 1; i < exp.length(); i += 2) {
            char operator = exp.charAt(i);
            String left = exp.substring(0, i);
            String right = exp.substring(i + 1, exp.length());
            
            int leftTrue = boolEval(left, true, memo);
            int leftFalse = boolEval(left, false, memo);
            int rightTrue = boolEval(right, true, memo);
            int rightFalse = boolEval(right, false, memo);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int totalTrue = 0;
            if (operator == '^') {
                totalTrue = (leftTrue * rightFalse) + (leftFalse * rightTrue);
            } else if (operator == '&') {
                totalTrue = (leftTrue * rightTrue);
            } else if (operator == '|') {
                totalTrue = (leftTrue * rightTrue) + (leftTrue * rightFalse) + (leftFalse * rightTrue);
            }
            ways += (result ? totalTrue : total - totalTrue);
        }

        memo.put(result + exp, ways);
        return ways;
    }
    private boolean charToBool(String c) {
        return c.equals("1") ? true : false;
    }

    public int[] sortedMerge(int[] A, int[] B) {
        int ptrA, ptrB;
        ptrA = ptrB = 0;

        while (ptrA < A.length && ptrB < B.length) {
            if (A[ptrA] == 0) { break; }
            if (B[ptrB] <= A[ptrA]) {
                // shift and insert
                for (int i = A.length - 2; i >= ptrA; i--) {
                    A[i + 1] = A[i];
                }
                A[ptrA] = B[ptrB++];
            }
            ptrA++;
        }
        // append remained elements in B
        while (ptrB < B.length) {
            A[ptrA++] = B[ptrB++];
        }

        return A;
    }
}