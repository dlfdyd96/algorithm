package Samsung2021Sumer.PracticeBattle.P5;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private final static int MAX_NODE = 10000;
    private final static int ADD_HEAD = 1;
    private final static int ADD_TAIL = 2;
    private final static int ADD_NUM = 3;
    private final static int FIND = 4;
    private final static int REMOVE = 5;
    private final static int PRINT = 6;
    private final static int PRINT_REVERSE = 7;
    private final static int END = 99;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    public static void run() throws Exception {
        int cmd, data, num, ret;
        int[] output = new int[MAX_NODE];
        String str;
        StringTokenizer st;

        while(true) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            cmd = Integer.parseInt(st.nextToken());

            switch(cmd) {
                case ADD_HEAD :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Head(data);
                    break;

                case ADD_TAIL :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Tail(data);
                    break;

                case ADD_NUM :
                    data = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Num(data, num);
                    break;

                case FIND :
                    data = Integer.parseInt(st.nextToken());
                    num = usersolution.findNode(data);
                    System.out.println(num);
                    break;

                case REMOVE :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.removeNode(data);
                    break;

                case PRINT :
                    ret = usersolution.getList(output);
                    for(int i = 0; i < ret; i++) {
                        System.out.print(output[i] + " ");
                    }
                    System.out.println();
                    break;

                case PRINT_REVERSE :
                    ret = usersolution.getReversedList(output);
                    for(int i = 0; i < ret; i++) {
                        System.out.print(output[i] + " ");
                    }
                    System.out.println();
                    break;

                case END :
                    return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int TC;
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            System.out.println("#" + tc);
            usersolution.init();
            run();
            System.out.println();
        }
    }
}

class Node {
    public int data;
    public Node prev;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = null;
    }

    public void addNode2Head(int data) {
        Node newNode = getNode(data);
        if (head == null) {
            head = newNode;
        }
        else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }

    }

    public void addNode2Tail(int data) {
        Node newNode = getNode(data);
        if (head== null)
            head = newNode;
        else {
            Node currentNode = head;
            while(currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.prev = currentNode;
        }
    }

    public void addNode2Num(int data, int num) {
        if(num == 1) addNode2Head(data);
        else {
            Node newNode = getNode(data);
            Node previousNode = head;
            for (int i = 0; i < num - 2; i++) {
                previousNode = previousNode.next;
            }
            newNode.next = previousNode.next;
            newNode.prev = previousNode;
            previousNode.next = newNode;
            if(newNode.next != null) newNode.next.prev = newNode;
        }

    }

    public int findNode(int data) {
        Node currentNode = head;
        int cnt = 1;
        while(currentNode != null) {
            if(currentNode.data == data)
                return cnt;
            currentNode = currentNode.next;
            cnt++;
        }
        return cnt;
    }

    public void removeNode(int data) {
        Node currentNode = head;
        if(currentNode.data == data) {
            head = currentNode.next;
            head.prev = null;
            return;
        }
        while(currentNode.next != null) {
            if(currentNode.next.data == data) {
                if(currentNode.next.next != null) currentNode.next.next.prev = currentNode;
                currentNode.next = currentNode.next.next;
                break;
            }
            currentNode = currentNode.next;
        }


    }

    public int getList(int[] output) {
        int cnt = 0;
        Node currentNode = head;
        do {
            output[cnt++] = currentNode.data;
            currentNode = currentNode.next;
        } while(currentNode != null);
        return cnt;

    }

    public int getReversedList(int[] output) {
        Node currentNode = head;
        if(currentNode.next == null) {
            output[0] = currentNode.data;
            return 1;
        };
        while(currentNode.next != null) {
            currentNode = currentNode.next;
        }

        int cnt = 0;
        do {
            output[cnt++] = currentNode.data;
            currentNode = currentNode.prev;
        } while(currentNode != null);
        return cnt;
    }
}