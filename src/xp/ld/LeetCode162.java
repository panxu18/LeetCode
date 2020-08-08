package xp.ld;

public class LeetCode162 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        return findPeakElementCore(nums, 0, nums.length - 1);
    }

    /**
     * 通过二分查找，返回一个极大值,默认区间外的元素比区间的元素小
     * @param nums 输入数组
     * @return 数组中的一个极大值
     */
    private int findPeakElementCore(int[] nums, int start, int end) {
        int low = start;
        int high = end;
        int mid;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (mid > low && nums[mid - 1] > nums[mid])
                high = mid - 1;
            else if (mid < high && nums[mid + 1] > nums[mid])
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}
