package line;
import java.util.*;
import java.io.*;

// greedy
// 사람마다 나가는 시간 index 들어오는 idx
public class Solve4 {

    static class Solution {
        static public int[] solution(int[] enter, int[] leave) {
            int[] answer;
            int n = enter.length;

            ArrayList<People> list = new ArrayList<>();
            answer = new int[n];
            for (int i = 0; i < enter.length; i++) {
                People peo = new People();
                peo.num = enter[i];
                peo.in = i;
                list.add(peo);
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < leave.length; j++) {
                    if (list.get(i).num == leave[j]) {
                        list.get(i).out = j;
                    }
                }

            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(i).num == list.get(j).num) continue;

                    //나보다 늦게입실 했는데 나보다 일찍퇴실한경우
                    if (list.get(i).in < list.get(j).in) {
                        if (list.get(i).out > list.get(j).out) {
                            answer[list.get(i).num - 1]++;
                            answer[list.get(j).num - 1]++;
                        }
                    } else if (list.get(i).in > list.get(j).in) {
                        if (list.get(i).out > list.get(j).out) {
                            boolean flag = false;
                            //나보다 뒤에있는 애들이 j 보다 먼저 나간경우
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
        int num, in, out;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] enter = {1,4,2,3};
        int[] leave = {2,1,4,3};

        s.solution(enter,leave );
    }
}
