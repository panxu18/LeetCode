package xp.pan.rong360;

import java.util.Scanner;

/**
 * 计算螺旋数组最后一个数的坐标
 */
public class Test0908_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        int[][] map = new int[N][M];
        int x1 = 0;
        int y1 = 0;
        int x2 = N - 1;
        int y2 = M - 1;
        int cnt = 1;
        int max = N * M;
        int resX = 0, resY = 0;
        while (x1 <= x2 && y1 <= y2) {
            //上边
            for (int i = y1; i <= y2; i++) {
                map[x1][i] = cnt++;
                resX = x1;
                resY = i;
            }
            // 右边
            for (int i = x1 + 1; cnt <= max && i <= x2 ; i++) {
                map[i][y2] = cnt++;
                resX = i;
                resY = y2;
            }
            // 下边
            for (int i = y2 - 1; cnt <= max && i >= y1 ; i--) {
                map[x2][i] = cnt++;
                resX = x2;
                resY = i;
            }
            // 左边
            for (int i = x2 - 1; cnt <= max && i > x1 ; i--) {
                map[i][y1] = cnt++;
                resX = i;
                resY = y1;
            }
            x1++;
            y1++;
            x2--;
            y2--;
        }
        System.out.printf("%d %d\n", resX, resY);

    }
}
