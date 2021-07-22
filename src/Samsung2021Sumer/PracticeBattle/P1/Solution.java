package Samsung2021Sumer.PracticeBattle.P1;

import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Set;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        System.setIn(new FileInputStream("/Users/1yongs/IdeaProjects/algorithm/src/Samsung2021Sumer/PracticeBattle/res/sample_input_1.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {

            /////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
            /////////////////////////////////////////////////////////////////////////////////////////////
            int N, ans = 0;
            N=sc.nextInt();
            int cnt = 1;

            // Set<Integer> s = new HashSet<>();
            int flag = 0;

            while(true) {
                int currentN = N*cnt;
                String[] str = new String(currentN+"").split("");
                // System.out.println("#TEST "+ str[0]);
                for (String item: str) {
                    int currentNumber = Integer.parseInt(item);
                    // s.add(currentNumber);
                    flag |= (1 << currentNumber);
                    // if (s.size() == 10)
                    if (flag == 1023)
                        break;
                }
                // if (s.size() == 10)
                if (flag == 1023)
                    break;
                cnt++;
            }
            System.out.println("#"+test_case+ " " + N*cnt);
        }
    }
}