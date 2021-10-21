package JB.P14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static int N;
  public static int K;
  public static int JUMP_MAX_CNT;
  public static int[] stairs;
  public static LinkedList<Node> jumps;
  public static int maxGap = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();

    StringTokenizer st = new StringTokenizer(input);

    K = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    stairs = new int[N];
    JUMP_MAX_CNT = N - 1;
    jumps = new LinkedList<>();

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int item = Integer.parseInt(st.nextToken());
      stairs[i] = item;
    }

    solve(0, 0);

    System.out.println(maxGap + "");

  }
  public static void solve(int depth, int previous) {
    // 기저 조건
    // 다 채웠는데 마지막이 끝 END가 아니면 종료?
    if (depth >= K)
      endProcess();

    if (depth == K - 1) {
      jumps.push(new Node(previous, N - 1)); // 마지막 기회 일때 끝 idx 넣기
      solve(depth + 1, N - 1);
      jumps.pop();
    } else {
      for (int i = previous + 1; i < N; i++) {
        jumps.push(new Node(previous, i));
        solve(depth + 1, i);
        jumps.pop();
      }
    }
  }

  public static void endProcess() {
    int currentMaxGap = 0;
    for (Node jump: jumps) {
      int gap = stairs[jump.endIndex] - stairs[jump.startIndex];
      currentMaxGap = Math.max(currentMaxGap, gap);
    }
    maxGap = Math.min(currentMaxGap, maxGap);
  }
}

class Node {
  public int startIndex;
  public int endIndex;

  public Node(int startIndex, int endIndex) {
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }
}