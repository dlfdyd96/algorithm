package sendcorp.P4;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(
//                        2, 2, 5
                        3, 4, 20
                )
        );
    }
}

class Solution {
    private int N, K, M;
    private double[][] dp;
    private StringBuilder sb = new StringBuilder();

    public String solution(int n, int m, int k) {
        N = n;
        M = m;
        K = k;

        dp = new double[N + 1][M + 1];


        if (K > numWord(N, M)) {
            return "";
        } else {
            getWord(N, M, K - 1);
        }

        return sb.toString();
    }

    double numWord(int A, int Z) {
        if (A == 0 || Z == 0)
            return 1;
        if (dp[A][Z] != 0)
            return dp[A][Z];
        return dp[A][Z] = Double.min(numWord(A - 1, Z) + numWord(A, Z - 1), 1000000001);
    }

    void getWord(int A, int Z, double before) {
        if (A == 0) {
            for (int i = 0; i < Z; i++)
                sb.append(')');
            return;
        }
        if (Z == 0) {
            for (int i = 0; i < A; i++)
                sb.append('(');
            return;

        }
        double tmp = numWord(A - 1, Z);

        if (before < tmp) {
            sb.append('(');
            getWord(A - 1, Z, before);
        } else {
            sb.append(')');
            getWord(A, Z - 1, before - tmp);
        }
    }
}