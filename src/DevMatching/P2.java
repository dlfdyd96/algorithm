package DevMatching;

/**
 * 1. 배열 돌리고
 * 2.
 */
public class P2 {
    public static void main(String[] args) {
        int[] answer1 = solution(
                6,
                6,
                new int[][]{
                        {2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}
                }
        );
        for (int i = 0; i < answer1.length; i++) {
            System.out.print(answer1[i] + " ");
        }
        System.out.println();


        int[] answer2 = solution(
                3, 3,
                new int[][]{
                        {1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}
                }
        );
        for (int i = 0; i < answer2.length; i++) {
            System.out.print(answer2[i] + " ");
        }
        System.out.println();


        int[] answer3 = solution(
                100,
                97,
                new int[][]{
                        {1, 1, 100, 97}
                }
        );
        for (int i = 0; i < answer3.length; i++) {
            System.out.print(answer3[i] + " ");
        }
        System.out.println();
    }
    // ==============


    static int[][] field;

    public static void initField(int rows, int columns) {
        int cnt = 1;
        field = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field[i][j] = cnt;
                cnt++;
            }
        }
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        initField(rows, columns);
        for (int i = 0; i < queries.length; i++) {
            int min_num = rotateAndFindMinNum(
                    queries[i][0] - 1,
                    queries[i][1] - 1,
                    queries[i][2] - 1,
                    queries[i][3] - 1);
            // printField(rows, columns);
            answer[i] = min_num;
        }
        return answer;
    }

    public static int rotateAndFindMinNum(int x1, int y1, int x2, int y2) {
        int min_num = Integer.MAX_VALUE;
        int temp = field[x1][y1];
        min_num = min_num > temp ? temp : min_num;
        for (int i = 0; i < y2 - y1; i++) { // x1, y1  -> x1, y2
            int next = field[x1][y1 + i + 1];
            field[x1][y1 + i + 1] = temp;
            temp = next;

            min_num = min_num > temp ? temp : min_num;
        }
        for (int i = 0; i < x2 - x1; i++) { // x1, y2 -> x2, y2
            int next = field[x1 + i + 1][y2];
            field[x1 + i + 1][y2] = temp;
            temp = next;
            min_num = min_num > temp ? temp : min_num;
        }
        for (int i = 0; i < y2 - y1; i++) { // x2, y2 -> x2, y1
            int next = field[x2][y2 - i - 1];
            field[x2][y2 - i - 1] = temp;
            temp = next;

            min_num = min_num > temp ? temp : min_num;
        }
        for (int i = 0; i < x2 - x1; i++) { // x2, y1 -> x1, y1
            int next = field[x2 - i - 1][y1];
            field[x2 - i - 1][y1] = temp;
            temp = next;
            min_num = min_num > temp ? temp : min_num;
        }
        // field[x1][y1] = temp;

        return min_num;
    }

//    public static void printField(int rows, int columns) {
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                System.out.print(field[i][j] + "\t");
//            }
//            System.out.println();
//        }
//    }
}
