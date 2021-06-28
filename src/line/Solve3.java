package line;

import java.util.*;

public class Solve3 {
    public static void main(String[] args) {
        int[] enter = {1, 4, 2, 3};
        int[] leave = {2, 1, 4, 3};
        Solution s = new Solution();
        s.solution(enter, leave);
    }

    static class Solution {
        public int[] solution(int[] enter, int[] leave) {
            int[] answer;
            int N = enter.length;

            List<People> list = new ArrayList<>();
            answer = new int[N];

            for (int i = 0; i < enter.length; i++) {
                People people = new People();
                people.num = enter[i];
                people.in = i;
                list.add(people);
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < leave.length; j++) {
                    if (list.get(i).num == leave[j]) {
                        list.get(i).out = j;
                    }
                }

            }

            int size = list.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (list.get(i).num == list.get(j).num) continue;

                    // 일찍ㅌ 퇴실한 경우
                    if (list.get(i).in < list.get(j).in) {
                        if (list.get(i).out > list.get(j).out) {
                            answer[list.get(i).num - 1]++;
                            answer[list.get(j).num - 1]++;
                        }
                    } else if (list.get(i).in > list.get(j).in) {
                        if (list.get(i).out > list.get(j).out) {
                            boolean flag = false;
                            // 먼저 나간 경우
                            for (int j2 = i; j2 < list.size(); j2++) {
                                if (list.get(j2).out < list.get(j).out) {
                                    flag = true;
                                }

                            }
                            if (flag) {
                                answer[list.get(i).num - 1]++;
                                answer[list.get(j).num - 1]++;
                            }
                        }
                    }


                }
            }

            return answer;
        }
    }

    static class People {
        public int num, in, out;
    }
}
