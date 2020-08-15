package xp.oj;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sudoku {

    public static void main(String[] args) {
        new Sudoku().solve();
    }

    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);
    
    int[][] map = new int[10][10];
    long INF = Long.MAX_VALUE;
    int MAXN = 300005;
    DanceLinkX dlx;
    int rowCnt;
    int[][]row = new int[9 * 9 * 9 + 2][3];

    private void solve() {

        int n = in.nextInt();
        dlx = new DanceLinkX(9 * 9 * 9 * 4, COVERTYPE.EXACT);
        for (int i = 0; i < n; i++) {
            map = new int[10][10];
            dlx.reset(9 * 9 * 9 + 2, 4 * (9 * 9));
            rowCnt = 0;
            for (int j = 1; j < 10; j++) {
                char[] charArr = in.readLine().toCharArray();
                for (int k = 1; k < 10; k++) {
                    int q = charArr[k - 1] - '0';
                    if (q != 0) {
                        map[j][k] = q;
                        insert(j,k,q);
                    } else {
                        for (int l = 1; l < 10; l++) {
                            insert(j, k, l);
                        }
                    }
                }
            }
            if (dlx.dance(0, 1000000007)) {
                int[] ans = dlx.result();
                for (int j = 0; j < ans.length ; j++) {
                    map[row[ans[j]][0]][row[ans[j]][1]] = row[ans[j]][2];
                }
                for (int j = 1; j < 10; j++) {
                    for (int k = 1; k < 10; k++) {
                        out.print(map[j][k]);
                    }
                    out.print("\n");
                }
            }
        }
        out.flush();
    }





    /**
     * 数独第x行y列插入字符z，
     * 插入一个字符，对应4个节点，分别为4个约束
     * @param x 行号
     * @param y 列号
     * @param z 字符对应的索引
     */
    void insert(int x, int y, int z) {
        row[++rowCnt][0] = x;
        row[rowCnt][1] = y;
        row[rowCnt][2] = z;
        dlx.insert(rowCnt, 9 * (x - 1) + y); // x行y列已插入字符
        dlx.insert(rowCnt, 81 + 9 * (x - 1) + z); // 第x行已插入字符z
        dlx.insert(rowCnt, 162 + 9 * (y - 1) + z); // 第y列已插入字符z
        int k = (x - 1) / 3 * 3 + (y - 1) / 3 + 1;
        dlx.insert(rowCnt, 243 + 9 * (k - 1) + z); // 第k块已插入字符z

    }

    enum COVERTYPE {
        EXACT, // 精确覆盖
        REPEAT; // 重复覆盖
    }

    class DanceLinkX{

        COVERTYPE covertype;
        int maxn;
        int[] L, R, U, D;
        int[] row, col, ans;
        int[] sum; // 各列中的元素个数
        int n, m, resultCount;
        int cnt; // 元素计数
        private int minResultCount = -1;

        DanceLinkX(int len, COVERTYPE type) {
            covertype = type == null ? COVERTYPE.EXACT : COVERTYPE.REPEAT;

            maxn = len;
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
         * @param k 列号
         */
        void remove(int k) {
            // 移除列
            R[L[k]] = R[k];
            L[R[k]] = L[k];
            if (covertype == COVERTYPE.EXACT) {
                // 依次移除列中的行
                for (int i = D[k]; i != k ; i = D[i]) {
                    for (int j = R[i]; j != i ; j = R[j]) {
                        D[U[j]] = D[j];
                        U[D[j]] = U[j];
                        sum[col[j]]--;
                    }
                }
            } else {
                // 只删除当前列
                for (int i = D[k]; i != k ; i = D[i]) {
                    R[L[i]] = R[i];
                    L[R[i]] = L[i];
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
         * 估价函数，估计最少还需要多少次搜索
         * 粗略估计，从第一列开始依次删除一列，计算删除多少列可以将所有列删除完
         */
        int getEval() {
            int eval = 0;
            int[] rowCode = new int[MAXN];
            int[] del = new int[MAXN];
            for (int i = R[0]; i != 0; i = R[i]) {
                if (del[i] == 0) {
                    eval++;
                    del[i] = 1;
                    for (int j = D[i]; j != i ; j = D[j]) {
                        for (int k = R[j]; k != j ; k = R[k]) {
                            del[col[k]]  = 1;
                        }
                    }
                }
            }
            return 0;
        }


        /**
         * 在DLX中搜索
         * @param depth 当前深度
         * @param maxDepth 允许最大搜索深度，用来剪枝
         * @return
         */
        boolean dance(int depth, int maxDepth) {
//            if (minResultCount != -1 && depth > minResultCount) return false;
            if (R[0] == 0) {
                resultCount = depth;
//                if (minResultCount == -1 || resultCount < minResultCount)
//                    minResultCount = resultCount;
                return true;
            }
            if (maxDepth != -1 && depth + getEval() > maxDepth)
                return false;
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
                if (dance(depth + 1, maxDepth)) return true;
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



    class InputReader{
        BufferedReader bf;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bf = new BufferedReader(new InputStreamReader(stream));
            tokenizer = new StringTokenizer("");
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        private String next() {
            if (hasNext()) {
                return tokenizer.nextToken();
            }
            return null;
        }

        private boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String s;
                try {
                    s = bf.readLine();
                } catch (IOException e) {
                    return false;
                }
                tokenizer = new StringTokenizer(s);
            }
            return true;
        }

        private String readLine() {
            try {
                return bf.readLine();
            } catch (IOException e) {
                return null;
            }
        }
    }


}
