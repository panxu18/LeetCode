package xp.pan.pdd;

import java.util.Scanner;

public class Test1023_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        double a = in.nextDouble();
        double b = in.nextDouble();
        if (n == 2) {
            double p1 = 1.0;
            double p2 = 0.0;
            double ans = 1.0;
            for (int i = 2; i <= m; i++) {
                double np1 = p1 * (1.0 - a) + p2 * b;
                double np2 = p1 * a + p2 * (1.0 - b);
                p1 = np1;
                p2 = np2;
                ans += p1;
            }
            System.out.printf("%.8f", ans);
        } else if (n == 3) {
            double p1 = 1.0;
            double p2 = 0.0;
            double p3 = 0.0;
            double ans = 1.0;
            for (int i = 2; i <= m; i++) {
                double np1 = p1 * (1.0 - a) + p2 * b * (1.0 - a) + p3 * b * b;
                double np2 = p1 * a + p2 * (1.0 - b) * (1.0  -a) + p2 * b * a + p3 * b * (1.0 - b) * 2.0;
                double np3 = p2 * (1.0 - b) * a + p3 * (1.0 - b) * (1.0 - b);
                p1 = np1;
                p2 = np2;
                p3 = np3;
                ans += p1;
            }
            System.out.printf("%.8f", ans);
        }


    }
}
