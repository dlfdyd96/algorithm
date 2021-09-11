package line.P4;

import java.util.ArrayList;
import java.util.List;

// 1,
public class Main {
    public static void main(String[] args) {
        new Solution().solution(12);
        // new Solution().solution(8);
    }
}

class Solution {
    private int[] arr;

    public int[] solution(int n) {
        init(n);

        recursive(0, n);

        int[] answer = arr;
        for (int a: answer
             ) {
            System.out.println(a);
        }
        return answer;
    }

    void recursive(int start, int length) {
        if (length == 1) return; // 1이면 종료

        int primeNumber = findPrime(length);
        if (primeNumber == length) return; // 길이가 같으면 종료

        // 1. 섞고
        // 1-1. copy
        int[] copyArr = copy(start, length);
        // 1-2.
        for (int i = 0; i < length; i++) {
            int mod = i % primeNumber;
            int div = i / primeNumber;
            int target = start + (mod * (length / primeNumber) ) + div;
            arr[target] = copyArr[i];
        }

        // 2. 나누기
        for (int i = 0; i < primeNumber; i++) {
            recursive(start + (length / primeNumber) * i, length / primeNumber);
        }
    }

    private int[] copy(int start, int length) {
        int[] newarr = new int[length];
        for (int i = 0; i < length; i++) {
            newarr[i] = arr[start + i];
        }
        return newarr;
    }

    private void swap(int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private void init(int n) {
        arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = i + 1;
        }
    }

    static int findPrime(int len) {
        for (int i = 2; i <= len; i++) {
            if (isPrime(i)) return i;
        }
        return len;
    }

    static boolean isPrime(int n) {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
