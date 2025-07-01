package 김영주.simulation;

import java.io.*;
import java.util.*;

// N * N 정사각형 보드
// 뱀은 맨위 맨좌측(1행 1열)에 위치, 길이는 1

// 구현 순서
// 1. 몸길이를 늘려 머리를 다음 칸에 위치
// 2. 벽이나 자기자신의 몸에 부딪히면 게임 끝
// 3. 이동하는 칸에 사과
//      있을 때 -> 머리 이동(사과가 없애기) + 꼬리 움직이지 X
//      없을 때 -> 머리 이동 + 꼬리 움직이기 O
// 4. 해당 과정을 끝내고 소요 시간 == 방향 변환을 해야하는 시간
//      D -> 오른쪽으로 90도 회전
//      L -> 왼쪽으로 90도 회전

// 게임이 몇초에 끝나는지 출력

public class 백준_3190_뱀_반복 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static Deque<int[]> snake = new ArrayDeque<>();
    static int N, K, L, currSec = 0, currDir = 1;
    static int[][] board;
    static boolean end = false;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        board = new int[N][N];

        K = Integer.parseInt(br.readLine()); // 사과의 개수
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;

            board[i][j] = 1;
        }

        L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 정보
        Queue<DirectionInfo> q = new LinkedList<>();
        for (int l = 0; l < L; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            q.offer(new DirectionInfo(x, c));
        }

        // process
        int endSec = gameStart(q);

        // output
        System.out.println(endSec);
    }

    static int gameStart(Queue<DirectionInfo> q) {
        snake.offerLast(new int[]{0, 0});
        board[0][0] = 2; // 뱀이 위치한 칸:2, 사과가 위치한 칸:1, 아무것도 없는 칸:0

        while (!q.isEmpty()) {
            DirectionInfo changeSec = q.poll();
            move(changeSec.endSec);
            if (end) break;
            changeSec.changeDir();
        }
        if (!end) move(Integer.MAX_VALUE);

        return ++currSec;
    }

    static void move(int endSec) {
        while (currSec < endSec) {
            int[] currHead = snake.peekFirst();
            int nextHeadX = currHead[0] + dx[currDir];
            int nextHeadY = currHead[1] + dy[currDir];

            if (!isValidPos(nextHeadX, nextHeadY) || board[nextHeadX][nextHeadY] == 2) {
                end = true;
                break;
            }

            snake.offerFirst(new int[]{nextHeadX, nextHeadY});
            if (board[nextHeadX][nextHeadY] != 1) {
                int[] currTail = snake.pollLast();
                board[currTail[0]][currTail[1]] = 0;
            }
            board[nextHeadX][nextHeadY] = 2;
            currSec++;
        }
    }

    static boolean isValidPos(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    static class DirectionInfo {
        int endSec;
        char direction;

        public DirectionInfo(int endSec, char direction) {
            this.endSec = endSec;
            this.direction = direction;
        }

        void changeDir() {
            currDir = (direction == 'L') ? (currDir + 3) % 4 : (currDir + 1) % 4;
        }
    }
}
