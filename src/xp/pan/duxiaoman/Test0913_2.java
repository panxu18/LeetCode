package xp.pan.duxiaoman;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 陷阱迷宫
 * 在迷宫中#表示陷阱，陷阱需要花费M步，1表示墙壁，墙壁无法穿过，计算从起点左上角到达终点右下角最少需要的步。
 */
public class Test0913_2 {
    private static String[][] map = new String[105][];
    private static int[][] dis = new int[105][105];
    private static int N, M;
    private static int INF = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        in.nextLine();
        for (int i = 0; i < N; i++) {
            map[i] = in.nextLine().split("");
        }
        bfs();
        System.out.println(dis[N - 1][N - 1] == INF ? "No solution" : dis[N - 1][N - 1]);
    }

    private static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static void bfs() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dis[i], INF);
        }
        Queue<Node> que = new LinkedList<>();
        if ("0".equals(map[0][0])) {
            que.add(new Node(0, 0));
            dis[0][0] = 0;
        }
        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !"1".equals(map[nx][ny])) {
                    int newDis = dis[cur.x][cur.y] + ("0".equals(map[nx][ny]) ? 1 : M + 1);
                    if (newDis < dis[nx][ny]) {
                        que.add(new Node(nx, ny));
                        dis[nx][ny] = newDis;
                    }
                }
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
