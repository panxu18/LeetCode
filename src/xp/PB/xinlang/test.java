package xp.PB.xinlang;

import xp.pan.xinlang.Test0924_1;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @auther Peng
 * @date 2020/9/24 - 20:56
 */
public class test {
//    public static void main(String[] args) {
//        Test0924_1.TreeNode node = new Test0924_1.TreeNode(1);
//        node.left = new Test0924_1.TreeNode(2);
//        node.right = new Test0924_1.TreeNode(3);
//        node.left.left = new Test0924_1.TreeNode(4);
//        node.left.right = new Test0924_1.TreeNode(5);
//        node.right.left = new Test0924_1.TreeNode(6);
//        node.right.right = new Test0924_1.TreeNode(7);
//        preorderTraversal(node).stream().forEach(System.out::println);
//    }

    public static ArrayList<Integer> preorderTraversal (TreeNode root) {
        // write code here
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            ans.add(p.val);
            p = p.left;
        }
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                ans.add(p.val);
                p = p.left;
            } else {
                p = stack.pop().right;
            }
        }
        return ans;
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
