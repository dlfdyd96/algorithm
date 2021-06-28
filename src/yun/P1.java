package yun;


import java.util.*;

class Solution {
    public int[] solution(int[] grade) {
        int[] grade_copy = new int[grade.length];
        System.arraycopy(grade, 0, grade_copy, 0, grade.length);


        int[] ranks = new int[grade.length];
        HashMap<Integer, Integer> ranks_map = new HashMap<>();
        Integer[] grade_copy_integer = Arrays.stream(grade_copy).boxed().toArray(Integer[]::new);

        Arrays.sort(grade_copy_integer, Collections.reverseOrder());

//        for(int v : grade_copy_integer) {
//            System.out.print(v + ", ");
//        }
//        System.out.println();

        ranks[0] = 1;
        ranks_map.put(grade_copy_integer[0], 1);
        for (int i = 1; i < ranks.length; i++) {
            ranks[i] = grade_copy_integer[i] == grade_copy_integer[i - 1] ? ranks[i - 1] : i + 1;
            ranks_map.put(grade_copy_integer[i], ranks[i]);
            // System.out.println(grade_copy_integer[i] + ", " + ranks[i]);
        }

        int[] answer = new int[grade.length];
        for (int i = 0; i < ranks.length; i++) {
            answer[i] = ranks_map.get(Integer.valueOf(grade[i]));
        }

        System.out.println("랭크 개수 : " + ranks_map.size());

        return answer;
    }
}

public class P1 {

    static public class Solution {
        static public int[] solution(int N, int[][] relation) {
            int[] answer = new int[N];

            ArrayList<Integer> list[] = new ArrayList[N+1];

            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < relation.length; i++) {
                list[relation[i][0]].add(relation[i][1]);
                list[relation[i][1]].add(relation[i][0]);
            }

            check = new boolean[N+1];
            for (int i = 1; i <= N; i++) {
                check[i] = true;
                dfs(i,N,list,0);
                answer[i-1] = res;
                res =0;
                check = new boolean[N+1];
            }
            System.out.println(Arrays.toString(answer));
            return answer;
        }
        static int res;
        static boolean check[];
        static public void dfs(int num,int N, ArrayList<Integer> list[],int dep)
        {
            int cur = num;

            if(dep >= 2 )
            {
                return;
            }
            for (int i = 0; i <list[cur].size(); i++) {
                int next = list[cur].get(i);


                if(check[next]) continue;

                check[next]= true;
                res+=1;
                dfs(next,N,list,dep+1);
            }

        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] grade = { 7, 8, 9, 1 , 1 };
        int[] answer = s.solution(7, new int[][]{{2, 3}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {6, 7}, {6, 3}});
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}