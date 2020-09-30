package xp.PB.Test;

import java.util.*;

/**
 * @auther Peng
 * @date 2020/9/27 - 18:33
 */

//[4,3,5,7,9,2]    8
//        [2,2,4]
//        [3,5]
//        [2,2,2,2]


public class test {
//    public static void main(String[] args) {
//
//    }
//
//    public List<List<Integer>> findCombination(int[] array, int target) {
//        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(array);
//        int left = 0;
//        int right = array.length-1;
//        while (left<=right){
//            if(array[left]+array[right]>target){
//                right--;
//            }else if(array[left]+array[right]<target){
//                left++;
//            }else{
//                List<Integer> r = new ArrayList<>();
//                r.add(array[left]);
//                r.add(array[right]);
//                res.add(r);
//            }
//        }
//        return res;
//    }
//
//    public List<List<Integer>> findCombination2(int[] array, int target) {
//        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(array);
//        int right = array.length-1;
//        for(int i = right;i>=0;i--){
//            if(array[i]>target){
//                i--;
//            }
//            else  if(array[i]==target){
//                List<Integer> r = new ArrayList<>();
//                r.add(array[i]);
//                res.add(r);
//            }else{
//                for(int j = 0;j<i;j++){
//
//                }
//            }
//        }
//
//        return res;
//    }

    public List<List<Integer>> level(TreeNode root ){
        List<List<Integer>> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int high = 0;
        while (!queue.isEmpty()){
            List<Integer> tempList = new ArrayList<>();
            int sum = queue.size();
            for(int i = 0;i<sum;i++){
                TreeNode tempNode = queue.poll();
                if(tempNode!=null){
                    tempList.add(tempNode.val);
                    if(tempNode.left!=null){
                        queue.add(tempNode.left);
                    }
                    if(tempNode.right!=null){
                        queue.add(tempNode.right);
                    }
                }
            }
            res.add(tempList);
        }




        return res;
    }

    class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;

        public TreeNode(int val) {
            this.val = val;
        }
    }


}
