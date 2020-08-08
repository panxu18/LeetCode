package xp.ld;

import java.util.TreeSet;

public class LeetCode16 {

    public static void main(String[] args) {
        int[] arr = {-1,2,1,-4};
        System.out.println(new LeetCode16().threeSumClosest(arr, 1));
    }

    int minOffset = Integer.MAX_VALUE;
    int ans = 0;
    public int threeSumClosest(int[] nums, int target) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(nums[0] + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            Integer ceil = set.ceiling(target - nums[i]);
            if (ceil != null){
                update(ceil + nums[i], target);
            }
            Integer floor = set.floor(target - nums[i]);
            if (floor != null) {
                update(floor + nums[i], target);
            }
            for (int j = 0; j < i; j++) {
                set.add(nums[i] + nums[j]);
            }
        }
        return ans;

    }

    private void update(int a, int target) {
        int offset = Math.abs(a - target);
        if (offset < minOffset) {
            minOffset = offset;
            ans = a;
        }
    }
}
