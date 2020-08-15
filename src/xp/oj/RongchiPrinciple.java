package xp.oj;

import java.util.Scanner;

public class RongchiPrinciple {

    static int n,m;
    static int[] arr;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        arr = new int[m];
        for (int i = 0; i < m; i++)
            arr[i] = scan.nextInt();
        System.out.println(solve());
    }

    /**
     * 枚举所有的组合数，求最小公倍数，最小公倍数就代表这个组合的并集
     * 然后利用容斥原理公式计算
     */
    private static int solve(){

        int res = 0;
        //枚举
        for (int i = 1; i < (1 << m); i++) {
            int num = 0;
            //计算符号
            for (int j = i; j > 0; j >>= 1) num += j&1;
            int lcm = 1;
            // 计算最小公倍数
            for (int j = 0; j < m; j++) {
                if ((i >> j & 1) == 0) continue;
                int a = arr[j];
                lcm = lcm / gcd(lcm, a) * a;
                if (lcm > n) break;
            }

            if ((num & 1) == 1)
                res += n/lcm;
            else
                res -= n/lcm;
        }
        return res;
    }

    // 计算最大公约数
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }
}
