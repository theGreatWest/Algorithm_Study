package 김흥석;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class _2178 {
    static int n, m, ans;
    static final int MAX_NUM = 110;
    static int[][] graph = new int[MAX_NUM][MAX_NUM];

    static int[]dx = {-1,0,1,0};
    static int[]dy = {0,1,0,-1};
    static int[][] box ;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //참고로 DFS로 풀어봤는데 시간 초과가 났다.
        //*BufferedReader 는 시간제한이 빡센 문제만 사용
        //1. 초기 세팅
        n = sc.nextInt();
        m = sc.nextInt();
        box = new int[MAX_NUM][MAX_NUM];
        //1부터 시작하는 이유는 isRange 함수로 따로 인덱스 초과하는지 안봐도 되기 때문
        for(int i = 1; i <= n ; i ++){
            char[] str = sc.next().toCharArray();
            for(int j = 1; j <= m; j ++){
                if (str[j-1] == '1'){
                    graph[i][j] = 1;
                }
            }
        }
        //2. 함수 호출
        BFS(1,1);
        //3. 출력
        System.out.println(box[n][m]);
    }
    static void BFS(int x, int y){

        //x, y 를 담아야 하기 때문에 배열을 담는 queue
        Queue<int[]> queue = new LinkedList<>();
        box[x][y] = 1;
        queue.offer(new int[]{x,y});

        while (!queue.isEmpty()){
            int [] item = queue.poll();
            int x1 = item[0], y1 = item[1];
            //목적지에 도착하면 종료 -> 최단 거리기 때문에 젤 먼저 도착하는게 정답
            if (x1 == n && y1 == m){
                return;
            }

            for(int i = 0 ; i < 4; i ++){
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                //범위 초과하면 넘어감
                if (nx > n || ny > m) continue;
                //
                if (graph[nx][ny] == 1){
                    graph[nx][ny] = 0; // 0으로 만들어줘야 다시 방문하지 않음
                    box[nx][ny] = box[x1][y1] + 1; // 거리 + 1
                    queue.offer(new int[]{nx,ny}); // 새로 담는다
                }
            }
        }
    }
}
