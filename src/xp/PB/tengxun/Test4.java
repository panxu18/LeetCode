package xp.PB.tengxun;


import java.util.Arrays;
import java.util.Scanner;

public class Test4 {
//    public static void main(String[] args)  {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        //int[] list = new int[n];
//        //int[] res = new int[n];
//        StringBuilder str = new StringBuilder();
//        StringBuilder ans = new StringBuilder();
//        for(int i=0;i<n;i++){
//            str.append(scan.nextInt());
//          //  list[i] = scan.nextInt();
//        }
//        for(int i = 0;i<n;i++){
//            if(i<n/2){
//                ans.append(str.charAt(n/2));
//            }else{
//                ans.append(str.charAt(n/2-1));
//            }
//        }
////        for (int i:res) {
////            System.out.println(i);
////        }
////        for(int i = 0;i<n;i++){
////            if(i<n/2){
////                res[i] = list[n/2];
////            }else{
////                res[i] = list[n/2-1];
////            }
////
////        }
//        for(int i = 0;i<n;i++){
//            System.out.println(ans.charAt(i));
//        }
//
//
//    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Long[] list=new Long[n];
        for(int i=0;i<list.length;i++){
            list[i]=scan.nextLong();
        }
        Arrays.sort(list);
        for(int i=0;i<n/2;i++){
            System.out.println(list[n/2-1]);
        }
        for(int i=n/2;i<n;i++){
            System.out.println(list[n/2]);
        }

    }
}
