package study.programmers;

/**
 * [프로그래머스] 42579 - 베스트 앨범
 * <p>
 * [문제 정보]
 * - 난이도: 레벨 3
 * - 분류: 해시, 정렬
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42579
 * <p>
 * [문제 요약]
 * - 장르별 총 재생 횟수가 높은 장르부터 앨범에 수록한다.
 * - 같은 장르 안에서는 재생 횟수가 높은 노래를 먼저 수록한다.
 * - 같은 장르 안에서 재생 횟수가 같으면 고유 번호가 낮은 노래를 먼저 수록한다.
 * - 장르별 최대 2곡만 선택하고, 장르에 곡이 1개뿐이면 1곡만 선택한다.
 * <p>
 * [접근 방법]
 * 1. 장르별 총 재생 횟수가 많은 대로 정렬
 * 2. 각 장르당 재생 횟수가 높은 2개의 인덱스를 가져오기
 * <p>
 * [시간복잡도]
 * - 장르별 노래를 모으고 총 재생 횟수를 계산: O(N)
 * - 장르 정렬: O(G log G), G는 장르 수
 * - 각 장르별 노래 정렬: 전체적으로 O(N log N) 이하
 * - 전체 시간복잡도: O(N log N)
 * <p>
 * [특이사항]
 * - 모든 장르는 총 재생 횟수가 다르므로 장르 총합이 같은 경우는 고려하지 않아도 된다.
 * - 장르 내부에서는 재생 횟수 내림차순, 재생 횟수가 같으면 고유 번호 오름차순으로 정렬해야 한다.
 * <p>
 * [검증된 예시 케이스]
 * - 아래 정답은 현재 풀이 코드가 아니라 별도 기준 구현으로 검증했다.
 * <p>
 * 1) 기본 예시
 * genres = ["classic", "pop", "classic", "classic", "pop"]
 * plays  = [500, 600, 150, 800, 2500]
 * answer = [4, 1, 3, 0]
 * <p>
 * 2) 장르에 곡이 1개뿐인 경우
 * genres = ["classic"]
 * plays  = [100]
 * answer = [0]
 * <p>
 * 3) 장르 내부 재생 횟수가 같은 경우
 * genres = ["classic", "classic", "pop", "pop"]
 * plays  = [500, 500, 600, 100]
 * answer = [0, 1, 2, 3]
 * <p>
 * 4) 여러 장르 정렬과 장르별 2곡 선택
 * genres = ["jazz", "classic", "jazz", "pop", "classic", "pop"]
 * plays  = [300, 500, 800, 600, 150, 2500]
 * answer = [5, 3, 2, 0, 1, 4]
 * <p>
 * 5) 장르 총합 정렬 확인
 * genres = ["a", "b", "a", "b", "c"]
 * plays  = [100, 300, 100, 200, 1000]
 * answer = [4, 1, 3, 0, 2]
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PG42579_베스트_앨범 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        int[] plays = new int[]{500, 600, 150, 800, 2500};


        int[] result = solution.solution(genres, plays);
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        public int[] solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();
            Map<String, Map<Integer, Integer>> music = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                if (!music.containsKey(genres[i]))
                    music.put(genres[i], new HashMap<>());
                music.get(genres[i]).put(i, plays[i]);
            }

            List<Map.Entry<String, Map<Integer, Integer>>> list = music.entrySet().stream()
                    .sorted(new Comparator<Map.Entry<String, Map<Integer, Integer>>>() {
                        @Override
                        public int compare(Map.Entry<String, Map<Integer, Integer>> o1, Map.Entry<String, Map<Integer, Integer>> o2) {
                            Collection<Integer> values = o1.getValue().values();
                            int o1s = 0;
                            for (Integer value : values) {
                                o1s += value;
                            }

                            Collection<Integer> values2 = o2.getValue().values();
                            int o2s = 0;
                            for (Integer value : values2) {
                                o2s += value;
                            }

                            return o2s - o1s;
                        }
                    })
                    .collect(Collectors.toList());

            for (Map.Entry<String, Map<Integer, Integer>> genresList : list) {
                Map<Integer, Integer> value = genresList.getValue();
                List<Integer> indexes = value.entrySet().stream()
                        .sorted(new Comparator<Map.Entry<Integer, Integer>>() {
                            @Override
                            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                                return o2.getValue() - o1.getValue();
                            }
                        }).limit(2)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                answer.addAll(indexes);

            }

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}