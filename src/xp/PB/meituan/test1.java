package xp.PB.meituan;


import java.util.Scanner;

public class test1 {
    public static int[][] arr = new int[100010][6];
    public static int n,m;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        int min = n;
        for(int i=0;i<n;i++){
            for(int j = 0;j<m;j++){
                arr[i][j] = scan.nextInt();
            }
        }
        if(n%2==1){
            for(int i=0;i<m;i++){
                System.out.print(arr[0][i]+" ");
            }
        }else{
            int mid = n/2;
            while (isDuiCheng(mid)&&mid!=0){
                min = Math.min(min,mid);
                mid = mid/2;
            }
            for(int i=0;i<min;i++){
                for(int j = 0;j<m;j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }

        }
    }

    public static boolean isDuiCheng(int k){
        boolean isDC =true;
        boolean xt = true;
        boolean dc = true;
        int start1 = 0;
        int start2 = k;
        for(int i=0;i<k;i++){
            for(int j = 0;j<m;j++){
                if(arr[start1+i][j]!=arr[start2+i][j]){
                    xt = false;
                }
                if(arr[start1+i][j]!=arr[k+k-1-i][j]){
                    dc = false;
                }
            }
        }
        if(xt==false&&dc==false){
            isDC = false;
        }
        return isDC;
    }
}
