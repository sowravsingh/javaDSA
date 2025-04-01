package CustomArrays;

import java.util.Stack;

public class QueueWithStack {

    public static  Stack<Integer> stack1;
    public static  Stack<Integer> stack2;

    public QueueWithStack() {
        stack1 = new Stack<>();
        stack2 =new Stack<>();

    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int removed = stack2.pop();

        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return removed;
    }

    public int peek() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int removed = stack2.peek();

        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return removed;
    }

    public boolean empty() {

        return stack1.empty();
    }
}
