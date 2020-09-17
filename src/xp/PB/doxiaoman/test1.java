package xp.PB.doxiaoman;


import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sacn = new Scanner(System.in);
        long n = sacn.nextLong();
        long m = sacn.nextLong();
        long res = 0;
        for(long i=1;i<=3*n;i++){
            res = res+i*m;
        }
        System.out.println(res);
    }
}
