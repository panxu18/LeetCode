package xp.pan.tiantianjijin;

public class Test0929_2 {
    private static int[][] dp = new int[11][1005];
    private static int N, M;

    public static void main(String[] args) {
        N = 10;
        M = 2;
        dp[0][0] = 1;
        for (int j = 1; j <= M; j++) {
            for (int i = 1; i <= N; i++) {
                if (i >= j) {
                    dp[j][i] = dp[j][i - j] + dp[j - 1][i - j];
                }
            }
        }
        System.out.println(dp[M][N]);
    }


}
