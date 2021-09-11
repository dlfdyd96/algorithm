package line.P1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int answer = new Solution().solution(
//                 new int[]{0, 1, 0, 0,}, 1
//                new int[]{0, 1, 0, 0, 1, 1, 0}, 2
                new int[]{0, 1, 0}, 2
        );
        System.out.println(answer);
    }
}

// 1. 재학 생 index 저장 -> oldStudents
/*
    answer = 0 ;
    first = oldStudent[0];
    answer =
    for i+1 to oldStudents:
        if(i == 마지막 idx) :
            answer += (oldStudnetLength - i + 1)
        else :

 */

class Solution {
    private int oldStudentLength;
    private List<Integer> oldStudents;

    public int solution(int[] student, int k) {
        int answer = 0;

        // init
        init(student, k);

        // logic
        for (int i = 0; i <= oldStudentLength - k; i++) {
            answer += (
                    leftNewStudent(student, oldStudents.get(i)) *
                            rightNewStudent(student, oldStudents.get(i + k - 1))
            );
//            if (i == oldStudentLength - k) { // 마지막 경우 = 오른쪽으로 쭉
//                answer += (oldStudentLength - oldStudents.get(i));
//
//            } else
//            if (i == 0) { // 왼쪽으로 쭉
//
//            } else { // 왼쪽으로, 오른쪽으로
//
//            }

        }

        return answer;
    }

    private int leftNewStudent(int[] student, int oldStudentIndex) {
        int cnt = 1;
        for (int i = oldStudentIndex - 1; i >= 0 ; i--) {
            if (student[i] == 1) return cnt;
            cnt++;
        }
        return cnt;
    }

    private int rightNewStudent(int[] student, int oldStudentIndex) {
        int cnt = 1;
        for (int i = oldStudentIndex + 1; i < student.length ; i++) {
            if (student[i] == 1) return cnt;
            cnt++;
        }
        return cnt;
    }

    private void init(int[] student, int k) {
        this.oldStudents = new ArrayList<>();
        for (int i = 0; i < student.length ; i++) {
            if (student[i] == 1) oldStudents.add(i);
        }
        this.oldStudentLength = oldStudents.size();
    }
}