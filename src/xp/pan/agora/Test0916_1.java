package xp.pan.agora;

import java.util.Scanner;

/**
 * 除法模拟
 */
public class Test0916_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int N = in.nextInt();
        System.out.println(solve(N));
    }

    private static int solve(int n)  {
        int x = 1;
        int ans = 1;
        while (x != 0) {
            while (x < n) {
                x = x * 10 + 1;
                ans++;
            }
            x = x % n;
        }
        return ans;
    }
}
