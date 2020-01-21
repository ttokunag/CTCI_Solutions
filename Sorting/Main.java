import myPackage.*;

public class Main {
    public static void main(String[] args) {

        Sorting s = new Sorting();
        System.out.println("\n--------------------------------------------------");


        int[] arr = {3,6,0,1,7,8,2,5,4};
        // int[] sortedArr = {0,1,2,3,4,5,6,7,8};
        
        // s.selection(arr);
        // s.insertion(arr);
        s.bubble(arr);


        printArray(arr);

        System.out.println("--------------------------------------------------");
    }

    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}