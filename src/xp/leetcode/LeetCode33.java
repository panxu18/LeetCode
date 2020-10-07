package xp.leetcode;

public class LeetCode33 {
    public static void main(String[] args) {
        System.out.println(new LeetCode33().search(new int[]{4,5,6,7,0,1,2}, 0));
    }

    public int search(int[] nums, int target) {
        int lb = 0, ub = nums.length - 1;
        while (lb <= ub) {
            int mid = (lb + ub) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[0]) {
                if (target >= nums[lb] && target < nums[mid]) {
                    ub = mid - 1;
                } else {
                    lb = mid +1;
                }
            } else {
                if (target > nums[mid] && target <= nums[ub]) {
                    lb = mid + 1;
                } else {
                    ub = mid - 1;
                }
            }
        }
        return -1;
    }
}
