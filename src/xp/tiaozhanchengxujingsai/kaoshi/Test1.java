package xp.tiaozhanchengxujingsai.kaoshi;

import java.io.*;

public class Test1 {

    StreamTokenizer in = new StreamTokenizer(
            new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Test1().solve();
    }

    int INF = 1000000007;
    int N, M, K;
    int ans = 0;
    private void solve() throws IOException {

        N = read();
        for (int i = 0; i < N; i++) {
            int a = read();
            if (a >= 2) {
                ans += (a>>1);
            }
        }
        out.println(ans);
        out.flush();
    }

}
