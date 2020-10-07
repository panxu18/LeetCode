package xp.leetcode;

public class LeetCode124 {
    private int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return ans;
    }

    private int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxPath(root.left);
        int r = maxPath(root.right);
        int res = Math.max(root.val, Math.max(l, r) + root.val);
        ans = Math.max(ans, res);
        ans = Math.max(ans, l + r + root.val);
        return res;
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
