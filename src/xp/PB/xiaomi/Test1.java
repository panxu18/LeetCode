package xp.PB.xiaomi;


import java.util.Scanner;

public class Test1 {
    //网格
    public char[][] grid =
            {
                    {'A', 'B', 'C', 'E'},
                    {'S', 'F', 'C', 'S'},
                    {'A', 'D', 'E', 'E'}
            };

    //查找方向
    public int[][] direction = {{0, 1}, {1, 0}, {0, -1},};
    //是否到达标记数组
    public boolean[][] arrived;
    //网格的长和宽
    public int length,width;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        if(str.length()==0){
            System.out.println(false);
        }
        System.out.println(new Test1().isExist(str));
    }
    public boolean isExist(String word) {
        width = grid[0].length;
        length = grid.length;
        arrived = new boolean[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (deepFirstSerach(i, j, 0,word)) {
                    return true;
                }
            }
        }
        return false;
    }
    //深度优先搜索
    private boolean deepFirstSerach(int i, int j, int start,String word) {
        if (start == word.length() - 1) {
            return grid[i][j] == word.charAt(start);
        }
        if (grid[i][j] == word.charAt(start)) {
            arrived[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int currx = i + direction[k][0];
                int curry = j + direction[k][1];
                if (isInOrOut(currx, curry) && !arrived[currx][curry]) {
                    if (deepFirstSerach(currx, currx, start + 1,word)) {
                        return true;
                    }
                }
            }
            arrived[i][j] = false;
            System.gc();
        }
        return false;
    }
    //判断是否在网格中
    private boolean isInOrOut(int x, int y) {
        if (x >= 0 && x < length && y >= 0 && y < width) {
            return true;
        } else {
            return false;
        }
    }
}
