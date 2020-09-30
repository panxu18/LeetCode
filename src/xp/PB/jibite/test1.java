package xp.PB.jibite;

import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/9/25 - 21:09
 */
//质因数个数
public class test1 {

//    public static int max = 1000000;
//    public static boolean[] a = new boolean[max];
//
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int i,j;
//        for(i = 2;i<max;i++){
//            if(a[i] == false)
//                for(j = i+i;j<max;j+=i)
//                    a[j] = true;
//        }
//        int count = 0;
//        int n = scan.nextInt();
//        for(i=2;i<=n/2;i++){
//            if(a[i]==false){
//                if(n%i==0)
//                    count++;
//            }
//        }
//        System.out.println(count+1);
//
//    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        int n = scan.nextInt();
        for(int i =2;i<=Math.sqrt(n);i++){
            while(n%i==0){
                count++;
                n/=i;
            }
        }
        if(n>1) count++;
        System.out.println(count);

    }
}
