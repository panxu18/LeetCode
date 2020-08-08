package xp.ld;

import java.util.Scanner;

public class Lanqiao5 {
    public static void main(String[] args) {
        System.out.println(sovle());
    }

    static int sovle() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        int res = n;
        for (int i = 1; i <= n; i++) {
            if (i % a == 0 || i % b == 0 || i % c == 0)
                res--;
        }
        return res;
    }

    static int getLeast(int a, int b) {
        int d = gcd(a, b);
        return d * (a / d) * (b / d);
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
