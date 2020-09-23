package xp.leetcode;

import java.util.Scanner;
import java.util.TreeSet;

public class Lanqiao9 {

    public static void main(String[] args) {
        new Lanqiao9().solve();
    }


    static Edge[] head;
    static Edge[] edges;
    int eCnt;
    static Node[] nodes;
    private void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        init(n);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int h = in.nextInt();
            nodes[i] = new Node(x, y, h);
            // 添加边
            for (int j = 0; j < i; j++) {
                addEdge(i, j);
            }
        }
        System.out.printf("%.2f\n", dijsktla(0, n));
    }

    private void init(int n) {
        head = new Edge[n];
        edges = new Edge[n * n * 2];
        nodes = new Node[n];
    }

    double dijsktla(int i, int n) {
        TreeSet<Distance> distanceSet = new TreeSet<Distance>();
        Distance[] distances = new Distance[n];
        double sum = 0f;


        // 初始化
        Edge e = head[i];
        while (e != null) {
            Distance d = new Distance(e.to, e.cost, e.cost);
            distanceSet.add(d);
            distances[e.to] = d;
            e = e.next;
        }

        while (!distanceSet.isEmpty()) {
            Distance min = distanceSet.pollFirst();
            // 更新其他节点路径
            e = head[min.to];
            while (e != null){
                if (distances[e.to] != null) {
                    if (e.cost + min.cost < distances[e.to].cost) {
                        distances[e.to].cost = e.cost + min.cost;
                        distances[e.to].preCost = e.cost;
                    }

                }
                e = e.next;
            }
            sum += min.preCost;
            distances[min.to] = null;
        }
        return sum;


    }


    void addEdge(int i, int j) {
        double cost = getCost(nodes[i], nodes[j]);
        // i -> j
        edges[eCnt] = new Edge(i, j, cost, head[i]);
        head[i] = edges[eCnt++];
        // j->i
        edges[eCnt] = new Edge(j, i, cost, head[j]);
        head[j] = edges[eCnt++];
    }

    double getCost(Node n1, Node n2) {
        int x = (n1.x - n2.x) * (n1.x - n2.x);
        int y = (n1.y - n2.y) * (n1.y - n2.y);
        int h = (n1.h - n2.h) * (n1.h - n2.h);
        return Math.sqrt(x + y) + h;
    }


    class Distance implements Comparable<Distance>{
        int to;
        double cost;
        double preCost;

        public Distance(int to, double cost, double preCost) {
            this.preCost = preCost;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Distance o) {
            if (cost - o.cost > 0) {
                return 1;
            } else if (cost - o.cost < 0)
                return -1;
            else
                return 0;
        }

        @Override
        public boolean equals(Object o) {
            Distance distance = (Distance) o;
            return to == distance.to;
        }
    }

    class Edge{
        int from;
        int to;
        double cost;
        Edge next;

        public Edge(int from, int to, double cost, Edge next) {
            this.from = from;
            this.to = to;
            this.cost = cost;
            this.next = next;
        }
    }

    class Node {
        int x;
        int y;
        int h;

        public Node(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}
