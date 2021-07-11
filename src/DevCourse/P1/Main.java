package DevCourse.P1;

public class Main {
    public static void main(String[] args) {
        Solution s  = new Solution();
//        int result = s.solution(new int[]{1,3,2,5,4}, 6);
        int result = s.solution(new int[]{2,2,4,3}, 8);
        System.out.print(result);
    }
}

class Solution {
    public int solution(int[] d, int m) {
        int answer = -1;
        int cnt = 0;
        for (int i = 1; i <= d.length; i++) {
            int current = (int) Math.pow(2, cnt);
            if (d[i-1] >= current) // 실어서 갈 수 있음.
            {
                m -= current;
                cnt++;
            } else {
                cnt = 0;
            }
            if (m<= 0) return i;
        }
        return answer;
    }
}