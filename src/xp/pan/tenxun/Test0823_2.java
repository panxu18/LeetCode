package xp.pan.tenxun;

import java.util.PriorityQueue;
import java.util.Scanner;

import static java.lang.Math.min;

public class Test0823_2 {

    private static char[] arr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int k = in.nextInt();
        arr = str.toCharArray();
        PriorityQueue<StringNode> que = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        for (int i = 0; i < str.length(); i++) {
            for (int j = 1; j + i <= str.length(); j++) {
                StringNode node = new StringNode(i, j);
                if (que.contains(node)) {
                    continue;
                }
                if (que.size() < 5) {
                    que.add(node);
                } else {
                    que.add(node);
                    que.poll();
                }

//                if (!que.contains(node))
//                    que.add(node);
            }
        }
//        StringNode ans = null;
//        while (!que.isEmpty())  {
//            ans = que.poll();
//            System.out.println(String.valueOf(arr, ans.s, ans.len));
//        }

        StringNode[] top5 = new StringNode[5];
        int i = 4;
        while (!que.isEmpty()) {
            top5[i--] = que.poll();
        }
//        for (int j = 0; j < 5; j++) {
//            System.out.println(String.valueOf(arr, top5[j].s, top5[j].len));
//        }

        System.out.println(String.valueOf(arr, top5[k - 1].s, top5[k - 1].len));
    }

    static class StringNode implements Comparable<StringNode>{
        int s, len;

        public StringNode(int s, int len) {
            this.s = s;
            this.len = len;
        }

        @Override
        public int compareTo(StringNode o) {
            int minLen = min(len, o.len);
            for (int i = 0; i < minLen; i++) {
                if (arr[i + s] != arr[i + o.s]) {
                    return arr[i + s] < arr[i + o.s] ? -1 : 1;
                }
            }
            if (len == o.len) {
                return 0;
            }
            return len < o.len ? -1 : 1;
        }

        @Override
        public boolean equals(Object o) {
            return compareTo((StringNode) o) == 0;
        }
    }
}
