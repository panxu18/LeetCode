package xp.oj.search;

import java.util.Arrays;

/**
 *
 */
public class DLX {

    int[] L, R, U, D;
    int[] row, col, ans;
    int[] sum; // 各列中的元素个数
    int n, m, resultCount;
    int cnt; // 元素计数
    private int minResultCount = -1;

    int MAXN = 1000005;

    DLX(int maxn) {
        L = new int[maxn];
        R = new int[maxn];
        U = new int[maxn];
        D = new int[maxn];
        row = new int[maxn];
        col = new int[maxn];
        ans = new int[maxn];
        sum = new int[maxn];
    }

    /**
     * 添加节点
     * @param k 节点编号
     * @param l 左节点编号
     * @param r 右节点编号
     * @param u 上节点编号
     * @param d 下节点编号
     * @param x 节点行号
     * @param y 节点列号
     */
    void add(int k, int l, int r, int u, int d, int x, int y) {
        L[k] = l;
        R[k] = r;
        U[k] = u;
        D[k] = d;
        row[k] = x;
        col[k] = y;
    }

    /**
     * 初始化，重新构建第一行元素
     * @param n 行数
     * @param m 列数
     */
    void reset(int n, int m) {
        this.n = n;
        this.m = m;
        for (int i = 0; i <= m; i++) {
            add(i, i - 1, i + 1, i, i, 0, i);
            sum[i] = 0;
        }
        L[0] = m;
        R[m] = 0;
        cnt = m + 1;
    }

    /**
     * 按照行列递增的顺序插入元素，在x行y列插入一个新元素
     *
     * @param x 插入行号
     * @param y 插入列号
     */
    void insert(int x, int y) {
        int temp = cnt - 1; // 上一个插入的元素
        if (row[temp] != x) {
            // 在新的一行插入，U[y]表示第y列最后一个元素
            add(cnt, cnt, cnt, U[y], y, x, y);
            U[D[cnt]] = cnt;
            D[U[cnt]] = cnt;
        } else {
            // 在同一行插入
            add(cnt, temp, R[temp], U[y], y, x, y);
            R[L[cnt]] = cnt;
            L[R[cnt]] = cnt;
            U[D[cnt]] = cnt;
            D[U[cnt]] = cnt;
        }
        cnt++;
        sum[y]++;

    }

    /**
     * 移除编号为k的列，同时将列中包含的行都移除
     * 由上至下，由左至右删除，和之后的恢复顺序相反
     * @param k 列好
     */
    void remove(int k) {
        // 移除列
        R[L[k]] = R[k];
        L[R[k]] = L[k];
        // 依次移除列中的行
        for (int i = D[k]; i != k ; i = D[i]) {
            for (int j = R[i]; j != i ; j = R[j]) {
                D[U[j]] = D[j];
                U[D[j]] = U[j];
                sum[col[j]]--;
            }
        }
    }

    /**
     * 恢复第k列
     *
     * @param k
     */
    void resume(int k) {
        R[L[k]] = k;
        L[R[k]] = k;
        for (int i = U[k]; i != k ; i = U[i]) {
            for (int j = L[i]; j != i ; j = L[j]) {
                D[U[j]] = j;
                U[D[j]] = j;
                sum[col[j]]++;
            }
        }
    }

    /**
     * 估价函数，估计剩余列覆盖完还需要的最少行
     */
    int getEval() {
        int eval = 0;
        int[] rowCode = new int[MAXN];
        for (int i = R[0]; i != 0; i = R[i]) {

        }
        return 0;
    }


    boolean dance(int depth, int MaxDepth) {
//            if (minResultCount != -1 && depth > minResultCount) return false;
        if (R[0] == 0) {
            resultCount = depth;
//                if (minResultCount == -1 || resultCount < minResultCount)
//                    minResultCount = resultCount;
            return true;
        }
        // 选择行最少的列
        int now = R[0];
        for (int i = now; i != 0; i = R[i]) {
            if (sum[now] > sum[i]) now = i;
        }
        remove(now);

        // 枚举当前列的每一行
        for (int i = D[now]; i != now ; i = D[i]) {
            ans[depth] = row[i];
            // 删除枚举行上的所有列
            for (int j = R[i]; j != i ; j = R[j]) {
                remove(col[j]);
            }
            // 递归调用
            if (dance(depth + 1, MaxDepth)) return true;
            // 回溯
            for (int j = L[i]; j != i ; j = L[j]) {
                resume(col[j]);
            }
        }
        // 回溯
        resume(now);
        return false;
    }

    int[] result() {
        return Arrays.copyOf(ans, resultCount);
    }
}
