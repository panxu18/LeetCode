package xp.pan.wubatongcheng;

import java.util.*;

public class Test1011_2 {
    public static void main(String[] args) {
        Arrays.stream(new Test1011_2().removeDuplicate(new int[]{3, 5, 8, 2, 3, 8})).forEach(System.out::println);
    }

    public int[] removeDuplicate (int[] array) {
        // write code here
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = array.length - 1; i >= 0; i--) {
            if (set.contains(array[i])) {
                continue;
            }
            set.add(array[i]);
            ans.add(array[i]);
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }
}
