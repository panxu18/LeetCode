package xp.PB.kedaxunfei;

import java.util.*;

public class test1 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        long[] fMax = new long[n+1];
//        int[] mIndex = new int[n+1];
//        long[] sMax = new long[n+1];
//        int temp = 0;
//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=m;j++){
//                temp = in.nextInt();
//                if(temp>fMax[i]){
//                    sMax[i] = fMax[i];
//                    fMax[i] = temp;
//                    mIndex[i] = j;
//                }else{
//                    if(temp>sMax[i]){
//                        sMax[i] = temp;
//                    }
//                }
//            }
//        }
//        long res = 0;
//        for(int i=1;i<=n-1;i++){
//            for(int j=i+1;j<=n;j++){
//                if(mIndex[i]!=mIndex[j]){
//                    res = Math.max(res,fMax[i]*fMax[j]);
//                }
//                else{
//                    res = Math.max(res,fMax[i]*sMax[j]);
//                    res = Math.max(res,sMax[i]*fMax[j]);
//                }
//            }
//        }
//
//        System.out.println(res);
//
//
//
//
//
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = sc.nextInt();
        String s1 = str.substring(0, n);
        String s2 = str.substring(n,str.length());
        String s3 = s2+s1;
        System.out.println(s3);
    }
}
