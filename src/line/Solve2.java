package line;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
"AaTa+!12-3"	[2]
"aaaaZZZZ)"	[2, 3, 4]
"CaCbCgCdC888834A"	[1, 4, 5]
"UUUUU"	[1, 3, 4, 5]
"ZzZz9Z824"	[0]

 */
public class Solve2 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ans = s.solution("UUUUU(");
        for (int i = 0; i < ans.length; i++) {
            System.out.printf("%-3d", ans[i]);
        }

    }

    static class Solution {
        public int[] solution(String inp_str) {
            int[] answer = {};
            int cnt = 0;

            String p = "[^A-Za-z[0-9]~!@#$%^&*]+";
            String p1 = "[A-Z]+";
            String p2 = "[a-z]+";
            String p3 = "[0-9]+";
            String p4 = "[~!@#$%^&*]+";
            String p5 = "(\\S)\\1\\1\\1"; // continue
            ArrayList<Integer> arr = new ArrayList<>();

            // 조건 1
            if (inp_str.length() < 8 || inp_str.length() > 15)
                arr.add(1);


            // 조건2
            Matcher m = Pattern.compile(p).matcher(inp_str);
            if (m.find()) arr.add(2);

            // 조건3
            Matcher m1 = Pattern.compile(p1).matcher(inp_str);
            if (m1.find()) cnt += 1;
            Matcher m2 = Pattern.compile(p2).matcher(inp_str);
            if (m2.find()) cnt += 1;
            Matcher m3 = Pattern.compile(p3).matcher(inp_str);
            if (m3.find()) cnt += 1;
            Matcher m4 = Pattern.compile(p4).matcher(inp_str);
            if (m4.find()) cnt += 1;

            if (cnt < 3) arr.add(3);

            // 조건 4
            Matcher m5 = Pattern.compile(p5).matcher(inp_str);
            if (m5.find()) arr.add(4);

            // 조건 5
            HashMap<Character, Integer> map = new HashMap<>();
            char[] chars = inp_str.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                if (map.containsKey(chars[i]))
                    map.put(chars[i], map.get(chars[i]) + 1);
                else
                    map.put(chars[i], 1);
            }

            for (Map.Entry<Character, Integer> item : map.entrySet()) {
                if (item.getValue() >= 5) {
                    arr.add(5);
                    break;
                }
            }

            int size = arr.size();
            answer = new int[size];
            for (int i = 0; i < size; i++) {
                answer[i] = arr.get(i);
            }

            if (size == 0) answer = new int[]{0};

            return answer;
        }
    }
}
