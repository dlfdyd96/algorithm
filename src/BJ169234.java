import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ169234 {
    static int N, L, R;
    static int[][] field;

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int movedCount = 0;
        while(numberingField()) {
            // System.out.println("움직엿다 ㅋㅋ ");
            // printField();
            movedCount += 1;
        }

        bw.write(movedCount + "");
        bw.flush();
        bw.close();
        br.close();
    }

    /*
    1. (i, j) 부터 BFS로 근처 국경을 열 모든 땅을 찾는다.
    2. countNumber 처리

     */
    static boolean numberingField() {
        boolean isMovedSomePeople = false;
        int countNumber = 1;
        int[][] numberedField = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (numberedField[i][j] == 0) { // not visited
                    if (field[i][j] != bfs(i, j, numberedField, countNumber)) { // 혼자만 이동했단 거지.
                        isMovedSomePeople = true;
                    } else {
                        numberedField[i][j] = 0;
                    }
                    countNumber += 1;
                }
            }
        }

        return isMovedSomePeople;
    }

    static int bfs(int r, int c, int[][] numberedField, int countNumber) {
        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int sum = 0;
        ArrayList<Point> pointArray = new ArrayList<>();

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        sum += field[r][c];
        numberedField[r][c] = countNumber;
        pointArray.add(new Point(r, c));
        while (!q.isEmpty()) {
            Point current = q.poll();
            for (int i = 0; i < 4; i++) {
                Point next = new Point(current);
                next.i += direction[i][0];
                next.j += direction[i][1];

                if (next.i >= 0 && next.i < N && next.j >= 0 && next.j < N) {
                    int diffPopulation = Math.abs(field[current.i][current.j] - field[next.i][next.j]);
                    if (diffPopulation >= L && diffPopulation <= R) { // problem condition L, R
                        if (numberedField[next.i][next.j] == 0) {
                            numberedField[next.i][next.j] = countNumber;
                            q.add(next);
                            sum += field[next.i][next.j];
                            pointArray.add(new Point(next));
                        }
                    }
                }
            }
        } // end queue

        int average = (int) Math.floor(sum / pointArray.size());
        for (Point p : pointArray) {
            field[p.i][p.j] = average;
        }
        return sum;
    }

    static void printField() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(field[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
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
