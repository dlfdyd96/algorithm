package BJ9372;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
    This should solve with floyd warshall algorithm.
*/
public class BJ9372 {
    static int TEST_CASE;
    static final int INF = 10000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TEST_CASE = Integer.parseInt(st.nextToken());
        for (int t = 0; t < TEST_CASE; t++) {
            st = new StringTokenizer(br.readLine());
            int N, M;
            N = Integer.parseInt(st.nextToken()); // 국가 수
            M = Integer.parseInt(st.nextToken()); // 비행기 수
            int[][] field = new int[N][N];

            // 간선 넣기.
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
            }

            bw.write(N - 1+ "\n");

        }

        bw.flush();
        bw.close();
        br.close();

    }

}
