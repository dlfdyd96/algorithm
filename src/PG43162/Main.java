package PG43162;

public class Main {
    public static void main(String[] args) {
        int s = 0;

        s = new Solution().solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        System.out.println(s);

        s = new Solution().solution(3, new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}});
        System.out.println(s);

        s = new Solution().solution(3, new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        System.out.println(s);

        s = new Solution().solution(4, new int[][]{{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 1, 1}});
        System.out.println(s);

//        s = new Solution().solution(4, new int[][]{{1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}});
//        System.out.println(s);
//
//        s = new Solution().solution(4, new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}});
//        System.out.println(s);
    }
}

//enum DIRECITON {
//    LEFT(0, -1), RIGHT(0, 1), TOP(-1, 0), BOTTOM(1, 0);
//
//    private final int i;
//    private final int j;
//
//
//    DIRECITON(int i, int j) {
//        this.i = i;
//        this.j = j;
//    }
//
//    public static next
//}
//[
// [1, 1, 0, 0],
// [1, 1, 0, 0],
// [0, 0, 1, 1],
// [0, 0, 1, 1]]

// 직각 삼각형으로 순회하면서 visited 되는 건지 확인.
class Solution {

    private final int[][] direction = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    private boolean[][] visited;
    private int[][] computers;
    private int n;

    public int solution(int n, int[][] computers) {
        init(n, computers);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i][i]) {
                answer++;
                dfs(i);
            }
        }


        return answer;
    }

    private void init(int n, int[][] computers) {
        visited = new boolean[n][n];
        this.computers = computers;
        this.n = n;
    }

    public void dfs(int current) {

        for (int i = 0; i < n; i++) {
            if (!visited[current][i] && computers[current][i] == 1) {
                visited[current][i] = visited[i][current] = true;
                dfs(i);
            }
        }
    }

//    public void dfs(int i, int j) {
//        if (visited[i][j]) return; // 방문 했으면 ㅂ ㅂ
//
//        visited[i][j] = true; // 방문 체크
//        visited[j][i] = true; // 방문 체크
//
//        for (int d = 0; d < 4; d++) {
//            int nextI = i + direction[d][0];
//            int nextJ = j + direction[d][1];
//
//            if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n) continue;
//            if (computers[nextI][nextJ] == 0 || visited[nextI][nextJ]) continue; // 연결 안되어있으면 넘어가기
//            dfs(nextI, nextJ);
//        }
//    }
}
