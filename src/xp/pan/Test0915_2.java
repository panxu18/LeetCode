package xp.pan;

import java.util.Arrays;
import java.util.Collections;

public class Test0915_2 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int offset = 2;
        Arrays.stream(new Test0915_2().pushIntArray(arr, offset)).forEach(System.out::println);
    }

    public int[] pushIntArray(int[] arr, int pushOffset) {
        pushOffset %= arr.length;
        reverseArray(arr, 0, arr.length);
        reverseArray(arr, 0, pushOffset);
        reverseArray(arr, pushOffset, arr.length);
        return arr;
    }

    private void reverseArray(int[] arr, int s, int t)  {
        t--;
        while (s < t) {
            arr[s] ^= arr[t];
            arr[t] ^= arr[s];
            arr[s++] ^= arr[t--];
        }
    }

}
