package prgrms.weekly.w6;

import java.util.*;

public class W6 {
    public static void main(String[] args) {
        int solution = new Solution().solution(new int[][]{{0, 3}, {1, 9}, {2, 6}});
        System.out.println(solution);
    }
}

// 스케쥴링 알고리즘 중 Shortest Job First
// 실제로는 프로세스 사용시간이 얼마만큼인지 몰라서 사용 불가능한 알고리즘이지만 주어진 입력 값을 활용하면 댐

// 매초 마다의 time의 흐름이 포인뜨
// Job 클래스를 선언해주고
// 도착한 시간이 빠른 순서대로 정렬된 대기큐 (얘는 그냥 List sort)
// 작업이 빨리 되는 작업 큐 두 개를 선언해준다. (얘는 PriorityQueue)

class Job {
    public int arriveTime;
    public int workingtime;

    public Job(int arriveTime, int workingTime) {
        this.arriveTime = arriveTime;
        this.workingtime = workingTime;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        LinkedList<Job> waitingQueue = new LinkedList<>();
        PriorityQueue<Job> workingQueue = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.workingtime - o2.workingtime;
            }
        });
        for (int[] job : jobs) {
            waitingQueue.add(new Job(job[0], job[1]));
        }
        Collections.sort(waitingQueue, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.arriveTime - o2.arriveTime; // 음수가 나오면 o1이 먼저임!
            }
        });
        // 여기까지가 초기화 작업

        int answer = 0; // 나중에 리턴 ㅋㅋ
        int cnt = 0; // 작업 개수 만큼 iterate
        int time = 0; // 아까 말해줬던 타임이 뽀인뜨!


        while(cnt < jobs.length) {

            while (!waitingQueue.isEmpty() && waitingQueue.peek().arriveTime <= time) {
                Job currentJob = waitingQueue.pollFirst();
                workingQueue.add(currentJob);
            }

            if (!workingQueue.isEmpty()) {
                Job currentJob = workingQueue.poll();
                time += currentJob.workingtime;
                answer += time - currentJob.arriveTime;
                cnt++;
            } else {
                time++;
            }
        }


        return answer / cnt;
    }
}