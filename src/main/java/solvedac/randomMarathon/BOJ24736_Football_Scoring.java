package solvedac.randomMarathon;

import org.assertj.core.api.Assertions;

import java.io.*;
import java.util.*;

/**
 * <H1>[백준] 24736 - Football Scoring</H1>
 *
 * <h3>문제 정보</h3>
 * <ul>
 * <li>난이도: 브론즈 5</li>
 * <li>분류: 기본</li>
 * <li>링크: <a href="https://www.acmicpc.net/problem/24736">백준 24736</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>두 풋볼팀의 총 점수를 구하시오<br>
 * 입력은 T, F, S, P, C 순서로 들어온다.<br>
 * (0 ≤ T ≤ 10), (0 ≤ F ≤ 10), (0 ≤ S ≤ 10), (0 ≤ (P+C) ≤ T)<br>
 * T : 6 포인트, F: 3 포인트, S : 2 포인트, P : 1 포인트, C : 2 포인트</p>
 *
 * <h3>입출력 예시</h3>
 * <p>
 * - 입력<br>
 * 1 3 0 0 1<br>
 * 3 1 1 1 1<br>
 * - 출력<br>
 * 17 26
 * </p>
 *
 * @author kim jiye
 * @since 2026-01-28
 */
public class BOJ24736_Football_Scoring {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 테스트용 입력
        String input = "1 3 0 0 1\n3 1 1 1 1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        solution.solution(in);

        // 백준 제출 시: solution.solution(System.in);
    }

    static class Solution {
        public void solution(InputStream in) {
            Scanner sc = new Scanner(in);
            int t1 = 0;
            int t2 = 0;
            int[] scores = new int[]{6, 3, 2, 1, 2};

            String[] goals = sc.nextLine().split(" ");
            for (int i = 0; i < 5; i++) {
                t1 += Integer.parseInt(goals[i]) * scores[i];
            }

            goals = sc.nextLine().split(" ");
            for (int i = 0; i < 5; i++) {
                t2 += Integer.parseInt(goals[i]) * scores[i];
            }

            System.out.println(t1 + " " + t2);
        }
    }
}
