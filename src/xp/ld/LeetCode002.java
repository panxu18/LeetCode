package xp.ld;

public class LeetCode002 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        int c = 0;
        while (l1 != null || l2 != null || c != 0) {
            int sum = c;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            c = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
        }
        return head.next;
    }
}

class ListNode {

    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}