package xp.pan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class test1 {

    static int count = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= N / 4; i++) {
            int j = reverse(i);
            if (i * 4 == j) {
                count++;
                list.add(i + " " + j);
            }
        }
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.println(list.get(i));
        }
    }

    private static int reverse(int a) {
        int c = 0;
        while (a != 0) {
            c = c * 10 + a % 10;
            a /= 10;
        }
        return c;
    }
}
