package myPackage;

public class Sorting {
    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    // O(N^2) time O(1) space
    // "Select" a smallest element
    public void selection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // find a smallest element from an unsorted part
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            // place a smallest element in a sorted part
            swap(arr, i, minIdx);
        }
    }

    // O(N^2) time, O(1) space
    // O(N + k) time when an array is almost sorted
    // k: the number of misplacement
    public void insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int swapIdx = i - 1;
            // find the insertion place
            while (swapIdx >= 0 && curr < arr[swapIdx]) {
                arr[swapIdx + 1] = arr[swapIdx]; 
                swapIdx--;
                // what if use swap? is it more efficient?
            }
            arr[swapIdx + 1] = curr;
        }
    }

    // O(N^2) time, O(1) space
    public void bubble(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            // we know (len-i, len) elements are sorted
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}