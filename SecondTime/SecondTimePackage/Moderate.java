package SecondTimePackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Moderate {

    public void numSwapper(int[] arr, int i, int j) {
        arr[i] += arr[j];
        arr[j] = -(arr[j] - arr[i]);
        arr[i] -= arr[j];
    }

    public int occurrenceOfWords(String str, String target) {
        char[] punctuations = {
            '.',',','!','?','"','[',']','(',')','{','}',';',':','<','>','/'};

        // create a list of punctuations to ignore
        HashSet<Character> punctuationSet = new HashSet<>();
        for (char punctuation : punctuations) {
            punctuationSet.add(punctuation);
        }

        // generates a word list
        ArrayList<String> words = divideStringsIntoWords(str, punctuationSet);
        // a hash table to record the occurrences of each word
        HashMap<String, Integer> occurrences = new HashMap<>();
        for (String word : words) {
            String currWord = word.toLowerCase();
            Integer occurrence = occurrences.get(currWord);

            if (occurrence != null) { // when a word has been seen already
                occurrences.put(currWord, occurrence + 1);
            } else {
                occurrences.put(currWord, 1);
            }
        }

        Integer result = occurrences.get(target.toLowerCase());
        return result == null ? 0 : result;
    } 

    private ArrayList<String> divideStringsIntoWords(String str, HashSet<Character> puncs) {
        ArrayList<String> result = new ArrayList<>();

        int startIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            if (currChar == ' ' || puncs.contains(currChar) || String.valueOf(currChar).equals("'")) {
                if (i != startIndex) {
                    result.add(str.substring(startIndex, i));
                }
                startIndex = i + 1;
            } else if (i == str.length() - 1) {
                result.add(str.substring(startIndex, i + 1));
            }
        }

        return result;
    }

}