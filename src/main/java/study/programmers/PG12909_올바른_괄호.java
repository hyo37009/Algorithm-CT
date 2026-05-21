package study.programmers;

import java.io.*;
import java.util.*;

/**
 * <h2>[프로그래머스] 12909 - 올바른 괄호</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 레벨2</li>
 *   <li>분류: 스택</li>
 *   <li>링크: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12909">프로그래머스 12909</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>
 * <code>'('</code>와 <code>')'</code>로만 이루어진 문자열 <code>s</code>가 주어졌을 때,
 * 모든 여는 괄호가 올바르게 닫히는 문자열이면 <code>true</code>,
 * 그렇지 않으면 <code>false</code>를 반환하는 문제.
 * </p>
 *
 * <h3>내 접근 방법</h3>
 * <ul>
 *   <li>문자열을 문자 단위로 나눈 뒤, 직접 만든 <code>Stack</code>에 차례대로 넣는다.</li>
 *   <li><code>')'</code>가 들어왔을 때 직전의 <code>'('</code>와 짝이 맞으면 제거한다.</li>
 *   <li>모든 문자를 처리한 뒤 <code>Stack</code>이 비어 있으면 올바른 괄호라고 판단한다.</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(N)</b> - 문자열 길이 <code>N</code>만큼 한 번 순회한다.</p>
 *
 * <h3>특이사항</h3>
 * <p>-</p>
 *
 * <h3>테스트 케이스</h3>
 * <ul>
 *   <li><code>s = "()()"</code> -&gt; <code>true</code></li>
 *   <li><code>s = "(())()"</code> -&gt; <code>true</code></li>
 *   <li><code>s = ")()("</code> -&gt; <code>false</code></li>
 *   <li><code>s = "(()("</code> -&gt; <code>false</code></li>
 *   <li><code>s = "((()))"</code> -&gt; <code>true</code></li>
 *   <li><code>s = "())(()"</code> -&gt; <code>false</code></li>
 * </ul>
 */
public class PG12909_올바른_괄호 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        System.out.println(solution.solution(")))))))"));
    }

    static class Solution {
        public boolean solution(String s) {
            Stack stack = new Stack();
            String[] c = s.split("");
            for (String s1 : c)
                stack.push(s1);

            return stack.isEmpty();
        }

        public static class Stack {

            private List<String> stack = new ArrayList<>();

            public void push(String s) {
                if (stack.isEmpty() || s.equals("(")) {
                    stack.add(s);
                    return;
                }
                if (!pop(s))
                    stack.add(s);
            }

            private boolean pop(String s) {
                if (stack.get(stack.size() - 1).equals("(") && s.equals(")")) {
                    stack.remove(stack.size() - 1);
                    return true;
                }
                return false;
            }

            public boolean isEmpty() {
                return stack.isEmpty();
            }
        }
    }
}
