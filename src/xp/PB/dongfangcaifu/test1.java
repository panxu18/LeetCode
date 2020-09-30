package xp.PB.dongfangcaifu;


import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        System.out.println(zuhe(num));

    }

    public static int zuhe(int x){
        int res = 0;
        int sum=0;
        int[] a = {1,2,5,10,20};
        for(int i = 0; i<=x/a[4];i++){
            for(int j = 0;j<=x/a[3];j++){
                for(int k = 0;k<=x/a[2];k++){
                    for(int l=0;l<=x/a[1];l++){
                        int  m = x-(i*a[4]+j*a[3]+k*a[2]+l*a[1]);
                        sum = i*a[4]+j*a[3]+k*a[2]+l*a[1]+m*a[0];
                        if(sum == x&&m>=0){
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
