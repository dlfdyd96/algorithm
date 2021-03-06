package BJ19237;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int[][] smellsOwner = new int[N][N];
        Map<Integer, Node> sharks = new HashMap<>();

        for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
            // map[i] = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).mapToInt(item->item).toArray();
            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (number > 0) {
                    smellsOwner[i][j] = number;
                    map[i][j] = 1;
                    sharks.put(number, new Node(i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            Node current = sharks.get(i);
            current.direction = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= M; i++) {
            Node current = sharks.get(i);
            List<List<Integer>> priority = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                List<Integer> direction = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                for (int d = 0; d < 4; d++) {
                    Integer currentDirection = Integer.parseInt(st.nextToken());;
                    direction.add(currentDirection);
                }
                priority.add(direction);
            }
            current.priority = priority;
        }
        Solution s = new Solution(N,M,k,map,sharks,smellsOwner);

        bw.write(s.solution() + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Solution {
    private int N;
    private int M;
    private int k;
    private int[][] map;
    private int[][] smellsOwner;
    private Map<Integer, Node> sharks;
    private final int[][] direction = new int[][]{
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    private int ans;

    public Solution(int n, int m, int k, int[][] map, Map<Integer, Node> sharks, int[][] smellsOwner) {
        N = n;
        M = m;
        this.k = k;
        this.map = map;
        this.sharks = sharks;
        ans = 1;

        this.smellsOwner = smellsOwner;
    }

    public int solution() {
        while(sharks.size() > 1) {
            simulation();
            if (ans > 1000) return - 1;
        }

        return ans - 1;
    }

    private void simulation() {
        for (int i = 1; i <= M ; i++) {
            if (!sharks.containsKey(i)) continue;
            moveShark(i, sharks.get(i));
        }
        ans++;
        for (int i = 1; i <= M ; i++) {
            if (!sharks.containsKey(i)) continue;
            markShark(i, sharks.get(i));
        }
    }

    // visited??? shark ?????? ??? ?????? ?????? ??????
    private void markShark(int key, Node current) {
        if (map[current.i][current.j] == ans) { // ?????? ????????? ???????
            sharks.remove(key);
            return;
        }
        map[current.i][current.j] = ans;
        smellsOwner[current.i][current.j] = key;
    }

    private void moveShark(int key, Node current) {
        boolean canMove = false;
        List<Integer> priority = current.priority.get(current.direction - 1);
        for (Integer p: priority) {
            // ?????? ???????????? ?????? ?????????

            int nextI = current.i + direction[p - 1][0];
            int nextJ = current.j + direction[p - 1][1];
            int nextSecond = ans + 1; // 4 + 1
            // ?????? ???
            if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
            // ????????? ??????
            if (map[nextI][nextJ] != 0 && map[nextI][nextJ] >= nextSecond - k) continue;
            current.i = nextI;
            current.j = nextJ;
            current.second = nextSecond;
            current.direction = p;
            canMove = true;
            break;
        }
        if (!canMove) {
            for (Integer p: priority) {
                // ?????? ???????????? ?????? ?????????
                // if (getReverseDirection(current.direction) == p) continue;

                int nextI = current.i + direction[p - 1][0];
                int nextJ = current.j + direction[p - 1][1];
                int nextSecond = ans + 1; // 4 + 1
                // ?????? ???
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
                // ???????????? ?????? ???.
                if (smellsOwner[nextI][nextJ] != key) continue;
                current.i = nextI;
                current.j = nextJ;
                current.second = nextSecond;
                current.direction = p;
                break;
            }
        }
    }

    public int getReverseDirection(int currentDirection) {
        if (currentDirection == 1) {
            return 2;
        }
        else if (currentDirection == 2) {
            return 1;
        }
        else if (currentDirection == 3) {
            return 4;
        }
        else {
            return 3;
        }
    }
}

class Node {
    public int i;
    public int j;
    public int direction;
    public int second;
    public List<List<Integer>> priority;

    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}