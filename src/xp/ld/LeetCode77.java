package xp.ld;

import java.util.*;

public class LeetCode77 {
    public List<List<Integer>> combine(int n, int k) {
        if (k < 0 || k > n)
            return Collections.EMPTY_LIST;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>(k);
        core(n, k, result, list);
        return  result;
    }

    /**
     *
     * @param n 范围n
     * @param k k个数进行排列
     * @param result 结果容器
     * @param list 保存每种排列
     */
    private void core(int n, int k, List<List<Integer>> result, List<Integer> list) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = n; i >= k; i--) {
            list.add(i);
            core(i - 1, k - 1, result, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String args[]) {
        LeetCode77 leetCode77 = new LeetCode77();
        System.out.println(leetCode77.combine(4,2));
    }
}
