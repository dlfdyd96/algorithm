package concept;

public class FloydWarshall {
    static final int number = 4;
    static final int INF = 1000000;

    static int[][] a = {
            {0, 5, INF, 8},
            {7, 0, 9, INF},
            {2, INF, 0, 4},
            {INF, INF, 3, 0}
    };

    static void floydWarshall() {
        // 결과 그래프 초기화
        int[][] d = new int[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                d[i][j] = a[i][j];
            }
        }

        // i = 거쳐가는 노드
        for (int i = 0; i < number; i++) {
            // j = 출발 노드
            for (int j = 0; j < number; j++) {
                // k = 도착 노드
                for (int k = 0; k < number; k++) {
                    if (d[j][i] + d[i][k] < d[j][k]) {
                        d[j][k] = d[j][i] + d[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                System.out.printf("%4d", d[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        floydWarshall();
    }
}
