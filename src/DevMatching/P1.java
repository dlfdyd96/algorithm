package DevMatching;

import java.util.Arrays;
import java.util.stream.IntStream;

public class P1 {
    public static void main(String[] args) {
        int[] answer = solution(
                new int[]{45, 4, 35, 20, 3, 9},// new int[]{44, 1, 0, 0, 31, 25},
                new int[]{20, 9, 3, 45, 4, 35}// new int[]{31, 10, 45, 1, 6, 19}
        );
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + ", ");
        }

    }

    /**
     * @param lottos   - 알아볼 수 없는 수는 0으로 표시
     *                 -
     * @param win_nums
     * @return answer
     * <p>
     * 1. ㅇㅏ 기ㅜ찮다
     * 2. win nums 를 하나씩 읽으면서
     */
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int cnt = 0;
        int zero_cnt = 0;
        // 0 ㅐㄱ수 파
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) {
                zero_cnt += 1;
            }
        }

        // 로또 번호 확인
        for (int i = 0; i < win_nums.length; i++) {
            if (useList(lottos, win_nums[i])) {
                cnt += 1;
            }
        }
        System.out.println(cnt + ", " + zero_cnt);
        int min_lank = 0;
        int max_lank = 0;

        min_lank = 6 - cnt + 1;
        max_lank = 6 - cnt + 1 - zero_cnt;


        return new int[]{max_lank > 6 ? 6 : max_lank, min_lank > 6 ? 6 : min_lank};
    }

    public static boolean useList(int[] arr, int targetValue) {
        return IntStream.of(arr).anyMatch(x -> x == targetValue);
    }
}
