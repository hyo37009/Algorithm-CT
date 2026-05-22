package study.programmers;

import java.io.*;
import java.util.*;

/**
 * <h2>[프로그래머스] 42584 - 주식 가격</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 레벨2</li>
 *   <li>분류: 스택</li>
 *   <li>링크: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42584">프로그래머스 42584</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>
 * 초 단위로 기록된 주식 가격 배열 <code>prices</code>가 주어진다.
 * 각 시점의 가격이 이후 몇 초 동안 떨어지지 않는지 구해 배열로 반환하는 문제다.
 * </p>
 * <p>
 * 가격이 바로 다음 초에 떨어지더라도, 그 1초 동안은 가격이 떨어지지 않은 것으로 본다.
 * 마지막 시점은 더 이상 비교할 시간이 없으므로 항상 <code>0</code>초다.
 * </p>
 *
 * <h3>내 접근 방법</h3>
 * <ul>
 *   <li>모든 시점의 <code>index</code>와 <code>price</code>를 <code>right</code>에 담아 순서대로 처리한다.</li>
 *   <li><code>left</code>에는 아직 가격이 떨어지는 시점을 찾지 못한 값들을 보관한다.</li>
 *   <li>현재 가격 <code>now</code>가 <code>left</code>의 top보다 낮으면, top의 가격은 현재 시점에서 처음으로 떨어진 것이다.</li>
 *   <li>이때 <code>answer[이전 index] = 현재 index - 이전 index</code>로 유지 시간을 계산한다.</li>
 *   <li>현재 가격보다 높은 값들이 <code>left</code>에 연속해서 남아 있을 수 있으므로, <code>while</code>문으로 가능한 만큼 처리한다.</li>
 *   <li>끝까지 가격이 떨어지지 않은 값들은 마지막 시점과의 차이로 정답을 채운다.</li>
 * </ul>
 *
 * <h3>풀이 포인트</h3>
 * <p>
 * 단순히 각 가격마다 뒤쪽을 모두 확인하면 최악의 경우 <code>O(N^2)</code>이 된다.
 * <code>prices</code>의 길이가 최대 100,000이므로 모든 쌍을 비교하는 방식은 부담이 크다.
 * </p>
 * <p>
 * 이 풀이는 아직 답이 정해지지 않은 가격만 <code>left</code>에 남겨 두고,
 * 더 낮은 가격을 만나는 순간 해당 값들의 정답을 확정한다.
 * </p>
 *
 * <h3>시간복잡도</h3>
 * <p>
 * <b>O(N)</b> - 각 가격은 <code>left</code>에 한 번 들어가고, 정답이 확정될 때 한 번 제거된다.
 * </p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li><code>right</code>는 아직 처리하지 않은 값을 순서대로 꺼내기 위한 용도로 사용했다.</li>
 *   <li><code>left</code>는 아직 하락 시점을 찾지 못한 값을 관리하는 스택 역할을 한다.</li>
 *   <li>2019년 2월 28일 지문이 리뉴얼되었다.</li>
 * </ul>
 *
 * <h3>테스트 케이스</h3>
 * <ul>
 *   <li><code>prices = [1, 2, 3, 2, 3]</code> -&gt; <code>[4, 3, 1, 1, 0]</code></li>
 *   <li><code>prices = [5, 4, 3, 2, 1]</code> -&gt; <code>[1, 1, 1, 1, 0]</code></li>
 *   <li><code>prices = [1, 2, 3, 4, 5]</code> -&gt; <code>[4, 3, 2, 1, 0]</code></li>
 *   <li><code>prices = [3, 3, 3, 3]</code> -&gt; <code>[3, 2, 1, 0]</code></li>
 *   <li><code>prices = [2, 1, 2, 1]</code> -&gt; <code>[1, 2, 1, 0]</code></li>
 * </ul>
 */
public class PG42584_주식_가격 {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        int[] testcase = new int[]{1, 2, 3, 2, 3};
        int[] answer = solution.solution(testcase);
        System.out.println(answer);

        for (int i : answer) {
            System.out.printf("%d ", i);
        }
    }

    static class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            Deque<int[]> left = new ArrayDeque<>();
            Deque<int[]> right = new ArrayDeque<>();

            for (int i = 0; i < prices.length; i++) {
                right.add(new int[]{i, prices[i]});
            }
            left.push(right.pop()); // 첫번째 원소 넣어줌

            int now[] = right.peek();
            while (!right.isEmpty()) { // 볼게 없으면 종료
                now = right.pop(); // 이번 가격
                int[] last = left.peek(); // 아직 가격 안 매긴 것 중 마지막 것
                while (last[1] > now[1]) { // 내렸냐?
                    if (left.isEmpty()) // 이제 없는데?
                        break; // 그래

                    if (last[1] > now[1]) {// 가격이 떨어졌으면
                        answer[last[0]] = now[0] - last[0]; // 몇 초 버텼나? 채워줌
                        left.pop(); // 넌 이제 정답 찾았어
                    }

                    if (left.isEmpty())
                        break;
                    else
                        last = left.peek();
                }
                left.push(now);
            }
            while (!left.isEmpty()) {
                int[] last = left.pop();
                answer[last[0]] = now[0] - last[0]; // 몇 초 버텼나? 채워줌
            }

            return answer;
        }
    }

}
