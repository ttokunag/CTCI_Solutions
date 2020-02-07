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

    public class Person {
        public int birth;
        public int death;
        public Person(int birth, int death) {
            this.birth = birth;
            this.death = death;
        }
    }

    public int livingPeople(Person[] people) {
        int[] births = sortedYear(people, true);
        int[] deaths = sortedYear(people, false);

        int counter = 0;
        int maxCounter = Integer.MIN_VALUE;
        int theYear = Integer.MIN_VALUE;

        int birthPointer = 0;
        int deathPointer = 0;

        while (birthPointer < births.length && deathPointer < deaths.length) {
            if (births[birthPointer] <= deaths[deathPointer]) {
                counter++;
                if (maxCounter < counter) {
                    maxCounter = counter;
                    theYear = births[birthPointer];
                }
                if (births[birthPointer] == deaths[deathPointer]) {
                    counter--;
                    deathPointer++;
                }
                birthPointer++;
            } else {
                counter--;
                deathPointer++;
            }
        }

        return theYear;
    }

    private int[] sortedYear(Person[] people, boolean isBirth) {
        int[] result = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            result[i] = isBirth ? people[i].birth : people[i].death;
        }

        Arrays.sort(result);
        return result;
    }

    public String bisectSquares(double[][] square1, double[][] square2) {
        double[] center1 = new double[2];
        for (int i = 1; i < square1.length; i++) {
            if (square1[0][0] != square1[i][0]) {
                center1[0] = (square1[0][0] + square1[i][0]) / 2;
            }
            if (square1[0][1] != square1[i][1]) {
                center1[1] = (square1[0][1] + square1[i][1]) / 2;
            }
        }
        
        double[] center2 = new double[2];
        for (int i = 1; i < square2.length; i++) {
            if (square2[0][0] != square2[i][0]) {
                center2[0] = (square2[0][0] + square2[i][0]) / 2;
            }
            if (square2[0][1] != square2[i][1]) {
                center2[1] = (square2[0][1] + square2[i][1]) / 2;
            }
        }

        if (center1[0] == center2[0]) {
            if (center1[1] == center2[1]) {
                return "Any line which passes through (" + center1[0] + ", " + center2[0] + ")";
            }
            else {
                return "x = " + center1[0];
            }
        }
        
        double[] abPair = buildLine(center1, center2);
        String result = "y = " + abPair[0] + "x " + ((abPair[1] >= 0) ? "+ " : "- ") + abPair[1];
        
        return result;
    }
        
    private double[] buildLine(double[] point1, double[] point2) {
        double a = (point1[1] - point2[1]) / (point1[0] - point2[0]);
        double b = point1[1] - a * point1[0];
        
        return new double[] {a, b};
    }

    public int[] subSortRange(int[] arr) {
        int firstUnsorted = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length -1 ; i++) {
            if (arr[i + 1] < arr[i]) {
                firstUnsorted = i;
                break;
            }
        }
        // when a given array is already sorted
        if (firstUnsorted == Integer.MIN_VALUE) {
            return new int[2];
        }
        
        // Second pass begins
        int lastUnsorted = Integer.MIN_VALUE;
        int minUnsorted = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i > firstUnsorted; i--) {
            if ((arr[i - 1] > arr[i] || arr[i] < arr[firstUnsorted]) 
                && lastUnsorted == Integer.MIN_VALUE) {
                lastUnsorted = i;
            }
            if (minUnsorted == Integer.MIN_VALUE || arr[i] < arr[minUnsorted]) {
                minUnsorted = i;
            }
        }
        
        // Third pass begins
        int unsortedBegin = Integer.MIN_VALUE;
        int maxUnsorted = Integer.MIN_VALUE;
        for (int i = 0; i <= lastUnsorted; i++) {
            if (unsortedBegin == Integer.MIN_VALUE && arr[i] > arr[minUnsorted]) {
                unsortedBegin = i;
            }
            if (maxUnsorted == Integer.MIN_VALUE || arr[i] > arr[maxUnsorted]) {
                maxUnsorted = i;
            }
        }

        int unsortedEnd = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= maxUnsorted; i--) {
            if (arr[i] < arr[maxUnsorted]) {
                unsortedEnd = i;
                break;
            }
        }
        
        return new int[] {unsortedBegin, unsortedEnd};
    }


    public int[] subSortBF(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);
        
        int indexFirstDiff = Integer.MIN_VALUE;
        int indexOfDiff = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != clone[i]) {
                indexOfDiff = i;
                if (indexFirstDiff == Integer.MIN_VALUE) {
                    indexFirstDiff = i;
                }
            }
        }
        
        // when a given array is sorted
        if (indexFirstDiff == Integer.MIN_VALUE) {
            return new int[2];
        }
        
        return new int[] {indexFirstDiff, indexOfDiff};
    }
        
    public int[] largestInterval(int[] arr) {
        int runSum = 0;
        int startIndex = 0;
        int maxSum = Integer.MIN_VALUE;
        int maxStartIndex = 0;
        int maxEndIndex = 0;
        
        for (int i = 0; i < arr.length; i++) {
            runSum += arr[i];
            
            // check the sign of runSum
            if (runSum <= 0) {
                runSum = 0;
                startIndex = i + 1;

                // for the case all entries are negative
                if (arr[i] > maxSum) {
                    maxSum = arr[i];
                    maxStartIndex = maxEndIndex = i;
                }
            } else {
                if (runSum > maxSum) {
                    maxSum = runSum;
                    maxStartIndex = startIndex;
                    maxEndIndex = i;
                }
            }
        }
        
        return new int[]{maxStartIndex, maxEndIndex};
    }
        
    public HashSet<Integer> pondSize(int[][] map) {
        HashSet<Integer> ponds = new HashSet<>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] == 0) {
                    pondHelper(map, row, col, ponds);
                }
            }
        }

        return ponds;
    }

    private void pondHelper(int[][] map, int row, int col, HashSet<Integer> ponds) {
        map[row][col] = -1;
        
        int pondSize = 1;
        LinkedList<Integer[]> queue = new LinkedList<>();
        queue.addLast(new Integer[] {row, col});

        int[] rows = {-1,-1,-1,0,0,0,1,1,1};
        int[] cols = {-1,0,1,-1,0,1,-1,0,1};

        while (!queue.isEmpty()) {
            Integer[] currPos = queue.removeFirst();
            for (int i = 0; i < rows.length; i++) {
                if (validCell(map, currPos[0] + rows[i], currPos[1] + cols[i])) {
                    map[currPos[0] + rows[i]][currPos[1] + cols[i]] = -1;
                    pondSize++;
                    queue.addLast(new Integer[] {currPos[0] + rows[i], currPos[1] + cols[i]});
                }
            }
        }

        ponds.add(pondSize);
    }

    private boolean validCell(int[][] map, int row, int col) {
        return (row >= 0 && row < map.length) && (col >= 0 && col < map[0].length)
                && map[row][col] == 0;
    }

    public ArrayList<String> T9(String numInput, ArrayList<String> validWords) {
        // build a hash map
        HashMap<Character, String> numberMap = new HashMap<>();
        
        // num-val pair
        char[] nums = {'2','3','4','5','6','7','8','9'};
        String[] vals = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        for (int i = 0; i < nums.length; i++) {
        numberMap.put(nums[i], vals[i]);
        }
        
        ArrayList<String> result = new ArrayList<>();
        T9Helper(numInput, "", result,  numberMap, validWords);
        
        return result;
    }
        
    private void T9Helper(String input, String predict, ArrayList<String> result, 
        HashMap<Character, String> keyMap, ArrayList<String> validWords) {
        // base case: when all the input numbers are processed
        if (input.length() == 0) {
            if (validWords.contains(predict))
                result.add(predict);
            return;
        }
        
        char headNum = input.charAt(0);
        String alphabets = keyMap.get(headNum);
        String nextInput = input.substring(1, input.length());
        
        for (char letter : alphabets.toCharArray()) {
            T9Helper(nextInput, predict + letter, result, keyMap, validWords);
        }
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