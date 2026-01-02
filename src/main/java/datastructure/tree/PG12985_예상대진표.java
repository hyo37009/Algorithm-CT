package datastructure.tree;

import org.assertj.core.api.Assertions;
/**
 * <H1>[프로그래머스] 12985 - 예상대진표</H1>
 *
 * <h3>문제 정보</h3>
 * <ul>
 * <li>난이도: Lv.2 </li>
 * <li>분류: 트리</li>
 * <li>링크: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12985">프로그래머스 12985</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>토너먼트 대진표의 라이벌과 싸우게 되는 라운드를 구하시오</p>
 *
 * <h3>접근 방법</h3>
 * <p>완전이진트리로 보고, 배열을 이용해 표현한 뒤 <br>
 * 같은 부모를 가지는 경우 답을 구함.</p>
 *
 * <h3>시간복잡도</h3>
 * <p>O(log₂n) (<i>O(log n)</i>)<br>
 * 최악의 경우 트리 루트까지 올라가는 높이만큼 반복 <br>
 * (최대 ⌈log₂n⌉번)</p>
 *
 * <h3>특이사항</h3>
 * <h4>logₙ(x) 값을 구하는 방법</h4>
 * <p>Math.log()는 자연로그(ln)를 사용하므로, log₍ₐ₎(b)를 계산하려면 로그 밑 변환이 필요하다.<br>
 * <b>로그 밑 변환 공식</b><br>
 * logₐb = log₍c₎(b) / log₍c₎(a)  (c는 임의의 양수, c ≠ 1)<br>
 * </p>
 *
 *
 * @author kim jiye
 * @since 2026-01-02
 */
public class PG12985_예상대진표 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertThat(solution.solution(8, 4, 7)).isEqualTo(3);
    }

    static class Solution{
        public int solution(int n, long a, long b) {
            int answer = 1; // 첫 라운드에서 시작
            int height = depth(n);
            a += Math.pow(2, height) - 1;
            b += Math.pow(2, height) - 1;

            while (parent(a) != parent(b)) {
                // 부모가 동일하다면 싸운다!
                if (depth(a) < depth(b)) {
                    b >>= 1;
                } else if (depth(a) > depth(b)) {
                    a >>= 1;
                } else {
                    a >>= 1;
                    b >>= 1;
                }
                answer++;
            }

            return answer;
        }

        long parent(long i) {
            return i / 2;
        }

        /**
         * 1-base 배열 기반 완전 이진 트리에서 노드의 깊이를 구하는 함수.
         * 문제 범위가 $2^20$으로 주어졌으므로 depth는 1~20 사이이다.
         * 따라서 int형을 반환함. <br><br>
         *
         * <i>변수로 long을 쓰고 있어서 의미가 없을진 모르겠지만, <br>
         * 입력 범위와 변수의 표현범위를 신경써보았다.</i>
         * @param i 1-base 힙 인덱스
         * @return 해당 인덱스의 깊이
         */
        int depth(long i) {
            return (int) (Math.log(i) / Math.log(2));
        }
    }
}
