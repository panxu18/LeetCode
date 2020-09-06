package xp.ZQ.腾讯;

import java.util.Arrays;
import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Long[] array=new Long[n];
        for(int i=0;i<array.length;i++){
            String temp=sc.next();
            Long tem=Integer
            array[i]=temp;
        }
        Arrays.sort(array);
        for(int i=0;i<n/2;i++){
            System.out.println(array[n/2-1]);
        }
        for(int i=n/2;i<n;i++){
            System.out.println(array[n/2]);
        }
    }
}