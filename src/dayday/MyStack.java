package dayday;

import java.util.*;

/**
 * 两个队列实现一个栈
 * 栈：后进先出
 * 队列：先进先出
 */
public class MyStack {
    private Queue<Integer> firstQueue = new LinkedList<>();
    private Queue<Integer> secondQueue = new LinkedList<>();

    public void push(int x) {
        firstQueue.add(x);
        Queue<Integer> temp = firstQueue;
        while (!secondQueue.isEmpty()){
            Integer poll = secondQueue.poll();
            firstQueue.add(poll);
        }
        firstQueue = secondQueue;
        secondQueue = temp;
    }

    public int pop() {
        return firstQueue.poll();
    }

    public int top() {
        return firstQueue.peek();
    }

    public boolean empty() {
        return firstQueue.isEmpty();
    }

}
