package xp.pan.jinshanyun;

import java.util.Scanner;

public class Test0922_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int ans = 0;
        for (int i = 1; i <= 1005; i++) {
            int pow3 = i * i * i;
            if (a <= pow3 && pow3 <= b && check(i)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean check(int a) {
        int b = a * a * a;
        while (a > 0) {
            if (b % 10 != a % 10) {
                return false;
            }
            b /= 10;
            a /= 10;
        }
        return true;
    }
}
