package study.programmers;

import java.io.*;
import java.util.*;
/**
 * <h2>[프로그래머스] 64061 - 크레인 인형 뽑기 게임</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 레벨1</li>
 *   <li>분류: 구현, 스택</li>
 *   <li>링크: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64061">프로그래머스 64061</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>
 * 2차원 배열 <code>board</code>에는 인형이 쌓여 있고, <code>0</code>은 빈 칸을 의미한다.
 * <code>moves</code>에는 크레인을 작동할 열 번호가 1번부터 주어진다.
 * </p>
 * <p>
 * 크레인은 선택한 열의 가장 위에 있는 인형을 하나 뽑아 바구니에 넣는다.
 * 바구니의 가장 위 인형과 새로 뽑은 인형이 같으면 두 인형은 사라지고,
 * 사라진 인형의 총 개수를 반환한다.
 * </p>
 *
 * <h3>내 접근 방법</h3>
 * <ul>
 *   <li><code>moves</code>를 순회하면서 크레인이 움직일 열을 하나씩 처리한다.</li>
 *   <li><code>getFlush()</code>에서 해당 열을 위에서 아래로 탐색해 가장 먼저 만나는 인형을 꺼낸다.</li>
 *   <li>꺼낸 인형 위치는 <code>0</code>으로 바꿔, 다음에 같은 열을 뽑을 때 이미 제거된 칸으로 처리한다.</li>
 *   <li>바구니는 <code>Deque</code>를 스택처럼 사용해 가장 최근에 뽑은 인형을 <code>peek()</code>으로 확인한다.</li>
 *   <li>바구니의 top과 새로 뽑은 인형이 같으면 <code>pop()</code>하고 정답에 <code>2</code>를 더한다.</li>
 *   <li>다르면 새 인형을 바구니에 <code>push()</code>한다.</li>
 * </ul>
 *
 * <h3>풀이 포인트</h3>
 * <p>
 * 인형이 사라지는 조건은 바구니의 가장 위 인형과 새로 뽑은 인형이 같은 경우다.
 * 따라서 바구니 전체를 확인할 필요 없이 마지막에 들어간 인형만 확인하면 된다.
 * 이 구조는 스택과 잘 맞는다.
 * </p>
 *
 * <h3>시간복잡도</h3>
 * <p>
 * 현재 풀이는 <code>moves</code>의 각 원소마다 선택한 열을 위에서 아래로 탐색한다.
 * </p>
 * <p>
 * <b>O(M * H)</b> - <code>M</code>은 <code>moves</code>의 길이, <code>H</code>는 <code>board</code>의 높이다.
 * </p>
 * <p>
 * 보드가 <code>N x N</code>이면 <code>H = N</code>이므로 <b>O(M * N)</b>이다.
 * <code>moves</code>만 보면 한 번 순회하지만, 매번 열 탐색이 들어가기 때문에 단순 <code>O(M)</code>은 아니다.
 * </p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li><code>moves</code>의 열 번호는 1부터 시작하므로, 배열 인덱스로 사용할 때 <code>move--</code> 처리가 필요하다.</li>
 *   <li>빈 열에서 인형을 뽑으려고 하면 <code>getFlush()</code>가 <code>0</code>을 반환하고 바구니 처리를 건너뛴다.</li>
 *   <li>현재 코드는 보드를 직접 수정하면서 뽑힌 인형을 제거한다.</li>
 * </ul>
 *
 * <h3>테스트 케이스</h3>
 * <ul>
 *   <li>
 *     <code>board = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]</code>,
 *     <code>moves = [1,5,3,5,1,2,1,4]</code> -&gt; <code>4</code>
 *   </li>
 * </ul>
 *
 */
public class PG64061_크레인_인형_뽑기_게임 {


    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(solution.solution(board, moves));

    }

    static class Solution {
        public int solution(int[][] board, int[] moves) {
            Deque<Integer> queue = new ArrayDeque<>();
            int answer = 0;

            for(int move : moves) {
                int flush = getFlush(board, move);
                if (flush == 0)
                    continue;

                if (queue.isEmpty()) {
                    queue.push(flush);
                    continue;
                }

                if (queue.peek() == flush) {
                    queue.pop();
                    answer += 2;
                } else {
                    queue.push(flush);
                }

            }
            return answer;
        }

        private int getFlush(int[][] board, int move) {
            move--;
            for (int i = 0; i < board.length; i++) {
                if (board[i][move] != 0){
                    int flush = board[i][move];
                    board[i][move] = 0;
                    return flush;
                }
            }
            return 0;
        }
    }
}
