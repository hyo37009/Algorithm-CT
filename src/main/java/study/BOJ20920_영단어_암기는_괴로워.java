package study;

import java.io.*;
import java.util.*;

/**
 * [백준] 20920 - 영단어 암기는 괴로워
 * <p>
 * [문제 정보]
 * - 난이도: 실버 3
 * - 분류: 구현
 * - 링크: https://www.acmicpc.net/problem/20920
 * <p>
 * [문제 요약]
 * 길이가 m 이상인 단어에 대해서, 빈도-길이-알파벳 순으로 정렬하여 출력
 * <p>
 * [접근 방법]
 * <p>
 * <p>
 * [시간복잡도]
 * O(n)
 * [특이사항]
 * 반복 출력으로 시간초과. sb사용할 것
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