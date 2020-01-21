package SecondTimePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashSet;

public class Recursion {

/* ****************************** PUBLIC FUNCTIONS ****************************** */ 

    public ArrayList<String> permWithoutDups(String str) {
        // a linked-list containing characters in a given string
        // we use a linked-list because of frequent add/remove.
        LinkedList<Character> charList = new LinkedList<>();
        for (char c : str.toCharArray()) {
            charList.add(c);
        }

        return permWithoutDupsRecHelper(charList);
    }

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


/* ****************************** PRIVATE FUNCTIONS ****************************** */ 

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

}