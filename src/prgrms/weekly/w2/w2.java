package prgrms.weekly.w2;

import java.text.MessageFormat;
import java.util.Arrays;

public class w2 {
    // private final static int[][] inputs = {{50,90},{50,87}};
    private final static int[][] inputs = {{70,49,90},{68,50,38},{73,31,100}};
//    private final static int[][] inputs = {{100, 90, 98, 88, 65}, {50, 45, 99, 85, 77}, {47, 88, 95, 80, 67}, {61, 57, 100, 80, 65}, {24, 90, 94, 75, 65}};

    public static void main(String[] args) {
        String answer = new Solution().solution(inputs);
        System.out.println(answer);
    }
}

class Solution {
    public String solution(int[][] scores) {
        // String answer = "";

        int studentCount = scores.length;
        double[] grade = new double[studentCount];


        for (int i = 0; i < studentCount; i++) {
            int selfEvaluate = 0;
            int max = Integer.MIN_VALUE;
            boolean dupMax = false;
            int min = Integer.MAX_VALUE;
            boolean dupMin = false;

            for (int j = 0; j < studentCount; j++) {
                if (i == j) { // 자기 자신을 평가할때
                    selfEvaluate = scores[i][j];
                }
                if (min > scores[j][i]) {
                    min = scores[j][i];
                    dupMin = false;
                } else if (min == scores[j][i]) {
                    dupMin = true;
                }

                if (max < scores[j][i]) {
                    max = scores[j][i];
                    dupMax = false;
                } else if (max == scores[j][i]) {
                    dupMax = true;
                }
            }

            int sum = 0;
            for (int j = 0; j < studentCount; j++) {
                sum += scores[j][i];
            }

            if (selfEvaluate == max) {
                if (!dupMax) {
                    System.out.println(MessageFormat.format("sum -> {0}", sum - selfEvaluate));
                    grade[i] = (double) (sum - selfEvaluate) / (studentCount - 1);
                } else {
                    System.out.println(MessageFormat.format("sum -> {0}", sum));
                    grade[i] = (double) sum / studentCount;
                }
            } else if(selfEvaluate == min) {
                if (!dupMin) {
                    System.out.println(MessageFormat.format("sum -> {0}", sum - selfEvaluate));
                    grade[i] = (double) (sum - selfEvaluate) / (studentCount - 1);
                } else {
                    System.out.println(MessageFormat.format("sum -> {0}", sum));
                    grade[i] = (double) sum / studentCount;
                }
            } else {
                System.out.println(MessageFormat.format("sum -> {0}", sum));
                grade[i] = (double) sum / studentCount;
            }

        }

        Arrays.stream(grade).forEach(System.out::println);

        // System.out.println(rankArray(grade));


        return rankArray(grade);


    }

    private String rankArray(double[] grade) {
        StringBuilder answer = new StringBuilder();

        for (double s : grade) {
            int score = (int) Math.floor(s);

            if (score >= 90)
                answer.append("A");
            else if (score >= 80)
                answer.append("B");
            else if (score >= 70)
                answer.append("C");
            else if (score >= 50)
                answer.append("D");
            else
                answer.append("F");
        }


        return answer.toString();
    }


//    private String rankArray(double[] grade) {
//        Map<Double, Integer> map = new HashMap<>(); // value, rank
//
//        // List<Double> copyed = Arrays.asList((Double[]) Arrays.stream(grade).boxed());
//
//        List<Double> copyed =  Arrays.stream(Arrays.copyOf(grade, grade.length)).boxed().collect(Collectors.toList());
//        System.out.println();
//        copyed.sort(Comparator.reverseOrder());
//        copyed.forEach(System.out::println);
//
//        for (double value: copyed) {
//            map.putIfAbsent(value, map.size());
//        }
//
//        StringBuilder st = new StringBuilder();
//        for (int i = 0; i < grade.length; i++) {
//            st.append((char)('A'+ map.get(grade[i]).doubleValue()));
//        }
//
//        return st.toString();
//    }
}
