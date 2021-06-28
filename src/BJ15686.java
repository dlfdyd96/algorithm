import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ15686 {
    static int N, M;
    static int[][] field;
    // static ArrayList<Integer> visited = new ArrayList<>();
    static ArrayList<Point> selectedStore = new ArrayList<>();
    static ArrayList<Point> stores = new ArrayList<>();
    static int storesCount;
    static int minCityChickenDistance = Integer.MAX_VALUE;
    static int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] fieldVisited;
    static int[][] currentField;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        field = new int[N + 1][N + 1];
        // int currentChickenCounts = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i + 1][j + 1] = Integer.parseInt(st.nextToken());
                if (field[i + 1][j + 1] == 2) {
                    stores.add(new Point(i + 1, j + 1));
                    //visited.add(0);
                }
            }
        }
        storesCount = stores.size();
        selectStore(0, 0);
        bw.write("" + minCityChickenDistance);
        bw.flush();
        bw.close();
        br.close();
    }

    public static void selectStore(int depth, int idx) {
        if (depth == M) {
            int result = simulation();
            minCityChickenDistance = minCityChickenDistance > result ? result : minCityChickenDistance;
            return;
        }
        for (int i = idx; i < storesCount; i++) {
            selectedStore.add(stores.get(i));
            selectStore(depth + 1, i + 1);
            selectedStore.remove(selectedStore.size() - 1);
        }
    }

    static int simulation() {
        // fieldVisited = new boolean[N+1][N+1];
        currentField = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            Point currentSelectedStore = selectedStore.get(i);
            currentField[currentSelectedStore.r][currentSelectedStore.c] = 2;
        }

        // ======

        int cityChickenDistance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i + 1][j + 1] == 1) { // 집인경우
                    fieldVisited = new boolean[N+1][N+1];
                    cityChickenDistance += getClosetStoreDistanceBFS(i + 1, j + 1);
                }
            }
        }
        return cityChickenDistance;
    }

    /*
    public static int getClosetStoreDistanceSimple(int r, int c) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < storesCount; i++) {
            if (visited.get(i) == 1) {
                Point store = stores.get(i);
                int distance = getDistance(r, c, store.r, store.c);
                min = min > distance ? distance : min;
            }
        }
        return min;
    }
    */

    public static int getClosetStoreDistanceBFS(int r, int c) {
        int min = Integer.MAX_VALUE;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c));
        fieldVisited[r][c] = true;


        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                Point next = new Point(
                        current.r + direction[i][0],
                        current.c + direction[i][1]
                );

                // 범위안
                if(next.r >= 1 && next.r <= N && next.c >= 1 && next.c <= N ) {
                    // 유효한 값인지
                    if(fieldVisited[next.r][next.c] == false) {
                        // 치킨 집인지
                        if(currentField[next.r][next.c] == 2) {
                            return getDistance(r, c, next.r, next.c);
                        } else {
                            fieldVisited[next.r][next.c] = true;
                            queue.add(next);
                        }
                    }
                }
            }

        }


        return min;
    }

    public static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static class Point {
        public int r;
        public int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

