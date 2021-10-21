package JB.P13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();

    StringTokenizer st = new StringTokenizer(input);


    int N = Integer.parseInt(st.nextToken());
    List<Item> items = new ArrayList<>();

    int maxExit = 0;


    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int index =Integer.parseInt(st.nextToken());
      int s =Integer.parseInt(st.nextToken());
      int e =Integer.parseInt(st.nextToken());
      maxExit = Math.max(maxExit, e);
      items.add(new Item(
          index,s,e
      ));
    }

    // Solution s = new Solution(N, items, maxExit);


    System.out.println(solve1(items));
    System.out.println(solve2(maxExit, items));
  }

  public static int solve1(List<Item> items) { // 화장실 1칸
    PriorityQueue<Item> pq = new PriorityQueue<>();

    items.forEach(pq::offer);

    int end = 0;
    int tot = 0;
    Item sub;

    while(pq.size() > 0) {
      sub = pq.peek();
      if (end <= sub.s) { // 현재 꺼낸 사람이 현재 가장 최근에 나온 사람 이후 일때
        tot++; //
        end = sub.e;
      }
      pq.poll();
    }

    return tot;
  }

  public static int solve2(int maxExit, List<Item> items) { // 화장실 n칸
    int[] timeline = new int[maxExit + 1];

    int currentMax = 0;

    for (Item user: items
    ) {
      if (user.time == 0) timeline[user.s]++;
      for (int i = user.s; i < user.e; i++) {
        timeline[i]++;
        currentMax = Math.max(currentMax, timeline[i]);
      }
    }

//    for (int i = 1; i < maxExit + 1; i++) {
//      System.out.print(timeline[i] + " ");
//    }
//    System.out.println();

    return currentMax;
  }
}


class Item implements Comparable<Item> {
  public int i;
  public int s;
  public int e;

  public int time; // 화장실 채류 시간

  public Item() {
  }

  public Item(int i, int s, int e) {
    this.i = i;
    this.s = s;
    this.e = e;
    this.time = e - s;
  }

  @Override
  public int compareTo(Item o) {
    if(this.e == o.e) {
      return this.s < o.s ? -1: 1;
    }
    return this.e < o.e ? -1 : 1; // end time fast first
  }
}


/*
for list 순회:



 */
class Solution {
  private int n;
  private List<Item> items;
  private int maxExit;



  public Solution(int n, List<Item> items, int maxExit) {
    this.n = n;
    this.items = items;
    this.maxExit = maxExit;
  }

  public int solve2() { // 칸 여러개
    int[] timeline = new int[maxExit + 1];

    int currentMax = 0;

    int size = items.size();

    for (Item user: items
    ) {
      for (int i = user.s; i <= user.e; i++) {
        timeline[i]++;
        currentMax = Math.max(currentMax, timeline[i]);
      }
    }
    return currentMax;
  }

  public int solve1() {
    return 1;
  }

}