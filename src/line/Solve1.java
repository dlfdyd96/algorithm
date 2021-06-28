package line;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Solve1 {

    public static void main(String[] args) {

    }

    public class Solution {
        public String solution(String[] table, String[] languages, int[] preference) {
            String answer = "";
            String tempName = "";
            int MAX = Integer.MIN_VALUE;

            for (int i = 0; i < table.length; i++) {
                StringTokenizer st = new StringTokenizer(table[i]);

                tempName = st.nextToken();

                int result = 0;
                String[] temp = new String[5];

                for (int j = 0; j < temp.length; j++) {
                    temp[j] = st.nextToken();
                }

                for (int j = 0; j < languages.length; j++) {
                    for (int j2 = 0; j2 < temp.length; j2++) {
                        if (languages[j].equals(temp[j2])) {
                            result += (preference[j] * (5 - j2));
                        }
                    }
                }

                if (result > MAX) {
                    MAX = result;
                    answer = tempName;
                } else if (result == MAX) {
                    if (answer.compareTo(tempName) > 0) {
                        answer = tempName;
                    } else
                        answer = answer;
                }
            }

            return answer;
        }
    }
}
