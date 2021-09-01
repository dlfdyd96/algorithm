package prgrms.weekly.w4;

import javax.sound.midi.Soundbank;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class w4 {
    public static void main(String[] args) {
        String[] table = {
                "SI JAVA JAVASCRIPT SQL PYTHON C#",
                "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
                "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
                "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
                "GAME C++ C# JAVASCRIPT C JAVA"
        };
        String[] languages = {
                "PYTHON",
                "C++",
                "SQL"
        };
        int[] preference = {7, 5, 5};
        String s = new Solution().solution(table, languages, preference);
        System.out.println(s);
    }
}

class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";

        // 1. 개발자 언어 선호도 map
        Map<String, Integer> preferenceMap = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            preferenceMap.put(languages[i], preference[i]);
        }

        // 2. 직업군 언어 점수 계산
        HashMap<String, Integer> totalScore = new HashMap<>();
        for (int i = 0; i < table.length; i++) {
            String[] item = table[i].split(" ");
            String occupation = item[0];// 직업군영어로
            int score = 0;

            for (int j = 0; j < 5; j++) {
                String lang = item[j + 1];
                if (preferenceMap.containsKey(lang)) {
                    score += preferenceMap.get(lang) * (5 - j);
                }
            }
            totalScore.put(occupation, score);
        }
        // 3. 최대 찾기
        List<String> keys = new ArrayList<>(totalScore.keySet());
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(MessageFormat.format("{0} : {1}", keys.get(i), totalScore.get(keys.get(i))));
        }
        answer = keys.get(0);
        for (int i = 1; i < keys.size(); i++) {
            String current = keys.get(i);
            if (totalScore.get(answer) < totalScore.get(current)) {
                System.out.println(MessageFormat.format("renew {0} ", current));
                answer = keys.get(i);
            } else if (totalScore.get(answer) == totalScore.get(current)) {
                System.out.println(MessageFormat.format("compare {0} with {1}", answer, current));
                answer = answer.compareTo(current) > 0 ? current: answer;
            }
        }

        return answer;
    }
}
