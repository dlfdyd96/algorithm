package Samsung2021Sumer.P2;

import java.util.Scanner;

class Solution {
    private static Scanner sc;
    private static UserSolution usersolution = new UserSolution();

    private final static int MAX_N	= 1000;
    private final static int MAX_M	= 20;

    private static int Map[][] = new int[MAX_N][MAX_N];
    private static int Stars[][] = new int[MAX_M][MAX_M];
    private static int mSeed;

    public static class Result {
        int y, x;
    }

    private static int pseudo_rand()
    {
        mSeed = mSeed * 431345 + 2531999;
        return mSeed & 0x7FFFFFFF;
    }

    private static int run(int Ans)
    {
        int N, M, K;

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        for (int i = 0; i < N; ++i) {
            int num;
            int cnt = N / 30;
            int idx = 0;
            for (int k = 0; k < cnt; ++k) {
                num = sc.nextInt();
                for (int m = 0; m < 30; ++m) {
                    Map[i][idx++] = num & 0x01;
                    num = num >> 1;
                }
            }

            if (N % 30 > 0) {
                num = sc.nextInt();
                for (int m = 0; m < (N % 30); ++m) {
                    Map[i][idx++] = num & 0x01;
                    num = num >> 1;
                }
            }
        }

        usersolution.init(N, M, Map);

        for (int t = 0; t < K; ++t) {
            mSeed = sc.nextInt();
            int num = sc.nextInt();
            int sy = sc.nextInt();
            int sx = sc.nextInt();

            for (int i = 0; i < M; ++i)
                for (int k = 0; k < M; ++k)
                    Stars[i][k] = 0;

            int y = pseudo_rand() % M;
            int x = pseudo_rand() % M;
            Stars[y][x] = 9;
            for (int i = 1; i < num; ++i) {
                y = pseudo_rand() % M;
                x = pseudo_rand() % M;
                Stars[y][x] = 1;
            }

            Result answer = usersolution.findConstellation(Stars);
            if ((answer.y != sy) || (answer.x != sx))
                Ans = 0;
        }

        return Ans;
    }

    public static void main(String[] args) throws Exception {

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        sc = new Scanner(System.in);

        int TC = sc.nextInt();
        int Ans = sc.nextInt();
        for (int testcase = 1; testcase <= TC; ++testcase) {
            System.out.println("#" + testcase + " " + run(Ans));
        }

        sc.close();
    }
}

class UserSolution {
    private final static int MAX_M	= 20;
    private static int Map[][];
    private static int N;
    private static int M;
    private static int Stars[][] = new int[MAX_M][MAX_M];

    public void init(int N, int M, int[][] Map) {
        this.N = N;
        this.M = M;
        this.Map = Map;
    }

    public Solution.Result findConstellation(int[][] _Stars) {
        Stars = _Stars;
        Solution.Result result = new Solution.Result();
        result.x = 0;
        result.y = 0;

        for (int y = 0; y <= N - M; y++) {
            for (int x = 0; x <= N - M; x++) {
                // if(true) {}// 범위를 벗어나는 경우
                if(check(y, x)) {
                    result = findCriteria();
                    result.x += x;
                    result.y += y;
                    return result;
                }
            }
        }
        for (int d = 0; d < 3; d++) {
            // 방향 틀기
            int[][] newStars = rotateStars();
            Stars = newStars;
            for (int y = 0; y <= N - M; y++) {
                for (int x = 0; x <= N - M; x++) {
                    // if(true) {}// 범위를 벗어나는 경우
                    if(check(y, x)) {
                        result = findCriteria();
                        result.x += x;
                        result.y += y;
                        return result;
                    }
                }
            }
        }

        return result;
    }

    private boolean check(int y, int x) {
        int cntStars = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if(Stars[i][j] == 1 || Stars[i][j] == 9){
                    if(Map[i+y][j+x] == 1) {
                        cntStars++;
                        continue;
                    } else if (Map[i+y][j+x] == 0) {
                        return false;
                    }
                }
                if(cntStars >= 20) return true;
            }
        }
        return true;
    }

    private int[][] rotateStars() {
        int length = Stars.length;
        int[][] rotate = new int[length][length];
        int cntStars = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if(Stars[M-1-j][i] != 0) {
                    rotate[i][j] = Stars[M-1-j][i];
                    cntStars++;
                    continue;
                }
                if(cntStars >= 20)
                    return rotate;
                // rotate[i][j] = Stars[M-1-j][i];
            }
        }
        return rotate;
    }

    private Solution.Result findCriteria() {
        Solution.Result ans = new Solution.Result();
        ans.x = ans.y = 0;
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                if(Stars[y][x] == 9) {
                    ans.x = x;
                    ans.y = y;
                    return ans;
                }
            }
        }
        return ans;
    }
}