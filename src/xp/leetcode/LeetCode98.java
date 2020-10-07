package xp.leetcode;

public class LeetCode98 {
    private long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return (root == null)
                || isValidBST(root.left)
                && pre != root.val && (pre = Math.max(pre, root.val)) == root.val
                && isValidBST(root.right);

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
