package xp.ZQ;

import java.util.Scanner;

public class Test22 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int count =0;
        while(n>=5){
            int temp=n/5;
            count=count+temp;
            n=n/5;
        }
        System.out.println(count);
    }
}
