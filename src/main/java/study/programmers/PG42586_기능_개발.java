package study.programmers;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>[프로그래머스] 42586 - 기능 개발</h1>
 *
 * <h2>문제 정보</h2>
 * <ul>
 *     <li>난이도: 레벨 2</li>
 *     <li>분류: 스택/큐, 큐, 시뮬레이션, 배열</li>
 *     <li>링크: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42586">문제 바로가기</a></li>
 * </ul>
 *
 * <h2>문제 요약</h2>
 * <p>
 * 각 기능은 현재 진행률 {@code progresses[i]}와 하루 작업 속도 {@code speeds[i]}를 가진다.
 * 하루가 지날 때마다 모든 기능의 진행률이 각자의 속도만큼 증가한다.
 * </p>
 * <p>
 * 진행률이 100 이상이 된 기능은 배포할 수 있지만, 앞에 있는 기능이 아직 완료되지 않았다면
 * 뒤 기능이 먼저 완료되어도 앞 기능과 함께 나중에 배포되어야 한다.
 * 각 배포일마다 몇 개의 기능이 함께 배포되는지 배열로 반환한다.
 * </p>
 *
 * <h2>접근 방법</h2>
 * <p>
 * 진행률과 속도를 각각 큐에 저장해서 작업 순서를 유지한다.
 * 하루 단위로 큐에 남아 있는 모든 작업을 한 번씩 꺼낸 뒤, 해당 작업의 속도만큼 진행률을 더하고
 * 다시 큐의 뒤에 넣는다.
 * </p>
 * <p>
 * 하루 작업이 끝난 뒤 큐의 맨 앞 작업이 100 이상이면, 앞에서부터 연속으로 완료된 작업들을 제거한다.
 * 이때 진행률 큐에서 작업을 제거할 때 속도 큐에서도 같은 위치의 속도를 함께 제거해야 한다.
 * 그래야 앞 작업이 배포된 이후에도 남은 작업과 각 작업의 속도가 어긋나지 않는다.
 * </p>
 * <p>
 * 제거한 작업 수가 0보다 크면 그 수가 해당 날짜의 배포 개수이므로 {@code answer}에 추가한다.
 * </p>
 *
 * <h2>시간복잡도</h2>
 * <ul>
 *     <li>현재 구현: 작업 수 N, 최대 소요 일수 D에 대해 {@code O(N * D)}</li>
 *     <li>개선 가능: 각 기능의 완료일을 먼저 계산하면 {@code O(N)}</li>
 *     <li>공간복잡도: 진행률 큐, 속도 큐, 정답 리스트를 사용하므로 {@code O(N)}</li>
 * </ul>
 *
 * <h2>특이사항</h2>
 * <p>
 * 진행률 큐에서 완료된 작업을 제거하면서 {@code speeds} 배열을 인덱스로 직접 참조하면 인덱스가 어긋날 수 있다.
 * 예를 들어 0번 작업이 배포되어 빠진 뒤 남은 첫 번째 작업은 원래 1번 작업인데,
 * 다시 {@code speeds[0]}을 적용하면 잘못된 속도로 진행률을 갱신하게 된다.
 * 이 문제를 피하기 위해 속도도 {@code speedQueue}로 함께 관리한다.
 * </p>
 */

public class PG42586_기능_개발 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        int[] progresses = new int[]{95, 99, 80, 99, 93};
        int[] speeds = new int[]{1, 2, 3, 4, 5};

        int[] result = solution.solution(progresses, speeds);
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            List<Integer> answer = new ArrayList<>();
            Queue<Integer> queue = Arrays.stream(progresses)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayDeque::new));
            Queue<Integer> speedQueue = Arrays.stream(speeds)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayDeque::new));
            while(!queue.isEmpty()) {
                for (int i = 0; i < queue.size(); i++) {
                    int now = queue.poll();
                    int nowSpeed = speedQueue.poll();
                    queue.add(now + nowSpeed);
                    speedQueue.add(nowSpeed);
                }

                int bepo = 0;
                if (queue.peek() >= 100) {
                    while (!queue.isEmpty() && queue.peek() >= 100) {
                        queue.poll();
                        speedQueue.poll();
                        bepo++;
                    }
                }
                if (bepo > 0)
                    answer.add(bepo);
            }


            return answer.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
    }
}
