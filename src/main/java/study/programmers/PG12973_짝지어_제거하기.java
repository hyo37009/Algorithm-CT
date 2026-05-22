package study.programmers;

import java.io.*;
import java.util.*;

/**
 * <h2>[프로그래머스] 12973 - 짝지어 제거하기</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 레벨2</li>
 *   <li>분류: 스택</li>
 *   <li>링크: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12973">프로그래머스 12973</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>
 * 알파벳 소문자로 이루어진 문자열에서 서로 붙어 있는 같은 문자 2개를 계속 제거한다.
 * 제거 후 앞뒤 문자열이 이어지면서 새로운 짝이 생길 수 있다.
 * 최종적으로 문자열을 모두 제거할 수 있으면 <code>1</code>, 남는 문자가 있으면 <code>0</code>을 반환한다.
 * </p>
 *
 * <h3>내 접근 방법</h3>
 * <ul>
 *   <li>문자열을 앞에서부터 순회하면서 <code>Deque</code>를 스택처럼 사용한다.</li>
 *   <li>현재 문자를 먼저 스택에 넣고, 스택의 위쪽 문자 2개를 꺼내 같은지 확인한다.</li>
 *   <li>두 문자가 같으면 짝이므로 제거된 상태로 둔다.</li>
 *   <li>두 문자가 다르면 제거할 수 없으므로 꺼낸 순서를 유지해서 다시 넣는다.</li>
 *   <li><code>while</code>문을 사용해 짝 제거 후 새로 붙은 문자들도 이어서 확인한다.</li>
 *   <li>모든 문자를 처리한 뒤 스택이 비어 있으면 모든 문자를 제거할 수 있다고 판단한다.</li>
 * </ul>
 *
 * <h3>풀이 포인트</h3>
 * <p>
 * 문자열에서 문자를 직접 삭제하면 중간 삭제와 이어 붙이기 비용이 커진다.
 * 대신 스택을 사용하면 현재까지 남아 있는 문자열의 끝부분만 비교할 수 있다.
 * 같은 문자가 연속되면 스택에서 제거하고, 다르면 다시 남겨 두는 방식으로 짝지어 제거 과정을 표현했다.
 * </p>
 *
 * <h3>시간복잡도</h3>
 * <p>
 * <b>O(N)</b> - 문자열 길이 <code>N</code>만큼 순회하고, 각 문자는 스택에 들어가거나 제거된다.
 * </p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li>문자열 길이가 최대 1,000,000이므로 실제 문자열 삭제 방식은 비효율적이다.</li>
 *   <li>2020년 6월 8일, 2023년 8월 31일에 테스트케이스가 추가되었다.</li>
 * </ul>
 *
 * <h3>테스트 케이스</h3>
 * <ul>
 *   <li><code>s = "baabaa"</code> -&gt; <code>1</code></li>
 *   <li><code>s = "cdcd"</code> -&gt; <code>0</code></li>
 *   <li><code>s = "aa"</code> -&gt; <code>1</code></li>
 *   <li><code>s = "abccba"</code> -&gt; <code>1</code></li>
 *   <li><code>s = "aabbccdd"</code> -&gt; <code>1</code></li>
 *   <li><code>s = "abcddcba"</code> -&gt; <code>1</code></li>
 *   <li><code>s = "abcabc"</code> -&gt; <code>0</code></li>
 * </ul>
 */
public class PG12973_짝지어_제거하기 {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        System.out.println(solution.solution("cdcd"));
    }

    static class Solution {
        public int solution(String s) {
            Deque<String> dq = new ArrayDeque<>();
            for (String now : s.split("")) {
                dq.push(now);
                if (dq.size() < 2){
                    continue;
                }

                while (dq.size() > 1) {
                    String b = dq.pop();
                    String a = dq.pop();
                    if (!a.equals(b)) {
                        dq.push(a);
                        dq.push(b);
                        break;
                    }
                }

            }
            return dq.isEmpty()? 1 : 0;
        }
    }
}
