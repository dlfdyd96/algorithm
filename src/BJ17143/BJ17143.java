package BJ17143;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ17143 {
    static int R, C, M;
    static int[][] field;
    // static ArrayList<Shark> sharks = new ArrayList<>();
    static Map<Integer, Shark> sharks;
    static Map<Integer, Integer[]> direction;
    static int fisherMan;
    static int sumOfCatchedShark;

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        sharks = Collections.synchronizedMap(new HashMap<>());
        direction = Collections.synchronizedMap(new HashMap<>());
        fisherMan = 0;
        sumOfCatchedShark = 0;

        direction.put(1, new Integer[]{-1, 0});
        direction.put(2, new Integer[]{1, 0});
        direction.put(3, new Integer[]{0, 1});
        direction.put(4, new Integer[]{0, -1});


        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[R + 1][C + 1];
//        for (int i = 0; i < R; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < C; j++) {
//                field[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            // int remakeS = this.d <= 2 ? this.s % ((R - 1) * 2) : this.s & ((C-1) * 2)
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks.put(i + 1, new Shark(
                    r, c, s, d, z
            ));
            field[r][c] = i + 1;
        }

        // drawField();
        simulation();

        bw.write(sumOfCatchedShark + "");
        bw.flush();
        bw.close();
        br.close();

    }

    static void simulation() {
        while (true) {
            if (moveFisherManAndCheck())
                return;
            catchShark();
            // printField();
            moveShark();
            // printField();
        }
    }


    static void printField() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++)
                System.out.print(field[i][j] + "\t");
            System.out.println();
        }

        System.out.println();
    }


    static boolean moveFisherManAndCheck() {
        fisherMan += 1;
        return fisherMan == (C + 1);
    }

    static void catchShark() {
        for (int i = 1; i <= R; i++) {
            if (field[i][fisherMan] > 0) {
                int key = field[i][fisherMan];
                Shark selectedShark = sharks.get(key);
                sumOfCatchedShark += selectedShark.z;
                // TODO: ?????? ?????? ??????
                field[selectedShark.r][selectedShark.c] = 0;
                // sharks.remove(key);
                break;
            }
        }
    }

    static void moveShark() {
        ArrayList<Integer> removeKeyStack = new ArrayList<>();

        for (Map.Entry<Integer, Shark> entry : sharks.entrySet()) {
            // action.accept(entry.getKey(), entry.getValue());
            Shark currentShark = entry.getValue(); // Unmark Field
            field[currentShark.r][currentShark.c] = 0;
            currentShark.move();

            // TODO: Check if exist Shark on that position
            if(field[currentShark.r][currentShark.c] > 0) {
                Shark existedShark = sharks.get(field[currentShark.r][currentShark.c]);
                if(existedShark.z > currentShark.z) {
                    removeKeyStack.add(entry.getKey()); // ????????? ??????????????? ????????? ??????????
                } else {
                    removeKeyStack.add(field[currentShark.r][currentShark.c]); // ???????????? ????????? ?????????.
                    field[currentShark.r][currentShark.c] = entry.getKey().intValue(); // Mark Field
                }
            } else {
                field[currentShark.r][currentShark.c] = entry.getKey().intValue(); // Mark Field
            }
        }

//        for (int i = 0; i < removeKeyStack.size(); i++) {
//            sharks.remove(removeKeyStack.get(i));
//        }
    }

    static class Shark {
        public int r;
        public int c;
        public int s;
        public int d;
        public int z;

        Shark(Shark s) {
            this.r = s.r;
            this.c = s.c;
            this.s = s.s;
            this.d = s.d;
            this.z = s.z;
        }

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = d <= 2 ? s % ((R - 1) * 2) : s % ((C - 1) * 2);
            this.d = d;
            this.z = z;
        }

        public void move() {
            for (int i = 0; i < this.s; i++) {
                int nextR = this.r + direction.get(this.d)[0];
                int nextC = this.c + direction.get(this.d)[1];

                if (nextR > 0 && nextR <= R && nextC > 0 && nextC <= C) {
                    // ?????? ???.
                    this.r = nextR;
                    this.c = nextC;
                } else {
                    // TODO: ????????? ?????? ????????? ?????????
                    this.changeDirection();
                    this.r += direction.get(this.d)[0];
                    this.c += direction.get(this.d)[1];
                }
            }
        }

        public void move2() {
            int nextR = this.r + direction.get(this.d)[0] * this.s;
            int nextC = this.c + direction.get(this.d)[1] * this.s;
            if (this.d < 3) { // up & down ..
                // 2 + 5 = 7 % 5 = 2(3) 7 % r(5) == ?????? ??????
                // 2 + 10 = 12 % 5 == 2 (4)
            } else { // left & right ...

            }
        }

        private void changeDirection() {
            switch (this.d) {
                case 1:
                    this.d = 2;
                    break;
                case 2:
                    this.d = 1;
                    break;
                case 3:
                    this.d = 4;
                    break;
                case 4:
                    this.d = 3;
                    break;
                default:
                    break;
            }
        }
    }

    static class Point {
        public int i;
        public int j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        Point(Point p) {
            this.i = p.i;
            this.j = p.j;
        }

    }
}
