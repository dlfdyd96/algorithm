import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 지훈쓰 위치가
 * bfs 로 j가 움직여 근데 level이 존재.
 * 불은 queue가 돌때 level이 바뀔때를 감지하고 불이 퍼져나가도록 하자.
* TEST CASE
d
4 4
####
#JF#
#..#
#..#


4 4
####
J.F#
#..#
####

5 4
##.#
#.F#
#JF#
#..#
#..#

6 6
##.###
#F...#
#....#
#....#
#F..J#
######

6 6
##.###
#F...#
#....#
#....#
#F..J#
######


6 6
##..##
#....#
#....#
#....#
FF..JF
FFFFFF


*/
public class BJ4179 {
    static int R, C;
    static char[][] field;
    static Point firstJPoint;
    static Queue<Point> firstFPointQueue = new LinkedList<>();
    static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        field = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            char[] charLine = line.toCharArray();

            for (int j = 0; j < C; j++) {
                field[i][j] = charLine[j];
                if (charLine[j] == 'J') { //
                    firstJPoint = new Point(i, j);
                } else if (charLine[j] == 'F') {
                    firstFPointQueue.add(new Point(i, j));
                }
            }
        }

        int result = simulationWithBFS();
        if(canHeEscape(firstJPoint.i, firstJPoint.j))
            result = 0;

        if(result == -1)
            bw.write("IMPOSSIBLE");
        else
            bw.write((result + 1) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    static void PrintField() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        // System.out.println("J: " + "(" +  ")");
        System.out.println();
    }

    static int simulationWithBFS() {
        boolean[][] JVisited = new boolean[R][C];
        Queue<Point> firePointQueue = new LinkedList<>();
        while(!firstFPointQueue.isEmpty())
            firePointQueue.add(firstFPointQueue.poll());

        Queue<Point> q = new LinkedList<>();
        q.add(firstJPoint);
        JVisited[firstJPoint.i][firstJPoint.j] = true;
        // firePointQueue.add(firstFPoint);

        int previousLevel = firstJPoint.level;

        while (!q.isEmpty()) {
            Point current = q.poll();
            if(previousLevel != current.level) {
                firePointQueue = fireField(firePointQueue); // 불질러
                // PrintField(); // TODO: 지우셈
            }

            previousLevel = current.level;

            if(field[current.i][current.j] == 'F') { // 현재 자리가 불이면?
                continue;
            }

            // 만약 끝이면? return true!
            if(canHeEscape(current.i, current.j))
                return current.level;


            for (int i = 0; i < 4; i++) {
                Point next = new Point(current.i + direction[i][0], current.j + direction[i][1], current.level);
                if(next.i >= 0 && next.i < R && next.j >= 0 && next.j < C) { // 범위안
                    if(field[next.i][next.j] != '#') { // 벽 안
                        if(field[next.i][next.j] != 'F') { // 현재 자리가 불 아님
                            if(!JVisited[next.i][next.j]) { // 현재 자리가 지나간 곳이 아님.

                                // previousLevel = next.level;
                                next.level += 1; // 레벨 증가
//                                // 만약 끝이면? return true!
//                                if(canHeEscape(next.i, next.j))
//                                    return next.level;
                                q.add(next);
                                JVisited[next.i][next.j] = true;

                            }
                        }
                    }
                }

            }
        }

        return -1;
    }

    static Queue<Point> fireField(Queue<Point> firePointArray) {
        Queue<Point> returnQueue = new LinkedList<>();

        boolean[][] FVisited = new boolean[R][C];

        while(!firePointArray.isEmpty()) {
            Point current = firePointArray.poll();
            FVisited[current.i][current.j] = true;

            for (int[] d : direction) {
                Point next = new Point(current.i + d[0], current.j + d[1]);
                if (next.i >= 0 && next.i < R && next.j >= 0 && next.j < C) { // 범위안
                    if (field[next.i][next.j] != '#' && field[next.i][next.j] != 'F') { // 벽 안
                        if (!FVisited[next.i][next.j]) { // 현재 자리가 지나간 곳이 아님.
                            returnQueue.add(next);
                            // FVisited[next.i][next.j] = true;
                            field[next.i][next.j] = 'F';
                        }
                    }
                }
            }
        }
        return returnQueue;
/**
 *
        for (Point firePoint: firePointArray) {
            q.add(firePoint);
            FVisited[firePoint.i][firePoint.j] = true;
        }

        while(!firePointArray.isEmpty()) {
            Point current = q.poll();

            for (int[] d: direction) {
                Point next = new Point(current.i + d[0], current.j + d[1]);
                if(next.i >= 0 && next.i < R && next.j >= 0 && next.j < C) { // 범위안
                    if(field[next.i][next.j] != '#') { // 벽 안
                        if(!FVisited[next.i][next.j]) { // 현재 자리가 지나간 곳이 아님.
                            q.add(next);
                            FVisited[next.i][next.j] = true;
                            field[next.i][next.j] = 'F';
                        }
                    }
                } // 범위 안 end
            }
        }
 */
    }

    static boolean canHeEscape(int r, int c) {
        if (r == 0 || c == 0 || r == R - 1 || c == C - 1)
            return true;
        return false;
    }

    static class Point {
        public int i;
        public int j;
        public int level;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
            this.level = 0;
        }

        Point(int i, int j, int level) {
            this.i = i;
            this.j = j;
            this.level = level;
        }

        Point(Point p) {
            this.i = p.i;
            this.j = p.j;
            this.level = p.level;
        }
    }

}
