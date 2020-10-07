package xp.leetcode;

import java.util.Stack;

public class LeetCode232 {

    public static void main(String[] args) {

        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.empty());

    }

    static class MyQueue {
        Stack<Integer> stack0, stack1;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack0 = new Stack<>();
            stack1 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack0.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack0.push(stack1.pop());
                }
            }
            return stack0.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack0.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack0.push(stack1.pop());
                }
            }
            return stack0.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack0.isEmpty() && stack1.isEmpty();
        }
    }
}
