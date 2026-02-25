package study;

import java.io.*;
import java.util.*;

/**
 * [백준] 2531 - 회전 초밥
 *
 * [문제 정보]
 * - 난이도: 실버 1
 * - 분류:
 * - 링크: https://www.acmicpc.net/problem/2531
 *
 * [문제 요약]
 * 연속 k개의 접시를 먹은 경우 할인도니 가격으로 제공
 * 위를 만족한 경우 쿠폰에 쓰인 초밥은 무료 (없다면 만들어줌)
 * 손님이 먹을 수 있는 초밥의 가짓수의 최댓값
 *
 * [접근 방법]
 *
 *
 * [시간복잡도]
 *
 *
 * [특이사항]
 *
 */
public class BOJ2531_회전_초밥 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0]; // 접시의 수
        int d = input[1]; // 초밥의 가짓수 (초밥의 범위(1 ~ d))
        int k = input[2]; // 연속해서 먹는 접시의 수
        int c = input[3]; // 쿠폰 번호

        int[] sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Set<Integer> eat = new HashSet<>();
        eat.add(c);
        for (int i = 0; i < k; i++) {
            // 초기 세팅
            eat.add(sushi[i]);
        }

        int answer = eat.size();
        for (int i = 0; i < n; i++) {
            int last = (i + k - 1 < n)? i + k - 1 : i + k - 1 - n; // 새 접시 인덱스
            // 첫 접시 뺌
            eat.remove(sushi[Math.max(i - 1, 0)]);
            eat.add(sushi[last]); // 새 접시 추가
            eat.add(c); // 쿠폰 추가

            answer = Math.max(answer, eat.size());
        }

        System.out.println(answer);
    }
}