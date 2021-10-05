package BJ2475;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        for (int i = 0; i < 5; i++) {
            int current = Integer.parseInt(st.nextToken());
            ans += current * current;
        }


        bw.write(ans % 10 + "");



        bw.flush();
        bw.close();
        br.close();
    }
}
