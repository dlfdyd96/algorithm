package BJ14697;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int[] rooms = new int[3];
    static int[] upperBounds = new int[3];
    static int A, B, C, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        rooms[0] = Integer.parseInt(st.nextToken());
        rooms[1] = Integer.parseInt(st.nextToken());
        rooms[2] = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(st.nextToken());

        upperBounds[0] = (int) Math.ceil(N / rooms[0]);
        upperBounds[1] = (int) Math.ceil(N / rooms[1]);
        upperBounds[2] = (int) Math.ceil(N / rooms[2]);
//
//        System.out.println(upperBounds[0]);
//        System.out.println(upperBounds[1]);
//        System.out.println(upperBounds[2]);

        int ans = solution();

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

     static int solution() {
        for (int a = 0; a <= upperBounds[0]; a++) {
            for (int b = 0; b <= upperBounds[1]; b++) {
                for (int c = 0; c <= upperBounds[2]; c++) {
                    if (
                            ((a * rooms[0]) + (b * rooms[1]) + (c * rooms[2])) == N
                    ) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}


