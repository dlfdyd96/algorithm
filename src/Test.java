import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        int ans = new Solution().solution(new int[]{1, 2, 5, 7, 10, 15, 18, 20}, 15);
        System.out.println(ans);
    }
}

class Solution {
    // 크루스칼 알고리즘은 각 도시를 도로를 이용해 연결하고자 할 때 비용을 최소한으로 하고자 할 때 실제로 적용되는 알고리즘.
    List<Node> edges = new ArrayList<>();

    boolean find(int set[], int a, int b) {
        a = getParent(set, a);
        b = getParent(set, b);

        if (a == b) return true;
        return false;
    }

    private int getParent(int[] set, int a) {
        if (set[a] == a) return a;
        return set[a] = getParent(set, set[a]);
    }

    private void unionParent(int set[], int a, int b) {
        a = getParent(set, a);
        b = getParent(set, b);

        if (a < b) set[b] = a;
        else
            set[a] = b;
    }

    public void solution(int n) {
        int[] set = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = i;
        }

        int sum = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (!find(set, edges.get(i).start - 1, edges.get(i).end - 1)) {
                sum += edges.get(i).distance;
                unionParent(set, edges.get(i).start - 1, edges.get(i).end - 1);
            }
        }
    }
}

class Node {
    public int start;
    public int end;
    public int distance;
}