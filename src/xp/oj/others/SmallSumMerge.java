package xp.oj.others;

/**
 * 小和归并
 */
public class SmallSumMerge {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 4, 6};
        System.out.println(smallSum(arr));
    }

    private static int smallSum(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int[] temp = new int[arr.length];
        return mergeSort(arr, 0, arr.length, temp);
    }

    private static int mergeSort(int[] source, int fromIndex, int toIndex, int[] temp) {
        if (fromIndex + 1 >= toIndex) {
            return 0;
        }
        int mid = (fromIndex + toIndex) >> 1;
        int sum = mergeSort(source, fromIndex, mid, temp);
        sum += mergeSort(source, mid, toIndex, temp);
        return sum + merge(source, fromIndex, toIndex, temp);
    }

    private static int merge(int[] source, int fromIndex, int toIndex, int[] temp) {
        int mid = (fromIndex + toIndex) >> 1;
        int leftIndex = fromIndex, rightIndex = mid;
        int tempIndex = fromIndex;
        int sum = 0;
        while (leftIndex < mid && rightIndex < toIndex) {
            if (source[leftIndex] <= source[rightIndex]) {
                temp[tempIndex++] = source[leftIndex];
                sum += source[leftIndex] * (toIndex - rightIndex);
                leftIndex++;
            } else {
                temp[tempIndex++] = source[rightIndex++];
            }
        }
        int restIndex = leftIndex < mid ? leftIndex : rightIndex;
        while (tempIndex < toIndex) {
            temp[tempIndex++] = source[restIndex++];
        }
        // copy back to source
        for (int i = fromIndex; i < toIndex; i++) {
            source[i] = temp[i];
        }
        return sum;
    }
}
