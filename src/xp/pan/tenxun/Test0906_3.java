package xp.pan.tenxun;

import java.util.*;
import java.util.stream.Collectors;

public class Test0906_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        in.nextLine();
        HashMap<String, Node> strMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String str = in.nextLine();
            if (strMap.containsKey(str)) {
                strMap.get(str).cnt++;
            } else {
                strMap.put(str, new Node(str, 1));
            }
        }
        ArrayList<Node> sortedNodes = strMap.values()
                .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < K; i++) {
            System.out.printf("%s %d%n", sortedNodes.get(i).str, sortedNodes.get(i).cnt);
        }
        for (int i = N - K; i < N; i++) {
            System.out.printf("%s %d%n", sortedNodes.get(i).str, sortedNodes.get(i).cnt);
        }
    }

    static class Node implements Comparable<Node>{
        String str;
        int cnt;

        public Node(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if (cnt == o.cnt) {
                return str.compareTo(o.str);
            }
            return Integer.compare(cnt, o.cnt);
        }
    }
}
