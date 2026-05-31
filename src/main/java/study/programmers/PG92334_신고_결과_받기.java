package study.programmers;

/**
 * [프로그래머스] 92334 - 신고 결과 받기
 * <p>
 * [문제 정보]
 * - 난이도: 레벨 1
 * - 분류: 해시, 구현
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/92334
 * <p>
 * [문제 요약]
 * - 한 유저가 같은 유저를 여러 번 신고해도 신고 횟수는 1회로 처리한다.
 * - k번 이상 신고된 유저는 정지된다.
 * - 정지된 유저를 신고했던 유저는 처리 결과 메일을 1통 받는다.
 * - id_list 순서대로 각 유저가 받은 메일 수를 반환한다.
 * <p>
 * [접근 방법]
 * - id를 배열 인덱스로 빠르게 바꾸기 위해 HashMap에 저장한다.
 * - report를 HashSet에 넣어 중복 신고를 제거한다.
 * - 중복 제거된 신고 목록으로 각 유저가 신고당한 횟수를 센다.
 * - 다시 신고 목록을 보며, 신고당한 유저가 정지 대상이면 신고한 유저의 메일 수를 1 증가시킨다.
 * <p>
 * [시간복잡도]
 * - id_list 길이를 N, report 길이를 R이라고 할 때 O(N + R)
 * - HashMap, HashSet, 배열을 사용하므로 공간복잡도는 O(N + R)
 * <p>
 * [특이사항]
 * - "ryan con" 같은 문자열 자체를 Set에 넣으면 동일 신고자를 간단히 제거할 수 있다.
 *
 */

import java.io.*;
import java.util.*;

public class PG92334_신고_결과_받기 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String[] id_list = new String[]{"con", "ryan"};
        String[] report = new String[]{"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 3;

        int[] result = solution.solution(id_list, report, k);
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, Integer> idIndex = new HashMap<>();
            for (int i = 0; i < id_list.length; i++) {
                idIndex.put(id_list[i], i);
            }

            Set<String> uniqueReports = new HashSet<>(Arrays.asList(report));
            int[] reportedCount = new int[id_list.length];

            for (String uniqueReport : uniqueReports) {
                String[] split = uniqueReport.split(" ");
                int reportedIndex = idIndex.get(split[1]);
                reportedCount[reportedIndex]++;
            }

            int[] answer = new int[id_list.length];
            for (String uniqueReport : uniqueReports) {
                String[] split = uniqueReport.split(" ");
                int reporterIndex = idIndex.get(split[0]);
                int reportedIndex = idIndex.get(split[1]);

                if (reportedCount[reportedIndex] >= k) {
                    answer[reporterIndex]++;
                }
            }

            return answer;
        }
    }
}
