package xp.pan;

import java.util.*;
import java.util.stream.Collectors;

public class Test3 {

    private static int N, M;
    private static int[] par = new int[100005];
    private static int[] rank = new int[100005];
    private  static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        init();
        for (int i = 0; i < M; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            union(a,b);
        }

        for (int i = 1; i <= N; i++) {
            int p = find(i);
            if (map.containsKey(p)) {
                map.get(p).add(i);
            } else  {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(p, list);
            }
        }

        ArrayList<ArrayList<Integer>> res = (ArrayList<ArrayList<Integer>>) map.values().stream().collect(Collectors.toList());
        res.forEach(Collections::sort);
        res.sort((list1, list2)->Integer.compare(list1.get(0), list2.get(0)));
        System.out.println(res.size());
        for (ArrayList<Integer> list: res) {
            for (int i = 0; i < list.size() - 1; i++ ) {
                System.out.printf("%d ", list.get(i));
            }
            System.out.println(list.get(list.size()-1));
        }
    }

    private static void init() {
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        Arrays.fill(rank,0);
    }

    private static void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[x] < rank[y]) {
            par[px] = py;
        } else {
            par[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
        }
    }

    private static int find(int x) {
        return par[x] == x ? x : (par[x] = find(par[x]));
    }

    private static boolean isSame(int x, int y) {
        return find(x) == find(y);
    }
}
