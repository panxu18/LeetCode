package xp.pan.xiaomi;

import java.util.Scanner;

public class Test0908_2 {
    private static char[][] charMap = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
    private static char[] charArr;
    private static int row = 3;
    private static int col = 4;
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        charArr = in.nextLine().toCharArray();
        if (charArr.length == 0) {
            System.out.println("false");
            return;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(i, j, 0)) {
                    System.out.println("true");
                    return;
                }
            }
        }
        System.out.println("false");
        return;
    }

    private static boolean[][] used = new boolean[10][10];
    private static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1},};
    private static boolean dfs(int x, int y, int d) {
        if (d == charArr.length - 1) {
            return charMap[x][y] == charArr[d];
        }
        if (charMap[x][y] != charArr[d]) {
            return false;
        }
        used[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx >= 0 && nx < row && ny >= 0 && ny < col && used[nx][ny] == false && dfs(nx, ny, d+1)) {
                return true;
            }
        }
        used[x][y] = false;
        return false;
    }
}
