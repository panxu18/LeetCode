package xp.leetcode;

public class LeetCode79 {
    public static void main(String[] args) {
//        char[][] board =
//                {{'A', 'B', 'C', 'E'},
//                        {'S', 'F', 'C', 'S'},
//                        {'A', 'D', 'E', 'E'}};
        char[][] board = {{'a'}};
        System.out.println(new LeetCode79().exist(board, "a"));
    }

    public boolean exist(char[][] board, String word) {
        char[] expectedArr = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, expectedArr, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static boolean[][] used = new boolean[205][205];

    private static boolean dfs(char[][] board, int x, int y, char[] arr, int depth) {
        if (depth == arr.length) {
            return true;
        }
        if (x < 0 || x >= board.length
                || y < 0 || y >= board[0].length
                || used[x][y]) {
            return false;
        }
        used[x][y] = true;
        if (board[x][y] == arr[depth]) {
            for (int i = 0; i < 4; i++) {
                int nx = x + direct[i][0];
                int ny = y + direct[i][1];
                if (dfs(board, nx, ny, arr, depth + 1)) {
                    used[x][y] = false;
                    return true;
                }
            }
        }
        used[x][y] = false;
        return false;
    }
}
