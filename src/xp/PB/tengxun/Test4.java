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

//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        String str = scan.nextLine();
//        String[] arr = str.split(" ");
//        long[] list = new long[n];
//        for (int i=0;i<n;i++) {
//            list[i] = Long.parseLong(arr[i]);
//        }
//        long[] list2 = Arrays.copyOf(list,n);
//        Arrays.sort(list);
//        int index = (n - 1) /2; // 中位数索引
//        long mid = list[index]; // 中位数
//        for (int i = 0; i < n; i++) {
//            if (list[i] > mid) {
//                System.out.println(mid);
//            } else {
//                System.out.println(arr[index + 1]);
//            }
//        }
//
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Long[] array=new Long[n];
        for(int i=0;i<array.length;i++){
            array[i]=sc.nextLong();
        }
        Long[] array2=new Long[n];
        array2 = Arrays.copyOf(array, n);
        Arrays.sort(array);
        int index = (n - 1) >> 1; // 中位数索引
        long mid = array[index]; // 中位数
        for (int i = 0; i < n; i++) {
            if (array2[i] > mid) {
                System.out.println(mid);
            } else {
                System.out.println(array2[index + 1]);
            }
        }
    }
}
