package xp.pan.aiqiyi;

import java.util.Scanner;
import java.util.Stack;

public class Test0823_3 {

    private static Stack<Character> stack = new Stack<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        if (str == "") {
            System.out.println("True");
            return;
        }
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (!stack.isEmpty()
                    && (c == ')' && stack.peek().charValue() == '('
                        || c == '}' && stack.peek().charValue() == '{'
                        || c == ']' && stack.peek().charValue() == '[')){
                stack.pop();
            } else {
                System.out.println("False");
                return;
            }
        }
        System.out.println("True");
//        System.out.println(stack.isEmpty() ? "Ture" : "False");
    }


}
