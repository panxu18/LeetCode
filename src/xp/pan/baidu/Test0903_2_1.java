package xp.pan.baidu;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class Test0903_2_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCnt = in.nextInt();
        for (int t = 0; t < caseCnt; t++) {
            int N = in.nextInt();
            int M = in.nextInt();
            BitSet[] bitSets = new BitSet[M];
            for (int i = 0; i < M; i++) {
                bitSets[i] = new BitSet(N+1);
            }
            for (int i = 0; i < M; i++) {
                int k = in.nextInt();
                for (int j = 0; j < k; j++) {
                    bitSets[i].set(in.nextInt(), in.nextInt() + 1);
                }
                bitSets[0].and(bitSets[i]);
            }
            ArrayList<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (bitSets[0].get(i)) {
                    resultList.add(i);
                }
            }

            System.out.println(resultList.size());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < resultList.size(); i++) {
                sb.append(resultList.get(i));
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }

    }
}
