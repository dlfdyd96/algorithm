package BJ19236;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
1. 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.
2. 물고기는 번호가 작은 물고기부터 순서대로 이동한다.
    2-1. 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸
    2-2. 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸
    2-3. 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Solution s = new Solution();
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int fishNumber = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                s.setFish(i, j, fishNumber - 1, direction - 1);
            }
        }

        s.initShark();
        int ans = s.getSumOfEatFishNumber();
//        s.simulation();

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Solution {
    private int[][] board;
    private int[][] direction;
    private List<Node> fishes = new ArrayList<>();
    private Node shark;

    private int sumOfEatFishNumber;

    public Solution() {
        board = new int[4][4];
        direction = new int[][]{
                {-1, 0},
                {-1, -1},
                {0, -1},
                {1, -1},
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1}
        };
        for (int i = 0; i < 16; i++) {
            fishes.add(new Node());
        }
        sumOfEatFishNumber = 0;
    }

    public int getSumOfEatFishNumber() {
        return sumOfEatFishNumber;
    }

    public void setFish(int r, int c, int fishNumber, int direction) {
        Node current = new Node(r, c, direction);
        fishes.set(fishNumber, current);
        board[r][c] = fishNumber;
    }

    public void initShark() {
        shark = new Node(0, 0, fishes.get(board[0][0]).direction);

        sumOfEatFishNumber += (board[shark.r][shark.c] + 1);
        fishes.get(board[shark.r][shark.c]).enable = false;
        board[shark.r][shark.c] = -1;
        moveFish();
        simulation(sumOfEatFishNumber);
    }

    private void moveFish() {
        // 1번 부터 이동
        for (int i = 0; i < 16; i++) {
            Node current = fishes.get(i);
            if (!current.enable) continue;
            while (true) {
                int nextR = current.r + direction[current.direction][0];
                int nextC = current.c + direction[current.direction][1];
                if (nextR < 0 || nextR >= 4 || nextC < 0 || nextC >= 4){
                    current.direction = (current.direction + 1) % 8; //
                    continue;
                }
                if (nextR == shark.r && nextC == shark.c) {
                    current.direction = (current.direction + 1) % 8; //
                    continue;
                }

                // 1. 둘이를 바꿔야함.
                if (board[nextR][nextC] < 0) { // 빈칸
                    board[nextR][nextC] = i;
                    board[current.r][current.c] = -1;
                    current.r = nextR;
                    current.c = nextC;
                } else {
                    Node targetFish = fishes.get(board[nextR][nextC]);
                    int tmp = board[nextR][nextC];
                    board[nextR][nextC] = i;
                    board[current.r][current.c] = tmp;

                    targetFish.r = current.r;
                    targetFish.c = current.c;
                    current.r = nextR;
                    current.c = nextC;
                }
                break;
            }
        }
    }

    private int[][] copyboard() {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }


    public void simulation(int currentSum) {

        List<Integer> candidates = findCandidate();
        // 0. 기저 조건 = shark가 이동할 수 없다.
        if (candidates.isEmpty()) {
            // 최대 값 비교
            sumOfEatFishNumber = Math.max(sumOfEatFishNumber, currentSum);
            return;
        }

        for (int candidate: candidates) {
            // init
            Node currentFish = fishes.get(candidate);

            // before
            int[][] originBoard = copyboard();
            List<Node> originFishes = copyFishes();
            Node originShark = new Node(shark.r, shark.c, shark.direction);

            shark.direction = currentFish.direction;
            shark.r = currentFish.r;
            shark.c = currentFish.c;
            board[currentFish.r][currentFish.c] = -1;
            currentFish.enable = false;

            moveFish();

            // recursive
            simulation(currentSum + candidate + 1);

            // after
            // board[candidate.r][candidate.c] = fishNumber;
            shark.r = originShark.r;
            shark.c = originShark.c;
            shark.direction = originShark.direction;

            board = originBoard;
            fishes = originFishes;
        }
    }

    private List<Node> copyFishes() {
        List<Node> newFishes = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Node fish = fishes.get(i);
            newFishes.add(
                    new Node(
                            fish.r,
                            fish.c,
                            fish.direction,
                            fish.enable
                    )
            );
        }
        return newFishes;
    }

    public List<Integer> findCandidate() {
        List<Integer> result = new ArrayList<>();

        int nextR = shark.r + direction[shark.direction][0];
        int nextC = shark.c + direction[shark.direction][1];
        while(true) {
            if (nextR < 0 || nextR >= 4 || nextC < 0 || nextC >= 4) break;
            if (board[nextR][nextC] < 0) {

                nextR = nextR + direction[shark.direction][0];
                nextC = nextC + direction[shark.direction][1];
                continue;
            } // 빈칸이면 못감
            if (!fishes.get(board[nextR][nextC]).enable) {

                nextR = nextR + direction[shark.direction][0];
                nextC = nextC + direction[shark.direction][1];
                continue;
            } //
            result.add(board[nextR][nextC] );
            nextR = nextR + direction[shark.direction][0];
            nextC = nextC + direction[shark.direction][1];
        }

        return result;
    }
}

class Node {
    public int r;
    public int c;
    public int direction;
    public boolean enable;

    public Node() {
    }

    public Node(int r, int c, int direction) {
        this.r = r;
        this.c = c;
        this.direction = direction;
        enable = true;
    }

    public Node(int r, int c, int direction, boolean enable) {
        this.r = r;
        this.c = c;
        this.direction = direction;
        this.enable = enable;
    }
}