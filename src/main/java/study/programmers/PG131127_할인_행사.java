package study.programmers;

/**
 * [프로그래머스] 131127 - 할인 행사
 *
 * [문제 정보]
 * - 난이도: 레벨 2
 * - 분류: 해시, 슬라이딩 윈도우
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/131127
 *
 * [문제 요약]
 * - 회원가입일로부터 연속 10일 동안 매일 할인 상품을 하나씩 구매할 수 있다.
 * - want와 number는 10일 동안 정확히 구매해야 하는 상품별 개수를 의미한다.
 * - discount의 모든 연속 10일 구간 중 원하는 상품 구성과 정확히 일치하는 시작일 개수를 반환한다.
 *
 * [접근 방법]
 * - wantMap에는 원하는 상품별 필요 개수를 저장한다.
 * - discountMap에는 현재 10일 구간의 할인 상품별 개수를 저장한다.
 * - HashMap.equals()는 두 Map의 key-value 쌍이 모두 같으면 true를 반환하므로, 두 빈도 맵을 직접 비교할 수 있다.
 * - 첫 10일 구간을 만든 뒤, 하루씩 오른쪽으로 이동하면서 빠지는 상품은 1 감소시키고 들어오는 상품은 1 증가시킨다.
 * - 빠지는 상품의 개수가 0이 되면 Map에서 제거해야 equals 비교 시 불필요한 0 값이 남지 않는다.
 *
 * [시간복잡도]
 * - 첫 10일 구간 생성: O(10)
 * - discount 순회: O(discount.length)
 * - Map.equals(): want와 number의 종류가 최대 10개라 상수 시간으로 볼 수 있다.
 * - 전체 시간복잡도: O(discount.length)
 * - 공간복잡도: O(want.length + 10), 입력 제한 기준 O(1)
 *
 * [특이사항]
 * - wantMap은 기준표이므로 수정하지 않고, 현재 구간을 나타내는 discountMap만 갱신한다.
 * - 문제의 "10일 동안"과 예시의 discount 길이 14일을 혼동하지 않아야 한다.
 * - 원하지 않는 상품도 discountMap에 포함해야 한다. 그래야 10일 구간에 불필요한 상품이 섞였을 때 equals가 false가 된다.
 */

import java.io.*;
import java.util.*;

public class PG131127_할인_행사 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String[] want = new String[]{"banana", "apple", "rice", "pork", "pot"};
        int[] number = new int[]{3, 2, 2, 2, 1};
        String[] discount = new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        int result = solution.solution(want, number, discount);
        System.out.println(result);
    }

    static class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            Map<String, Integer> wantMap = new HashMap<>();
            Map<String, Integer> discountMap = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }

            for (int i = 0; i < 10; i++) {
                discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
            }

            int answer = wantMap.equals(discountMap) ? 1 : 0;
            for (int i = 10; i < discount.length; i++) {
                String out = discount[i - 10];
                discountMap.put(out, discountMap.get(out) - 1);
                if (discountMap.get(out) == 0)
                    discountMap.remove(out);

                String in = discount[i];
                discountMap.put(in, discountMap.getOrDefault(in, 0) + 1);

                if (wantMap.equals(discountMap))
                    answer++;
            }
            return answer;
        }
    }
}
