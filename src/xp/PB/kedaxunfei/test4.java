package xp.PB.kedaxunfei;

import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/9/12 - 19:53
 */
public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = sc.nextInt();
        if(" ".equals(str)||str==null){
            System.out.println(" ");
        }
        int m=str.length();
        n=n%m;
        String s1 = str.substring(0, n);
        String s2 = str.substring(n,str.length());
        String s3 = s2+s1;
        System.out.println(s3);
    }
}
