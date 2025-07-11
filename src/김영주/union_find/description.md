# Union - Find
**서로소 집합(Disjoint Set Union, DSU)** 자료구조 기반의 알고리즘

## ⚡️ 핵심
- **같은 그룹(집합)에 속하는지 빠르게 판별**
- **집합 병합(Union)**, **대표 노드 찾기(Find)** 동작 구현
- 트리 구조 & 경로 압축 최적화 활용 가능

## ⚡️ 대표 유형
- **Node의 연결 여부 판별** (ex. 연결 요소 찾기, 사이클 검출)
- **그룹화 처리** (ex. 친구 관계, 네트워크 연결, 여행 가능 여부)
- **MST(최소 신장 트리)** 구성 (ex. 크루스칼 알고리즘)

## ⚡️ 주요 테크닉: Find / Union

### 1️⃣ 부모 노드(Root Node) 찾기 == 경로 압축
```java
int find(int x) {
    if (parent[x] == x) 
        return x;
    
    return parent[x] = find(parent[x]);
}
```

### 2️⃣ 연결된 Node의 집합 만들기(합치기) == 그룹화
```java
void union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x != y) 
        parent[y] = x; 
}
```
아래와 같이 수가 작은 Node를 부모 Node로 설정 ▶ 연결된 도시인지 확인할 때, 다시 업데이트 필요 X
```java
void union(int x, int y) {
    x = find(x);
    y = find(y);

    int big = Math.max(x, y);
    int small = Math.min(x, y);

    if (x != y)
        parent[big] = small; 
}
```