package line2;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Step {
    public static void main(String[] args) {
        Solution s = new Solution();
        // boolean[] answer = s.solution("line", new String[]{"-s STRING", "-n NUMBER", "-e NULL"}, new String[]{"line -n 100 -s hi -e", "lien -s Bye"});
        // boolean[] answer2 = s.solution("line", new String[]{"-s STRING", "-n NUMBER", "-e NULL"}, new String[]{"line -s 123 -n HI", "line fun"});
//        boolean[] answer3 = s.solution("line",
//                new String[]{"-s STRINGS", "-n NUMBERS", "-e NULL"},
//                new String[]{"line -n 100 102 -s hi -e", "line -n id pwd -n 100"});
//        printAnswer(answer3);
//        boolean[] answer4 = s.solution("trip",
//                new String[]{"-days NUMBERS", "-dest STRING"},
//                new String[]{"trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest Seoul"});
//        printAnswer(answer4);
        boolean[] answer5 = s.solution("line",
                new String[]{"-s STRING", "-num NUMBER", "-e NULL", "-n ALIAS -num"},
                new String[]{"line -n 100 -s hi -e", "line -n 100 -e -num 150"});
        printAnswer(answer5);
        boolean[] answer6 = s.solution("bank",
                new String[]{"-send STRING", "-a ALIAS -amount", "-amount NUMBERS"},
                new String[]{"bank -send abc -amount 500 200 -a 400", "bank -send abc -a hey"});
        printAnswer(answer6);

    }

    static void printAnswer(boolean[] ans) {
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        System.out.println();
    }


}
// 주어진 명령어가 모든 flag_rule을 지키는지 검사하는 Class
class Solution {
    static final String alphabet_pattern = "^[a-zA-Z]*$";
    static final String number_pattern = "^[0-9]*$";

    // main function
    public boolean[] solution(String program, String[] flag_rules, String[] commands) {
        boolean[] answer = {};

        // answer 배열 할당.
        answer = new boolean[commands.length];

        // ALIAS를 마지막에 처리하기 위한 array
        List<String> aliasStack = new ArrayList<>();
        HashMap<String, String> alias = new HashMap<>();
        HashMap<String, Integer> visited = new HashMap<>();

        // flag 규칙 정보 할당
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < flag_rules.length; i++) {
            // flag_rules string 분리
            String[] flag_rules_info = flag_rules[i].split(" ");

            String flag = flag_rules_info[0];
            String flag_type = flag_rules_info[1];

            if(flag_type.equals("ALIAS")) {
                aliasStack.add(flag_rules[i]);
            }


            // map에 규칙 담기
            map.put(flag, flag_type);
        }

        // alias 처리
        for (int i = 0; i < aliasStack.size(); i++) {
            String[] flag_rules_info = aliasStack.get(i).split(" ");

            String flag = flag_rules_info[0];
            String flag_type = flag_rules_info[1];
            String flag2 = flag_rules_info[2];

            map.put(flag, map.get(flag2));
            visited.put(flag2, 0);
            alias.put(flag, flag2);
        }

