package study.ect;

import java.io.*;
import java.util.*;
/**
 * <h2>[ECT] 9 - 10진수를 2진수로 변환하기</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: -</li>
 *   <li>분류: 구현, 수학</li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>
 * 10억 미만의 자연수 <code>decimal</code>을 2진수 문자열로 변환해 반환하는 문제.
 * </p>
 *
 * <h3>내 접근 방법</h3>
 * <ul>
 *   <li><code>Math.log(decimal) / Math.log(2)</code>로 필요한 비트 길이를 계산한다.</li>
 *   <li>자연수를 2로 나누며 나머지를 <code>StringBuilder</code>에 저장한다.</li>
 *   <li>나머지는 낮은 자리 비트부터 구해지므로, 마지막에 <code>reverse()</code>로 뒤집어 반환한다.</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p>
 * <b>O(log N)</b> - <code>N</code>을 2로 계속 나누므로 반복 횟수는
 * <code>log₂N</code>에 비례한다.
 * </p>
 *
 * <h3>공간복잡도</h3>
 * <p><b>O(log N)</b> - 변환된 2진수 문자열의 길이가 <code>log₂N</code>에 비례한다.</p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li>알고리즘 분석에서는 입력값 <code>N</code> 기준으로 <code>O(log N)</code>이라고 보는 것이 자연스럽다.</li>
 *   <li>입력값이 10억 미만이므로 <code>int</code> 범위로 충분하다.</li>
 *   <li>10억 미만에서는 반복 횟수가 최대 약 30번이다.</li>
 * </ul>
 *
 */

public class ECT9_10진수를_2진수로_변환하기 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        System.out.println(solution.solution(10));
    }

    static class Solution{
        public String solution(int decimal) {
            int length = (int) (Math.log(decimal) / Math.log(2));
            StringBuilder sb = new StringBuilder();
            for (int i = length; i >= 0; i--) {
                sb.append(decimal % 2);
                decimal /= 2;
            }
            return sb.reverse().toString();
        }
    }
}
