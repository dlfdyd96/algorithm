package DevMatching;

import java.util.HashMap;

/**
 * 칫솔의 판매에 의하여 발생하는 이익에서 10% 를 계산하여 자신을 조직에 참여시킨 "추천인"에게 배분
 * 나머지는 자신이 가집니다
 *
 * 모든 판매원은 자신이 칫솔 판매에서 발생한 이익 뿐만 아니라, 자신이 조직에 추천하여 가입시킨 판매원에게서 발생하는 이익의 10% 까지 자신에 이익이 됨
 * 발생하는 이익 또한 마찬가지의 규칙으로 자신의 추천인에게 분배됨
 *
 * 제한사항
 *  10% 를 계산할 때에는 원 단위에서 절사하며,
 *  10%를 계산한 금액이 1 원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.
 */
public class P3 {
    public static void main(String[] args) {
        int[] answer = solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
                );
        for (int ans: answer
             ) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];


        HashMap<String, Member> map = new HashMap<>();
        // 다 넣어주고
        for (int i = 0; i < enroll.length; i++) {
            Member m = new Member(enroll[i], referral[i], 0);
            map.put(enroll[i], m);
        }

        // 수익판매발생!~
        for (int i = 0; i < seller.length; i++) {
            String key = seller[i];
            int money = 100 * amount[i];

            simulation(map, key, money);
        }

        // answer
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]).mount;
        }

        return answer;
    }

    public static void simulation(HashMap<String, Member> map, String key, int money) {
        // 1. 일단 자기가 가지고
        // 2. 10프로를 계속 (재귀) 올리고
        // - 조건은 1미만이면 그만.
        // - floor 하고, key 가 "-"라면 멈춰! 학교폭력! 멈춰!
        if (key.equals("-") || money < 1)
            return;

        // System.out.println(key);
        Member current = map.get(key);
        int shitMoney = (int) Math.floor(money * 0.1);
        current.mount = current.mount + (money - shitMoney);
        map.put(current.name, current); // 갱신

        simulation(map, current.parent, shitMoney); // 재귀
    }
}

class Member {
    public String name;
    public String parent;
    public int mount;

    Member(String name, String parent, int mount) {
        this.name = name;
        this.parent = parent;
        this.mount = mount;
    }
}
