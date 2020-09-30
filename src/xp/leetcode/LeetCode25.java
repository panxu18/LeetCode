package xp.leetcode;

public class LeetCode25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode tail = dummy;
        while (true) {
            int count = 0;
            while (tail != null && count < k) {
                count++;
                tail = tail.next;
            }
            if (tail == null) {
                break;
            }
            ListNode head1 = pre.next;
            while (pre.next != tail) {
                ListNode cur = pre.next;
                pre.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
            }
            pre = head1;
            tail = head1;
        }
        return dummy.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5, null);
        head = new ListNode(4, head);
        head = new ListNode(3, head);
        head = new ListNode(2, head);
        head = new ListNode(1, head);
        head = new LeetCode25().reverseKGroup(head, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

}
