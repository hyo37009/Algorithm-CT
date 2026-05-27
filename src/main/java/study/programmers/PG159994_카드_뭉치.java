package study.programmers;

/**
 * [프로그래머스] 159994 - 카드 뭉치
 *
 * [문제 정보]
 * - 난이도: 레벨 1
 * - 분류: 큐, 구현, 그리디
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/159994
 *
 * [문제 요약]
 * - 두 카드 뭉치(cards1, cards2)의 맨 앞 카드만 순서대로 사용할 수 있다.
 * - 카드를 건너뛰거나, 뭉치 안의 순서를 바꾸거나, 사용한 카드를 다시 사용할 수 없다.
 * - goal의 단어들을 왼쪽부터 차례대로 만들 수 있으면 "Yes", 아니면 "No"를 반환한다.
 *
 * [접근 방법]
 * - cards1, cards2, goal을 각각 Queue로 변환한다.
 * - goal의 현재 단어를 꺼낸 뒤 cards1의 맨 앞 단어와 먼저 비교한다.
 * - cards1에서 만들 수 없으면 cards2의 맨 앞 단어와 비교한다.
 * - 두 카드 뭉치의 맨 앞 단어가 모두 goal의 현재 단어와 다르면 만들 수 없는 순서이므로 "No"를 반환한다.
 * - 제한사항상 cards1과 cards2에는 서로 다른 단어만 존재하므로, 같은 goal 단어가 두 뭉치의 맨 앞에 동시에 걸리는 분기 탐색이 필요 없다.
 *
 * [시간복잡도]
 * - Queue 초기화: O(cards1.length + cards2.length + goal.length)
 * - goal 처리: O(goal.length)
 * - 전체 시간복잡도: O(cards1.length + cards2.length + goal.length), 입력 전체 크기를 N으로 보면 O(N)
 * - 공간복잡도: Queue를 새로 만들기 때문에 O(cards1.length + cards2.length + goal.length)
 *
 * [특이사항]
 * - 처음에는 DFS로 두 뭉치 중 어느 쪽에서 단어를 꺼낼지 탐색할 수 있다고 생각할 수 있다.
 * - 하지만 두 카드 뭉치에 같은 단어가 중복되지 않으므로 현재 goal 단어를 꺼낼 수 있는 뭉치는 최대 하나다.
 * - 따라서 매 순간 가능한 선택이 사실상 결정되어 있고, 앞에서부터 순차 검증하면 충분하다.
 */

import java.io.*;
import java.util.*;

public class PG159994_카드_뭉치 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String[] cards1 = new String[]{"i", "water", "drink"};
        String[] cards2 = new String[]{"want", "to"};
        String[] goal = new String[]{"i", "want", "to", "drink", "water"};

        String result = solution.solution(cards1, cards2, goal);
        System.out.println(result);
    }

    static class Solution {
        public String solution(String[] cards1, String[] cards2, String[] goal) {
            Queue<String> cards1Q = new ArrayDeque<>(Arrays.asList(cards1));
            Queue<String> cards2Q = new ArrayDeque<>(Arrays.asList(cards2));
            Queue<String> goalQ = new ArrayDeque<>(Arrays.asList(goal));

            while (true) {
                if (goalQ.isEmpty()) {
                    return "Yes";
                }

                String peek = goalQ.poll();
                if (!cards1Q.isEmpty() && cards1Q.peek().equals(peek)) {
                    cards1Q.poll();
                    continue;
                } else if (!cards2Q.isEmpty() && cards2Q.peek().equals(peek)) {
                    cards2Q.poll();
                    continue;
                }
                return "No";
            }
        }
    }
}
