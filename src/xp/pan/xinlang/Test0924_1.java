package xp.pan.xinlang;

import java.util.ArrayList;
import java.util.Stack;

public class Test0924_1 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);
        preorderTraversal(node).stream().forEach(System.out::println);
    }

    public static ArrayList<Integer> preorderTraversal (TreeNode root) {
        // write code here
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            res.add(p.val);
            p = p.left;
        }
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                res.add(p.val);
                p = p.left;
            } else {
                p = stack.pop().right;
            }
        }
        return res;
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
