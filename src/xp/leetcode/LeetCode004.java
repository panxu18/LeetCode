package xp.leetcode;

public class LeetCode004 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null)
            throw new IllegalArgumentException("至少有一个参数不为空");
        if (nums1 == null)
            nums1 = new int[0];
        if (nums2 == null)
            nums2 = new int[0];

        if ((nums1.length + nums2.length) / 2 == 1)
            return soluteCore(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1,
                    (nums1.length + nums2.length) / 2 + 1);
        else {
            int kth1 = soluteCore(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1,
                    (nums1.length + nums2.length) / 2);
            int kth2 = soluteCore(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1,
                    (nums1.length + nums2.length) / 2 + 1);
            return  (kth1 + kth2) / 2.0d;

        }

    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private static int soluteCore(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int kth) {
        while (true) {
            if (start1 > end1)
                return nums2[start2 + kth - 1];
            if (start2 > end2)
                return nums1[start1 + kth - 1];
            if (kth == 1)
                return Math.min(nums1[start1], nums2[start2]);
            int pos1 = end1 - start1 + 1 < kth / 2 ? end1 : start1 + kth / 2 - 1;
            int pos2 = end2 - start2 + 1 < kth - (kth / 2) ? end2 : start2 + kth - (kth / 2) - 1;
            if (nums1[pos1] == nums2[pos2]) {
                if ((pos1 - start1 + 1) + (pos2 - start2 + 1) == kth)
                    return nums1[pos1];
                else  {
                    kth = kth - (pos1 - start1 + 1) - (pos2 - start2 + 1);
                    start1 = pos1 + 1;
                    start2 = pos2 + 1;
                }
            } else if(nums1[pos1] > nums2[pos2]) {
                end1 = pos1;
                kth = kth - (pos2 - start2 + 1);
                start2 = pos2 + 1;
            } else {
                end2 = pos2;
                kth = kth - (pos1 - start1 + 1);
                start1 = pos1 + 1;
            }
        }
    }

    public static void main(String args[]) {
        double d = new LeetCode004().findMedianSortedArrays(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22}, new int[]{0,6});
        System.out.println(d);
    }

}
