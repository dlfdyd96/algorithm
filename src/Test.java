import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String binary1 = "101";
        String binary2 = "100111";
        int result =  Integer.parseInt(binary1, 2) & Integer.parseInt(binary2, 2);
        System.out.printf("%d: %s\n", result, Integer.toBinaryString(result));

        System.out.println(Integer.toBinaryString((1 << 2)));
    }
}