package xp.PB.jinshanyun;


import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n+1];
        int[][] res = new int[2*n][2*n];
        arr[0] = 1;
        arr[1] = 1;
        if(n>=2){
            for(int i = 2 ;i<arr.length;i++){
                arr[i] = arr[i-1]+arr[i-2];
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<i+1;j++){
                res[i][j] = arr[j];
                System.out.print(res[i][j]+" ");
            }
            for(int j=i;j>0;j--){
                res[i][j] = arr[j-1];
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }



    }
}
