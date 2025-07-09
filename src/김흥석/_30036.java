package 김흥석;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class _30036 {
    static int n, l;
    static int ink;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int x,y;
    static int colorChoice;
    static char[][] arr;
    static char[] color;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt(); //잉크 종류 갯수
        n = sc.nextInt(); //게임판 크기
        int k = sc.nextInt(); //필요 없음
        ink = 0; //잉크 잔량 수
        colorChoice = 0; //컬러 선택
        color = sc.next().toCharArray(); //컬러 갯수

        //1. 입력받기
        arr = new char[n][n];
        for(int i = 0 ; i < n ; i ++){
            arr[i] = sc.next().toCharArray();
        }
        char[] command = sc.next().toCharArray(); // 명령어 모음

        //2.사각형 찾기
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < n ; j ++){
                if (arr[i][j] == '@'){
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        //3.로직 시작
        arr[x][y] = '.'; //출발지를 .로 만듬
        for (char c : command) {
            int m = move(c);
            if (m != -1){ //-1이면 이동이 아니기 때문에 이렇게 잡았음
                int nx = x + dx[m];
                int ny = y + dy[m];
                if (!isRange(nx,ny) || arr[nx][ny] != '.') continue;
                x = nx;
                y = ny;
            }
        }
        //로직이 끝난 뒤에 마지막 위치에 @ 찍어줌
        arr[x][y] = '@';

        //게임판 출력
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < n ; j ++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }


    //명령어 처리 로직
    static int move(char c){
        switch (c){
            case 'U':
                return 0;
            case 'R':
                return 1;
            case 'D':
                return 2;
            case 'L':
                return 3;
            case 'j':
                ink ++;
                return -1;
            case'J':
                seed();
                return -1;
        }
        return -1;
    }
    //잉크 뿌리기 로직
    static void seed(){
        //컬러 종류 선택하는게 계속 반복되어야 해서 l 을 넘으면 0으로 초기화
        if (colorChoice >= l){
            colorChoice = 0;
        }
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < n ; j ++){
                //1. 범위를 벗어나지 않으면서
                if (inkCheck(i,j)){
                    //컬러 종류이거나, 장애물이면 색칠한다.
                    if (checkAlphabet(i,j)){
                        arr[i][j] = color[colorChoice];
                    }
                }
            }
        }
        //뿌리고 나면 잉크는 0개로 초기화 , 컬러선택 ++
        ink = 0;
        colorChoice ++;
    }
    //잉크 뿌리는 범위 지정하는 로직
    static boolean inkCheck(int nx, int ny){
        return Math.abs(x - nx) + Math.abs(y - ny) <= ink;
    }

    //잉크 뿌릴 때 그 대상이 컬러의 종류이거나 장애물일 때 만 뿌림
    static boolean checkAlphabet(int i, int j) {
        for (char c : color) {
            if (arr[i][j] == c || arr[i][j] == '#'){
                return true;
            }
        }
        return false;
    }
    //범위 초과 로직
    static boolean isRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
