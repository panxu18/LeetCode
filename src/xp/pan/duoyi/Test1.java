package xp.pan.duoyi;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 检测1000以内的素数，且各位之和为偶数
 */
public class Test1 {
    static boolean[] isPirme = new boolean[2000];
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        init();
        for (int i = 2; i < 1000; i++) {
            if (isPirme[i] && check(i)) {
                out.printf("%d ", i);
            }
        }
        out.flush();
    }
    private static void init() {
        Arrays.fill(isPirme, true);
        for (int i = 2; i * i< 2000; i++) {
            if (isPirme[i]) {
                for (int j = i + i; j < 2000; j += i) {
                    isPirme[j] = false;
                }
            }
        }
    }

    private static boolean check(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i%10;
            i /= 10;
        }
        return (sum & 1) == 0;
    }
}
