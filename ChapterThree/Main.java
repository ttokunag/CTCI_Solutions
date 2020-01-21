import myPackage.*;
import myPackage.StackAndQueue.MinStack;
import myPackage.StackAndQueue.SetOfStack;

public class Main {
    public static void main(String[] args) {

        StackAndQueue s = new StackAndQueue();
        System.out.println("\n--------------------------------------------------");

        // MinStack minStack = new MinStack();
        // minStack.push(new int[]{2,1,3});
        // minStack.pop(); minStack.pop();
        // System.out.println("Min: " + minStack.min());

        SetOfStack setStacks = new SetOfStack();
        for (int i = 0; i < 8; i++) {
            setStacks.push(i);
        }
        setStacks.printStacks();
        setStacks.pop(); 
        setStacks.printStacks();

        System.out.println("--------------------------------------------------");
    }

    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}