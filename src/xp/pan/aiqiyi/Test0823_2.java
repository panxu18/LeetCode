package xp.pan.aiqiyi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test0823_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] array = str.toCharArray();
        add(0, 0);
        int x = 0;
        int y = 0;
        for (int i = 0; i < array.length; i++) {
            switch (array[i]) {
                case 'E':
                    x++;
                    break;
                case 'W':
                    x--;
                    break;
                case 'N':
                    y++;
                    break;
                case 'S':
                    y--;
                    break;
            }
            if (!add(x, y)) {
                System.out.println("True");
                return;
            }
        }
        System.out.println("False");
    }

    private static Set<String> set = new HashSet<>();
    private static boolean add(int x, int y) {
        return set.add(String.format("%d,%d", x, y));
    }
}
