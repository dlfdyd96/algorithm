package sendcorp.P2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(
                s.solution(
                        new int[][]{
                                {0, 0, 3, 0, 0, 0, 0},
                                {1, 0, 3, 0, 0, 0, 0},
                                {0, 0, 3, 0, 0, 0, 0},
                                {0, 0, 2, 0, 0, 3, 3},
                                {2, 2, 2, 0, 2, 0, 0},
                                {3, 3, 2, 0, 2, 0, 3},
                                {3, 3, 2, 0, 2, 0, 4}
                        }

                )
        );
    }
}

/*
init()
- 빨간색 차에 대해 갯수를 새어야 함. : int numberOfRedCar;
- 빨간색 차에 대한 정보 얻어야함 : List<Node> redCars;
 */
/*
simulation()
빨간색 차 0개 없을 때 ~ 빨간색 차n까지 모든 거 돌리기.
 */
class Solution {
    private final static int[][] direction = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    private int MAP_LENGTH;
    private int[][] cars;
    private Node me;
    private Node exit;


    private void init(int[][] cars) {
        this.cars = cars;
        MAP_LENGTH = cars.length;

        for (int i = 0; i < MAP_LENGTH; i++) {
            for (int j = 0; j < MAP_LENGTH; j++) {
                if (cars[i][j] == 1) {
                    me = new Node(i, j);
                } else if (cars[i][j] == 4) {
                    exit = new Node(i, j);
                }
            }
        }
    }

    public int solution(int[][] cars) {
        int answer = -1;

        init(cars);

        // answer = selectRedCars();
        answer = findLoad();

        return answer;
    }

//    private int selectRedCars() {
//        int minSelected = -1;
//
//        for (int i = 0; i <= numOfRedCar; i++) {
//            boolean result = simulation(i, 0, 0);
//            if (result) {
//                minSelected = i;
//                break;
//            }
//        }
//        return minSelected;
//    }
//
//    private boolean simulation(int maxDepth, int depth, int index) {
//        if (depth == maxDepth) {
//            return findLoad();
//        }
//
//        for (int i = index; i < numOfRedCar; i++) {
//            selectedRedCars.add(redCars.get(i));
//            boolean result = simulation(maxDepth, depth + 1, i + 1);
//            if (result) return true;
//            selectedRedCars.remove(selectedRedCars.size() - 1);
//        }
//
//        return false;
//    }

    /*
    방향 조건
    - 범위 밖인지
    - visited 인지
    - 폐차 인지
    - 빨간 차이면 사라진 벽인지

     */
    private int findLoad() {
        int[][] visited = new int[MAP_LENGTH][MAP_LENGTH];
        for (int i = 0; i < MAP_LENGTH; i++) {
            for (int j = 0; j < MAP_LENGTH; j++) {
                visited[i][j] = -1;
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(me);

        visited[me.r][me.c] = me.redCnt;

        int minAns = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Node current = q.poll();
            visited[current.r][current.c] = current.redCnt;

            for (int[] d : direction) {
                Node next = current.nextNode(d);
                if (exit.equals(next)) {
                    return next.redCnt;
//                    minAns = Math.min(minAns, next.redCnt);
//                    continue;
                }
                if (!next.validateRange(MAP_LENGTH)) continue;
                if (cars[next.r][next.c] == 3) continue;
                if (cars[next.r][next.c] == 2) next.redCnt++;
                // 다음 방문할 곳이 방문은 했고 next가 가성비가 없으면 ? 다음으로 넘김.;
                if (visited[next.r][next.c] >= 0 && visited[next.r][next.c] <= next.redCnt) continue;
                q.add(next);
            }

        }
        return minAns == Integer.MAX_VALUE ? -1 : minAns;
    }


}

class Node implements Comparable<Node> {
    public int r;
    public int c;
    public int redCnt = 0;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Node nextNode(int[] direction) {
        return new Node(r + direction[0], c + direction[1], redCnt);
    }

    public Node(int r, int c, int redCnt) {
        this.r = r;
        this.c = c;
        this.redCnt = redCnt;
    }

    public boolean validateRange(int MAX_LENGTH) {
        return !(r < 0 || r >= MAX_LENGTH || c < 0 || c >= MAX_LENGTH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return r == node.r && c == node.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }

    @Override
    public int compareTo(Node o) {
        return this.redCnt < o.redCnt ? -1 : 1;
    }
}
