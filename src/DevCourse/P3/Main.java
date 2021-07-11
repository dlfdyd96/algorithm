package DevCourse.P3;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Solution s  = new Solution();
//        int result = s.solution(new int[]{1,3,2,5,4}, 6);
        int[][] inputs = new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        int result = s.solution(9, inputs);
        System.out.print(result);
    }
}

/**
 * 1. 모든 줄에대해서 하나씩 끊어본다.
 * 2. visited와 dfs를 활용해서 연결된 간선 구하기.
 * 2-1 방문했으면 넘어가
 */
class Solution {
    static int minDiff = Integer.MAX_VALUE;
    public int solution(int n, int[][] wires) {
        int answer = -1;

        // 0. 초기화 작업
        boolean[][] map = new boolean[n + 1][n + 1];
        for (int[] current: wires) {
            int r = current[0];
            int c = current[1];
            map[r][c] = map[c][r] = true;
        }

        // 1. simulation
        simulation(map, wires);

        answer = minDiff;

        return answer;
    }

    static void simulation(boolean[][] map, int[][] wires) {
        for (int[] current: wires) {
            int r = current[0];
            int c = current[1];
            map[r][c] = map[c][r] = false;
            getMinDiff(map);
            map[r][c] = map[c][r] = true;
        }
    }

    static void getMinDiff(boolean[][] map) {
        // 방문 했거나 간선 정보가 없으면 넘어가기
        int current = -1;
        boolean[] visited = new boolean[map.length];
        for (int i = 1; i < map.length; i++) {
            if(visited[i]) continue;
            int connectionCnt = getConnectionCount(visited, map, i);
            if(connectionCnt > 0) {
                if(current > 0) {
                    minDiff = Math.min(minDiff, Math.abs(current - connectionCnt));
                    break;
                }
                else
                    current = connectionCnt;
            }
        }
    }

    static int getConnectionCount(boolean[] visited, boolean[][] map, int point) {
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();

        q.add(point);
        visited[point] = true;
        cnt++;

        while(!q.isEmpty()) {
            int current = q.poll();
            for (int i = 1; i < map.length; i++) {
                if(map[current][i]) { // 연결되어있고
                    if (!visited[i]) // 방문 안한 정점
                    {
                        q.add(i);
                        visited[i] = true;
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }
}