package study.programmers;

/**
 * [프로그래머스] 42576 - 완주하지 못한 선수
 *
 * [문제 정보]
 * - 난이도: 레벨 1
 * - 분류: 해시, 정렬
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42576
 *
 * [문제 요약]
 * - participant에는 마라톤 참가자 이름이 담겨 있다.
 * - completion에는 완주자 이름이 담겨 있으며, participant보다 길이가 1 작다.
 * - 참가자 중 동명이인이 있을 수 있으므로 이름의 존재 여부뿐 아니라 개수 차이를 확인해야 한다.
 * - 완주하지 못한 한 명의 이름을 반환한다.
 *
 * [접근 방법]
 * - 현재 풀이는 participant와 completion을 각각 사전순으로 정렬한다.
 * - 정렬 후 같은 인덱스끼리 비교하면, 완주자와 참가자가 순서대로 짝지어진다.
 * - 처음으로 이름이 달라지는 위치의 participant[i]가 완주하지 못한 선수다.
 * - 끝까지 모두 같다면 마지막 participant가 완주하지 못한 선수다.
 *
 * [시간복잡도]
 * - Arrays.sort(participant): O(N log N)
 * - Arrays.sort(completion): O(N log N)
 * - 비교 순회: O(N)
 * - 전체 시간복잡도: O(N log N)
 * - 공간복잡도: 정렬 구현의 보조 공간을 제외하면 O(1)
 *
 * [특이사항]
 * - ArrayList로 바꾼 뒤 removeAll을 사용하면 동명이인 처리가 깨진다.
 * - removeAll은 "완주자 목록에 포함된 이름"을 모두 제거하므로, 같은 이름이 여러 명 있을 때 한 명만 제거하지 않는다.
 * - 해시 풀이에서는 HashSet이 아니라 HashMap<String, Integer>로 이름별 개수를 세는 방식이 적합하다.
 */

import java.io.*;
import java.util.*;

public class PG42576_완주하지_못한_선수 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String[] participant = new String[]{"leo", "kiki", "eden"};
        String[] completion = new String[]{"eden", "kiki"};
        String result = solution.solution(participant, completion);
        System.out.println(result);
    }

    static class Solution {
        public String solution(String[] participant, String[] completion) {
            Arrays.sort(participant);
            Arrays.sort(completion);

            for (int i = 0; i < completion.length; i++) {
                if (!participant[i].equals(completion[i]))
                    return participant[i];
            }
            return participant[participant.length - 1];
        }
    }
}
