package study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>[백준] 1406 - 에디터</h1>
 *
 * <h2>문제 정보</h2>
 * <ul>
 *     <li>난이도: 실버 2</li>
 *     <li>분류:</li>
 *     <li>링크: <a href="https://www.acmicpc.net/problem/1406">https://www.acmicpc.net/problem/1406</a></li>
 * </ul>
 *
 * <h2>문제 요약</h2>
 * <ul>
 *     <li>L: 앞으로 한 칸</li>
 *     <li>D : 뒤로 한 칸</li>
 *     <li>B : 앞 문자 삭제, 인덱스 1 줄음</li>
 *     <li>P $ : $ 문자 왼쪽에 추가, 인덱스 1 늘음</li>
 * </ul>
 *
 * <h2>접근 방법</h2>
 * <p>문자열이 있고 어떻게 조작할지는 고민</p>
 * <ul>
 *     <li>L이면 인덱스++</li>
 *     <li>D이면 인덱스--</li>
 *     <li>P면 현재 인덱스에 추가하고 인덱스++</li>
 *     <li>B이면 인덱스--하고 지움</li>
 * </ul>
 *
 * <h2>시간복잡도</h2>
 *
 *
 * <h2>특이사항</h2>
 * <p>엣지케이스 1</p>
 * <p>변수명을 헷갈리게 지어서(st, sb) 둘 구분을 못하고 초기 문자열만 조작했다</p>
 */
public class BOJ1406_에디터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(st);

        String[] command = new String[2];
        int cursor = st.length();

        for (int i = 0; i < n; i++) {
            command = br.readLine().split(" ");
            switch (command[0]) {
                case "D":
                    if(cursor < sb.length())
                        cursor++;
                    break;
                case "L" :
                    if (cursor > 0)
                        cursor--;
                    break;
                case "P" :
                    sb.insert(cursor, command[1]);
                    cursor++;
                    break;
                case "B" :
                    if (cursor == 0)
                        break;
                    cursor--;
                    sb.deleteCharAt(cursor);
                    break;
                default:
                    break;
            }
        }
        br.close();
        System.out.print(sb);
    }
}
