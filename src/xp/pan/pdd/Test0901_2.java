package xp.pan.pdd;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 深搜，简单枚举
 * 在N*M的01矩阵中，如果可以移动一个1，计算由彼此相邻（上下左右相邻）的1构成的最大区域
 *
 * 移动1只会移动到0所在的位置，所以枚举所有0，从该点开始进行dfs，找到相邻的区域。因为需要将一个1移动到该位置，所以最终区域大小
 * 不会大于所有1的个数。
 * 如果需要进行优化，则先对原始矩阵进行dfs计算所有区域的大小，然后再枚举所有0，此时只需要将0所在位置周围最多4个不同的区域相加即可。
 * 对原始矩阵dfs时需要保存所有区域的大小，以及区域序号。
 */
/*
输入
4 4
1 0 1 1
1 1 0 1
0 0 0 0
1 1 1 1
输出
8
 */
public class Test0901_2 {

    private static int[][] map = new int[405][405];
    private static boolean[][] flag = new boolean[405][405];
    private static HashMap<Integer, Integer> setCount = new HashMap<>();
    static int N;
    static int M;
    private static int ans = 0;
    private static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        M = in.nextInt();
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = in.nextInt();
                if (map[i][j] == 1) {
                    max++;
                }
            }
        }
        // 初始化
        int cnt = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int sum = dfs(i, j, cnt);
                if (sum > 0) {
                    setCount.put(cnt++, sum);
                }
            }
        }
        // 枚举0,
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = dir[k][0] + i;
                        int ny = dir[k][1] + j;
                        if (nx >= 0 && nx < N
                            && ny >= 0 && ny < M
                            && map[nx][ny] > 0
                            && !list.contains(map[nx][ny])) {
                            list.add(map[nx][ny]);
                        }
                    }
                    int sum = list.stream().mapToInt(s->setCount.get(s)).sum();
                    ans = Math.max(ans, sum + 1);
                }
            }
        }
        // 不超过所有士兵之和
        out.println(Math.min(ans, max));
        out.flush();

    }


    private static int dfs(int x, int y, int cnt) {
        int sum = 0;
        if (map[x][y] != 1) {
            return sum;
        }
        map[x][y] = cnt;
        sum++;
        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + x;
            int ny = dir[i][1] + y;
            if (nx >= 0 && nx < N
                    && ny >= 0 && ny < M
                    && map[nx][ny] == 1) {
                sum += dfs(nx, ny, cnt);
            }
        }
        return sum;
    }
}
