package xp.oj.search;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 切蛋糕
 * 二分，贪心，DFS
 */
public class P1528 {

    private static int[] cakes = new int[52];
    private static int[] persons = new int[1025];
    private static int n, m;
    // 总量剪枝优化，ac一个检查点
    private static int allCake, needCake;
    // 前i个人需要的总量
    private static int[] preNeedCake = new int[1025];
    // 蛋糕渣(小于最小需求的蛋糕)优化，ac一个点
    private static int wastedCake, minNeedCake;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        for (int i = 0; i < n; i++) {
            cakes[i] = in.nextInt();
            allCake += cakes[i];
        }
        m = in.nextInt();
        for (int i = 0; i < m; i++) {
            persons[i] = in.nextInt();

        }
        Arrays.sort(persons, 0, m);
        minNeedCake = persons[0];
        for (int i = 0; i < m; i++) {
            preNeedCake[i + 1] = preNeedCake[i] + persons[i];
        }
        int lb = 0, ub = m;
        while (lb < ub) {
            int mid = (lb + ub + 1) >> 1;
            needCake = preNeedCake[mid];
            if (dfs(mid - 1)) {
                lb = mid;
            } else {
                ub = mid - 1;
            }
        }
        System.out.println(ub);
    }

    /**
     * 判断能否满足0-pId的人
     *
     * @param pId
     * @return
     */
    private static boolean dfs(int pId) {
        if (pId < 0) {
            return true;
        }
        if (allCake - wastedCake < needCake) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (cakes[i] >= persons[pId]) {
                boolean flag = false;
                cakes[i] -= persons[pId];
                allCake -= persons[pId];
                needCake -= persons[pId];
                if (cakes[i] < minNeedCake) {
                    wastedCake += cakes[i];
                }
                flag = dfs(pId - 1);
                if (cakes[i] < minNeedCake) {
                    wastedCake -= cakes[i];
                }
                cakes[i] += persons[pId];
                allCake += persons[pId];
                needCake += persons[pId];
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }
}
