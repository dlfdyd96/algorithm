package DevCourse.P2;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Solution s  = new Solution();
//        int result = s.solution(new int[]{1,3,2,5,4}, 6);
        int[] result = s.solution(new int[]{500, 1000, -300, 200, -400, 100, -100});
        for (int item: result) {
            System.out.print(item);
        }
    }
}

class Solution {
    public int[] solution(int[] deposit) {
        int[] answer = {};
        Stack<Integer> withdrawal = new Stack<>();
        for (int current: deposit) {
            if(current > 0) { // input
                withdrawal.add(current);
                continue;
            }

            // output
            while(true) {
                int top = withdrawal.pop();
                top += current;
                if(top > 0) {
                    withdrawal.add(top);
                    break;
                } else if(top == 0)
                    break;
                else {
                    current = top;
                }
            }


        }

        answer = withdrawal.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}