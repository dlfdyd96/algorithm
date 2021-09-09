package PG43105;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int a = new Solution().solution(new int[][]{
//                {7}, // d[0] i = 0;
//                {3, 8}, // d[1][0] = Max(d[0][0]) <- 첫 번째는 . // d[1][1] = Max(d[0][0], d[0][1])
//                {8, 1, 0},
//                {2, 7, 4, 4},
//                {4, 5, 2, 6, 5}
//        });

        int b = new Solution().solution(new int[][]{
                {7}, // d[0] i = 0;
                {3, 8}, // d[1][0] = Max(d[0][0]) <- 첫 번째는 . // d[1][1] = Max(d[0][0], d[0][1])

        });
        System.out.println(b);
    }
}

/*

 */
class Solution {
    private int[][] dp;

    public int solution(int[][] triangle) {
        init(triangle);

        int answer = simulation(triangle);

        return answer;
    }

    private int simulation(int[][] triangle) {
        int maxThroughWay = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {

            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j];
                if (j == 0) {
                    dp[i][j] += dp[i-1][j];
                } else if (j == i) {
                    dp[i][j] += dp[i-1][j-1];
                } else {
                    dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }

                maxThroughWay = Math.max(dp[i][j], maxThroughWay);
            }
        }

        return maxThroughWay;
    }

    private void init(int[][] triangle) {
        int length = triangle.length;

        dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = triangle[0][0];
    }
}