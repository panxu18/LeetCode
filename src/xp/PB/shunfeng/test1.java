package xp.PB.shunfeng;


import java.util.Scanner;
import java.util.Stack;

public class test1 {
    public static void main(String[] args) {
        Scanner sacn = new Scanner(System.in);
        char[] arr = sacn.nextLine().toCharArray();
        if (arr.length == 0) {
            System.out.println("empty");
            return;
        }
        Stack<Character> stack = new Stack<>();
        Stack<Character> stack1 = new Stack<>();
        for (int i = 0; i < arr.length; i++) {

            if (!stack.empty()) {
                if(stack.peek()==arr[i]){
                    stack.pop();
                }else {
                    stack.push(arr[i]);
                }
            }else{
                stack.push(arr[i]);
            }
        }
        if(stack.empty()){
            System.out.println("empty");
            return;
        }
        while (!stack.empty()){
            stack1.push(stack.pop());
        }
        while (!stack1.empty()){
            System.out.print(stack1.pop());
        }
    }

}
