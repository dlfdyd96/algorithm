package PG49189;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private final static int n = 6;
    private final static int[][] input = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

    public static void main(String[] args) {
        int s = new Solution().solution(n, input);
        System.out.println(s);
    }
}


class Solution {
    private int N;
    private List<Pair>[] edge; // 간선 정보
    private int[] distance; // 최소 비용

    PriorityQueue<Pair> pq; // 우선순위 큐

    public int solution(int n, int[][] edge) { // Runnable()
        int answer = 1;

        init(n, edge);

        dijkstra(1);

        int maxValue = 0;
        for (int i = 1; i <= N; i++) {
            if (maxValue == distance[i])
                answer++;
            else if (maxValue < distance[i]){
                answer = 1;
                maxValue = distance[i];
            }
        }
        // System.out.println("\n" + answer);

        return answer;
    }

    public void init(int n, int[][] edge) {
        this.N = n;
        this.edge = new List[this.N + 1];
        this.distance = new int[this.N + 1];

        for (int i = 0; i <= this.N; i++) {
            this.edge[i] = new ArrayList<>();
            this.distance[i] = Integer.MAX_VALUE;
        }


        int vertexLength = edge.length;
        for (int i = 1; i <= vertexLength; i++) {
            int start = edge[i-1][0];
            int end = edge[i-1][1];
            this.edge[start].add(new Pair(end, 1));
            this.edge[end].add(new Pair(start, 1));
        }
    }

    /**
     * 1. 1번 노드를 출발하는 최단 경로를 구해야함 (다익스트라 알고리)
     */
    void dijkstra(int start) {
        this.distance[start] = 0;
        this.pq = new PriorityQueue<>();
        this.pq.add(new Pair(start, 0));

        while(!pq.isEmpty()) {
            int currentNode = pq.peek().first;
            int currentDistance = -pq.peek().second; // 짧은 것이 먼저 오도록 음수화(-) 합니다.
            pq.poll();

            if (distance[currentNode] < currentDistance) continue; // 최단 거리가 아닌 경우 스킵

            for (int i = 0; i < this.edge[currentNode].size(); i++) {
                // 선택된 노드의 인접 노드
                int nextNode = this.edge[currentNode].get(i).first;
                // 선택 된 노드를 인접 노드로 거쳐서 가는 비용
                int nextDistance = currentDistance + this.edge[currentNode].get(i).second;
                // 기존의 최소 비용보다 다 저렴하다면 교체한다.
                if (nextDistance < distance[nextNode]) {
                    distance[nextNode] = nextDistance;
                    pq.add(new Pair(nextNode, -nextDistance));
                }

            }
        }
    }
}


class Pair implements Comparable<Pair> {
    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair o) {
        return this.second <= o.second ? 1 : -1;
    }
}
