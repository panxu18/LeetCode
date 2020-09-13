package xp.PB.kedaxunfei;

import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/9/12 - 19:49
 */
public class test3 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

           // if(n==0) System.out.print(0);
            int count = 0;
            if(n>0) count = Integer.bitCount(n);
            if(n<0) count = Integer.bitCount(n)+1;
            System.out.println(count);

        }


}
