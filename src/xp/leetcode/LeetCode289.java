package xp.leetcode;

public class LeetCode289 {
    private static int[][] dets = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    /**
     *
     * @param board
     * @throws NullPointerException 如果入参为空产生空指针异常
     */
    public void gameOfLife(int[][] board) {
        int lenx = board.length;
        if (lenx == 0)
            return;
        int leny = board[0].length;
        for (int i = 0; i < lenx; i++) {
            for (int j = 0; j < leny; j++) {
                int livies = countLivies(board, i, j);
                if ((board[i][j] & 1) == 1) {
                    if (livies >= 2 && livies <= 3)
                        board[i][j] = 3;
                } else {
                    if (livies == 3)
                        board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
        for (int i = 0; i < lenx; i++) {
            for (int j = 0; j < leny; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    /**
     * 计算细胞周围存活细胞数量
     * @param board 输入数组
     * @param i 横坐标
     * @param j 纵坐标
     * @return 细胞周围存活细胞数量
     */
    private int countLivies(int[][] board, int i, int j) {
        int count = 0;
        for (int[] det: dets) {
            int x = i + det[0];
            int y = j + det[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
                continue;
            count += board[x][y] & 1;
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode289 leetCode289 = new LeetCode289();
        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        leetCode289.gameOfLife(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                 System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }
}
