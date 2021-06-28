import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ17144 {
    static int R, C, T;
    static ArrayList<Point> airClear = new ArrayList<>();
    static int[][] field;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        field = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == -1)
                    airClear.add(new Point(i, j, -1));
            }
        }

        simulation();

        bw.write(calculateRemainDust() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void simulation() {
        for (int i = 0; i < T; i++) {
            dustDiffusion();
            // printField();
            activeAirCleaner();
            // printField();
        }
    }

    static void dustDiffusion() {
        ArrayList<Point> dustPoints = initDustPoint();
        for (Point dustPoint : dustPoints) {
            int sumOfDiffusedDust = 0;
            int amountOfDiffusingDust = (int) Math.floor(dustPoint.dust / 5);
            for (int[] d : direction) {
                Point next = new Point(dustPoint.i + d[0], dustPoint.j + d[1], dustPoint.dust);
                if (next.i >= 0 && next.i < R && next.j >= 0 && next.j < C) { // 범위 안
                    if (field[next.i][next.j] != -1) { // 공기청정기 구역은 확산이 일어나지 않음.
                        sumOfDiffusedDust += amountOfDiffusingDust;
                        field[next.i][next.j] += amountOfDiffusingDust;
                    }
                }
            }
            field[dustPoint.i][dustPoint.j] -= sumOfDiffusedDust;
        }

    }

    static ArrayList<Point> initDustPoint() {
        ArrayList<Point> dustPoints = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (field[i][j] > 0) {
                    dustPoints.add(new Point(i, j, field[i][j]));
                }
            }
        }
        return dustPoints;
    }

    static void activeAirCleaner() {
        int[][] movingDirectionOfTopAirCleaner = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int[][] movingDirectionOfBottomAirCleaner = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Point topAirCleaner = airClear.get(0);
        Point bottomCleaner = airClear.get(1);

        activateAirCleaner(movingDirectionOfTopAirCleaner, topAirCleaner);
        activateAirCleaner(movingDirectionOfBottomAirCleaner, bottomCleaner);

    }

    static void activateAirCleaner(int[][] movingDirection, Point pointOfAirCleaner) {
        Point p = new Point(pointOfAirCleaner);

        p.i += movingDirection[0][0]; // 일단 한칸 이동시켜줌
        p.j += movingDirection[0][1];

        int swapTempDust1;
        int swapTempDust2 = field[p.i][p.j];
        field[p.i][p.j] = 0;

        int currentDirectionIdx = 0;
        while (true) {
            Point next = new Point(p.i + movingDirection[currentDirectionIdx][0], p.j + movingDirection[currentDirectionIdx][1], 0);
            if (next.i >= 0 && next.i < R && next.j >= 0 && next.j < C) { // 범위 안
                if(next.isSamePosition(pointOfAirCleaner)) break;

                swapTempDust1 = field[next.i][next.j];
                field[next.i][next.j] = swapTempDust2;
                swapTempDust2 = swapTempDust1;
                p = next;
            } else {
                currentDirectionIdx += 1;
            }
        }
    }

    static int calculateRemainDust() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (field[i][j] > 0)
                    sum += field[i][j];
            }
        }
        return sum;
    }

    static void printField() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                System.out.print(field[i][j] + "\t");
            System.out.println();
        }

        System.out.println();
    }

    static class Point {
        public int i;
        public int j;
        public int dust;

        Point(int i, int j, int dust) {
            this.i = i;
            this.j = j;
            this.dust = dust;
        }

        Point(Point p) {
            this.i = p.i;
            this.j = p.j;
            this.dust = p.dust;
        }

        public boolean isSamePosition(Point p) {
            return this.i == p.i && this.j == p.j;
        }
    }
}
