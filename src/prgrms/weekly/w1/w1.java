package prgrms.weekly.w1;

public class w1 {
    private static final int[] inputs = {3, 20, 4};

    public static void main(String[] args) {
        long answer = new Solution().solution(inputs[0], inputs[1], inputs[2]);
        System.out.println(answer);
    }
}

class Solution {
    public long solution(int price, int money, int count) {
        // amount = count(count+1)/2 + (price - 1)
        long amount = (long) count * ((long) count + 1) / 2 * (long) price;
        long result = (long) money - amount;
        money -= amount;

//        for (int i = 0; i < count; i++) {
//            money -= price++;
//            if (money < 0) break;
//        }


        return result < 0 ? Math.abs(result) : 0;
    }
}
