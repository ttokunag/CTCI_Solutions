package myPackage;
import java.util.LinkedList;

public class StackAndQueue {

    private static class MinStackNode {
        int value, minBelow;
        MinStackNode prev, next;

        private MinStackNode(int value) {
            this.value = value;
            this.next = this.prev = null;
        }
        private void setMinBelow(int value) {
            this.minBelow = value;
        }
        private int getMinBelow() {
            return this.minBelow;
        }
    }
    
    public static class MinStack {
        MinStackNode top;
        int size;

        public MinStack() {
            this.top = null;
            this.size = 0;
        }

        public void push(int value) {
            // initialize a node
            MinStackNode node = new MinStackNode(value);

            if (top == null) {
                top = node;
                top.setMinBelow(value);
            } else {
                // if the value is smallest, update
                node.setMinBelow(Math.min(top.getMinBelow(), value));
                top.next = node;
                node.prev = top;
                top = node;
            }
            size++;
        }

        public void push(int[] values) {
            for (int value : values) {
                this.push(value);
            }
        }

        public int pop() {
            if (top == null) {
                return Integer.MIN_VALUE;
            } else {
                MinStackNode popped = top;
                top = popped.prev;
                size--;

                return popped.value;
            }
        }

        public int min() {
            return top.minBelow;
        }
    }

    public static class StackNode {
        int value;
        StackNode prev, next;

        public StackNode(int value) {
            this.value = value;
            this.prev = this.next = null;
        }
    }

    public static class Stack {
        StackNode bottom, top;
        private int size;

        public Stack() {
            this.bottom = this.top = null;
            this.size = 0;
        }

        public void push(int value) {
            StackNode node = new StackNode(value);
            size++; // increment the size

            if (top == null) {
                bottom = top = node;
            } else {
                top.next = node;
                node.prev = top;
                top = node;
            }
        }

        public int pop() {
            if (top == null) {
                return Integer.MIN_VALUE;
            }

            size--;  
            // modify the top element
            StackNode popped = top;
            top = popped.prev;
            if (size != 0) {
                top.next = null;
            }
        
            // deal with a bottom element
            if (size == 0) {
                bottom = null;
            }

            return popped.value;
        }

        public int size() {
            return this.size;
        }
    }

    public static class SetOfStack {
        // initialize a set
        LinkedList<Stack> set;
        Stack currStack;
        int capacity = 3;

        public SetOfStack() {
            this.set = new LinkedList<>();
            this.currStack = new Stack();
            this.set.add(currStack);
        }

        public void push(int value) {
            if (currStack.size() == capacity) {
                // initialize and add a new stack
                Stack newStack = new Stack();
                newStack.push(value);
                set.add(newStack);

                // update a current stack
                currStack = newStack;
            } else {
                currStack.push(value);
            }
        }
        
        public int pop() {
            // when a stack is empty
            if (set.size() == 1 && currStack.size() == 0) {
                return Integer.MIN_VALUE;
            }
            // pop a top element
            int popped = currStack.pop();
            // remove an empty stack
            if (set.size() > 0 && currStack.size() == 0) {
                // set.removeLast();
            }

            return popped;
        }

        public void printStacks() {
            for (int i = 0; i < set.size(); i ++) {
                Stack stack = set.get(i);
                System.out.printf("Stack %d: {", i + 1);

                StackNode curr = stack.bottom;
                while (curr != null) {
                    System.out.printf("%d ", curr.value);
                    curr = curr.next;
                }
                System.out.println("}");
            }
        }
    }
}