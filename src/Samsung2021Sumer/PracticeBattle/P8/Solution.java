package Samsung2021Sumer.PracticeBattle.P8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * Greedy 는 간단하고 빠르다, 또한 위험하다.
 * 완탐은 느리다, 하지만 반드시 답을 찾는다.
 * 		=> 시간을 개선하기위한 가지치기를 잘하면 (Bracktracking) 빠른 결과를 낼 수 있다.
 */

// Z35_Solution_SWEA_1244_최대상금_백트래킹
public class Solution {

    static int max;
    static HashSet<String> set;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String s = st.nextToken(); // 숫자판

            int[] num = new int[s.length()];

            for (int i = 0; i < s.length(); i++) {
                num[i] = s.charAt(i) - '0';
            }

            int N = Integer.parseInt(st.nextToken()); // 교환횟수\
            set = new HashSet<String>();	// 같은상태의 중복을 제거
            max = 0;
            find(num, N);
            System.out.println("#" + tc + " " + max);

        }
    }

    public static void find(int[] num, int N) {
        // num 배열을 10진수 숫자로 만들기
        int val = 0;

        for (int i = 0; i < num.length; i++) {
            val = val * 10 + num[i];
        }

        if(set.contains(""+val+N)) {
            return; // 검토 했던 작업이므로 리턴
        } else {
            set.add(""+val+N); // 현재 상태 저장
        }

        if(N == 0) { // 종료파트, 교환회수가 0이면 종료
            if(max < val) max = val;
        } else { // 임의의 2개 숫자를 골라서(조합) 교환
            for(int i=0; i<num.length-1; i++) {
                for (int j = i+1; j < num.length; j++) {

                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;

                    find(num, N-1);

                    // 원복
                    temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }
    }
}