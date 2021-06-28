package concept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Kruskal Algorithm
- 가장 적은 비용으로 모든 노드를 연결하기 위해 사용하는 알고리즘
- 다시말해 최소 비용 신장 트리를 만들기 위한 대표적인 알고리즘.
- 조건에 맞게 적은 비용 순서대로 연결하여 union - find 개념을 적용합니다.
 */
public class kruskal {
    public static void main(String[] args) {
        int n = 7;
        int m = 11;

        List<Edge> v = new ArrayList<>();
        v.add(new Edge(1, 7, 12));
        v.add(new Edge(1, 4, 28));
        v.add(new Edge(1, 2, 67));
        v.add(new Edge(1, 5, 17));
        v.add(new Edge(2, 4, 24));
        v.add(new Edge(2, 5, 62));
        v.add(new Edge(3, 5, 20));
        v.add(new Edge(3, 6, 37));
        v.add(new Edge(4, 7, 13));
        v.add(new Edge(5, 6, 45));
        v.add(new Edge(5, 7, 73));

        // 1. 간선의 비용으로 오름차순 정렬
        Collections.sort(v);

        // 2. 각 정점이 포함된 그래프가 어디인지 저장
        int[] set = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = i;
        }

        // 거리의 합을 0으로 초기화
        int sum = 0;
        for (int i = 0; i < v.size(); i++) {
            // 동일한 부모를 가르키지 않는 경우, 즉 사이클이 발생하지 않을 때만 선택!
            if (!find(set, v.get(i).node[0] - 1, v.get(i).node[1] - 1)) {
                sum += v.get(i).distance;
                unionParent(set, v.get(i).node[0] - 1, v.get(i).node[1] - 1);
            }
        }
        System.out.println(sum);
    }

    static boolean find(int[] set, int a, int b) {
        a = getParent(set, a);
        b = getParent(set, b);
        if (a == b)
            return true;
        return false;
    }

    static int getParent(int[] set, int x) {
        if (x == set[x]) return x;
        return set[x] = getParent(set, set[x]);
    }

    static void unionParent(int set[], int a, int b) {
        a = getParent(set, a);
        b = getParent(set, b);

        // 더 숫자가 작은 부로 병합
        if (a < b)
            set[b] = a;
        else
            set[a] = b;
    }

}

class Edge implements Comparable<Edge> {
    public int[] node = new int[2];
    public int distance;

    Edge(int a, int b, int distance) {
        this.node[0] = a;
        this.node[1] = b;
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }


    @Override
    public int compareTo(Edge o) {
        return getDistance() - o.getDistance(); // 오름차순
    }
}