package xp.oj.poj.bingchaji;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 并查集 食物链
 *
 * 问题描述
 * 有N种动物，它们分为三类（A、B、C），它们之间的捕食关系为A->B->C->A。现在给出K条关于这些动物的关系，包括两种动物的捕食
 * 关系或者是同类。判断其中有多少条关系是错误（和前面的关系有矛盾）的。
 * 问题分析
 * 并查集能将同一种类的动物合并在一个集合中，但是这里需要表示捕食关系。这里x可以属于三类，那么最终合理的结果也可以分为3
 * 种，因此这里对结果合并，xA、xB和xC分别用来表示x属于三种类别。如果x和y是同一种类别，那么xA和yA、xB和yB以及xC和yC分别属
 * 于在一个集合中，同理x捕食y时xA和yB、xB和yC以及xC和yA分别属于同一集合。
 */
public class FoodChain1182 {

    public static void main(String[] args) {
        new FoodChain1182().solve();
    }

    int N, K, D, X, Y;
    int[] parent = new int[150005];
    int[] rank = new int[150005];
    void solve() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        try {
            String[] arr = in.readLine().split(" ");
            N = Integer.parseInt(arr[0]);
            K = Integer.parseInt(arr[1]);
            for (int i = 0; i <= 3 * N; i++) {
                parent[i] = i;
            }
            int ans = 0;
            for (int i = 0; i < K; i++) {
                arr = in.readLine().split(" ");
                D = Integer.parseInt(arr[0]);
                X = Integer.parseInt(arr[1]);
                Y = Integer.parseInt(arr[2]);
                if (check()) continue;
                ans++;
            }
            out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
    }


    private boolean check() {
        if (X > N || Y > N) return false;
        if (D == 1) {
            if (same(X, Y + N) || same(X, Y + 2 * N)) return false;
            union(X, Y);
            union(X + N, Y + N);
            union(X + 2 * N, Y + 2 * N);
        } else {
            if (same(X, Y) || same(Y, X + N)) return false;
            union(X, Y + N);
            union(X + N, Y + 2 * N);
            union(X + 2 * N, Y);
        }
        return true;
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else {
            parent[py] = px;
            if (rank[py] == rank[px]) rank[px]++;
        }
    }

    private int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    private boolean same(int x, int y) {
        return find(x) == find(y);
    }
}
