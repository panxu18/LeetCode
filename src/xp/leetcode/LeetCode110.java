package xp.leetcode;

public class LeetCode110 {
    private boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        getHeight(root);
        return flag;
    }

    private int getHeight(TreeNode root) {
        if (!flag || root == null) {
            return 0;
        }
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        if (l - r > 1 || l - r < -1) {
            flag = false;
        }
        return 1 + Math.max(l, r);
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
