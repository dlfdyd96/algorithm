package Samsung2021Sumer.P1;
import java.util.Scanner;

public class Solution {
    private final static int N              = 4;
    private final static int MAX_QUERYCOUNT = 1000000;

    private static int digits[] = new int[N];
    private static int digits_c[] = new int[10];

    private static int T;

    private static int querycount;

    // the value of limit_query will be changed in evaluation
    private final static int limit_query = 234;

    static class Result {
        public int hit;
        public int miss;
    }

    private static boolean isValid(int guess[]) {
        int guess_c[] = new int[10];

        for (int count = 0; count < 10; ++count) guess_c[count] = 0;
        for (int idx = 0; idx < N; ++idx) {
            if (guess[idx] < 0 || guess[idx] >= 10 || guess_c[guess[idx]] > 0) return false;
            guess_c[guess[idx]]++;
        }
        return true;
    }

    // API : return a result for comparison with digits[] and guess[]
    public static Result query(int guess[]) {
        Result result = new Result();

        if (querycount >= MAX_QUERYCOUNT) {
            result.hit = -1;
            result.miss = -1;
            return result;
        }

        querycount++;

        if (!isValid(guess)) {
            result.hit = -1;
            result.miss = -1;
            return result;
        }

        result.hit = 0;
        result.miss = 0;

        for (int idx = 0; idx < N; ++idx)
            if (guess[idx] == digits[idx])
                result.hit++;
            else if (digits_c[guess[idx]] > 0)
                result.miss++;

        return result;
    }

    private static void initialize(Scanner sc) {
        for (int count = 0; count < 10; ++count) digits_c[count] = 0;

        String input;
        do input = sc.next(); while(input.charAt(0) < '0' || input.charAt(0) > '9');

        for (int idx = 0; idx < N; ++idx) {
            digits[idx] = input.charAt(idx) - '0';
            digits_c[digits[idx]]++;
        }

        querycount = 0;
    }

    private static boolean check(int guess[]) {
        for (int idx = 0; idx < N; ++idx)
            if (guess[idx] != digits[idx]) return false;
        return true;
    }

    public static void main(String[] args) throws Exception
    {

        int total_score = 0;
        int total_querycount = 0;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        UserSolution usersolution = new UserSolution();
        for (int testcase = 1; testcase <= T; ++testcase) {
            initialize(sc);

            int guess[] = new int[N];
            usersolution.doUserImplementation(guess);

            if (!check(guess)) querycount = MAX_QUERYCOUNT;
            if (querycount <= limit_query) total_score++;
            System.out.printf("#%d %d\n", testcase, querycount);
            total_querycount += querycount;
        }
        if (total_querycount > MAX_QUERYCOUNT) total_querycount = MAX_QUERYCOUNT;
        System.out.printf("total score = %d\ntotal query = %d\n", total_score * 100 / T, total_querycount);
        sc.close();
    }
}


/*
    0. 설정
        0-1. 포함되어있는 숫자들을 Set<Integer> numSet로 표현
        0-2. new Set<Integer>[10][4];
    1. 첫 번째 숫자부터 올려보고
        1-1. hit 줄어듦?
            1-1-1. 원래 그 숫자인겨 다음 인덱스로
        1-2. miss 줄어듦?
            1-2-1. 일단 숫자가 포함되어있다. numSet 에 추가하자.
        1-3. hit 올라감?
        1-4. miss 올라감?

 */
class UserSolution {
    public final static int N = 4;

    public static boolean[] impossible ; // 올 수 없는 수
    public static boolean[] usingNum ; // 사용하고 있는 수
    public static boolean[] isAns;
    public static int currentIdx; // [0, 4)
    public static int[] prevGuess;
    // public static Solution.Result prevResult = new Solution.Result();

    class Result {
        public int hit;
        public int miss;
    }

    public void copyGuess(int src[], int dist[]) {
        for (int i = 0; i < N; i++) dist[i] = src[i];
    }

    public void copyResult(Solution.Result src, Result dist) {
        dist.hit = src.hit;
        dist.miss = src.miss;
    }

