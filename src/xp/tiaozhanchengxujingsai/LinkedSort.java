package xp.tiaozhanchengxujingsai;

import java.util.stream.IntStream;

public class LinkedSort {

    public static void main(String[] args) {
        int[] arr = IntStream.iterate(1, (p)-> p+1).limit(20).toArray();
        LinkedSort linkedSort = new LinkedSort();
        Node tempHead = linkedSort.new Node(-1, null);
        for (int i = arr.length - 1; i >= 0 ; i--) {
            tempHead.next = linkedSort.new Node(arr[i], tempHead.next);
        }
        tempHead.next = linkedSort.solve(tempHead.next);
        Node p = tempHead.next;
        while (p != null) {
            System.out.println(p.v);
            p = p.next;
        }
    }

    Node solve(Node head){
        if (head == null) return null;
        Node tempHead = new Node(-1, head);
        // 对奇数节点排序
        tempHead.next = sort(head, 2, SORT_TYPE.ASCEND);
        // 对偶数节点排序
        if (tempHead.next.next != null)
            tempHead.next.next = sort(tempHead.next.next, 2, SORT_TYPE.DESCEND);
        return tempHead.next;
    }

    /**
     * 链表排序
     * @param head
     * @param k
     * @return 返回排序后的链表头结点
     */
    Node sort(Node head, int k, SORT_TYPE type) {
        if (head == null) return null;
        Node tempHead = new Node(-1, head);
        Node p = head;
        Node pre = tempHead;
        out : while (p != null) {
            p = getLimit(p, k, type);
            pre.next = p;
            for (int i = 0; i < k ; i++) {
                if (p.next == null) break out;
                pre = p;
                p = p.next;
            }
        }
        return tempHead.next;
    }


    /**
     * 查找最小值交换到头节点
     * @param head 头结点
     * @param k 步长
     * @return 新的头结点
     */
    Node getLimit(Node head, int k, SORT_TYPE type) {
        if (head == null) return null;
        Node tempHead = new Node(-1, head);
        Node p = head, ans = head;
        Node pre = tempHead, preAns = tempHead;
        // 查找最小值,如果剩余不足一跨则退出
        out : while (p != null) {
            if (type.compareNode(ans, p) < 0) {
                preAns = pre;
                ans = p;
            }
            for (int i = 0; i < k ; i++) {
                if (p.next == null) break out;
                pre = p;
                p = p.next;
            }
        }
        // 交换
        tempHead.next = ans;
        preAns.next = head;
        Node temp = head.next;
        head.next = ans.next;
        ans.next = temp;
        return tempHead.next;
    }

    enum SORT_TYPE{
        ASCEND {
            int compareNode(Node n1, Node n2) {
                return n1.v - n2.v;
            }
        },
        DESCEND {
            int compareNode(Node n1, Node n2) {
                return n2.v - n1.v;
            }
        };

        int compareNode(Node n1, Node n2) {
            return n1.v - n2.v;
        }


    }


    class Node{
        int v;
        Node next;

        public Node(int v) {
            this.v = v;
            this.next = null;
        }

        public Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }


    }
}
