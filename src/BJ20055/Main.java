package BJ20055;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // int inputUpperBound = 2 * N;

        int zeroCnt = 0;
        LinkedList<Node> upQ = new LinkedList<>();
        LinkedList<Node> downQ = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            upQ.add(
                    new Node(
                            Integer.parseInt(st.nextToken())
                    )
            );
        }
        // Stack<Node> tempDownQ = new Stack<>();
        for (int i = 0; i < N; i++) {
            downQ.add(
                    new Node(
                            Integer.parseInt(st.nextToken())
                    )
            );
        }
//        for (int i = 0; i < N; i++) {
//            downQ.add(tempDownQ.pop());
//        }


        ConveyorBelt conveyorBelt = new ConveyorBelt(N, zeroCnt, upQ, downQ);
        Solution s = new Solution(conveyorBelt, K, N);

        bw.write(s.solution() + "");
        bw.flush();
        bw.close();
        br.close();
    }
}


class Solution {
    private ConveyorBelt conveyorBelt;
    private int K;
    private int N;
    private int ans;

    public Solution(ConveyorBelt conveyorBelt, int k, int n) {
        this.conveyorBelt = conveyorBelt;
        K = k;
        N = n;
        ans = 1;
    }

    public int solution() {
        simulation();
        return ans;
    }

    public void simulation() {
//        System.out.println("start");
//        conveyorBelt.print();
        while (true) {
            // conveyorBelt.print();
            rotate(); // STEP 1.
            moving(); // STEP 2.
            putRobot(); // STEP 3.
            if (toleranceCount() >= K) break;
            ans++;
        }
//        System.out.println("end");
//        conveyorBelt.print();

    }

    private void putRobot() {
//        System.out.println("put robot");
        conveyorBelt.putRobot();
//        conveyorBelt.print();
    }

    private void moving() {
//        System.out.println("moving robot");
        conveyorBelt.moving();
//        conveyorBelt.print();
    }

    private void rotate() {
//        System.out.println("rotate robot");
        conveyorBelt.rotate();
//        conveyorBelt.print();
    }

    private int toleranceCount() {
        return conveyorBelt.getZeroCnt();
    }
}

class ConveyorBelt {
    private int N;
    private int zeroCnt;
    private LinkedList<Node> upQ;
    private LinkedList<Node> downQ;

    public ConveyorBelt(int N, int zeroCnt, LinkedList<Node> upQ, LinkedList<Node> downQ) {
        this.N = N;
        this.zeroCnt = zeroCnt;
        this.upQ = upQ;
        this.downQ = downQ;
    }

    public void rotate() {
//        Queue<Node> temp = new LinkedList<>();
//        temp.poll();
        Node upQEnd = upQ.pollLast();
        Node downQEnd = downQ.pollLast();
        upQEnd.onItem = false;
        upQ.addFirst(downQEnd);
        downQ.addFirst(upQEnd);
    }

    public int getZeroCnt() {
        return zeroCnt;
    }

    public void moving() {
        Node prevNode = upQ.pollLast();
        if (prevNode.onItem) prevNode.onItem = false;
        upQ.addFirst(prevNode);

        for (int i = 0; i < N - 1; i++) {
            Node current = upQ.pollLast();
            if (current.onItem) {
                if (!prevNode.onItem) { // 앞 쪽이 비어있음
                    if (prevNode.tolerance > 0) {
                        prevNode.tolerance--;
                        prevNode.onItem = true;
                        current.onItem = false;
                        if (prevNode.tolerance == 0) {
                            zeroCnt++;
                        }
                    }
                }
            }
            prevNode = current;
            upQ.addFirst(current);
        }
    }

    public void putRobot() {
        Node current = upQ.peekFirst();
        if (current.tolerance > 0) {
            current.onItem = true;
            current.tolerance--;
            if (current.tolerance == 0) zeroCnt++;
        }
    }

    public void print() {
        System.out.print("upQ: ");
        upQ.forEach((item) -> System.out.print(item.tolerance + " "));
        System.out.println();

        System.out.print("downQ: ");
        downQ.forEach((item) -> System.out.print(item.tolerance + " "));
        System.out.println();
        System.out.println();
    }
}

class Node {
    public int tolerance;
    public boolean onItem;

    public Node(int tolerance) {
        this.tolerance = tolerance;
        onItem = false;
    }
}