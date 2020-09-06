package xp.pan.tenxun;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给组字符串，输出数量最多和最少的K个字符串，如果数量相同则输出字典序较小的字符串。
 * 首先用hashmap计数,然后根据(数量，字符串)排序，注意需要分两次排序，分别得到数量最多的K的字符串，
 * 以及数量最少的K个字符串。
 */
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
        ArrayList<Node> nodes = strMap.values().stream().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Node> topK = getTopK(nodes, K, comparator1);
        topK.forEach((node -> System.out.printf("%s %d%n", node.str, node.cnt)));
        topK = getTopK(nodes, K, comparator2);
        Collections.reverse(topK);
        topK.forEach((node -> System.out.printf("%s %d%n", node.str, node.cnt)));
    }

    private static Comparator<Node> comparator1 = (o1, o2) -> {
        if (o1.cnt == o2.cnt) {
            return o1.str.compareTo(o2.str);
        }
        return Integer.compare(o2.cnt, o1.cnt);
    };

    private static Comparator<Node> comparator2 = (o1, o2) -> {
        if (o1.cnt == o2.cnt) {
            return o1.str.compareTo(o2.str);
        }
        return Integer.compare(o1.cnt, o2.cnt);
    };

    private static ArrayList<Node> getTopK(ArrayList<Node> sources, int k, Comparator<Node> comparator) {
        return sources.stream().sorted(comparator).limit(k)
                .collect(Collectors.toCollection(ArrayList::new));

    }

    static class Node{
        String str;
        int cnt;

        public Node(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
}
