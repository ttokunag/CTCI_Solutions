package SecondTimePackage;

import java.util.*;

public class Moderate {

    /* *********************************************** PUBLIC FUNCTIONS *********************************************** */ 

    public void numSwapper(int[] arr, int i, int j) {
        arr[i] += arr[j];
        arr[j] = -(arr[j] - arr[i]);
        arr[i] -= arr[j];
    }

    public int occurrenceOfWords(String str, String target) {
        // // create a list of punctuations to ignore
        // char[] punctuations = {
        //     '.',',','!','?','"','[',']','(',')','{','}',';',':','<','>','/'};
        // // Translate a punctuation list into a HashSet
        // HashSet<Character> punctuationSet = new HashSet<>();
        // for (char punctuation : punctuations) {
        //     punctuationSet.add(punctuation);
        // }

        // // generates a word list
        // ArrayList<String> words = divideStringsIntoWords(str, punctuationSet);

        String[] words = str.split("[\\p{Punct}\\s]+");

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

    public int factorialZerosOne(int n) {
        int numTwo = 0;
        int numFive = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num > 1 && (num % 2 == 0 || num % 5 == 0)) {
                if (num % 2 == 0) {
                    numTwo++;
                    num /= 2;
                }
                if (num % 5 == 0) {
                    numFive++;
                    num /= 5;
                }
            }
        }

        return Math.min(numTwo, numFive);
    }

    public int smallestDifference(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int ptr1 = 0;
        int ptr2 = 0;
        int minDiff = Integer.MAX_VALUE;

        while (ptr1 < arr1.length && ptr2 < arr2.length) {
            int diff = Math.abs(arr1[ptr1] - arr2[ptr2]);
            if (diff < minDiff) {
                minDiff = diff;
            }

            if (arr1[ptr1] > arr2[ptr2]) {
                ptr2++;
            } else {
                ptr1++;
            }
        }

        return minDiff;
    }

    public boolean ticTacWin(char[][] board) {
        // exclude an invalid board
        if (board.length != board[0].length) {
            return false;
        }

        // chech a diagonal condition
        if (compareDiagonal(board)) {
            return true;
        }

        // set an array to compare with each row or col
        char[] winRowCol = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            winRowCol[i] = 'o';
        }

        // traverse a board
        for (int row = 0; row < board.length; row++) {
            if (compareRows(board[row], winRowCol)) {
                return true;
            }
            if (row == 0) {
                for (int col = 0; col < board.length; col++) {
                    if (compareCols(winRowCol, row, col, board)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public String englishInt(int num) {
        if (num == 0) {
            return "Zero";
        }

        String[] large = {""," Thousand ", " Million "," Billion ", " Trillion "};
        String[] medium = {"Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String[] small = {
            "","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven",
            "Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"
        };

        int currNum = num;
        int unitIdx = 0;
        String result = "";
        while (currNum > 0) {
            String partial = englishIntHelper(currNum % 1000, unitIdx, large, medium, small);
            result = partial + large[unitIdx] + result;
            currNum = currNum / 1000;
            unitIdx++;
        }

        return  result;
    }

    


    /* *********************************************** PRIVATE FUNCTIONS *********************************************** */ 

    // private ArrayList<String> divideStringsIntoWords(String str, HashSet<Character> puncs) {
    //     ArrayList<String> result = new ArrayList<>();

    //     int startIndex = 0;
    //     for (int i = 0; i < str.length(); i++) {
    //         char currChar = str.charAt(i);
    //         if (currChar == ' ' || puncs.contains(currChar) || String.valueOf(currChar).equals("'")) {
    //             if (i != startIndex) {
    //                 result.add(str.substring(startIndex, i));
    //             }
    //             startIndex = i + 1;
    //         } else if (i == str.length() - 1) {
    //             result.add(str.substring(startIndex, i + 1));
    //         }
    //     }

    //     return result;
    // }

    private boolean compareRows(char[] row1, char[] row2) {
        for (int i = 0; i < row1.length; i++) {
            if (row1[i] != row2[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean compareCols(char[] cols, int row, int col, char[][] board) {
        for (int r = 0; r < board.length; r++) {
            if (cols[r] != board[r][col]) {
                return false;
            }
        }
        return true;
    }

    private boolean compareDiagonal(char[][] board) {
        int currPos = 0;
        boolean won = true;

        while (currPos < board.length) {
            if (board[currPos][currPos] == 'x') {
                won = false;
                break;
            }
            currPos++;
        }

        if (!won) {
            currPos = board.length - 1;
            while (currPos >= 0) {
                if (board[board.length - currPos - 1][currPos] == 'x') {
                    return false;
                }
                currPos--;
            }
            won = true;
        }

        return won;
    }

    private String englishIntHelper(int num, int unitIdx, String[] large, String[] medium, String[] small) {
        String result;
        int currNum = num;

        // deal with the last two digit
        int digit = currNum % 100;
        if (digit < 20 && digit > 0) {
            result = small[digit];
        } else {
            digit = digit % 10;
            result = small[digit];
            int temp = currNum / 10;
            digit = temp % 10;
            result = medium[digit - 2] + " " + result;
        }

        // deal with the head digit
        currNum = currNum / 100;
        if (currNum > 0) {
            result = small[currNum] + " Hundred " + result;
        }

        return result;
    }

}