    public void doUserImplementation(int guess[]) {
        // Implement a user's implementation function
        //
        // The array of guess[] is a return array that
        // is your guess for what digits[] would be.
        // init
        impossible = new boolean[10]; // 올 수 없는 수
        usingNum = new boolean[10]; // 사용하고 있는 수
        isAns = new boolean[4];
        currentIdx = 0; // [0, 4)
        prevGuess = new int[4];

        Result prevResult = new Result();
        for (int i = 0; i < N; i++) {
            guess[i] = prevGuess[i] = i;
            usingNum[i] = true;
        }

        Solution.Result result = Solution.query(guess);
        copyGuess(guess, prevGuess);
        copyResult(result, prevResult);


        while(currentIdx < 4) {
            for (int i = 0; i < 10; i++) {
                if(usingNum[i] || impossible[i]) continue;

                usingNum[guess[currentIdx]] = false; // 이전 값은 사용 안한다고 체크
                guess[currentIdx] = i; // 바꾸고
                usingNum[guess[currentIdx]] = true; // 현재 값은 사용 한다고 체크

                result = Solution.query(guess); // 평가해주세요!

                // 평가
                if(result.hit > prevResult.hit) { // 바꿨는데 정답임.
                    if(result.miss == prevResult.miss) { // 그 자리가 Miss 였던 자리도 되는거였어!
                        impossible[prevGuess[currentIdx]] = true; // 이전 값은 아님!
                    }
//                    impossible[prevGuess[currentIdx]] = true; // 이전 값은 아님!
                    isAns[currentIdx] = true;
                    copyGuess(guess, prevGuess);
                    copyResult(result, prevResult);
                    break;
                } else if(result.hit < prevResult.hit) { // 바꿧는데 그자리 hit가 내려감
                    if(result.miss == prevResult.miss) { // 그 자리가 Miss 였던 자리도 되는거였어!
                        impossible[guess[currentIdx]] = true;
                    }
//                    impossible[guess[currentIdx]] = true; // 현재 값은 전혀 올 수가 없다고 체크
                    isAns[currentIdx] = true; // 현재 자리 정답이라고 체크
                    usingNum[guess[currentIdx]] = false; // 현재 값 usingNum에서 빼기 (솔직히 안빼도 댐)
                    guess[currentIdx] = prevGuess[currentIdx]; // 이전 값으로 바꾸기
                    usingNum[guess[currentIdx]] = true; // 이전 값은 사용한다고 체크

                    result.hit = prevResult.hit;
                    result.miss = prevResult.miss;
                    break;
                } else if(result.miss > prevResult.miss) { // 미스가 올랏네
                    impossible[prevGuess[currentIdx]] = true; // 이전 값은 전혀 올 수가 없다고 체크
                    copyGuess(guess, prevGuess);
                    copyResult(result, prevResult);
                    break;
                } else if (result.miss < prevResult.miss) { // 미스가 내려감
                    // impossible[guess[currentIdx]] = true; // 현재 값은 전혀 올 수가 없다고 체크
                    usingNum[guess[currentIdx]] = false; // 현재 값 usingNum에서 빼기 (솔직히 안빼도 댐)
                    guess[currentIdx] = prevGuess[currentIdx]; // 이전 값으로 바꾸기
                    usingNum[guess[currentIdx]] = true; // 이전 값은 사용한다고 체크

                    result.hit = prevResult.hit;
                    result.miss = prevResult.miss;
                    break;
                }
                // 아무 것도 안변했으면
                // impossible[guess[currentIdx]] = true; // 현재 값은 전혀 올 수가 없다고 체크
                copyGuess(guess, prevGuess);
                copyResult(result, prevResult);
            }
//            copyGuess(guess, prevGuess);
//            copyResult(result, prevResult);
            currentIdx++;
        }

        // for (int i = 0; i < N; i++) { // miss된 숫자들 끼리 바꾼다.
        int i = 0;
        int notAnsNumber = 0;
        for (int j = 0; j < 10; j++) {
            if(impossible[j]) {
                notAnsNumber = j;
                break;
            }
        }


        while(result.hit != 4) {
            if (isAns[i]) {
                i = (i + 1) % N;
                continue;
            }
            for (int j = i+1; j < N; j++) {
                if (isAns[j]) continue;
                int temp = guess[i];
                guess[i] = guess[j];
                guess[j] = temp;

                result = Solution.query(guess); // 평가해주세요!
                // 평가
                if(result.hit - prevResult.hit == 2) { // 바꿨는데 두 자리 정답임.
                    isAns[i] = true;
                    isAns[j] = true;
                    copyResult(result, prevResult);
                    break;
                } else if(result.hit - prevResult.hit == 1) { // 하나만 맞아
                    // 값을 복사해
                    copyGuess(guess, prevGuess);
                    copyResult(result, prevResult);
                    // 1. i 를 바꿔봐
                    guess[i] = notAnsNumber;
                    result = Solution.query(guess);
                    if (prevResult.hit > result.hit) { // i자리가 맞아.
                        isAns[i] = true;
                        guess[i] = prevGuess[i];
                    }
                    else {
                        isAns[j] = true;
                        guess[i] = prevGuess[i];
                    }
                    break;
                }
                temp = guess[i];
                guess[i] = guess[j];
                guess[j] = temp;

                // copyResult(result, prevResult);
            }
            i = (i + 1) % N;
        }
        // 주석 필요
//        for (int z: guess) System.out.print(z + ", ");
//        System.out.println();

    }
}
