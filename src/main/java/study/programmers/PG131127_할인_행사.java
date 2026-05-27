package study.programmers;

/**
 * [프로그래머스] 131127 - 할인 행사
 * <p>
 * [문제 정보]
 * - 난이도: 레벨
 * - 분류:
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/131127
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
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }

            int answer = 0;
            int count = 10;
            for (int i = 0; i < discount.length; i++) {
                String todayDiscount = discount[i];

                if (wantMap.containsKey(todayDiscount)) {
                    if (wantMap.get(todayDiscount) > 0) {
                        wantMap.put(todayDiscount, wantMap.get(todayDiscount) - 1);
                        count--;
                    }

                    if (count == 0) {
                        answer++;
                    }
                }

                if (i > 14) {
                    String oldDiscount = discount[i - 14];
                    if (wantMap.containsKey(oldDiscount)) {
                        wantMap.put(oldDiscount, wantMap.get(oldDiscount) - 1);
                        count++;
                    }
                }

            }
            return answer;
        }
    }
}
