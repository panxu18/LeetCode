package xp.pan.tenxun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Test0823_1 {
    private static int[] arr;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        String str = in.readLine();
        String[] strArr = str.split(" ");
        int n = Integer.valueOf(strArr[0]);
        int k = Integer.valueOf(strArr[1]);
        str = in.readLine();
        strArr = str.split(" ");
        Node head = new Node(0, null);
        Node tail = head;
        for (int i = 0; i < n; i++) {
            if (i == k - 1) {
                continue;
            }
            tail.next = new Node(Integer.valueOf(strArr[i]), null);
            tail = tail.next;
        }
        StringBuilder sb = new StringBuilder();
        Node p = head.next;
        while (p != null) {
            sb.append(p.val);
            sb.append(" ");
            p = p.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        out.println(sb.toString());
        out.flush();
    }

    static class Node{
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
