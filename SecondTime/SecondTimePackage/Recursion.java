package SecondTimePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashSet;

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

    public void paren(int n) {
        parenHelper("", n, 0, 0);
        System.out.println();
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

}