package xp.pan.xiaomi;

import java.util.Scanner;

public class Test0908_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split(" ");
        for (String str : strs) {
            System.out.println(check(str));
        }

    }

    private static int check(String str) {
        if (str.length() < 8 || str.length() > 120) {
            return 1;
        }
        if (!str.matches(".*[^\\dA-Za-z].*")||!str.matches(".*\\d.*") || !str.matches(".*[a-z].*") || !str.matches(".*[A-Z].*")
                ) {
            return 2;
        }
        return 0;
    }
}
