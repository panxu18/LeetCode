package xp.ld;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LeetCode001 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val_2 = target - nums[i];
            if (map.containsKey(val_2))
                return new int[] {map.get(val_2), i};
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
