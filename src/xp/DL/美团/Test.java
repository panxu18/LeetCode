package xp.DL.美团;

import java.util.*;

/**
 * 求N个数组的笛卡尔积
 * 假设当前有三个不等长数组A，B，C。三个数组分别包含如下元素
 * A：[a1, a2, a3]
 * B：[b1, b2]
 * C：[c1, c2, c3]
 * 从每个数组中选择一项，组成数列，可以获得如下数列：a1b1c1，a1b1c2，a1b1c3，a2b1c1，a2b1c2……
 * 全部的组合即为笛卡尔积。
 * 现题目要求是有N个不等长数组，要求获得这N个数组的笛卡尔积，请编写代码打印出全部的组合。
 */
public class Test {
    private List<List<String>> combineAlg(List<String[]> nArray) {
        List<List<String>> values = new LinkedList<>();
        int[] x = new int[nArray.size()];

        int flag = 0;
        do {
            List<String> s = new LinkedList<>();
            for (int looper = 0; looper < nArray.size(); looper++) {
                s.add(nArray.get(looper)[x[looper]]);
            }
            flag = NextPermutation(x, nArray);
            values.add(s);
        } while (flag != 1);
        return values;
    }

    private int NextPermutation(int[] x, List<String[]> nArray) {
        int carry = 0;
        for (int looper = nArray.size() - 1; looper >= 0; looper--) {
            if (x[looper] + 1 == nArray.get(looper).length) {
                carry = 1;
                x[looper] = 0;
            } else {
                x[looper] = x[looper] + 1;
                return 0;
            }
        }

        if (carry == 1)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        Test t = new Test();
        List<String[]> nArray = new ArrayList<>();
        String[] s1 = new String[]{"a1", "a2", "a3"};
        String[] s2 = new String[]{"b1", "b2"};
        String[] s3 = new String[]{"c1", "c2", "c3"};
        nArray.add(s1);
        nArray.add(s2);
        nArray.add(s3);
        List<List<String>> res = t.combineAlg(nArray);

        /**
         * 最后输出的结果集如下：
         * [[a1, b1, c1], [a1, b1, c2], [a1, b1, c3], [a1, b2, c1], [a1, b2, c2], [a1, b2, c3], [a2, b1, c1], [a2, b1, c2], [a2, b1, c3], [a2, b2, c1], [a2, b2, c2], [a2, b2, c3], [a3, b1, c1], [a3, b1, c2], [a3, b1, c3], [a3, b2, c1], [a3, b2, c2], [a3, b2, c3]]
         */
        System.out.println(res);
    }
}
