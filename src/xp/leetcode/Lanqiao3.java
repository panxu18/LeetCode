package xp.leetcode;

import java.util.Stack;

public class Lanqiao3 {

    public static void main(String[] args) {
        int op1 = 4;
        int op2 = 4;
//        int[] stack = new int[100];
//        Arrays.fill(stack, -1);
        Stack<Integer> stack = new Stack<>();
        System.out.println(solve(stack, op1, op2));
        int n = 2019;
        System.out.println((1 + n - 1) * (n - 1));
    }

    private static int solve(Stack<Integer> stack, int op1, int op2) {
        if (op1 == 0 || op2 == 0) return 1;

        int ans = 0;
        if (stack.isEmpty()) {
            // 入栈
            stack.push(0);
            ans += solve(stack, op1 - 1, op2);
            stack.pop();
            return ans;
        } else {
            // 入栈
            stack.push(0);
            ans += solve(stack, op1 - 1, op2);
            stack.pop();
            // 出栈
            stack.pop();
            ans += solve(stack, op1, op2 - 1);
            stack.push(0);

        }
        return ans;

    }


}
