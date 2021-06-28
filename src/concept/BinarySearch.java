package concept;

public class BinarySearch {
    static final int NUMBER = 13;

    static int[] a = { 1, 3, 5, 7, 9, 11, 14, 15, 18, 19, 25, 28, 30};
    static int data = 30;

    public static void main(String[] args) {
        int result = binarySearch(0, NUMBER - 1, data);
        System.out.printf("%5d에서 찾았습니다.", result + 1);
    }

    static int binarySearch(int start, int end, int target ) {
        System.out.println("자 드가자~");
        if (start > end)
            return -1;
        int mid = (start + end ) / 2;

        if (a[mid] == target) // 찾았으면
            return mid;
        else {
            if(a[mid] > target)
                return binarySearch(start, mid - 1, target);
            else
                return binarySearch(mid + 1, end, target);
        }
    }
}
