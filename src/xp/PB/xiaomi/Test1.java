package xp.PB.xiaomi;

/**
 * @auther Peng
 * @date 2020/9/8 - 18:30
 */
public class Test1 {

    //查找方向
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    //是否到达标记数组
    private boolean[][] arrived;

        private int m;
        private int n;
        private String word;
        private char[][] board;

        public boolean exist(char[][] board, String word) {
            m = board.length;
            if (m == 0) {
                return false;
            }
            n = board[0].length;
            arrived = new boolean[m][n];
            this.word = word;
            this.board = board;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (deepFirstSerach(i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        //深度优先搜索
        private boolean deepFirstSerach(int i, int j, int start) {
            if (start == word.length() - 1) {
                return board[i][j] == word.charAt(start);
            }
            if (board[i][j] == word.charAt(start)) {
                arrived[i][j] = true;
                for (int k = 0; k < 4; k++) {
                    int newX = i + direction[k][0];
                    int newY = j + direction[k][1];
                    if (inArea(newX, newY) && !arrived[newX][newY]) {
                        if (deepFirstSerach(newX, newY, start + 1)) {
                            return true;
                        }
                    }
                }
                arrived[i][j] = false;
            }
            return false;
        }

        private boolean inArea(int x, int y) {
            if(x >= 0 && x < m && y >= 0 && y < n){
                return true;
            }else{
                return false;
            }
        }

        public static void main(String[] args) {

        char[][] board =
                {
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                };

      // String word = "ABCCED";


//            char[][] board = {{'a', 'b'}};
            String word = "SEE";

            boolean exist = new Test1().exist(board, word);
            System.out.println(exist);
        }

}
