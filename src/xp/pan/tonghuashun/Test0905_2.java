package xp.pan.tonghuashun;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 将0移动到数组末尾
 */
public class Test0905_2 {
    public static void main(String[] args) {
        int[] arr = {0,1,2,0, 0, 3,0,4,0,5,6};
        partialSort(arr);
        System.out.println(Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining()));
    }

    private static void partialSort(int[] arr) {
        int l = 0;
        while (l < arr.length && arr[l] != 0) {
            l++;
        }
        for (int i = l + 1; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[i]^=arr[l];
                arr[l]^=arr[i];
                arr[i]^=arr[l];
                l++;
            }
        }

    }
}
