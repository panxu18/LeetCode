package xp.pan.qunar;

import java.util.Arrays;
import java.util.Scanner;

public class Test0923_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String[] arr1 = in.nextLine().split(" ");
        String[] arr2 = in.nextLine().split(" ");

        int[][] dp = new int[2][n + 1];
        // dp[i][j] = max(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])
        Arrays.fill(dp[0], 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr1[i-1].equals(arr2[j-1])) {
                    dp[i & 1][j] = dp[(i - 1) & 1][j - 1] + 1;
                } else {
                    dp[i & 1][j] = Math.max(dp[(i - 1) & 1][j], dp[i & 1][j - 1]);
                }
            }
        }
        System.out.println(dp[n&1][n]);
    }
}
