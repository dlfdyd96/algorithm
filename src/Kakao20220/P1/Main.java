package Kakao20220.P1;

import java.util.HashMap;
import java.util.Map;

/*
["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
["con", "ryan"]	["ryan con", "ryan con", "ryan con", "ryan con"]	3	[0,0]

 */
public class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(
                        new String[]{"muzi", "frodo", "apeach", "neo"},
                        new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
                        2
                )
        );
        System.out.println(
                new Solution().solution(
                        new String[]{"con", "ryan"},
                        new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
                        3
                )
        );
    }
}


class Solution {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};

        // init
        Map<String, Integer> bannedMap = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            bannedMap.put(id_list[0], 0);
        }

        // solution
        for (int i = 0; i < id_list.length; i++) {

        }
        return answer;
    }
}