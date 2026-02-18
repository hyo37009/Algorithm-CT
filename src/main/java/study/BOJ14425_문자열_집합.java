package study;

import java.io.*;
import java.util.*;

/**
 * <h2>[백준] 14425 - 문자열 집합</h2>
 *
 * <h3>[문제 정보]</h3>
 * <ul>
 *   <li>난이도: 실버 4</li>
 *   <li>분류: 자료구조, HashSet</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/14425">https://www.acmicpc.net/problem/14425</a></li>
 * </ul>
 *
 * <h3>[문제 요약]</h3>
 * <p>
 * N개의 문자열로 이루어진 집합 S가 주어질 때,
 * M개의 검사 문자열 중 S에 포함된 것이 몇 개인지 세는 문제.<br>
 * (N, M ≤ 10,000, 문자열 길이 ≤ 500)
 * </p>
 *
 * <h3>[접근 방법]</h3>
 * <p>
 * 처음엔 <code>LinkedList</code>를 사용했다가 자료구조를 잘못 선택한 것을 깨달았다.
 * </p>
 *
 * <p><b>1차 시도 (LinkedList):</b></p>
 * <ul>
 *   <li><code>contains()</code> 메서드가 O(N)이므로 M번 호출 시 O(N × M)</li>
 *   <li>최악: 10,000 × 10,000 × (문자열 비교) ≈ 10억 연산</li>
 *   <li>예상 시간: 약 10초 → <b>시간 초과 확정</b></li>
 * </ul>
 *
 * <p><b>2차 시도 (HashSet):</b></p>
 * <ul>
 *   <li><code>contains()</code>가 O(1)이므로 전체 O(N + M)</li>
 *   <li>최악: 10,000 + 10,000 = 20,000 연산</li>
 *   <li>예상 시간: 0.0002초 미만 → <b>여유롭게 통과</b></li>
 * </ul>
 *
 * <p>
 * <b>핵심 교훈:</b> "포함 여부 확인"이 주요 연산이면 반드시 <code>HashSet</code>/<code>HashMap</code>을 사용해야 한다.
 * </p>
 *
 * <h3>[시간복잡도]</h3>
 * <p>
 * <b>O(N + M)</b>
 * </p>
 * <ul>
 *   <li>N개 문자열 HashSet에 추가: O(N)</li>
 *   <li>M개 문자열 contains() 확인: O(M)</li>
 * </ul>
 *
 * <h3>[특이사항]</h3>
 * <p>자료구조별 성능 비교 (N = M = 10,000 기준):</p>
 * <table border="1">
 *   <tr>
 *     <th>자료구조</th>
 *     <th>add()</th>
 *     <th>contains()</th>
 *     <th>전체 복잡도</th>
 *     <th>예상 시간</th>
 *   </tr>
 *   <tr>
 *     <td>ArrayList</td>
 *     <td>O(1)</td>
 *     <td>O(N)</td>
 *     <td>O(N × M)</td>
 *     <td>~10초</td>
 *   </tr>
 *   <tr>
 *     <td>LinkedList</td>
 *     <td>O(1)</td>
 *     <td>O(N)</td>
 *     <td>O(N × M)</td>
 *     <td>~10초</td>
 *   </tr>
 *   <tr>
 *     <td><b>HashSet</b></td>
 *     <td><b>O(1)</b></td>
 *     <td><b>O(1)</b></td>
 *     <td><b>O(N + M)</b></td>
 *     <td><b>~0.0002초</b></td>
 *   </tr>
 * </table>
 *
 * <p><b>ArrayList vs LinkedList:</b></p>
 * <ul>
 *   <li><code>contains()</code>는 둘 다 O(N)이지만, ArrayList가 메모리 지역성 때문에 실제로는 더 빠름</li>
 *   <li>LinkedList는 포인터 추적 오버헤드가 있어 실전에서 거의 사용하지 않음</li>
 * </ul>
 *
 * <p><b>실수 회고:</b></p>
 * <ul>
 *   <li>문제 이름이 "문자열 집합"인데도 List를 사용한 것이 실수</li>
 *   <li>"집합(Set)"이라는 단어가 나오면 HashSet을 먼저 떠올려야 함</li>
 *   <li>코드 작성 전에 시간복잡도를 계산하는 습관이 중요함</li>
 * </ul>
 */
public class BOJ14425_문자열_집합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int m = input[1];

        Set<String> s = new HashSet<>();

        for (int i = 0; i < n; i++) {
            s.add(br.readLine());
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            String now = br.readLine();
            if(s.contains(now))
                answer++;
        }
        br.close();

        System.out.println(answer);
    }
}

/*
===========================================
COMMIT MESSAGE:
===========================================
solve: [BOJ14425] 문자열 집합 문제 해결

자료구조 선택 실수로 인한 시간복잡도 문제 해결 과정

1차 시도 실패 (LinkedList):
- contains() 메서드 시간복잡도를 고려하지 않고 LinkedList 사용
- contains()가 O(N)이므로 M번 호출 시 O(N × M) = 100,000,000 연산
- 예상 시간 약 10초로 시간 제한 2초 초과 예상
- 문제 이름이 "문자열 집합"임에도 Set을 떠올리지 못한 것이 실수

2차 시도 성공 (HashSet):
- "포함 여부 확인" 연산에는 HashSet이 최적임을 깨달음
- contains()가 O(1)로 개선되어 전체 O(N + M) = 20,000 연산
- 50,000배 성능 향상으로 여유롭게 통과

학습 내용:
- ArrayList vs LinkedList vs HashSet 성능 비교표 작성
- 코드 작성 전 시간복잡도 계산의 중요성 확인
- "집합", "포함 여부" 키워드 → HashSet/HashMap 패턴 학습
- 실수 회고 및 자료구조별 특징을 상세히 JavaDoc으로 문서화

Co-Authored-By: Claude Sonnet 4.5 <noreply@anthropic.com>
===========================================
*/