package xp.ZQ;

import java.util.Scanner;
import java.util.Stack;

public class Test24 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        char[] chars=s.toCharArray();
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='('||chars[i]=='['||chars[i]=='{'){
                stack.push(chars[i]);
            }else if(chars[i]==')'||chars[i]==']'||chars[i]=='}'){
                if(stack.isEmpty()){
                    System.out.println("False");
                }
                char temp=stack.pop();
                if(temp=='('&&chars[i]!=')'){
                    System.out.println("False");
                }
                if(temp=='['&&chars[i]!=']'){
                    System.out.println("False");
                }
                if(temp=='{'&&chars[i]!='}'){
                    System.out.println("False");
                }
            }else{
                System.out.println("False");
            }

        }
        System.out.println(stack.isEmpty());
//
//        Stack<Character> stack = new Stack<>();
//        for (int i=0;i<s.length();i++){
//            char c=s.charAt(i);
//            if (c=='('||c=='{'||c=='['){
//                stack.push(c);
//            }else{
//                if (stack.isEmpty()){
//                    System.out.println("False");
//                }
//                char cc=stack.pop();
//                if (c==')'&& cc!='('){
//                    System.out.println("False");
//                }
//                if (c=='}'&& cc!='{'){
//                    System.out.println("False");
//                }
//                if (c==']'&& cc!='['){
//                    System.out.println("False");
//                }
//            }
//        }
//        if (stack.isEmpty()){
//            System.out.println("True");
//        }else{
//            System.out.println("False");
//        }
    }
}
