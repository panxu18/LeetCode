package xp.DL.美团;

public class 链表相加 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode res = addTwo(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode addTwo(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = new ListNode(-1);
        ListNode head = res;
        int flag = 0;
        while (l1 != null || l2 != null) {
            int n1 = 0;
            int n2 = 0;
            if (l1 != null) {
                n1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                n2 = l2.val;
                l2 = l2.next;
            }
            int temp = n1 + n2 + flag;
            res.next = new ListNode(temp % 10);
            res = res.next;
            flag = temp / 10;
        }
        if (flag != 0) {
            res.next = new ListNode(flag);
        }
        return head.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
