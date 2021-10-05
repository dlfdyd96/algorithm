package BJ20061;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Solution s = new Solution();

        int N;
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            s.putBlock(t, x, y);
        }

        bw.write(s.getScore() + "\n");
        bw.write(s.getBoardBlocks() + "");


        bw.flush();
        bw.close();
        br.close();
    }
}

class Solution {
    private int[][] blueBoard;
    private int[][] greenBoard;
    private int score;

    public Solution() {
        blueBoard = new int[4][6];
        greenBoard = new int[6][4];

        score = 0;
    }

    public void putBlock(int t, int x, int y) {
        putBlockAtBlue(t, x, y);
        putBlockAtGreen(t, x, y);

        for (int i = 2; i < 6; i++) {
            isFilledGreenRow(i);
            isFilledBlueCol(i);
        }
        for (int i = 0; i < 2; i++) {
            isAtLightBlue();
            isAtLightGreen();
        }
    }

    public void putBlockAtGreen(int t, int x, int y) {
        switch (t) {
            case 1: // 네모 하나
                for (int i = 1; i < 6; i++) {
                    if (i == 5) {
                        if (greenBoard[i][y] != 0) { // 마지막이
                            greenBoard[i - 1][y] = 1;
                            break;
                        } else {
                            greenBoard[i][y] = 1;
                        }
                    } else {
                        if (greenBoard[i][y] != 0) {
                            greenBoard[i - 1][y] = 1;
                            break;
                        }
                    }
                }

                break;
            case 2: // ㅡ
                for (int i = 1; i < 6; i++) {
                    if (i == 5) {
                        if (greenBoard[i][y] != 0 || greenBoard[i][y + 1] != 0) { // 마지막이
                            greenBoard[i - 1][y] = 1;
                            greenBoard[i - 1][y + 1] = 1;
                            break;
                        } else {
                            greenBoard[i][y] = 1;
                            greenBoard[i][y + 1] = 1;
                        }
                    } else {
                        if (greenBoard[i][y] != 0 || greenBoard[i][y + 1] != 0) {
                            greenBoard[i - 1][y] = 1;
                            greenBoard[i - 1][y + 1] = 1;
                            break;
                        }
                    }
                }
                break;
            case 3: // ㅣ
                for (int i = 1; i < 6; i++) {
                    if (i == 5) {
                        if (greenBoard[i][y] != 0) { // 마지막이
                            greenBoard[i - 1][y] = 1;
                            greenBoard[i - 2][y] = 1;
                            break;
                        } else {
                            greenBoard[i][y] = 1;
                            greenBoard[i - 1][y] = 1;
                        }
                    } else {
                        if (greenBoard[i][y] != 0) {
                            greenBoard[i - 1][y] = 1;
                            greenBoard[i - 2][y] = 1;
                            break;
                        }
                    }
                }
                break;
        }
    }

    public void putBlockAtBlue(int t, int x, int y) {
        switch (t) {
            case 1: // 네모 하나
                for (int i = 1; i < 6; i++) {
                    if (i == 5) {
                        if (blueBoard[x][i] != 0) { // 마지막이
                            blueBoard[x][i - 1] = 1;
                            break;
                        } else {
                            blueBoard[x][i] = 1;
                        }
                    } else {
                        if (blueBoard[x][i] != 0) {
                            blueBoard[x][i - 1] = 1;
                            break;
                        }
                    }
                }

                break;
            case 2: // ㅡ
                for (int i = 1; i < 6; i++) {
                    if (i == 5) {
                        if (blueBoard[x][i] != 0) { // 마지막이
                            blueBoard[x][i - 1] = 1;
                            blueBoard[x][i - 2] = 1;
                            break;
                        } else {
                            blueBoard[x][i] = 1;
                            blueBoard[x][i - 1] = 1;
                        }
                    } else {
                        if (blueBoard[x][i] != 0) { // 마지막이
                            blueBoard[x][i - 1] = 1;
                            blueBoard[x][i - 2] = 1;
                            break;
                        }
                    }
                }
                break;
            case 3: // ㅣ
                for (int i = 1; i < 6; i++) {
                    if (i == 5) {
                        if (blueBoard[x][i] != 0 || blueBoard[x + 1][i] != 0) { // 마지막이
                            blueBoard[x][i - 1] = 1;
                            blueBoard[x + 1][i - 1] = 1;
                            break;
                        } else {
                            blueBoard[x][i] = 1;
                            blueBoard[x + 1][i] = 1;
                        }
                    } else {
                        if (blueBoard[x][i] != 0 || blueBoard[x + 1][i] != 0) { // 마지막이
                            blueBoard[x][i - 1] = 1;
                            blueBoard[x + 1][i - 1] = 1;
                            break;
                        }
                    }
                }
                break;
        }
    }

    public void isFilledBlueCol(int col) {
        // col 다 채워졌는지 확인
        // 점수 올리기
        // 줄 없애고 한 칸 다 내리기
        for (int i = 0; i < 4; i++) {
            if (blueBoard[i][col] == 0) return;
        }
        score++;
        for (int i = col; i >= 1; i--) {
            for (int j = 0; j < 4; j++) {
                blueBoard[j][i] = blueBoard[j][i - 1];
            }
        }
        for (int i = 0; i < 4; i++) {
            blueBoard[i][0] = 0;
        }
    }

    public void isFilledGreenRow(int row) {
        // col 다 채워졌는지 확인
        // 점수 올리기
        // 줄 없애고 한 칸 다 내리기
        for (int i = 0; i < 4; i++) {
            if (greenBoard[row][i] == 0) return;
        }
        score++;
        for (int i = row; i >= 1; i--) {
            for (int j = 0; j < 4; j++) {
                greenBoard[i][j] = greenBoard[i - 1][j];
            }
        }
        for (int i = 0; i < 4; i++) {
            greenBoard[0][i] = 0;
        }
    }

    public void isAtLightBlue() {
        // 맞는지 판별
        // 맨 마지막줄 없애고 한칸 댕기기

        boolean hasBlock = false;
        for (int i = 0; i < 4; i++) {
            if (blueBoard[i][1] == 1) {
                hasBlock = true;
                break;
            }
        }
        if (!hasBlock) return;

        for (int i = 5; i >= 1; i--) {
            for (int j = 0; j < 4; j++) {
                blueBoard[j][i] = blueBoard[j][i - 1];
            }
        }
        for (int i = 0; i < 4; i++) {
            blueBoard[i][0] = 0;
        }
    }

    public void isAtLightGreen() {
        // 맞는지 판별
        // 맨 마지막줄 없애고 한칸 댕기기

        boolean hasBlock = false;
        for (int i = 0; i < 4; i++) {
            if (greenBoard[1][i] == 1) {
                hasBlock = true;
                break;
            }
        }
        if (!hasBlock) return;

        for (int i = 5; i >= 1; i--) {
            for (int j = 0; j < 4; j++) {
                greenBoard[i][j] = greenBoard[i - 1][j];
            }
        }
        for (int i = 0; i < 4; i++) {
            greenBoard[0][i] = 0;
        }
    }

    public int getScore() {
        return score;
    }

    public int getBoardBlocks() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                sum += (greenBoard[i][j] + blueBoard[j][i]);
            }
        }
        return sum;
    }


}

/*
9
2 1 0
2 1 0
2 1 0
2 1 0
2 1 0
3 0 2
3 0 2
3 0 3
3 0 3
 */
