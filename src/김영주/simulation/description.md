# Simulation
**주어진 조건을 그대로 구현**하는 알고리즘

## ⚡️ 핵심
- 구현력
- 문제에 제시된 요구사항을 정확히 분석하는 것

## ⚡️ 대표 유형
- 배열 조작(회전/복사/이동 등)
- 시간 흐름에 따른 상태 추적

## ⚡️ 주요 테크닉: dx/dy
### 📌 방향 지정
`` 상 → 우 → 하 → 좌 `` 순서로 아래와 같이 인덱스 설정 
```java
int[] dx = {-1, 0, +1, 0}; // 행
int[] dy = {0, +1, 0, -1}; // 열
```
### 📌 다음 위치 설정
다음 위치 = 현재 위치 + dx[이동하고자 하는 방향의 인덱스]
```java
int nextPositionX = currentPositonX + dx[ 이동하고자하는방향의IDX ];
int nextPositionY = currentPositonY + dy[ 이동하고자하는방향의IDX ];
```
### 📌 이동 방향 회전
인덱스의 변화 속 규칙을 찾아 적용하는 연습 필요
```java
// 시계 방향
int nextDirection = (currentDirection + 1) % 4;

// 반시계 방향
int nextDirection = (currentDirection + 3) % 4;

// 반시계 방향: 파이썬에서만 적용 가능
int nextDirection = (currentDirection - 1) % 4;
```


