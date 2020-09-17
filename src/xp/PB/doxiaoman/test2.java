package xp.PB.doxiaoman;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/9/13 - 20:49
 */
public class test2 {
    public static int minTime;
    public static int n,m;
    public static void main(String[] args) {
        Scanner sacn = new Scanner(System.in);
        n = sacn.nextInt();
         m = sacn.nextInt();
        sacn.nextLine();
        String[][] arr = new String[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = sacn.nextLine().split("");
        }
        bfs(arr);
        System.out.println(minTime);
    }
    public static void bfs(String[][] arr){
        int[][] distance = new int[n][n];
        int dx[] = {1,0,-1,0},dy[]={0,1,0,-1};
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0));
        while(!queue.isEmpty()){
            Point p = queue.poll();
            if(p.x==n-1 && p.y==n-1) break;
            for(int i=0;i<4;i++){
                int x = p.x+dx[i];
                int y = p.y+dy[i];
                if(x>=0 &&y>=0 &&x<n&&y<n && arr[x][y] !="1" &&distance[x][y]==0){
                    queue.add(new Point(x,y));
                    if(arr[x][y] !="0"){
                        distance[x][y] = distance[p.x][p.y]+1;
                    }else if(arr[x][y] !="#"){
                        distance[x][y] = distance[p.x][p.y]+1+m;
                    }


                }

            }
        }
    }

    static  class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
