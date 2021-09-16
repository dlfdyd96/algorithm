package prgrms.weekly.w7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
[1,3,2]	[1,2,3]	[0,1,1]
[1,4,2,3]	[2,1,3,4]	[2,2,1,3]
[3,2,1]	[2,1,3]	[1,1,2]
[3,2,1]	[1,3,2]	[2,2,2]
[1,4,2,3]	[2,1,4,3]	[2,2,0,2]

 */
public class Main {
    public static void main(String[] args) {
        int[] answer = new Solution().solution(
//                new int[]{1, 3, 2}, new int[]{1, 2, 3}
        new int[]{1,4,2,3}, new int[]{2,1,3,4}
//        new int[]{3,2,1}, new int[]{2,1,3}
//        new int[]{3,2,1}, new int[]{1,3,2}
//        new int[]{1,4,2,3}, new int[]{2,1,4,3}
        );
        Arrays.stream(answer).forEach(System.out::print);
    }
}

class Solution {
    public int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length];
        boolean[][] visitedMap = new boolean[enter.length][enter.length];
        boolean[] visited = new boolean[enter.length];

        for (int i = 0; i < leave.length; i++) {
            int index = 0;
            // find idx && 방에 누구 있는지 검사
            for (int j = 0; j < enter.length; j++) {
                if (leave[i] == enter[j]) {
                    index = j;
                    break;
                }
            }


            for (int j = 0; j <= index; j++) {
                int current1 = enter[j] - 1;
                if (visited[current1]) continue;
                for (int k = j + 1; k <= index ; k++) {
                    int current2 = enter[k] - 1;
                    if (visited[current2]) continue;
                    visitedMap[current1][current2] = visitedMap[current2][current1] = true;
                }
            }
            visited[enter[index] - 1] = true;
        }

        for (int i = 0; i < enter.length; i++) {
            int cnt = 0;
            for (int j = 0; j < enter.length; j++) {
                if (visitedMap[i][j]) cnt++;
            }
            answer[i] = cnt;
        }

        return answer;
    }
}