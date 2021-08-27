package Samsung2021Sumer.PracticeBattle.P12;

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int V, E, P, Q;

    // static Node nodes[];

    static int[][] tree;
    static boolean visit[];
    static int deep[];
    static int parent[][];

    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

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
            V = sc.nextInt();
            E = sc.nextInt();
            P = sc.nextInt();
            Q = sc.nextInt();
            parent = new int[V][21];
            visit = new boolean[V];
            deep = new int[V];


//            nodes = new Node[V + 1];
//            for (int i = 1; i <= V; i++) {
//                nodes[i] = new Node(i);
//            }

            for (int i = 0; i < E; i++) {
                int parent = sc.nextInt();
                int child = sc.nextInt();

//                if(nodes[parent].left == 0)
//                    nodes[parent].left = child;
//                else
//                    nodes[parent].right = child;
            }

            // int cla = findLCA(nodes[1], nodes[P], nodes[Q]);
        }
    }
    static void dfs(int current, int depth) {
        visit[current] = true;
        deep[current] = depth;

        for (int next: tree[current]
             ) {
            if (visit[next]) continue;
            parent[next][0] = current;
            dfs(next, depth + 1);
        }
    }

    static void connecting() {
        for (int i = 0; i < 21; i++) {
            for (int cur = 0; cur < V; cur++) {
                parent[cur][i] = parent[parent[cur][i-1]][i-1];
            }
        }
    }

    static int LCA(int node1, int node2) {
        if (deep[node1] > deep[node2]) {
            node1 = node1 ^ node2;
            node2 = node1 ^ node2;
            node1 = node1 ^ node2;
        }

        for (int i = 20; i >= 0; i--) {
            int jump = 1 << i;
            if (deep[node2] - deep[node1] >= jump ) node2 = parent[node2][i];
        }

        if (node1 == node2) return node1;

        for (int i = 20; i >= 0; i--) {

            if (parent[node1][i] == parent[node2][i]) continue;

            node1 = parent[node1][i];
            node2 = parent[node2][i];
        }
        return parent[node1][0];
    }
}

class Node {
    public static int left, right, value;
    Node(int _value) {
        value = _value;
    }
}