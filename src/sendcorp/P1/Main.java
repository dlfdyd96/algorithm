package sendcorp.P1;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();

        int ans = s.solution(
                new int[][]{
//                        {1, 3},
//                        {3, 1}
                        {2, 2},
                        {1, 3}
                }
        );

        System.out.println(ans + "");
    }
}


class Solution {
    private final int[][] direction = new int[][]{
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
            {-1, -1},
            {-1, 1},
            {1, 1},
            {1, -1},
    };
    private static final int MAP_SIZE = 3;

    private boolean[][] map;

    private void init(int[][] locations) {
        map = new boolean[MAP_SIZE + 1][MAP_SIZE + 1];
        for (int[] location : locations) {
            int r = location[0];
            int c = location[1];
            map[r][c] = true;
        }
    }

    public int solution(int[][] locations) {
        int answer = 0;

        init(locations);

        answer = clickCenter();

        return answer;
    }

    private int clickCenter() {
        final int centerRow = 2;
        final int centerCol = 2;

        int bomCnt = 0;

        if (map[centerRow][centerCol]) return -1;

        for (int[] d : direction) {
            int nextR = centerRow + d[0];
            int nextC = centerCol + d[1];

            if (map[nextR][nextC]) bomCnt++;
        }

        return bomCnt;
    }
}