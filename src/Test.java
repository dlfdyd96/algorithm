import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int A = 0, B = 787;
        A = A ^ B;
        B = A ^ B;
        A = A ^ B;
        System.out.println(A + " " + B);
    }
}