package study;

import java.io.*;
import java.util.*;

/**
 * <h2>[백준] 20920 - 영단어 암기는 괴로워</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 실버 3</li>
 *   <li>분류: 정렬, 해시맵</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/20920">BOJ 20920</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>길이가 M 이상인 단어를 빈도-길이-알파벳 순으로 정렬하여 출력한다.</p>
 *
 * <h3>접근 방법</h3>
 * <ul>
 *   <li>HashMap으로 단어 빈도 카운팅</li>
 *   <li>빈도(내림차순) → 길이(내림차순) → 알파벳(오름차순) 다중 조건 정렬</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(N log N)</b> - 정렬</p>
 *
 * <h3>특이사항</h3>
 * <p>반복 출력으로 시간초과 → StringBuilder 사용할 것</p>
 */
public class BOJ20920_영단어_암기는_괴로워 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        HashMap<String, Integer> words = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (word.length() >= m) {
                words.putIfAbsent(word, 0);
                words.put(word, words.get(word) + 1);
            }
        }
        br.close();

        StringBuilder sb = new StringBuilder();

        // 빈도-길이-알파벳 순으로 정렬하여 출력
        words.entrySet().stream()
                        .sorted((a, b) -> {
                            if (!a.getValue().equals(b.getValue()))
                                return b.getValue() - a.getValue();
                            if (a.getKey().length() != b.getKey().length())
                                return b.getKey().length() - a.getKey().length();
                            return a.getKey().compareTo(b.getKey());
                        }).forEach(e -> sb.append(e.getKey()).append("\n"));

        System.out.println(sb);
    }
}