package xp.pan.baidu;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test0903_2_1 {
    private static int T, N, M;
    private static int MAXN = 1010;
    private static BitSet[] bitSets = new BitSet[11];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for (int t = 0; t < T; t++) {
            N = in.nextInt();
            M = in.nextInt();
            for (int i = 0; i < M; i++) {
                bitSets[i] = new BitSet(1010);
            }
            for (int i = 0; i < M; i++) {
                int k = in.nextInt();
                for (int j = 0; j < k; j++) {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    bitSets[i].set(l, r+1);
                }
            }
            for (int i = 1; i < M; i++) {
                bitSets[0].and(bitSets[i]);
            }

            int[] result = bitSets[0].stream().toArray();

            System.out.println(result.length);
            System.out.println(Arrays.stream(result)
                    .mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }

    }
}
