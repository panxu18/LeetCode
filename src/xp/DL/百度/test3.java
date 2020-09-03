package xp.DL.百度;

import java.util.ArrayList;
import java.util.Scanner;

public class test3 {
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        helper(m, n, new ArrayList<>(), 1);
        System.out.println(count);
    }

    private static void helper(int m, int steps, ArrayList<Integer> list, int index) {
        if (steps == 0) {
            count++;
            return;
        }
        if (steps < 0) {
            return;
        }
        for (int i = 1; i <= m; i++) {
            if (list.size() >= 1 && list.get(list.size() - 1) == i) {
                continue;
            }
            if (list.size() >= 2 && list.get(list.size() - 2) == i) {
                continue;
            }
            steps = steps - i;
            list.add(i);
            helper(m, steps, list, i);
            list.remove(list.size() - 1);
        }
    }
}

