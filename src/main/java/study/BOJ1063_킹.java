package study;

import java.io.*;
import java.util.*;

/**
 * <h2>[백준] 1063 - 킹</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 실버 3</li>
 *   <li>분류: 구현, 시뮬레이션</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/1063">BOJ 1063</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>8×8 체스판 위에 킹과 돌이 있을 때, 주어진 이동 명령에 따라 킹을 움직인다.
 * 킹이 돌과 같은 위치로 이동하면 돌도 같은 방향으로 밀린다.
 * 킹 또는 돌이 체스판 밖으로 나가는 이동은 무시한다.</p>
 *
 * <h3>접근 방법</h3>
 * <ul>
 *   <li>킹과 돌의 위치를 char 배열로 관리하여 직접 이동 시뮬레이션</li>
 *   <li>이동 전 상태를 clone()으로 백업하고, 범위 초과 시 System.arraycopy()로 복원</li>
 *   <li>명령 문자열을 split("")으로 분리하여 R/L/T/B 각 방향을 독립 처리</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(N)</b> - 각 명령당 상수 시간 처리</p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li>char 배열 참조 교체(king = prev)는 호출 측에 반영되지 않으므로 System.arraycopy()로 내용을 복원해야 함</li>
 *   <li>범위 검사에서 논리 연산자 혼동 주의: 'A' &gt; x && x &gt; 'Z'는 항상 false</li>
 *   <li>Character.getName()은 유니코드 이름을 반환하므로 출력에 부적합. "" + char로 변환</li>
 * </ul>
 */
public class BOJ1063_킹 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        char[] king = st.nextToken().toCharArray();
        char[] doll = st.nextToken().toCharArray();
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            move(king, doll, br.readLine());
        }
        br.close();
        System.out.println("" + king[0] + king[1]);
        System.out.println("" + doll[0] + doll[1]);
    }

    static void move(char[] king, char[] doll, String command) {
        String[] move = command.split("");
        char[] kingPrev = king.clone();
        char[] dollPrev = doll.clone();
        move(king, move);
        if(!isIn(king)) {
            System.arraycopy(kingPrev, 0, king, 0, 2);
            return;
        }
        if(Arrays.equals(king, doll)) {
            move(doll, move);
            if(!isIn(doll)){
                System.arraycopy(kingPrev, 0, king, 0, 2);
                System.arraycopy(dollPrev, 0, doll, 0, 2);
                return;
            }
        }
    }

    public static boolean isIn(char[] king) {
        return ('A' <= king[0] && king[0] <= 'H') && ('1' <= king[1] && king[1] <= '8');
    }

    private static void move(char[] king, String[] move) {
        for (int i = 0; i < move.length; i++) {
            if(move[i].equals("R"))
                king[0]++;
            if(move[i].equals("L"))
                king[0]--;
            if(move[i].equals("T"))
                king[1]++;
            if(move[i].equals("B"))
                king[1]--;
        }
    }

}