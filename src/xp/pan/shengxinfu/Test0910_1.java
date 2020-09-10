package xp.pan.shengxinfu;

import java.util.Scanner;

public class Test0910_1 {
    private static long ans = Long.MAX_VALUE;
    private static int row = 4, col = 4;
    private static long[][] map = new long[4][4];
    private static boolean[][] used = new boolean[4][4];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = in.nextLong();
            }
        }
        dfs(0, 0, 0);
        System.out.println(ans);
    }

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static void dfs(int x, int y, long sum) {
        if (x == 3 && y == 3 || sum >= ans) {
            ans = Math.min(ans, sum + map[x][y]);
            return;
        }
        used[x][y] = true;
        sum += map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + x;
            int ny = dir[i][1] + y;
            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !used[nx][ny]) {
                dfs(nx, ny, sum);
            }
        }
        used[x][y] = false;
    }
}
