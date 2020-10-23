package xp.pan.xiecheng;

import java.util.Scanner;

public class Test1013_2 {
    private static int[][] incomes = new int[1007][2];
    private static int cnt = 0;

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            incomes[cnt][0] = cin.nextInt();
            incomes[cnt++][1] = cin.nextInt();
        }
        int[] dp = new int[cnt + 1];
        dp[0] = 0;
        for (int i = 1; i <= cnt; i++) {

            for (int j = i; j >= 1; j--) {
                dp[j] = Math.max(dp[j - 1] + incomes[i - 1][0], dp[j] + incomes[i - 1][1]);
            }
            dp[0] = dp[0] + incomes[i - 1][1];
        }
        System.out.println(dp[cnt >> 1]);
    }
}
