package xp.pan.baidu;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 区间覆盖，贪心
 * 被原题绕晕了，实际上就是给若干个区间，计算哪些坐标重叠次数为M。
 * 把区间的左右端点提取出来，然后一起排序需要保存每个坐标是区间开始坐标还是区间结束坐标，从第一坐标开始，计算所有坐标的重叠次数。
 * 如果两个开始区间相邻，例如s1,s2,...,那么在[s1,s2)之间这些坐标的覆盖次数不可能再增加了。
 * 如果一个开始区间和一个结束区间相邻，例如s1,e2,....那么同样[s1,e2]之间的坐标的覆盖次数不会增加了。
 */
 public class Test0903_2 {
    private static int T, N, M;
    private static Node[] nodes = new Node[2010];
    private static int[] times = new int[1010];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for (int t = 0; t < T; t++) {
            N = in.nextInt();
            M = in.nextInt();
            Arrays.fill(nodes, null);
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                int k = in.nextInt();
                for (int j = 0; j < k; j++) {
                    nodes[cnt++] = new Node(in.nextInt(), 1);
                    // 结束区间用开区间表示，方便后面计算
                    nodes[cnt++] = new Node(in.nextInt() + 1, -1);
                }
            }
            Arrays.sort(nodes, 0, cnt);
            int i = 0;
            int num = 0;
            while (i < cnt) {
                Node cur = nodes[i];
                // 同一个坐标值上的同种属性的坐标
                while (++i < cnt && nodes[i].val == cur.val && nodes[i].flag == cur.flag) {
                    num += cur.flag;
                }
                if (i >= cnt) {
                    break;
                }
                // [cur.val, nodes[i].val) 中的坐标应该具有相同的覆盖次数
                for (int j = cur.val; j < nodes[i].val; j++) {
                    times[j] = num;
                }
            }
            int ans = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= N; j++) {
                if (times[j] == M) {
                    ans++;
                    sb.append(j);
                    sb.append(" ");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(ans);
            System.out.println(sb.toString());
        }

    }

    static class Node implements Comparable<Node>{
        int val;
        int flag;

        public Node(int val, int flag) {
            this.val = val;
            this.flag = flag;
        }

        @Override
        public int compareTo(Node node) {
            if (val == node.val) {
                return Integer.compare(node.flag, flag);
            }
            return Integer.compare(val, node.val);
        }
    }


}
