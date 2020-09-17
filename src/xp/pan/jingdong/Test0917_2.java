package xp.pan.jingdong;

import java.util.Arrays;
import java.util.Scanner;

public class Test0917_2 {
    private static char[][] charMap = new char[105][];
    private static int row, col;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {

            row = in.nextInt();
            col = in.nextInt();
            in.nextLine();
            int x = 0, y = 0;
            for (int i = 0; i < row; i++) {
                charMap[i] = in.nextLine().toCharArray();
                for (int j = 0; j < col; j++) {
                    if (charMap[i][j] == 'S') {
                        x = i;
                        y = j;
                    }
                }
            }
            for (int i = 0; i < row; i++) {
                Arrays.fill(used[i], false);
            }
            System.out.println(dfs(x, y) ? "YES" : "NO");
        }
    }

    /**
     * 2
     * 2 2
     * .E
     * S.
     * 2 2
     * #E
     * S#
     */

    private static boolean[][] used = new boolean[105][105];
    private static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1},};

    private static boolean dfs(int x, int y) {
        if (charMap[x][y] == 'E') {
            return true;
        }
        used[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx >= 0 && nx < row && ny >= 0 && ny < col && charMap[nx][ny] != '#'
                    && !used[nx][ny] && dfs(nx, ny)) {
                return true;
            }
        }
        used[x][y] = false;
        return false;
    }
}
