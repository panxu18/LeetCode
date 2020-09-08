package xp.pan.rong360;

/**
 * 判断完美二叉树
 */
public class Test0908_2 {
    public static void main(String[] args) {
        Node root = new Node();
        System.out.println(check(root) > -1);
    }


    public int Test0908_2(){
        return 1;
    }

    /**
     * 使用后序遍历判断是否是完美二叉树
     * @param root 根节点
     * @return 如果不是完美二叉树返回-1，否则返回树的高度
     */
    private static int check(Node root) {
        int leftHigh, rightHigh ;
        if ((leftHigh = check(root.left)) == -1
                || (rightHigh = check(root.right)) == -1
                || Math.abs(leftHigh - rightHigh) > 1) {
            return -1;
        }
        return Math.max(leftHigh, rightHigh) + 1;
    }

    static class Node {
        Node left, right;
    }
}
