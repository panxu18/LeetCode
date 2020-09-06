package xp.pan.sougou;

import java.util.Arrays;
import java.util.Scanner;

public class Test0905_3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(solve(in.nextLine()));
    }

    private static long solve(String origin) {
        char[] charArray = origin.toCharArray();
        int[] arr = new int[charArray.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = charArray[i] - '0';
        }

        int N = arr.length;
        long[][] dp = new long[N][10];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 10; j++) {
                int sum = j + arr[i];
                if ((sum & 1) == 0) {
                    dp[i + 1][sum >> 1] += dp[i][j];
                } else {
                    dp[i + 1][(sum + 1) >> 1] += dp[i][j];
                    dp[i + 1][(sum - 1) >> 1] += dp[i][j];
                }
            }
        }
        long ans = 0L;
        for (int i = 0; i < 10; i++) {
            ans += dp[N - 1][i];
        }
        return ans;
    }

}
