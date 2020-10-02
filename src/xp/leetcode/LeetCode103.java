package xp.leetcode;

import java.util.*;

public class LeetCode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayList<TreeNode> que = new ArrayList<>();
        que.add(root);
        int level = 0;
        while (!que.isEmpty()) {
            ArrayList<TreeNode> nextQue = new ArrayList<>();
            ArrayList<Integer> resLine = new ArrayList<>();
            for (TreeNode node: que) {
                resLine.add(node.val);
                if (node.left != null) {
                    nextQue.add(node.left);
                }
                if (node.right != null) {
                    nextQue.add(node.right);
                }
            }
            que = nextQue;
            if ((level++&1) == 1) {
                Collections.reverse(resLine);
            }
            res.add(resLine);
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
