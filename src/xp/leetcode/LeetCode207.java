package xp.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> head = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            head.add(new ArrayList<Integer>());
        }
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];
            inDegrees[to]++;
            head.get(from).add(to);
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                que.add(i);
            }
        }
        while (!que.isEmpty()) {
            numCourses--;
            int from = que.remove();
            for (int to : head.get(from)) {
                if (--inDegrees[to] == 0) {
                    que.add(to);
                }
            }
        }
        return numCourses == 0;

    }
}
