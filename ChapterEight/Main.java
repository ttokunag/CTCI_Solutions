import java.awt.Point;
import java.util.*;

import myPackage.Recursion;

public class Main {
    public static void main(String[] args) {
        Recursion r = new Recursion();

        System.out.println("\n------------------------------------------------------------");

        // // Triple Step
        // System.out.println("Triple Step (n=2): " + r.tripleStep(2));
        // System.out.println("Triple Step (n=4): " + r.tripleStep(4));
        // System.out.println("Triple Step (n=36): " + r.tripleStep(36));


        // // Robot in a Grid
        // int[][] grid1 = {
        //     {0,0,0,0,1},
        //     {1,1,1,0,0},
        //     {0,1,0,0,0},
        //     {0,1,0,1,0},
        //     {0,0,0,0,0}};

        // int[][] grid2 = {
        //     {0,0,0,0,1},
        //     {1,1,1,0,0},
        //     {0,1,0,0,0},
        //     {0,1,0,1,0},
        //     {0,0,0,0,1}};

        // System.out.println("Robot in a Grid (true): ");
        // printGrid(grid1);
        // printPath(r.robotInGrid(grid1));
        // System.out.println("Robot in a Grid (false): ");
        // printGrid(grid2);
        // printPath(r.robotInGrid(grid2));


        // // Magic Index
        // int[] sortedArr1 = {-4,0,2,4};
        // int[] sortedArr2 = {2,3,4,5};
        // System.out.println("Magic index of {-4,0,2,4}: " + r.magicIndex(sortedArr1)); 
        // System.out.println("Magic index of {2,3,4,5}: " + r.magicIndex(sortedArr2)); 
        // int[] sortedArrWithDups1 = {-4,0,1,4,4};
        // int[] sortedArrWithDups2 = {0,0,1,4,5};
        // System.out.println("Magic index of {-4,0,1,4,4}: " + r.magicIndex(sortedArrWithDups1)); 
        // System.out.println("Magic index of {0,0,1,4,5}: " + r.magicIndex(sortedArrWithDups2)); 


        // // Power Set
        // char[] charArr1 = {'a','b','c'};
        // char[] charArr2 = {};
        // System.out.println("Power Set {a,b,c}:");
        // printArrArr(r.powerSet(charArr1));
        // System.out.println("Power Set {}:");
        // printArrArr(r.powerSet(charArr2));


        // // Recursive Multiply
        // System.out.println("Recursive Multiply (5 * 6): " + r.multiply(5, 6));
        // System.out.println("Recursive Multiply (7 * 4): " + r.multiply(7, 4));
        // System.out.println("Recursive Multiply (0 * 1): " + r.multiply(0, 1));


        // // Permtations without Dups
        // System.out.println("Permutations {a,b,c}:");
        // printStrArr(r.permWithoutDups("abc"));
        // System.out.println("Permutations {}:");
        // printStrArr(r.permWithoutDups(""));


        // // Permtations with Dups
        // System.out.println("Permutations {a,a,b}:");
        // printStrArr(r.permWithDups("aab"));
        // System.out.println("Permutations {a,a}:");
        // printStrArr(r.permWithDups("aa"));


        // // Parens
        // System.out.println("Parens (n=0):"); r.parens(0);
        // System.out.println("Parens (n=2):"); r.parens(2);
        // System.out.println("Parens (n=3):"); r.parens(3);


        // // Paint Fill
        // int[][] paint1 = {
        //     {0,0,1,1,1,1,0},
        //     {0,1,1,0,0,1,0},
        //     {1,1,0,0,0,1,1},
        //     {1,1,0,0,0,1,1},
        //     {0,1,1,0,0,1,0},
        //     {0,0,1,1,1,1,0}
        // };
        // r.paintFill(paint1, 2, 2, 2);
        // printGrid(paint1);


        // // Coins
        // System.out.println("Coins (n=6): " + r.coins(6));
        // System.out.println("Coins (n=10): " + r.coinsOpt(20000));
        // int[] coins = {1,5,10,25};
        // System.out.println("Solutions (n=10): " + makeChange(10000, coins));


        // // Eight Queens
        // ArrayList<Integer[]> queens = r.queens(8);
        // for (int i = 0; i < queens.size(); i++) {
        //     System.out.println((i+1) + ".");
        //     printQueens(queens.get(i));
        //     System.out.println();
        // }


        // System.out.println("Boolean Evaluates (1^0|0|1, false): " + r.boolEval("1^0|0|1", false, new HashMap<>()));
        // System.out.println("Boolean Evaluates (0&0&0&1^1|0, true): " + r.boolEval("0&0&0&1^1|0", true, new HashMap<>()));


        int[] sorted1 = {3,4,8,0,0,0,0};
        int[] sorted2 = {1,5,6,9};
        for (int num : r.sortedMerge(sorted1, sorted2)) {
            System.out.print(num + " ");
        }
        System.out.println();


        System.out.println("------------------------------------------------------------");
    }

