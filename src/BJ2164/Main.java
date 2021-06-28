package BJ2164;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Solution s = new Solution();

        int N = Integer.parseInt(st.nextToken());
        int ans = s.solution(N);

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Solution {
    int solution(int N) {
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N ; i++) {
            q.add(i);
        }

        if(q.size() == 1)
            ans = q.poll();
        else
            while(!q.isEmpty()) {
                int discardedCard = q.poll();
                if (q.size() == 1) {
                    ans = q.poll();
                    break;
                }
                int movedToEndCard = q.poll();
                q.add(movedToEndCard);
            }
        return ans;
    }
}