package xp.ld;

import java.util.Scanner;

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
        int max = n * m;

        while (x1 <= x2 && y1 <= y2) {
            //上边
            for (int i = y1; i <= y2; i++) {
                map[x1][i] = cnt++;
            }
            // 右边
            for (int i = x1 + 1; cnt <= max && i <= x2 ; i++) {
                map[i][y2] = cnt++;
            }
            // 下边
            for (int i = y2 - 1; cnt <= max && i >= y1 ; i--) {
                map[x2][i] = cnt++;
            }
            // 左边
            for (int i = x2 - 1; cnt <= max && i > x1 ; i--) {
                map[i][y1] = cnt++;
            }
            x1++;
            y1++;
            x2--;
            y2--;
            if (map[r - 1][c - 1] > 0) {
                System.out.println(map[r - 1][c -1]);
                break;
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%minDis ", input[i][j]);
//            }
//            System.out.print("\N");
//        }
//
//        System.out.println(input[r - 1][c -1]);
    }
}
