package study;

import java.io.*;
import java.util.*;

/**
 * <h2>[백준] 3986 - 좋은 단어</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 실버 4</li>
 *   <li>분류: 스택</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/3986">BOJ 3986</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>A와 B로 이루어진 단어에서, 같은 글자끼리 아치형 곡선으로 교차 없이 짝지을 수 있으면 좋은 단어다. 좋은 단어의 개수를 구한다.</p>
 *
 * <h3>접근 방법</h3>
 * <ul>
 *   <li>스택의 top과 현재 글자가 같으면 pop, 다르면 push</li>
 *   <li>모든 글자 처리 후 스택이 비어있으면 좋은 단어</li>
 *   <li>홀수 길이, A/B 각각 홀수 개인 경우 조기 스킵으로 최적화</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(S)</b> - S는 모든 단어 길이의 합 (최대 1,000,000)</p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li>처음에 <code>Queue</code>의 <code>add</code>/<code>peek</code>/<code>poll</code>을 사용하여 FIFO로 동작 → 스택의 <code>push</code>/<code>peek</code>/<code>pop</code>으로 수정</li>
 *   <li><code>Deque</code>를 스택으로 쓸 때는 반드시 <code>push</code>/<code>peek</code>/<code>pop</code> 조합을 사용할 것</li>
 * </ul>
 */
public class BOJ3986_좋은_단어 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Deque<String> deque = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < n; i++) {
            deque.clear();
            String[] abs = br.readLine().split("");

            if (abs.length % 2 == 1)
                continue;
            if (Arrays.stream(abs)
                    .filter(a -> a.equals("A"))
                    .count() % 2 == 1
                    || Arrays.stream(abs)
                    .filter(a -> a.equals("B"))
                    .count() % 2 == 1)
                continue;

            for (String ab : abs) {
                if (deque.isEmpty()) {
                    deque.push(ab);
                    continue;
                }
                if (deque.peek().equals(ab))
                    deque.pop();
                else
                    deque.push(ab);
            }
            if (deque.isEmpty())
                answer++;
        }
        br.close();
        System.out.println(answer);
    }
}