        // 각 commands에 대해 처리
        for (int i = 0; i < commands.length; i++) {
            boolean result = true;

            // 0. command를 공백을 기준으로 split
            StringTokenizer cmds = new StringTokenizer(commands[i]);

            // 1. program에 부합한지 검사
            String currentProgram = cmds.nextToken();

            if (!currentProgram.equals(program)) {
                answer[i] = false;
                System.out.println("Not Match Program");

                continue;
            }

            // 2. argument 검사
            boolean delay_flag = false;
            String current_flag_arguments = "";
            while(cmds.hasMoreTokens()) {
                // NUMBERS, STRINGS 대
                if (!delay_flag)
                    current_flag_arguments = cmds.nextToken();
                delay_flag = false;

                // flag_argument가 없을 경우
                if(!map.containsKey(current_flag_arguments)) {
                    result = false;
                    System.out.println("dont have argument");
                    break;
                }

                if(alias.containsKey(current_flag_arguments)) {
                    System.out.println("there is alias");

                    String alias_flag = alias.get(current_flag_arguments);
                    if (visited.get(alias_flag) > 0) {
                        result = false;
                        System.out.println("원래 이름과 별칭이 동시에 등장");
                        break;
                    }
                    visited.put(alias_flag, 1);
                }

                if (visited.containsKey(current_flag_arguments)) {
                    if (visited.get(current_flag_arguments) > 0) {
                        result = false;
                        System.out.println("원래 이름과 별칭이 동시에 등장");
                        break;
                    }
                    visited.put(current_flag_arguments, 1);
                }


                // current_type hashmap 가져오
                String current_type = map.get(current_flag_arguments);

                // 조건 검사.
                if (current_type.equals("STRING")) {
                    // 다음 token이 있는지 검사
                    if (!cmds.hasMoreTokens()) {
                        result = false;
                        System.out.println("need arugments");
                        break;
                    }
                    // 숫자인지 검사
                    String next_value = cmds.nextToken();
                    if (!isString(next_value)) {
                        result = false;
                        System.out.println("not string");
                        break;
                    }
                } else if(current_type.equals("NUMBER")) {
                    // 다음 token이 있는지 검사
                    if (!cmds.hasMoreTokens()) {
                        result = false;
                        System.out.println("need arugments");
                        break;
                    }

                    // 문자열인지 검사
                    String next_value = cmds.nextToken();
                    if (!isNumber(next_value)) {
                        result = false;
                        System.out.println("need numbers.");
                        break;
                    }
                } else if(current_type.equals("NUMBERS")) {
                    // 다음 token이 있는지 검사
                    if (!cmds.hasMoreTokens()) {
                        result = false;
                        System.out.println("need arugments");
                        break;
                    }

                    // 문자열인지 검사
                    String next_value1 = cmds.nextToken();
                    if (!isNumber(next_value1)) {
                        result = false;
                        System.out.println("need numbers.");
                        break;
                    }

                    // 다음 token이 있는지 검사 (다음 오는 숫자가 1개 이상이기 때문에 없어도 됩니다.)
                    if (!cmds.hasMoreTokens()) {
                        continue;
                    }

                    // 숫자인지 검사
                    String next_value2 = cmds.nextToken();
                    if (!isNumber(next_value2)) {
                        // 다음 토큰 값을 뽑지않고, 현재 토큰 값으로 사용합니다.
                        delay_flag = true;
                        current_flag_arguments = next_value2;
                        continue;
                    }
                } else if(current_type.equals("STRINGS")) {
                    // 다음 token이 있는지 검사
                    if (!cmds.hasMoreTokens()) {
                        result = false;
                        System.out.println("need arugments");
                        break;
                    }

                    // 문자열인지 검사
                    String next_value1 = cmds.nextToken();
                    if (!isString(next_value1)) {
                        result = false;
                        System.out.println("need strings.");
                        break;
                    }

                    // 다음 token이 있는지 검사 (다음 오는 숫자가 1개 이상이기 때문에 없어도 됩니다.)
                    if (!cmds.hasMoreTokens()) {
                        continue;
                    }

                    // 문자인지 검사
                    String next_value2 = cmds.nextToken();
                    if (!isString(next_value2)) {
                        // 다음 토큰 값을 뽑지않고, 현재 토큰 값으로 사용합니다.
                        delay_flag = true;
                        current_flag_arguments = next_value2;
                        continue;
                    }
                } else if(current_type.equals("NULL")) {
                } else {
                    // 지정되지 않은 토큰이 온 경우 false
                    result = false;
                    System.out.println("Invalid flag");
                    break;
                }
            }

            // result 반환
            answer[i] = result;
        }
        return answer;
    }

    static boolean isString(String value) {
        return Pattern.matches(alphabet_pattern, value);
    }

    static boolean isNumber(String value) {
        return Pattern.matches(number_pattern, value);
    }
}

