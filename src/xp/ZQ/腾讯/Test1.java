package xp.ZQ.腾讯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            map.put(num, 1);
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int num = sc.nextInt();
            if (map.containsKey(num)) {
                res.add(num);
            }
        }
        for (Integer a : res) {
            System.out.print(a + " ");
        }
    }
}
