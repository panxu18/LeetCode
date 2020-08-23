package xp.oj.shulun;

import java.util.Scanner;

/**
 * 欧几里得
 */
public class Euclid {

    static int n, m, c;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        c = scan.nextInt();
        for (int i = 1; i <= 20; i++)
            solve(n,m,i);
//        System.out.println(solve(N, m, c));
    }

    static int gcd (int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    static int extgcd(int a, int b, int[] x, int[] y) {
        if (b == 0){
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            int d = extgcd(b, a%b, y, x);
            y[0] -= (a/b) * x[0];
            return d;
        }
    }

    static int solve(int n, int m, int c) {
        int[] x = new int[1], y = new int[1];
        int res = extgcd(n, m, x, y);
        int s1 = m/res, s2 = n/res;
        x[0] = x[0] % s1;
        if (x[0] < 0)
            x[0] += s1;
        y[0] = (res - x[0] * n) / m;

        x[0] *= c;
        y[0] *= c;

        int k = (0 -  y[0] + s2 - 1) / s2;
        x[0] -= k * s1;
        y[0] += k * s2;

        k = (x[0] + s1) / s1;


        System.out.println(k);
        return res;
    }
}
