package xp.pan.didi;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 螺旋数组
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long[][] ans = new long[9][9];
        int n = in.nextInt();
        long[] fabonace = initFibonace();
        int limit = n*n - 1;
        int cnt = 0;
        int x1 = 0;
        int y1 = 0;
        int x2 = n - 1;
        int y2 = n - 1;
        for (;cnt <= limit && x1<= x2 && y1<=y2;) {

            int x = x1;
            int y = y1;
            for (x = x1; cnt <= limit&& x <= x2; x++) {
                ans[y][x] = fabonace[limit - cnt];
                cnt++;
            }
            y = y1 + 1;
            x = x2;
            for (;cnt <= limit&& y <= y2; y++) {
                ans[y][x] = fabonace[limit - cnt];
                cnt++;
            }
            x = x2 - 1;
            y = y2;
            for (; cnt <= limit && x >= x1; x--) {
                ans[y][x] = fabonace[limit - cnt];
                cnt++;
            }

            x = x1;
            y = y2 - 1;
            for (; cnt <= limit && y > y1; y--) {
                ans[y][x] = fabonace[limit - cnt];
                cnt++;
            }
            x1 += 1;
            y1+=1;
            x2 -=1;
            y2-=1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                System.out.printf("%d ", ans[i][j]);
            }
            System.out.println(ans[i][n - 1]);
        }

    }



    private static long[] initFibonace() {
        long[] res = new long[81];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < 81; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res;
    }
}
