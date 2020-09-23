package xp.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class LeetCode451 {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        // 统计频率
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c,0);
            }
            map.put(c, map.get(c) + 1);
        }
        return map.entrySet()
                .stream()
                .sorted((e1, e2)-> {return e2.getValue().compareTo(e1.getValue());})
                .map(e->{
                    char[] chars = new char[e.getValue()];
                    Arrays.fill(chars, e.getKey());
                    return String.valueOf(chars);
                }).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        LeetCode451 leetCode451 = new LeetCode451();
        System.out.println(leetCode451.frequencySort("tree"));
    }
}
