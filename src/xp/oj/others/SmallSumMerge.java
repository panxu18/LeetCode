package xp.oj.others;

/**
 * 小和归并
 */
public class SmallSumMerge {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 4, 6};
        System.out.println(smallSum(arr));
    }

    private static long smallSum(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        temp = new int[arr.length];
        long ans =  mergeSort(arr, 0, arr.length - 1);
        return ans;
    }

    private static int[] temp;

    private static long mergeSort(int[] arr, int s, int t) {
        if (s == t) {
            return 0;
        }
        int mid = (s + t) >> 1;
        long res = 0;
        res += mergeSort(arr, s, mid);
        res += mergeSort(arr, mid + 1, t);

        int k = s;
        int left = s;
        int right = mid + 1;
        while (left <= mid && right <= t) {
            if (arr[left] <= arr[right]) {
                res += arr[left] * (t - right + 1);
                temp[k++] = arr[left++];
            } else {
                res +=
                temp[k++] = arr[right++];
            }
        }
        if (left <= mid) {
            System.arraycopy(arr, left, temp, k, mid - left + 1);
        } else {
            System.arraycopy(arr, right, temp, k, t - right + 1);
        }
        System.arraycopy(temp, s, arr, s, t - s + 1);
        return res;
    }
}
