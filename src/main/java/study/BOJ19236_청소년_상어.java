package study;

import java.io.*;
import java.util.*;

/**
 * [백준] 19236 - 청소년 상어
 * <p>
 * [문제 정보]
 * - 난이도: 골드 1
 * - 분류:
 * - 링크: https://www.acmicpc.net/problem/19236
 * <p>
 * [문제 요약]
 * 구현, 시뮬레이션, 백트래킹
 * <p>
 * [접근 방법]
 * <p>
 * <p>
 * [시간복잡도]
 * <p>
 * <p>
 * [특이사항]
 *
 */
public class BOJ19236_청소년_상어 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        Fish[][] fishes = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                fishes[i][j] = new Fish(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        i, j);  // row=i, col=j
            }
        }
        State state = new State(fishes);

    }

    static class Fish {
        int key, direction;
        int row, col;
        private static final int[] drow = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        private static final int[] dcol = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

        Fish(int key, int direction, int row, int col) {
            this.key = key;
            this.direction = direction;
            this.row = row;
            this.col = col;
        }

        Fish(Fish other) {
            this.key = other.key;
            this.direction = other.direction;
            this.row = other.row;
            this.col = other.col;
        }

        int nextRow() {
            return row + drow[direction];
        }

        int nextCol() {
            return col + dcol[direction];
        }

        void rotate() {
            this.direction = (this.direction + 1) % 8;
        }

        boolean isSamePosition(Fish other) {
            return this.row == other.row && this.col == other.col;
        }

        Fish go() {
            this.row = nextRow();
            this.col = nextCol();
            return this;
        }
    }

    static class State {
        Fish[][] grid;
        Fish shark = new Fish(0, 0, 0, 0);

        State(Fish[][] grid) {
            this.grid = grid;
        }

        State(Fish[][] grid, Fish shark) {
            this.grid = grid;
            this.shark = shark;
        }

        State copy() {
            Fish[][] g = new Fish[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid[i][j] != null)
                        g[i][j] = new Fish(grid[i][j]);
                }
            }
            return new State(g, new Fish(shark));
        }

        void go(int row, int col) {
            Fish move = grid[row][col];
            for (int i = 0; i < 8; i++) {
                int nr = move.nextRow();
                int nc = move.nextCol();

                if ((nr == shark.row && nc == shark.col)        // 상어 위치
                        || nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {  // 격자 밖
                    move.rotate();
                    continue;
                }

                if (grid[nr][nc] != null) {
                    // 빈 칸이 아니면 교환
                    Fish exchangeFish = grid[nr][nc];
                    grid[move.row][move.col] = exchangeFish;
                    exchangeFish.row = move.row;
                    exchangeFish.col = move.col;
                } else {
                    grid[move.row][move.col] = null;
                }
                grid[nr][nc] = move.go();
                break;
            }
        }

        Fish findFishByIndex(int idx) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid[i][j] != null && grid[i][j].key == idx)
                        return grid[i][j];
                }
            }
            return null;
        }
    }
}
