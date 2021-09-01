package prgrms.weekly.w5;

import java.util.Arrays;

/** Solution
 * ex A AA AAA AAAA AAAAA AAAAAE
 * binary?
 * A E I O U
 */
public class W5 {
    public static void main(String[] args) {
        String[] inputs = {
                "AAAAE",
                "AAAE",
                "I",
                "EIO"
        };
        for (String input : inputs
        ) {
            int answer = new Solution().solution(input);
            System.out.println(answer);
        }
    }
}

class Solution {
    public int solution(String word) {
        int answer = 0;
        int creteria = 781;

        String[] targets = {"A", "E", "I", "O", "U"};
        char[] strings = word.toCharArray();
        for (char s: strings) {
            // System.out.println(Arrays.asList(targets).indexOf(s+""));
            answer += Arrays.asList(targets).indexOf(s+"") * creteria + 1;
            creteria = (creteria - 1) / 5;
        }

        return answer;
    }
}
