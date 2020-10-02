package xp.pan.shunfeng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Test0913_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] charArr = in.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < charArr.length; i++) {
            if (!stack.isEmpty() && stack.peek().equals(charArr[i])) {
                stack.pop();
            } else {
                stack.push(charArr[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        System.out.println(sb.length() > 0 ? sb : "empty");
    }
}
