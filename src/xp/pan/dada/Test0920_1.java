package xp.pan.dada;

import java.util.Scanner;
import java.util.Stack;

public class Test0920_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strings = in.nextLine().split("");
        Stack<String> stack = new Stack<>();
        for (String str : strings) {
            if (("(".equals(str) || "{".equals(str) || "[".equals(str))) {
                stack.push(str);
            } else {
                if (stack.isEmpty() || !check(stack.pop(), str)) {
                    System.out.println("false");
                    return;
                }
            }
        }

        System.out.println(stack.isEmpty() ? "true" : "false");
    }

    private static boolean check(String s1, String s2) {
        return "(".equals(s1) && ")".equals(s2) || "[".equals(s1) && "]".equals(s2) || "{".equals(s1) && "}".equals(s2);
    }
}
