package dayday;

import java.util.Stack;

/**
 * 两个栈实现队列
 * 队列：先进先出
 * 栈：先进后出
 */
public class MyQueue {
    private Stack<Integer> firstS = new Stack<>();
    private Stack<Integer> secondS = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {
        while (!secondS.isEmpty()) {
            Integer pop = secondS.pop();
            firstS.add(pop);
        }
        firstS.add(x);
        while (!firstS.isEmpty()) {
            Integer pop = firstS.pop();
            secondS.add(pop);
        }
    }

    public int pop() {
        return secondS.pop();
    }

    public int peel() {
        return secondS.peek();
    }

    public boolean empty() {
        return secondS.empty();
    }
}
