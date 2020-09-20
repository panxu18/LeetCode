package xp.pan.dada;

import java.util.Scanner;

public class Test0920_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long ans = n;
        while (ans > 10) {
            long temp = ans;
            ans = 0;
            while (temp > 0) {
                ans += temp % 10;
                temp /= 10;
            }
        }
        System.out.println(ans);
    }
}
