package PG1830;

import java.util.Arrays;

public class Main {
    public final static String[] inputs = {
            "HaEaLaLaObWORLDb",
            "SpIpGpOpNpGJqOqA",
            "AxAxAxAoBoBoB"
    };

    public static void main(String[] args) {
        for (String input: inputs) {
            String ans = new Solution().solution(input);
            System.out.println(ans);
        }
    }
}
/*

final String INVALID = "invalid";

sentence = "~!@";
rule1 = false;
rule2 = false;

error = false;

string Solution() {
    length = sentence.length
    prev = sentence[0]

    for 1 -> length - 1:
        answer = checkStatus(
            i,
            Character.isLowerCase(sentence[index-1]),
            answer
        );

}


String checkStatus(int index, boolean isPrevLowerCase, String answer) {
    if (!rule1 && !rule2)
        if (isPrevLowerCase)
            isNextLowerCase = Character.isLowerCase(sentence[index+1]);
            if (isNextLowerCase) // aEa
                return answer + sentence[index];
            else // aEE
                if (index == length - 1) // error
                    error = true;
                    return INVALID;
                rule2 = true;
                return index == length - 1 ? answer + sentence[index] + sentence[index + 1]: answer + sentence[index];
        else
            isNextLowerCase = Character.isLowerCase(sentence[index+1]);
            if (isNextLowerCase) // AEa -> 얘는 마지막만 확인하면 되겠다.
                if (index == length - 1) // error
                    error = true;
                    return INVALID;
                return answer + sentence[index];
            else // AAA
                return index == length - 1 ?
                    answer + sentence[index] + sentence[index + 1] :
                    answer + sentence[index];
    else if (rule1 && !rule2) // 규칙 1 켜진 상태 (HaEaLaO)
        if (isPrevLowerCase)
            isNextLowerCase = Character.isLowerCase(sentence[index+1]);
            if (isNextLowerCase) // a
                return answer + sentence[index];
            else // aEE
                if (index == length - 1) // error
                    error = true;
                    return INVALID;
                rule2 = true;
                return index == length - 1 ? answer + sentence[index] + sentence[index + 1]: answer + sentence[index];
        else
            isNextLowerCase = Character.isLowerCase(sentence[index+1]);
            if (isNextLowerCase) // AEa -> 얘는 마지막만 확인하면 되겠다.
                if (index == length - 1) // error
                    error = true;
                    return INVALID;
                return answer + sentence[index];
            else // AAA
                return index == length - 1 ?
                    answer + sentence[index] + sentence[index + 1] :
                    answer + sentence[index];


    else if (!rule1 && rule2)

    else // 둘 다 켜져있음


}

 */
class Solution {
    public final int LOWER_ALPHABET_CNT;

    public int[] visited;

    public Solution() {
        LOWER_ALPHABET_CNT = 'z' - 'a' + 1;
        visited = new int[LOWER_ALPHABET_CNT];
    }

    public String solution(String sentence) {
        String answer = "";

        char[] sentenceChars = sentence.toCharArray();
        int length = sentence.length();




        return answer;
    }

    public void init() {

    }
}