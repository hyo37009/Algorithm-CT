package study.BOJ;

import java.util.*;
import java.util.stream.IntStream;

/**
 * [백준] 2559 - 수열
 *
 * [문제 정보]
 * - 난이도: 실버 3
 * - 분류: 수열의 합
 * - 링크: https://www.acmicpc.net/problem/2559
 *
 * [문제 요약]
 * 길이 n인 배열의
 *
 * [접근 방법]
 * 실패 - 매번 새 배열을 만듦 (메모리 초과)
 * 성공 - 슬라이딩 윈도우
 * [시간복잡도]
 * 실패 -
 * 성공 - O(n)
 *
 * [특이사항]
 * Intstream, BufferedReader를 공부하고싶음
 */
public class BOJ2559_수열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] days = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] temps = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = days[0];
        int k = days[1];

        int sum = IntStream.of(Arrays.copyOfRange(temps, 0, k)).sum();
        int answer = sum;

        for (int i = 1; i < n - k + 1; i++) {
            sum = sum - temps[i - 1] + temps[i + k - 1];
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }
}
