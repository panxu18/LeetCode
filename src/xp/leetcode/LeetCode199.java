package xp.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayList<TreeNode> que = new ArrayList<>();
        que.add(root);
        while (!que.isEmpty()) {
            res.add(que.get(que.size()).val);
            ArrayList<TreeNode> next = new ArrayList<>();
            for (TreeNode node : que) {
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            que = next;
        }
        return res;
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
