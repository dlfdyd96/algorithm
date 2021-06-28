package BJ5373;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ5373 {
    static int TEST_CASE;
    static int N;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Map<Plane, char[][]> field;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        TEST_CASE = Integer.parseInt(st.nextToken());
        for (int i = 0; i < TEST_CASE; i++) {
            initField();
            solve();
        }


    }

    public static enum Plane {
        U, D, F, B, L, R
    }

    static void initField() {
        field = new EnumMap<Plane, char[][]>(Plane.class);
        field.put(Plane.U, new char[][]{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}});
        field.put(Plane.D, new char[][]{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}});
        field.put(Plane.F, new char[][]{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}});
        field.put(Plane.B, new char[][]{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}});
        field.put(Plane.L, new char[][]{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}});
        field.put(Plane.R, new char[][]{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}});
    }

    static void solve() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String cmd = st.nextToken();
            action(cmd.toCharArray());
        }


    }

    static void action(char[] cmd) {
        char plane = cmd[0];
        char direction = cmd[1];
        switch (plane) {
            case 'U': //윗 면,
                break;
            case 'D': //아랫 면,
                break;
            case 'F': //앞 면,
                break;
            case 'B': //뒷 면,
                break;
            case 'L': //왼쪽 면,
                break;
            case 'R': //오른쪽 면
            default:
                break;
        }
    }

    /** U()
     * 앞 오 뒤 왼
     *
     */
    static void U(char direction) {
        String line = new String("");

        line += field.get(Plane.F)[0];
        line += field.get(Plane.R)[0];
        line += field.get(Plane.B)[0];
        line += field.get(Plane.L)[0];

        if (direction == '+')
            line = line.substring(9) + line.substring(0, 9); // shift
        else
            line = line.substring(3) + line.substring(0, 3); // shift

        field.get(Plane.F)[0] = line.substring(0, 3).toCharArray();
        field.get(Plane.R)[0] = line.substring(3, 6).toCharArray();
        field.get(Plane.B)[0] = line.substring(6, 9).toCharArray();
        field.get(Plane.L)[0] = line.substring(9).toCharArray();
    }

    static void D(char direction) {
        String line = new String("");

        line += field.get(Plane.F)[2];
        line += field.get(Plane.R)[2];
        line += field.get(Plane.B)[2];
        line += field.get(Plane.L)[2];

        if (direction == '+')
            line = line.substring(9) + line.substring(0, 9); // shift
        else
            line = line.substring(3) + line.substring(0, 3); // shift

        field.get(Plane.F)[2] = line.substring(0, 3).toCharArray();
        field.get(Plane.R)[2] = line.substring(3, 6).toCharArray();
        field.get(Plane.B)[2] = line.substring(6, 9).toCharArray();
        field.get(Plane.L)[2] = line.substring(9).toCharArray();
    }

    // u, r, d, l
    static void F(char direction) {
        String line = new String("");

        line += field.get(Plane.U)[2];
        line += "" + field.get(Plane.R)[0][0] + field.get(Plane.R)[0][2] + field.get(Plane.R)[0][2];
        line += field.get(Plane.D)[2];
        line += field.get(Plane.L)[2];

        if (direction == '+')
            line = line.substring(9) + line.substring(0, 9); // shift
        else
            line = line.substring(3) + line.substring(0, 3); // shift

        field.get(Plane.F)[2] = line.substring(0, 3).toCharArray();
        field.get(Plane.R)[2] = line.substring(3, 6).toCharArray();
        field.get(Plane.B)[2] = line.substring(6, 9).toCharArray();
        field.get(Plane.L)[2] = line.substring(9).toCharArray();
    }

    static void B() {

    }

    static void L() {

    }

    static void R() {

    }
}
