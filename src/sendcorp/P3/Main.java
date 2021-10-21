package sendcorp.P3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(
//                        new int[]{1,2,3,0,6,5,4},
//                        new int[]{1,2,3,4,5,6,0}
                        new int[]{4,3,5,2,1,0,6},
                        new int[]{4,3,5,2,1,0,6}
                )
        );
    }
}

class Solution {
    private String answerShape;
    private Map<String, Integer> setOfShape;

    private boolean finishFlag = false;
    private int answer = Integer.MAX_VALUE;


    public int solution(int[] s1, int[] s2) {
        // System.out.println(arrayToString(s1));
        init(s1, s2);

        simulation(s1, 0, findZero(s1));

        return answer;
    }

    private void simulation(int[] arr, int depth, int zeroIndex) {
        String currentShape = arrayToString(arr);
        if (answerShape.equals(currentShape)) {
            answer = Math.min(answer, depth);
            return;
        }

        if (setOfShape.containsKey(currentShape)) {
            if (setOfShape.get(currentShape) <= depth) return;
            setOfShape.put(currentShape, depth);
        }
        setOfShape.put(currentShape, depth);

        int nextZero = -1;

        switch (zeroIndex) {
            case 0:

                // 1.
                nextZero = 1;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 6.
                nextZero = 6;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 5.
                nextZero = 5;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);

                break;
            case 1:
                // 0
                nextZero = 0;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 2
                nextZero = 2;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 6
                nextZero = 6;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                break;
            case 2:
                // 1
                nextZero = 1;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 3
                nextZero = 3;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 6
                nextZero = 6;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                break;
            case 3:
                // 2
                nextZero = 2;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 4
                nextZero = 4;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 6
                nextZero = 6;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                break;
            case 4:
                // 3
                nextZero = 3;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 5
                nextZero = 5;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 6
                nextZero = 6;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                break;
            case 5:
                // 0
                nextZero = 0;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 4
                nextZero = 4;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 6
                nextZero = 6;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                break;
            case 6:
                // 1
                nextZero = 1;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 2
                nextZero = 2;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 3
                nextZero = 3;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 4
                nextZero = 4;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);
                // 5
                nextZero = 5;
                swapAndSimulate(arr, depth, zeroIndex, nextZero);

                break;
        }
    }

    private void swapAndSimulate(int[] arr, int depth, int zeroIndex, int nextZero) {
        swapIndex(arr, zeroIndex, nextZero);
        simulation(arr, depth + 1, nextZero);
        swapIndex(arr, zeroIndex, nextZero);
    }

    private void swapIndex(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void init(int[] s1, int[] s2) {
        findZero(s1);
        // More something...
        answerShape = arrayToString(s2);
        setOfShape = new HashMap<>();
    }

    private int findZero(int[] s1) {
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == 0) {
                return i;
            }
        }
        return -1;
    }


    private String arrayToString(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
        }
        return stringBuilder.toString();
    }
}