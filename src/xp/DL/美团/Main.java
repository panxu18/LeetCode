package xp.DL.美团;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int MAX_NODE_COUNT = 100;

    private static List<String> nodes = new ArrayList<>();

    private static int[][] adjMatrix = new int[MAX_NODE_COUNT][MAX_NODE_COUNT];

    private static int addNode(String nodeName) {
        if (!nodes.contains(nodeName)) {
            if (nodes.size() >= MAX_NODE_COUNT) {
                return -1;
            }
            nodes.add(nodeName);
            return nodes.size() - 1;
        }
        return nodes.indexOf(nodeName);
    }

    public static void addLine(String startNode, String endNode) {
        int startIndex = addNode(startNode);
        int endIndex = addNode(endNode);

        if (startIndex >= 0 && endIndex >= 0) {
            adjMatrix[startIndex][endIndex] = 1;
        }
    }

    public static List<String> find() {
        ArrayList<Integer> trace = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        if (adjMatrix.length > 0) {
            findCycle(0, trace, res);
        }
        return res;
    }

    private static void findCycle(int v, ArrayList<Integer> trace, ArrayList<String> res) {
        int j;
        if ((j = trace.indexOf(v)) != -1) {
            StringBuilder sb = new StringBuilder();
            String startNode = nodes.get(trace.get(j));
            while (j < trace.size()) {
                sb.append(nodes.get(trace.get(j))).append("-");
                j++;
            }
            res.add("cycle:" + sb.toString() + startNode);
            return;
        }
        trace.add(v);
        for (int i = 0; i < nodes.size(); i++) {
            if (adjMatrix[v][i] == 1) {
                findCycle(i, trace, res);
            }
        }
        trace.remove(trace.size() - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String a = sc.next();
            String b = sc.next();
            Main.addLine(a, b);
        }
        List<String> res = Main.find();
        System.out.println(res.size());
    }
}
