package xp.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minQue = new PriorityQueue<>(Comparator.comparingInt(o->o.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null){
                minQue.add(lists[i]);
            }
        }
        ListNode head = new ListNode(-1, null);
        ListNode tail = head;
        while (!minQue.isEmpty()) {
            ListNode min = minQue.poll();
            tail.next = min;
            tail = min;
            min = min.next;
            if (min != null) {
                minQue.offer(min);
            }
        }
        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
