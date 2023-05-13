package dayday;

import java.util.Stack;

/**
 * 最小栈
 * 设计一个栈，可以常数时间检索到最小值
 */
public class MinStack {

    private final Stack<Integer> stack = new Stack<>();
    private final Stack<Integer> minStack = new Stack<>();

    public MinStack() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(),val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    @Override
    public String toString() {
        return "dayday.MinStack{" +
                ", stack=" + stack +
                ", minStack=" + minStack +
                '}';
    }
}
