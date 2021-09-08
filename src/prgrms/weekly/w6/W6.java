package prgrms.weekly.w6;

import java.util.*;
import java.util.stream.Collectors;

public class W6 {
    public static void main(String[] args) {
        int[] solution = new Solution().solution(
//                new int[]{50, 82, 75, 120}, new String[]{"NLWL", "WNLL", "LWNW", "WWLN"}
                 new int[]{145,92,86}, new String[]{"NLW","WNL","LWN"}
//                 new int[]{60,70,60}, new String[]{"NNN","NNN","NNN"}
        );
        Arrays.stream(solution).forEach(System.out::println);
    }
}

class Fighter {
    public int weight;
    public String record;
    public int idx;

    public Fighter(int weight, String record, int idx) {
        this.weight = weight;
        this.record = record;
        this.idx = idx;
    }

    public double getWinRate() {
        return record.chars()
                .filter((c) -> c != 'N')
                .map((c) -> c == 'W' ? 1 : 0)
                .average()
                .orElse(0);
    }

    // The number of times you beat a boxer who weighs more than you.
    public int countWinOfMoreThanSelfWeight(int[] weights) {
        int cnt = 0;
        for (int i = 0; i < weights.length; i++) {
            if (record.charAt(i) == 'W') {
                if (weights[i] > weight) cnt++;
            }
        }
        return cnt;
    }
}

class Solution {
    public int[] solution(int[] weights, String[] head2head) {
        // 초기화
        List<Fighter> ranks = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            ranks.add(new Fighter(weights[i], head2head[i], i + 1));
        }

        Collections.sort(ranks, new Comparator<Fighter>() {
            @Override
            public int compare(Fighter o1, Fighter o2) {
                int resultCompareWinRate = Double.compare(o1.getWinRate(), o2.getWinRate());
                if (resultCompareWinRate != 0) return -resultCompareWinRate;

                int resultCompareWinOverWeightCnt = Integer.compare(
                        o1.countWinOfMoreThanSelfWeight(weights),
                        o2.countWinOfMoreThanSelfWeight(weights)
                );
                if (resultCompareWinOverWeightCnt != 0) return -resultCompareWinOverWeightCnt;

                int resultCompareWeight = Integer.compare(o1.weight, o2.weight);
                if (resultCompareWeight != 0) return -resultCompareWeight;

                return Integer.compare(o1.idx, o2.idx);
            }
        });

        return ranks.stream().mapToInt(item->item.idx).toArray();
    }
}