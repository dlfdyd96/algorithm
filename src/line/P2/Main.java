package line.P2;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Consumer;


class Input {
    final String[] research;
    final int n;
    final int k;

    public Input(String[] research, int n, int k) {
        this.research = research;
        this.n = n;
        this.k = k;
    }
}

public class Main {
    private static Input[] inputs = new Input[]{
            new Input(
                    new String[]{"abaaaa","aaa","abaaaaaa","fzfffffffa"},
                    2,
                    2
                    ),
            new Input(
                    new String[]{"yxxy","xxyyy"},
                    2,
                    1
                    ),
            new Input(
                    new String[]{"yxxy","xxyyy","yz"},
                    2,
                    1
                    ),
            new Input(
                    new String[]{"xy","xy"},
                    1,
                    1
                    )
    };
    public static void main(String[] args) {
        for (Input input: inputs
             ) {
            String answer = new Solution().solution(input.research, input.n, input.k);
            System.out.println(answer);
        }
    }
}

class Solution {
    private String[] research;
    private int n;
    private int k;

    private List<Map<Character, Integer>> table;
    private Set<Character> keys;

    public String solution(String[] research, int n, int k) {
        init(research, n, k);

        String answer = findMostIssueResearch();
        return answer;
    }

    private void init(String[] research, int n, int k) {
        this.n = n;
        this.k = k ;
        this.table = new ArrayList<>();
        this.keys = new HashSet<>();
        for (int i = 0; i < research.length; i++) {
            Map<Character, Integer> item = new HashMap<>();
            for (int j = 0; j < research[i].length(); j++) {
                char currentKey = research[i].charAt(j);
                if (item.containsKey(currentKey)) {
                    item.put(currentKey, item.get(currentKey) + 1);
                } else {
                    item.put(currentKey, 1);
                    this.keys.add(currentKey);
                }
            }
            this.table.add(item);
        }
    }

//    private void addAlphaBet(, Consumer<Character> cons) {
//
//    }

    private String findMostIssueResearch() {
        // char max;
        // int maxTotal;
        // int continuouskDayTotal
        //      int currentContinuousKDay
        Optional<Character> maxKey = Optional.empty();
        int maxTotal = 0;
        List<Character> keyArr = new ArrayList<>(keys);
        // 1. key에 대해 돌아
        for (char key: keyArr) {
            int totalContinousKDay = 0;
            // 2. 날짜에 대해 돌아
            for (int i = 0; i <= table.size() - n; i++) {
                int continousKDay = 0;
                boolean valid = true;
                for (int j = 0; j < n; j++) {
                    if (!table.get(i+j).containsKey(key)) {
                        valid = false;
                        break;
                    }
                    int current = table.get(i + j).get(key);
                    if (current < k) {
                           valid = false;
                           break;
                    }
                    continousKDay += table.get(i + j).get(key);
                }
                if (valid && continousKDay >= 2 * k * n) {
                    totalContinousKDay++;
                }
            }
            // compareMax
            if (totalContinousKDay > 0 && maxTotal <= totalContinousKDay) {
                if (maxTotal == totalContinousKDay) {
                    maxKey = Optional.of(Character.compare(
                            key,
                            maxKey.get()
                    ) < 0 ? key
                            : maxKey.get());
                } else {
                    maxKey = Optional.of(key);
                    maxTotal = totalContinousKDay;
                }
            }
        }

        /*
        for (int i = 0; i <= table.size() - k; i++) {
            // key에 대해서 돌아야함.
            Character[] keys = (Character[]) table.get(i).keySet().toArray();
            int totalContinousKDay = 0;
            for (char key: keys) {
                totalContinousKDay = 0;
                int continousKDay = 0;
                for (int j = 0; j < k; j++) {
                    continousKDay = table.get(i + j).get(key);
                    // [i + j]
                    // if 오늘 k 이상인지, 내일 k 이상인지 비교
                    //      int currentContinuousKDay = 오늘 + 내일
                    //
                    // else ->
                    //
                    // 그리
                }
                if (continousKDay > k) {
                    totalContinousKDay += continousKDay;
                }
                // if currentContinuousKDay > 2 * k * n
                //                    //          continuouskDayTotal += currentContinuousKDay;
                //                    //          compareMaxResearch(max,
            }
        }
        if (totalContinousKDay > 2 * k * n) {

        }*/

        String answer = maxKey.isPresent() ? String.valueOf(maxKey.get()) : "None";
        // String answer = maxKey.isEmpty() ? String.valueOf(maxKey.get()) : "None";
        return  answer;
    }
}
