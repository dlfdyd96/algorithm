package BJ14712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int answer = 0;
    static boolean[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][M+1];

        dfs(1, 1);

        System.out.print(answer);
        // br.close();
    }

    static void dfs(int r, int c) {
        if(r == N && c == M + 1) {
            answer++;
            return;
        }

        for (int i = r; i <= N; i++) {
            for (int j = (i==r ? c : 1); j <= M; j++) {
                if (check(i, j)) continue;

                map[i][j] = true;
                dfs(i, j + 1);
                map[i][j] = false;
            }
        }
        answer++;
    }

    static boolean check(int r, int c) {
        return map[r-1][c-1] && map[r-1][c] && map[r][c-1];
    }
}
