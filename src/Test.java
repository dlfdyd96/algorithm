import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] map = new int[6][6];
        for (int i = 0; i < 6; i++) {
            map[i] = Arrays.asList(
                    br.readLine().split(" ")
            ).stream().mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < 6; i++) {
            Arrays.stream(map[i]).boxed().forEach((e) -> System.out.print(e + " "));
            System.out.println("");
        }
    }
}