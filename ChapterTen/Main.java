import myPackage.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        SearchAndSort s = new SearchAndSort();

        System.out.println("\n==================================================");


        // // Group Anagrams
        // String[] arr = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        // s.groupAnagramsBucket(arr);
        // for (String str : arr) {
        //     System.out.println(str);
        // }


        // // Search in Rotated Array
        // int[] arr = {15,16,19,20,25,1,3,4,5,7,10,14};
        // // int[] arr = {2,2,2,3,4,2};
        // for (int num : arr) {
        //     System.out.println(num + " is at " + s.searchRotatedArray(arr, num));
        // }

        // // Sparse Search 
        // String[] arr = {"at","","","","ball","","","car","","","dad","",""};
        // for (String str : arr) {
        //     System.out.println(str + " is at " + s.sparseSearch(arr, str));
        // }


        // // Sorted Matrix Search
        // int[][] mat = {{1,3,5,7},{2,4,6,8},{3,5,7,9}};
        // int[][] mat1 = {{}};
        // int[] result = s.matrixSearch(mat1, 6);
        // System.out.printf("Target is at [%d,%d]\n", result[0], result[1]);


        // Peaks and Valleys
        int[] arr1 = {1,2,3,4,5,6,7,8};
        int[] arr2 = {7,8,4,0,1,9};
        s.peaksValleysOpt(arr2);
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();


        System.out.println("==================================================");
    }
}