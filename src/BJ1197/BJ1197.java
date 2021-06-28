package BJ1197;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1197 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge> v = new ArrayList<>();

        // 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            v.add(new Edge(a- 1, b-1, c));
        }

        // 정렬
        Collections.sort(v);
        

        // 각 정점이 저장된 그래프가 어디인지 저장
        int[] set = new int[V];
        for (int i = 0; i < V; i++) {
            set[i] = i;
        }

        int sum = 0;
        for (int i = 0; i < v.size(); i++) {
            if(!find(set, v.get(i).node[0], v.get(i).node[1])) { // 다른 부모가 아니면
                sum += v.get(i).distance;
                unionParent(set, v.get(i).node[0], v.get(i).node[1]);
            }
        }

        bw.write(sum + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    static boolean find(int[] set, int a, int b) {
        a = getParent(set, a);
        b = getParent(set, b);

        if (a == b) return true; // 같은 부모?
        return false; // 다른 부모
    }

    static int getParent(int[] set, int x){
        if ( x == set[x]) return x;
        return set[x] = getParent(set, set[x]);
    }

    static void unionParent(int[] set, int a, int b) {
        a = getParent(set, a);
        b = getParent(set, b);

        if (a < b)
            set[b] = a;
        else
            set[a] = b;
    }

    static class Edge implements Comparable<Edge>{
        public int distance;
        public int[] node = new int[2];
        Edge(int a, int b, int distance) {
            this.node[0] = a;
            this.node[1] = b;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return distance - o.distance;
        }
    }
}


