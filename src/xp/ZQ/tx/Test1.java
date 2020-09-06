package xp.ZQ.tx;

import java.util.ArrayList;
import java.util.Scanner;

class ListNode{
    int value;
    ListNode next;
    public ListNode(int value){
        this.value=value;
    }
}
public class Test1 {
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        int n=sc.nextInt();
//        int k=sc.nextInt();
//        ListNode dummyHead=new ListNode(-1);
//        ListNode cur=dummyHead;
//        for(int i=0;i<n;i++){
//            int v=sc.nextInt();
//            cur.next=new ListNode(v);
//            cur=cur.next;
//        }
//        cur=dummyHead;
//        while(k>1){
//            cur=cur.next;
//            k--;
//        }
//        cur.next=cur.next.next;
//        cur=dummyHead.next;
//        while (cur!=null){
//            System.out.print(cur.value+" ");
//            cur=cur.next;
//        }

        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        ListNode dummyHead=new ListNode(-1);
        ListNode cur=dummyHead;
//        if(k<1||k>n){
//            return;
//        }
//        if(n==1&&k==1){
//            return;
//        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){
            if(i==k-1){
                sc.nextInt();
                continue;
            }
            int v=sc.nextInt();
            cur.next=new ListNode(v);
            cur=cur.next;
            sb.append(v+" ");
        }
        System.out.println(sb.toString());
    }
}
