package xp.oj.search;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 宽搜
 *
 * 问题描述
 * 求5*5的迷宫中从左上角到右下角最少要移动多少次，每次只能向上或向下移动一格。
 * 问题分析
 * BFS每一层代表移动一次，最早到达终点的肯定是最短路径。同一个位置第一次访问才可能最快到达终点，
 * 所以每个位置只能访问一次。
 */
public class MiGong3894 {

    public static void main(String[] args) {
        new MiGong3894().solve();
    }
    int[][] map = new int[5][5];
    boolean[][] visit = new boolean[5][5];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = in.nextInt();
            }
        }
        Node now = count();
        Node head = new Node(-1, -1, null);
        while (now != null) {
            Node t = now.pre;
            now.pre = head.pre;
            head.pre = now;
            now = t;
        }
        now = head.pre;
        while (now != null) {
            System.out.printf("(%d, %d)\n", now.x, now.y);
            now = now.pre;
        }

        out.flush();
    }

    private Node count() {
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(0, 0, null));
        map[0][0] = 1;
        Node now = null;
        while (!queue.isEmpty()) {
            now = queue.pollFirst();
            int x = now.x;
            int y = now.y;
            if (x == 4 && y == 4) break;
            if (x > 0 && map[x - 1][y] == 0) {
                queue.offer(new Node(x - 1, y, now));
                map[x - 1][y] = 1;
            }
            if (y > 0 && map[x][y - 1] == 0) {
                queue.offer(new Node(x, y - 1, now));
                map[x][y - 1] = 1;
            }
            if (x + 1 < 5 && map[x + 1][y] == 0) {
                queue.offer(new Node(x + 1, y, now));
                map[x + 1][y] = 1;
            }
            if (y + 1 < 5 && map[x][y + 1] == 0) {
                queue.offer(new Node(x, y + 1, now));
                map[x][y + 1] = 1;
            }
        }
        return now;
    }

    class Node{
        int x, y;
        Node pre;

        public Node(int x, int y, Node pre) {
            this.x = x;
            this.y = y;
            this.pre = pre;
        }
    }
}
