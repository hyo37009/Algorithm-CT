package datastructure.tree;

import org.assertj.core.api.Assertions;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * [프로그래머스] 77486 - 다단계 칫솔 판매
 * <p>
 * [문제 정보]
 * - 난이도: 레벨2
 * - 분류: 트리
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/77486
 * <p>
 * [문제 요약]
 * <p>
 * <p>
 * [접근 방법]
 * <p>
 * <p>
 * [시간복잡도]
 * <p>
 * <p>
 * [특이사항]
 *
 */
public class PG77486_다단계_칫솔_판매 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        assertThat(solution.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10})
        ).containsExactly(360, 958, 108, 0, 450, 18, 180, 1080);

        assertThat(solution.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"sam", "emily", "jaimie", "edward"},
                new int[]{2, 3, 5, 4})
        ).containsExactly(0, 110, 378, 180, 270, 450, 0, 0);
    }

    static class Solution {
        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            // 초기 세팅
            Map<String, Node> sellers = new HashMap<>();
            for (int i = 0; i < enroll.length; i++) {
                Node node = new Node(null);
                sellers.put(enroll[i], node);
                if(referral[i].equals("-")) {
                    continue;
                }
                Node prev = sellers.get(referral[i]);
                node.prev = prev;
            }

            for (int i = 0; i < seller.length; i++) {
                Node s = sellers.get(seller[i]);
                int mouke = amount[i] * 100;

                while (s.prev != null) {
                    s.amount += mouke * 0.9;
                    s = s.prev;
                    mouke *= 0.1;
                }
                s.amount += mouke;
            }

            return Arrays.stream(enroll)
                    .map(sellers::get)
                    .map(n -> n.amount)
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        class Node {
            Node prev;
            List<Node> next;
            int amount = 0;

            Node(Node prev) {
                if (prev != null)
                    this.prev = prev;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "prev=" + prev +
                        ", next=" + next +
                        ", amount=" + amount +
                        '}';
            }
        }
    }
}
