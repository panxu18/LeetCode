package xp.pan.wangyi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Test0912_1 {
    private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        in.nextToken();

        return (int) in.nval;

    }

    public static void main(String[] args) throws IOException {
        int[] parent = new int[105];
        int M = nextInt();
        int N = nextInt();
        for (int i = 0; i < N; i++) {
            int from = nextInt();
            in.nextToken();
            int to = nextInt();
            parent[to] = from;
        }

        for (int i = 1; i <= M; i++) {
            parent[parent[i]] = 0;
        }

        int[] childCnt = new int[105];
        int ans = 0;
        for (int i = 1; i <= M; i++) {
            if (parent[i] != 0 && ++childCnt[parent[i]] == 2) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