    static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.print("|");
            for (int i = 0; i < row.length; i++) {
                if (i == row.length - 1) {
                    System.out.printf("%d|\n", row[i]);
                } else {
                    System.out.printf("%d ", row[i]);
                }
            }
        }
    }
    static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.print("|");
            for (int i = 0; i < row.length; i++) {
                if (i == row.length - 1) {
                    System.out.printf("%c|\n", row[i]);
                } else {
                    System.out.printf("%c ", row[i]);
                }
            }
        }
    }
    static void printPath(ArrayList<Recursion.Point> path) {
        System.out.print("Path: \n");
        if (path == null) {
            System.out.println("null"); 
            return;
        }
        for (int i = 0; i < path.size(); i++) {
            Recursion.Point curr = path.get(i);
            if (i == path.size() - 1) {
                System.out.printf("(%d,%d)\n", curr.row, curr.col);
            } else {
                System.out.printf("(%d,%d)->", curr.row, curr.col);
            }
        }
        System.out.println();
    }
    static void printArrArr(ArrayList<ArrayList<Character>> arr) {
        System.out.print("{");
        int arrSize = arr.size();
        for (int idx = 0; idx < arrSize; idx++) {
            ArrayList<Character> curr = arr.get(idx);
            int size = curr.size();
            // when a current set is empty
            if (size == 0) {
                if (arrSize == 1) {
                    System.out.print("{}");
                } else {
                    System.out.print("{},");
                }
                continue;
            }
            // when a current set is nonempty
            System.out.print("{");
            for (int i = 0; i < size; i++) {
                // when at the last element
                if (i == size - 1) {
                    // when at the last set
                    if (idx == arrSize - 1) {
                        System.out.printf("%c}", curr.get(i));
                    } else {
                        System.out.printf("%c},", curr.get(i));
                    }
                } else {
                    System.out.printf("%c,", curr.get(i));
                }
            }
        }
        System.out.println("}\n");
    }
    static void printStrArr(ArrayList<String> arr) {
        System.out.print("{");
        for (int i = 0; i < arr.size(); i++) {
            if (i == arr.size() - 1) {
                System.out.printf("%s}\n\n", arr.get(i));
            } else {
                System.out.printf("%s, ", arr.get(i));
                if (i % 7 == 6) {
                    System.out.println();
                }
            }
        }
    }
    public static int makeChangeHelper(int total, int[] denoms, int index) {
		int coin = denoms[index];
		if (index == denoms.length - 1) { // One denom left, either you can do it or not
			int remaining = total % coin; 
			return remaining == 0 ? 1 : 0;
		}
		int ways = 0;
		/* Try 1 coin, then 2 coins, 3 three, ... (recursing each time on rest). */
		for (int amount = 0; amount <= total; amount += coin) { 
			ways += makeChangeHelper(total - amount, denoms, index + 1); // go to next denom
		}
		return ways;
	}
	
	public static int makeChange(int amount, int[] denoms) {
		return makeChangeHelper(amount, denoms, 0);
    }
    
    public static void printQueens(Integer[] queens) {
        int size = queens.length;
        char[][] board = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = '*';
            }
        }
        for (int row = 0; row < size; row++) {
            board[row][queens[row]] = 'Q';
        }
        printGrid(board);
    }
}