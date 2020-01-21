package SecondTimePackage;

import java.util.ArrayList;
import java.util.LinkedList;

public class Recursion {

    public ArrayList<String> permWithoutDupsRec(String str) {
        // a linked-list containing characters in a given string
        // we use a linked-list because of frequent add/remove.
        LinkedList<Character> charList = new LinkedList<>();
        for (char c : str.toCharArray()) {
            charList.add(c);
        }

        return permWithoutDupsRecHelper(charList);
    }

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
            }
            // revert the remove operation
            charList.add(i, currChar);
        }
        
        return result;
    }

}