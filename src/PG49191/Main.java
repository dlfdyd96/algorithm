package PG49191;

public class Main {
    public static void main(String[] args) {

        int ans = new Solution().solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}
        );
        System.out.println(ans  );
    }
}

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] graph = new boolean[n+1][n+1];
        for (int[] result: results) {
            graph[result[0]][result[1]] = true;
        }

        for (int stopOver = 1; stopOver <= n; stopOver++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (graph[start][stopOver] && graph[stopOver][end])
                        graph[start][end] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] || graph[j][i]) cnt++;
            }
            if (cnt == n - 1)
                answer++;
        }


        return answer;
    }
}