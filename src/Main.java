import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        for (int i = 1000; i < 9999; i++) {
            if (i * 9 == revers(i)) {
                System.out.println(i);
            }
        }
        int[] arr = {};
        HashSet<Integer> set;





    }

    public static int revers(int i) {
        int res = 0;
        while (i > 0) {
            res *= 10;
            res += i %10;
            i /= 10;
        }
        return res;
    }
}