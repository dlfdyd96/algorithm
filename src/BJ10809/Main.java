package BJ10809;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int alphabetSize = 'z' - 'a' + 1;
        int[] charSet = new int[alphabetSize];
        for (int i = 0; i < alphabetSize; i++) {
            charSet[i] = -1;
        }

        char[] wordCharArray = st.nextToken().toCharArray();

        for (int i = 0; i < wordCharArray.length; i++) {
            if (charSet[wordCharArray[i] - 'a'] == -1) {
                charSet[wordCharArray[i] - 'a'] = i;
            }
        }

        for (int i = 0; i < alphabetSize; i++) {
            bw.write(charSet[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
