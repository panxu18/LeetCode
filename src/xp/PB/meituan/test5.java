package xp.PB.meituan;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class test5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] arr = new int[n][3];
        for(int i=0;i<n;i++){
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
            arr[i][2] = i+1;
        }
        Arrays.sort(arr,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1])
                    return o2[0]-o1[0];
                else
                    return o2[1]-o1[1];
            }
        });
        for(int i=0;i<n;i++){
            System.out.println(arr[i][2]+" ");
        }

    }
}
