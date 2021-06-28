package concept;

/**
 * 알고리즘
 * 1. 리스트 안에 있는 한 요소(피봇)를 선택한다.
 * 2. 피봇을 기준으로 피봇보다 작은 요소들은 모두 피봇의 왼쪽으로 옮겨지고 피봇보다 큰 요소들은 모두 피봇의 오른쪽으로 옮겨진다.
 * 3. 피봇을 제외한 왼쪽 리스트와 오른쪽 리스트를 다시 정렬한다.
 * 3-1. 분할된 부분 리스트에 대해서 순환 호출을 이용해서 정렬을 반복한다.
 * 3-2. 부분 리스트에서도 다시 피봇을 정하고 피봇을 기준으로 2개의 부분 리스트로 나누는 과정을 반복한다.
 * 4. 부분 리스트 부분들이 더 이상 분할이 불가능할 때까지 반복한다.
 * 4-1. 리스트의 크기가 0이나 1이 될 때까지 반복한다.
 */

/** 특징
 * 졀라 빠르다. O(n * log2(n))
 * 메모리 적게차지 O(log(n))
 * 최악) 정렬된 상태에서 -> O(n^2)
 */
public class QuickSort {
    static int[] data = {1, 10, 5, 8, 7, 6, 4, 3, 2, 9};
    static final int number = 10;

    public static void main(String[] args) {
        quickSort(data, 0, number - 1);
    }

    public static void quickSort(int[] data, int start, int end) {
        if (start >= end) { // 원소가 1개인 경우 그대로 두기
            return;
        }

        int key = start; // 키는 첫 번재 원소
        int i = start + 1;
        int j = end;
        int temp;

        while (i <= j) { // 엇 갈릴 때 까지 반복
            while (i <= end && data[i] <= data[key]) { // 키 값보다 큰 값을 만날 때
                i++;
            }
            while (j > start && data[j] >= data[key]) {
                j--;
            }

            if (i > j) { // 현재 엇갈린 상태면 키 값과 교체
                temp = data[j]; // 왜 j와 바꾸냐면 i 보다 작거든..
                data[j] = data[key];
                data[key] = temp;
            } else { // 엇갈리지 않았다면 i와 j를 교체
                temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        quickSort(data, start, j - 1);
        quickSort(data, j + 1, end);
    }
}
