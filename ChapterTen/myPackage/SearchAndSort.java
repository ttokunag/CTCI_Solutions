package myPackage;

import java.util.*;

public class SearchAndSort {

    /* Group Anagram */

    /* My Original Solution
     * O(N * max{M, N}) time, O(MN) space
     * M is the length of a longest string
     */
    public void groupAnagrams(String[] array) {
        ArrayList<HashMap<Character, Integer>> mapList = new ArrayList<>();
        
        for (String str : array) {
            // store the count of each letter in str
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                char currChar = str.charAt(i);
                map.put(currChar, map.getOrDefault(currChar, 0) + 1);
            }
            mapList.add(map);
        }

        // search each letter counter
        for (int i = 0; i < mapList.size() - 1; i++) {
            HashMap<Character, Integer> currMap = mapList.get(i);
            int swapIdx = i + 1;
            // compare the current counter with later counters
            for (int j = i+1; j < mapList.size(); j++) {
                HashMap<Character, Integer> targetMap = mapList.get(j);
                boolean mismatch = false; // check if counters match
                for (Character key : currMap.keySet()) {
                    // the case two don't match
                    if (currMap.get(key) != targetMap.get(key)) {
                        mismatch = true;
                        break;
                    }
                }
                // swapping
                if (!mismatch) {
                    String temp = array[swapIdx];
                    array[swapIdx] = array[j];
                    array[j] = temp;

                    HashMap<Character, Integer> tempHM = mapList.get(swapIdx);
                    mapList.set(swapIdx, targetMap);
                    mapList.set(j, tempHM);
                    swapIdx++;
                }
            }
            // we can skip a sorted part
            i = swapIdx - 1;
        }
    }

    /* Comparator x Arrays.sort
     * O(Nlog(N)) time, O(M) space for sortChar
     */
    class AnagramComparator implements Comparator<String> {
        // O(Mlog(M)) time
        public String sortChar(String str) {
            char[] content = str.toCharArray();
            Arrays.sort(content);
            return new String(content);
        }
        // O(1) time
        public int compare(String str1, String str2) {
            return sortChar(str1).compareTo(sortChar(str2));
        }
    }
    public void groupAnagramsComparator(String[] array) {
        Arrays.sort(array, new AnagramComparator());
    }

    /* Bucket sort modification
     * O(N) time, O(N) space
     */
    public void groupAnagramsBucket(String[] arr) {
        HashMap<String, ArrayList<String>> mapList = new HashMap<>();
        for (String str : arr) {
            String sorted = sortChar(str);
            if (!mapList.containsKey(sorted)) {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(str);
                mapList.put(sorted, newList);
            } else {
                ArrayList<String> currList = mapList.get(sorted);
                currList.add(str);
            }
        }

        int index = 0;
        for (ArrayList<String> list : mapList.values()) {
            for (String curr : list) {
                arr[index++] = curr;
            }
        }
    }
    public String sortChar(String str) {
        char[] content = str.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }


    /* Search in a Rotated Array */
    public int searchRotatedArray(int[] arr, int target) {
        return searchRotatedArrayHelper(arr, target, 0, arr.length - 1);
    }
    private int searchRotatedArrayHelper(int[] arr, int target, int left, int right) {
        // base case
        if (left > right) {
            return -1;
        }
        int median = (left + right) / 2;
        int medianValue = arr[median];
        if (medianValue == target) {
            return median;
        }

        // when we're in a sorted part
        if (arr[left] < arr[right]) {
            if (medianValue < target) {
                return searchRotatedArrayHelper(arr, target, median + 1, right);
            } else {
                return searchRotatedArrayHelper(arr, target, left, median - 1);
            }
        } else {
            return Math.max(
                searchRotatedArrayHelper(arr, target, left, median - 1),
                searchRotatedArrayHelper(arr, target, median + 1, right)
            );
        }
    }

    /* Sparse Search */
    public int sparseSearch(String[] arr, String target) {
        if (target == null) {
            return -1;
        }
        return sparseSearch(arr, target, 0, arr.length - 1);
    }
    private int sparseSearch(String[] arr, String target, int left, int right) {
        // base case for invalid target
        if (left > right) {
            return -1;
        }
        // base case for valid target
        int median = (left + right) / 2;
        String medianStr = arr[median];
        // assume we are case insensitive
        if (medianStr.compareTo(target) == 0) {
            return median;
        }
        // recursive phase
        if (!medianStr.equals("")) {
            if (medianStr.compareTo(target) > 0) { // median is larger
                return sparseSearch(arr, target, left, median - 1);
            } else { // median is smaller
                return sparseSearch(arr, target, median + 1, right);
            }
        } else {
            return Math.max(
                sparseSearch(arr, target, left, median - 1),
                sparseSearch(arr, target, median + 1, right)
            );
        }
    }


    /* Sorted Matrix Search */
    public int[] matrixSearch(int[][] mat, int target) {
        if (mat[0].length == 0 ||
            (target < mat[0][0] || target > mat[mat.length-1][mat[0].length-1])) {
            return new int[]{-1,-1};
        }
        for (int row = 0; row < mat.length; row++) {
            int[] currRow = mat[row];
            if (target >= currRow[0] && target <= currRow[currRow.length - 1]) {
                int col = binarySearch(currRow, target, 0, currRow.length - 1);
                if (col != -1) {
                    return new int[]{row, col};
                } else {
                    continue;
                }
            }
        }
        return new int[]{-1, -1};
    }
    public int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int median = (left + right) / 2;
        int medianValue = arr[median];
        if (medianValue == target) {
            return median;
        } else if (medianValue < target) {
            return binarySearch(arr, target, median + 1, right);
        } else {
            return binarySearch(arr, target, left, median - 1);
        }
    }


    /* Peaks and Valleys */
    // O(Nlog(N)) time, O(N) space
    public void peaksValleys(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        int curr, left, right;
        curr = left = 0;
        right = arr.length - 1;
        while (left <= right) {
            arr[curr++] = sorted[left++];
            if (curr < arr.length) {
                arr[curr++] = sorted[right--];
            }
        }
    }
    // O(N) time, O(1) space
    public void peaksValleysOpt(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i-1] && arr[i+1] > arr[i] ||
                arr[i] < arr[i-1] && arr[i+1] < arr[i]) {
                swap(arr, i, i+1);
            }
        }
    }
    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}