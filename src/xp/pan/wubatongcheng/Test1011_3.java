package xp.pan.wubatongcheng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Test1011_3 {

    public static void main(String[] args) {
        new Test1011_3().binaryTreeScan(new int[]{1,7,-1,-1,-1,-1,8})
                .stream().forEach(list -> {
            System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining()));
        });
    }

    public ArrayList<ArrayList<Integer>> binaryTreeScan(int[] input) {
        // write code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (input.length == 0 || input[0] == -1) {
            return ans;
        }
        Node root = init(input);
        ArrayList<Integer> result = new ArrayList<>();
        preOrder(root, result);
        ans.add(result);
        result = new ArrayList<>();
        inOrder(root, result);
        ans.add(result);
        result = new ArrayList<>();
        postOrder(root, result);
        ans.add(result);
        return ans;
    }

    private void preOrder(Node root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    private void inOrder(Node root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
    }

    private void postOrder(Node root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);
    }

    private Node init(int[] input) {
        Queue<Node> queue = new LinkedList<>();
        int i = 0;
        Node root = new Node(input[i++], null, null);
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (i < input.length && input[i++] > 0) {
                node.left = new Node(input[i - 1], null, null);
                queue.add(node.left);
            }
            if (i < input.length && input[i++] > 0) {
                node.right = new Node(input[i - 1], null, null);
                queue.add(node.right);
            }
        }
        return root;
    }


    class Node {
        int val;
        Node left, right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
