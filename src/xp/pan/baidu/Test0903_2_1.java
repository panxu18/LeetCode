package xp.pan.baidu;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class Test0903_2_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        for (int t = 0; t < c; t++) {
            int n = in.nextInt();
            int m = in.nextInt();
            BitSet[] sets = new BitSet[m];
            for (int i = 0; i < m; i++) {
                sets[i] = new BitSet(n + 1);
            }
            for (int i = 0; i < m; i++) {
                int k = in.nextInt();
                for (int j = 0; j < k; j++) {
                    sets[i].set(in.nextInt(), in.nextInt() + 1);
                }
                sets[0].and(sets[i]);
            }
            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (sets[0].get(i)) {
                    res.add(i);
                }
            }
            System.out.println(res.size());
            StringBuilder sb = new StringBuilder();
            for (Integer integer : res) {
                sb.append(integer).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }
    }
}
