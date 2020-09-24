package xp.oj.search;

import java.io.PrintWriter;
import java.util.Scanner;

public class Poj2676 {
    private static int[][] map = new int[10][10];
    private static boolean[][] row = new boolean[10][10];
    private static boolean[][] col = new boolean[10][10];
    private static boolean[][] grid = new boolean[10][10];

    private static void main() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            map = new int[10][10];
            row = new boolean[10][10];
            col = new boolean[10][10];
            grid = new boolean[10][10];

            for (int j = 1; j < 10; j++) {
                char[] charArr = in.nextLine().toCharArray();
                for (int k = 1; k < 10; k++) {
                    int q = charArr[k - 1] - '0';
                    if (q != 0) {
                        map[j][k] = q;
                        row[j][q] = col[k][q] = grid[getGrid(j, k)][q] = true;
                    }
                }
            }
            if (dfs(1,1)) {
                for (int j = 1; j < 10; j++) {
                    for (int k = 1; k < 10; k++) {
                        out.print(map[j][k]);
                    }
                    out.print("\n");
                }
            }
        }
        out.flush();
    }

    private static int getGrid(int x, int y) {
        int k1 = (x - 1) / 3;
        int k2 = (y + 2) / 3;
        return 3 * k1 + k2;
    }

    private static  boolean dfs(int x, int y) {
        if (x == 10) return true;

        boolean flag;
        if (map[x][y] != 0) {
            if (y == 9) return dfs(x + 1, 1);
            else return dfs(x, y + 1);
        } else {
            int k = getGrid(x, y); // 块索引
            for (int i = 1; i <= 9; i++) {
                if (!row[x][i] && !col[y][i] && !grid[k][i]) {

                    map[x][y] = i;
                    row[x][i] = col[y][i] = grid[k][i] = true;
                    if (y == 9) flag = dfs(x + 1, 1);
                    else flag = dfs(x, y + 1);
                    if (flag) return flag;
                    map[x][y] = 0;
                    row[x][i] = col[y][i] = grid[k][i] = false;
                }
            }
        }
        return false;

    }
}
