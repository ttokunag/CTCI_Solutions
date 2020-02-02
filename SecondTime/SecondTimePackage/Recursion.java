package SecondTimePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;

public class Recursion {

/* ****************************** PUBLIC FUNCTIONS ****************************** */ 

    /**
     * 
     * @param str : a string with unique letters to be permutated
     * @return : An ArrayList of permutaions
     * 
     */
    public ArrayList<String> permWithoutDups(String str) {
        // a linked-list containing characters in a given string
        // we use a linked-list because of frequent add/remove.
        LinkedList<Character> charList = new LinkedList<>();
        for (char c : str.toCharArray()) {
            charList.add(c);
        }

        return permWithoutDupsRecHelper(charList);
    }

    /**
     * 
     * @param str : a string with duplicated letters to be permutated
     * @return : An ArrayList of permutaions
     * 
     */
    public ArrayList<String> permWithDups(String str) {
        char[] sorted = str.toCharArray();
        Arrays.sort(sorted);
        // a linked-list containing characters in a given string
        // we use a linked-list because of frequent add/remove.
        LinkedList<Character> charList = new LinkedList<>();
        for (char c : sorted) {
            charList.add(c);
        }

        return permWithDupsHelper(charList);
    }

    /**
     * 
     * @param n : the number of paren pairs
     * 
     */
    public void paren(int n) {
        parenHelper("", n, 0, 0);
        System.out.println();
    }

    public int coins(int n) {
        int[] coinArr = {25,10,5,1};
        return coinsHelper(n, coinArr, 0, new int[n + 1][coinArr.length]);
    }

    public void printQueens(int size, int row, int[] cols) {
        if (row == size) {
            printQueens(cols);
            return;
        } else {
            // find an appropriate place for a new queen
            for (int col = 0; col < size; col++) {
                if (checkValid(row, col, cols)) {
                    cols[row] = col;
                    printQueens(size, row + 1, cols);
                    cols[row] = 0;
                }
            }
            return;
        }
    }

    public int countEvalExpressions(String exp, boolean bool) {
        return countEvalExpressionsHelper(exp, bool, new HashMap<>());
    }

    private int countEvalExpressionsHelper(String exp, boolean bool, HashMap<String, Integer> memo) {
        // base case
        if ((exp.equals("0") && !bool) || (exp.equals("1") && bool)) {
            return 1;
        }

        String key = exp + (bool ? "t" : "f");

        if (memo.get(key) == null) {
            int count = 0;
            // when exp contains at least one boolean operator
            for (int i = 1; i < exp.length(); i += 2) {
                String left = exp.substring(0, i);  // left expression
                char operator = exp.charAt(i);  // boolean operator
                String right = exp.substring(i + 1, exp.length());  // right expression

                int leftTrue = countEvalExpressionsHelper(left, true, memo);
                int leftFalse = countEvalExpressionsHelper(left, false, memo);
                int rightTrue = countEvalExpressionsHelper(right, true, memo);
                int rightFalse = countEvalExpressionsHelper(right, false, memo);

                // sum counts up
                if (operator == '&' && bool) {
                    count += (leftTrue * rightTrue);
                } else if (operator == '&' && !bool) {
                    count += (leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse);
                } else if (operator == '|' && bool) {
                    count += (leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue);
                } else if (operator == '|' && !bool) {
                    count += (leftFalse * rightFalse);
                } else if (operator == '^' && bool) {
                    count += (leftFalse * rightTrue + leftTrue * rightFalse);
                } else if (operator == '^' && !bool) {
                    count += (leftTrue * rightTrue + leftFalse * rightFalse);
                }
            }

            memo.put(key, count);
        }
        
        return memo.get(key);
    }


/* ****************************** PRIVATE FUNCTIONS ****************************** */ 

    /**
     * 
     * @param charList : a linked-list of a string to be permutated
     * @return : an ArrayList of permutaions
     * 
     */
    private ArrayList<String> permWithoutDupsRecHelper(LinkedList<Character> charList) {
        ArrayList<String> result = new ArrayList<>();
        // base case
        if (charList.size() == 0) {
            result.add("");
            return result;
        }

        for (int i = 0; i < charList.size(); i++) {
            // choose a character from a given list
            char currChar = charList.remove(i);
            // recursive phase
            ArrayList<String> prevList = permWithoutDupsRecHelper(charList);
            // appends a removed character to a returned permutations
            for (String str : prevList) {
                result.add(currChar + str);
                // Optional: 
                // you may insert currChar between letters of str
            }
            // revert the remove operation
            charList.add(i, currChar);
        }
        
        return result;
    }

    /**
     * 
     * @param charList : a linked-list of a string to be permutated
     * @return : an ArrayList of permutaions
     * 
     */
    private ArrayList<String> permWithDupsHelper(
        LinkedList<Character> charList) {

        ArrayList<String> result = new ArrayList<>();
        // base case
        if (charList.size() == 0) {
            result.add("");
            return result;
        }

        HashSet<Character> visited = new HashSet<>();

        for (int i = 0; i < charList.size(); i++) {
            // check duplication
            if (!visited.contains(charList.get(i))) {
                // choose a character from a given list
                char currChar = charList.remove(i);
                // mark a character used
                visited.add(currChar);
                // recursive phase
                ArrayList<String> prevList = permWithDupsHelper(charList);
                // appends a removed character to a returned permutations
                for (String str : prevList) {
                    result.add(currChar + str);
                }
                // revert the operations
                charList.add(i, currChar);
            }
        }

        return result;
    }

    private void parenHelper(String parens, int n, int numOpen, int numClose) {
        // rules out invalid parens
        if (numOpen > n || numClose > numOpen) {
            return;
        } 
        // prints a valid paren
        else if (numClose == n) {
            System.out.print(parens + " ");
            return;
        }
        // recursive phase
        parenHelper(parens + "(", n, numOpen + 1, numClose);
        parenHelper(parens + ")", n, numOpen , numClose + 1);
    }

    private int coinsHelper(int n, int[] coinArr, int coinIdx, int[][] memo) {
        /*
         * About memo[n][coinIdx]:
         * In this question, we ignore the order of coin combinations.
         * Without specifying what type of coins we've used so far, 
         * we'll have duplications.
         */

        // base cases
        if (n < 0) {
            return 0;
        } else if (n == 0 || coinIdx == coinArr.length - 1) {
            return 1;
        }

        // recursive phase
        if (memo[n][coinIdx] == 0) {
            for (int i = coinIdx; i < coinArr.length; i++) {
                memo[n][coinIdx] += coinsHelper(n - coinArr[i], coinArr, i, memo);
            }
        }

        return memo[n][coinIdx];
    }

    private boolean checkValid(int row, int col, int[] cols) {
        // look through all queens so far
        for (int i = 0; i < row; i++) {
            // column check
            if (col == cols[i]) {
                return false;
            }
            // diagonal check
            if (row - i == Math.abs(col - cols[i])) {
                return false;
            }
        }
        return true;
    }

    private void printQueens(int[] cols) {
        for (int i = 0; i < cols.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                if (j == cols[i]) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }

                if (j == cols.length - 1) {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

}