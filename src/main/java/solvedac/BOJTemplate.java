package solvedac;

import org.assertj.core.api.Assertions;

import java.io.*;
import java.util.*;

/**
 * <H1>[백준] 문제번호 - 문제제목</H1>
 *
 * <h3>문제 정보</h3>
 * <ul>
 * <li>난이도: </li>
 * <li>분류: </li>
 * <li>링크: <a href="https://www.acmicpc.net/problem/">백준 문제번호</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p></p>
 *
 * <h3>접근 방법</h3>
 * <p></p>
 *
 * <h3>시간복잡도</h3>
 * <p></p>
 *
 * <h3>특이사항</h3>
 * <p></p>
 *
 * @author kim jiye
 * @since
 */
public class BOJTemplate {

    public static void main(String[] args) {
        Main solution = new Main();

        // 테스트용 입력
        String input = "1 2 3\n4 5 6";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Assertions.assertThat(solution.solution(in)).isEqualTo(0);

        // 백준 제출 시: solution.solution(System.in);
    }

    static class Main {
        public int solution(InputStream in) {
            Scanner sc = new Scanner(in);
            int answer = 0;

            // 입력 처리

            return answer;
        }
    }
}