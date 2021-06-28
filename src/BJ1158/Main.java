package BJ1158;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Problem Solving : 시뮬레이션으로해보자
 *
 * currentCnt = 0 -> N
 * visited[]
 */
public class Main {
    static int N, K;
    public static void main(String args[]) throws Exception {
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Solution solution = new Solution();

        solution.soluition(N, K);

        br.close();
    }
}

class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void soluition(int N, int K) throws Exception {
        int cnt = 0;
        int kCnt = 0;

        Queue<Integer> q = new LinkedList<>();
        bw.write("<");

        // 0. queue idx 넣기.
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        while(!q.isEmpty()) {
            kCnt++;
            int current = q.poll();
            if (kCnt == K) {
                kCnt = 0;
                cnt++;
                bw.write(current + ((cnt != N) ? ", ": "" ));
                continue;
            }
            q.add(current);
        }
        bw.write(">");

        bw.flush();
        bw.close();
    }
}
