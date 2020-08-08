package xp.ld;

public class LeetCode73 {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        setZeroesCore(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    private void setZeroesCore(int[][] matrix, int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2)
            return;
        for (int i = y1; i <= y2; i++) {
            if (matrix[x1][i] == 0) {
                setZeroesWithIndex(matrix, x1, i);
                setZeroesCore(matrix, x1 + 1, y1, x2, i - 1);
                setZeroesCore(matrix, x1 + 1, i + 1, x2, y2);
                return;
            }
        }
        setZeroesCore(matrix, x1 + 1, y1, x2, y2);
    }

    private void setZeroesWithIndex(int[][] matrix, int x, int y) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[x][i] = 0;
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][y] = 0;
        }
    }
public LeetCode300 m;
    public static void main(String args[]) {
        LeetCode289 n = new LeetCode289() ;
        try {
            Thread.sleep(1000* 3600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
