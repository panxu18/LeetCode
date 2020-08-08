package xp.ld;

import java.util.Set;
import java.util.TreeSet;

public class Lanqiao1 {

    public static void main(String[] args) {
        String str = "LAA";
        solve(str.toCharArray(), 0);
        System.out.println(set.size());
        long i = 125 * 1024 * 1024;
        System.out.println(i);
    }
    static Set<String> set = new TreeSet<>();

    private static void solve(char[] arr, int k) {
        if (k == arr.length) {
            set.add(String.copyValueOf(arr));
        };
        for (int i = k; i < arr.length ; i++) {
            char c = arr[i];
            arr[i] = arr[k];
            arr[k] = c;
            solve(arr, k + 1);
            c = arr[i];
            arr[i] = arr[k];
            arr[k] = c;
        }
    }


}
