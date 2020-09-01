package xp.pan.tenxun;

import java.util.Scanner;

/**
 * 贪心
 * 给一个数N，将其分解为两个数a、b, N = a + b, 计算a和b各位数之和最大值。
 * 又题意可知，将整数分解为99999... + x 能得到最大值。
 */
public class Test0823_03 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            if (n < 9) {
                System.out.println(n);
                continue;
            }
           long a = 9;
           while (a < n) {
               if (a * 10 + 9 <= n) {
                   a = a * 10 + 9;
               }
           }
            System.out.println(countDigest(a) + countDigest(n - a));
        }
    }

    private static int countDigest(long x) {
        int res = 0;
        while (x > 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }
}
