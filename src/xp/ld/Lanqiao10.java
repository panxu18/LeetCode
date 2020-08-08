package xp.ld;

import java.util.Scanner;

public class Lanqiao10 {

    public static void main(String[] args) {
        new Lanqiao10().solve();
    }

    static int[] xs;
    static int[] ys;
    static int[] rs;
    static int[] choose;
    int cnt;
    /**
     * 搜索
     * 策略为n种
     * 约束为1000*1000
     */
    void solve() {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        init(n);
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
            rs[i] = in.nextInt();
        }
        System.out.println(dfs(0, n, 0));
    }

    private void init(int n) {
        xs = new int[n];
        ys = new int[n];
        rs = new int[n];
        choose = new int[n];
    }

    int dfs(int i, int n, int sum) {
        if (i == n) return sum;

        int ans = 0;
        ans = dfs(i + 1, n, sum);

        boolean flag = true;
        for (int j = 0; j < cnt; j++) {
            if (!check(i, choose[j])){
                flag = false;
                break;
            };
        }
        if (flag) {
            choose[cnt++] = i;
            ans = Math.max(ans, dfs(i + 1, n, sum + rs[i] * rs[i]));
            cnt--;
        }
        return ans;

    }

    private boolean check(int a, int b) {
        int d = (xs[a] - xs[b]) * (xs[a] - xs[b]) + (ys[a] - ys[b]) * (ys[a] - ys[b]);
        int d2 = (rs[a] + rs[b]) * (rs[a] + rs[b]);
        if (d >= d2) return true;
        return false;
    }

    class DLX{
        int[] L, R, U, D, ans, row, col, sum, w;
        int n, m, cnt;
        int maxResult;
        void add(int k, int l, int r, int u, int d, int x, int y) {
            L[k] = l;
            R[k] = r;
            U[k] = u;
            D[k] = d;
            row[k] = x;
            col[k] = y;
        }

        void insert(int x, int y, int weight){
            int temp = cnt - 1;
            if (row[temp] == x) {
                add(cnt, temp, R[temp], U[y], y, x, y);
                L[R[cnt]] = cnt;
                R[L[cnt]] = cnt;
                U[D[cnt]] = cnt;
                D[U[cnt]] = cnt;
            } else{
                add(cnt, cnt, cnt, U[y], y, x, y);
                U[D[cnt]] = cnt;
                D[U[cnt]] = cnt;
                w[x] = weight;
            }
            cnt++;
            sum[y]++;
        }

        void reset(int n, int m) {
            this.n = n;
            this.m = m;
            for (int i = 0; i <= m; i++) {
                add(i, i - 1, i + 1, i, i, 0, i);
            }
            L[0] = m;
            R[m] = 0;
            cnt = m + 1;
        }

        void del(int k) {
            L[R[k]] = L[k];
            R[L[k]] = R[k];

            for (int i = D[k]; i != k ; i = D[k]) {
                for (int j = R[i]; j != i ; j = R[j]) {
                    U[D[j]] = U[j];
                    D[U[j]] = D[j];
                    sum[col[j]]--;
                }
            }
        }

        void resume(int k) {
            L[R[k]] = k;
            R[L[k]] = k;
            for (int i = k; i != k ; i = U[k]) {
                for (int j = i; j != i ; j = L[j]) {
                    U[D[j]] = j;
                    D[U[j]] = j;
                    sum[col[j]]++;
                }

            }
        }

        boolean dance(int depth, int result){
            if (R[0] == 0) {
                maxResult = Math.max(maxResult, result);
                return true;
            }
            if (result + getval() < maxResult) {
                return false;
            }

            int now = R[0];
            for (int i = R[0]; i != 0; i = L[i]) {
                if (sum[i] < sum[now])
                    now = i;
            }

            del(now);
            for (int i = D[now]; i != now ; i = D[now]) {
                ans[depth] = row[i];
                for (int j = R[i]; j != i ; j = R[j]) {
                    del(col[j]);
                }
                if (dance(depth + 1, result + w[row[i]])) return true;
                for(int j = L[i]; j != i; j = L[i]){
                    resume(col[j]);
                }
            }
            resume(now);
            return false;
        }

        private int getval() {
            int res = 0;
            int[] del = new int[m + 1];
            for (int i = R[0]; i != 0 ; i = R[i]) {
                if (del[i] == 0) {
                    for (int j = D[i]; j != i ; j = D[j]) {
                        res += w[row[j]];
                        for (int k = R[j]; k != j ; k = R[j]) {
                            del[col[k]] = 1;
                        }
                    }
                }
            }
            return res;
        }
    }
}
