package xp.ld;

import java.util.Scanner;

/**
 * 螺旋数组
 */
public class Lanqiao7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int r = in.nextInt();
        int c = in.nextInt();

        int[][] map = new int[n][m];

        int x1 = 0;
        int y1 = 0;
        int x2 = n - 1;
        int y2 = m - 1;
        int cnt = 1;

        while (x1 <= x2 && y1 <= y2) {
            for (int i = y1; i <= y2; i++) {
                map[x1][i] = cnt++;
            }
            x1++;
            for (int i = x1; i <= x2; i++) {
                map[i][y2] = cnt++;
            }
            y2--;
            for (int i = y2; x1 <= x2 && i >= y1; i--) {
                map[x2][i] = cnt++;
            }
            x2--;
            for (int i = x2; y1 <= y2 && i >= x1; i--) {
                map[i][y1] = cnt++;
            }
            y1++;
            if (map[r - 1][c - 1] > 0) {
                System.out.println(map[r - 1][c -1]);
                break;
            }
        }

    }
}
