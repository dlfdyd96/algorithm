package PG1836;

import java.util.*;
import java.util.stream.Collectors;

/*
// List<Ans>랑 cnt에 다 넣기

 */
public class Main {
    public static void main(String[] args) {
        String answer;
        answer = new Solution().solution(
                3,
                3,
                new String[]{
                        "DBA",
                        "C*A",
                        "CDB"});
        System.out.println(answer);
        answer = new Solution().solution(2, 4, new String[]{"NRYN", "ARYA"});
        System.out.println(answer);
        answer = new Solution().solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."});
        System.out.println(answer);
        answer = new Solution().solution(2, 2, new String[]{"AB", "BA"});
        System.out.println(answer);
    }
}

class Solution {

    boolean isOk = false;
    int nextVisit = -1;
    boolean[] visited;

    public String solution(int m, int n, String[] board) {
        String answer = "";
        TreeSet<Character> set = new TreeSet<>();
        char[][] map = new char[m][n];

        for (int i = 0; i < map.length; i++)
            map[i] = board[i].toCharArray();

        HashMap<Integer, ArrayList<Node>> results = new HashMap<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (Character.isAlphabetic(map[i][j]))
                    set.add(map[i][j]);
            }
        }

        ArrayList<Character> check = new ArrayList<>(set);


        int index = 0;
        visited = new boolean[set.size()];
        while (true) {
            ArrayList<Node> list = new ArrayList<>();
            isOk = false;


            for (int t = 0; t < check.size(); t++) {
                if (visited[t]) continue;
                char c = check.get(t);
                nextVisit = t;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] != c) continue;
                        if (isOk) break;
                        findPair(list, map, i, j, i, j - 1, 0, 0);
                        findPair(list, map, i, j, i - 1, j, 1, 0);
                        findPair(list, map, i, j, i, j + 1, 2, 0);
                        findPair(list, map, i, j, i + 1, j, 3, 0);
                    }
                    if (isOk) break;
                }
            }

            replace5ToPossible(map);

            if (list.isEmpty()) break;
            else {
                results.put(index++, list);
            }
        }

        String data = "";
        for (int key : results.keySet()) {
            ArrayList<Node> list = results.get(key);
            Collections.sort(list);
            for (Node node : list) {
                data += node.word;
            }
        }

        for (boolean visit : visited)
            if (!visit) return "IMPOSSIBLE";

        return data;
    }

    public void replace5ToPossible(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == '5') map[i][j] = '.';
            }
        }
    }

    public void findPair(ArrayList<Node> list, char[][] map, int firstX, int firstY, int endX, int endY, int direction, int level) {
        if (endX < 0 || endY < 0 || endX >= map.length || endY >= map[0].length) return;
        if (level >= 2) return;
        if (map[firstX][firstY] > 'Z' || map[firstX][firstY] < 'A') return;
        if (firstX == endX && firstY == endY) return;
        if (map[endX][endY] == map[firstX][firstY] && map[endX][endY] <= 'Z' && map[endX][endY] >= 'A') {
            int[] first = new int[2];
            first[0] = firstX;
            first[1] = firstY;
            int[] second = new int[2];
            second[0] = endX;
            second[1] = endY;
            list.add(new Node(first, second, map[endX][endY]));
            map[endX][endY] = '.';
            map[firstX][firstY] = '.';
            isOk = true;
            visited[nextVisit] = true;
            return;
        } else if (map[endX][endY] == '.') {
            int[] directionX = {0, -1, 0, 1};
            int[] directionY = {-1, 0, 1, 0};
            for (int i = 0; i < 4; i++) {
                if (direction != i)
                    findPair(list, map, firstX, firstY, endX + directionX[i], endY + directionY[i], i, level + 1);
                else
                    findPair(list, map, firstX, firstY, endX + directionX[i], endY + directionY[i], i, level);
            }
        } else
            return;
    }
}

class Node implements Comparable<Node> {
    public int[] first;
    public int[] second;
    public char word;

    public Node(int[] first, int[] second, char word) {
        this.first = first;
        this.second = second;
        this.word = word;
    }

    @Override
    public int compareTo(Node other) {
        return Character.compare(this.word, other.word);
    }
}