package datastructure.tree;

public class 트리_순회 {
    /**
     * [기타]  - 트리 순회
     *
     * [문제 정보]
     * - 난이도: 쉬움
     * - 분류: 트리
     * - 링크: 코딩테스트 합격자 되기: 자바편
     *
     * [문제 요약]
     * 이진 트리가 주어졌을 때 해당 이진 트리에 대해 전위, 중위, 후위 순화 결과를 반환하는 함수를 구현
     *
     * [접근 방법]
     * 입력값이 1-base숫자로 주어지므로 탐색시 인덱스 0값에 임의의 값을 넣어주어 탐색
     *
     * [시간복잡도]
     * 1. 각 노드 방문 횟수 분석:
     *    - 트리의 모든 노드를 정확히 한 번씩만 방문
     *    - n개의 노드가 있으면 총 n번의 노드 방문
     * 
     * 2. 각 노드에서 수행하는 연산:
     *    - System.out.println(array[i]): O(1)
     *    - left(i), right(i) 계산: O(1)
     *    - 조건문 체크: O(1)
     *    → 노드당 연산 시간: O(1)
     * 
     * 3. 재귀 호출 분석:
     *    - 각 노드는 최대 2개의 자식 노드로 재귀 호출
     *    - 총 재귀 호출 횟수 = n번 (모든 노드)
     *    - 재귀 깊이 = 트리의 높이 (공간복잡도에 영향)
     * 
     * 4. 최종 계산:
     *    - 노드 방문 횟수: n번
     *    - 노드당 연산 시간: O(1)
     *    - 전체 시간복잡도: n × O(1) = O(n)
     *
     * ∴ 시간복잡도: O(n) (n = 트리의 노드 개수)
     *
     * [주의할 점]
     * 오른쪽이랑 왼쪽을 헷갈리지 말자.
     *
     */
    public static void main(String[] args) {
        전위_순회(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 1);
        System.out.println();
        중위_순회(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 1);
        System.out.println();
        후위_순회(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 1);
        System.out.println();
    }

    static void 전위_순회(int[] array, int i) {
        if (i < 0 || i > array.length) {
            return;
        }

        System.out.print(array[i] + " ");
        if (left(i) < array.length) {
            전위_순회(array, left(i));
        }
        if (right(i) < array.length) {
            전위_순회(array, right(i));
        }
    }

    static void 중위_순회(int[] array, int i) {
        if(i < 0 || i >= array.length)
            return;

        if (left(i) < array.length) {
            중위_순회(array, left(i));
        }
        System.out.print(array[i] + " ");
        if (right(i) < array.length) {
            중위_순회(array, right(i));
        }
    }

    static void 후위_순회(int[] array, int i) {
        if(i < 0 || i >= array.length)
            return;

        if (left(i) < array.length) {
            후위_순회(array, left(i));
        }
        if (right(i) < array.length) {
            후위_순회(array, right(i));
        }
        System.out.print(array[i] + " ");
    }

    static int left(int i) {
        return 2 * i;
    }

    static int right(int i) {
        return 2 * i + 1;
    }

}
