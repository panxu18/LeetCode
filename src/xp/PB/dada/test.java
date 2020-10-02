package xp.PB.dada;

import java.util.*;

public class test {
//1,2,4|1,3,4,8,9

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strs = str.split("\\|");
        String[] str11= strs[0].split(",");
        String[] str22= strs[1].split(",");

        int[] list1 = Arrays.stream(str11).mapToInt(Integer::parseInt).toArray();
        int[] list2 =  Arrays.stream(str22).mapToInt(Integer::parseInt).toArray();
//        for(int i=0;i<str11.length;i++){
//            list1[i] = Integer.parseInt(str11[i]);
//        }
//        for(int i=0;i<str22.length;i++){
//            list2[i] = Integer.parseInt(str22[i]);
//        }
        Node nodeList1 = bulidNodeList(list1);
        Node nodeList2 = bulidNodeList(list2);
        Node sumList = marge(nodeList1,nodeList2);
        while(sumList!=null){
            System.out.print(sumList.val);
            sumList = sumList.next;
            if(sumList!=null){
                System.out.print(",");
            }
        }




    }
    public static Node marge(Node nodeList1,Node nodeList2){
        Node head = new Node(-1);
        Node temp = head,temp1 =nodeList1 ,temp2 = nodeList2;
        while((temp1!=null) && (temp2 !=null)){
            if(temp1.val<=temp2.val){
                temp.next=temp1;
                temp1 = temp1.next;
                temp = temp.next;
                temp.next=null;
            }else{
                temp.next=temp2;
                temp2 = temp2.next;
                temp = temp.next;
                temp.next=null;
            }
        }
        if(temp1!=null){
            temp.next = temp1;
        }
        if(temp2!=null){
            temp.next = temp2;
        }
        return head.next;
    }


    public static Node bulidNodeList(int[] arr){
        Node head = new Node(-1);
        Node temp = head;
        for(int i: arr){
            Node n = new Node(i);
            temp.next = n;
            temp = n;
        }
        return head.next;
    }
    static class Node{
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
