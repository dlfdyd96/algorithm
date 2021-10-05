package prgrms.weekly.w8;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;

        int width = 0;
        int height = 0;

        for (int i = 0; i < sizes.length; i++) {
            int first = sizes[i][0];
            int second = sizes[i][1];

            if (first < second) {
              width = Math.max(width, second);
              height = Math.max(height, first);
            } else {
                width = Math.max(width, first);
                height = Math.max(height, second);
            }
        }

        answer = width * height;
        return answer;
    }
}