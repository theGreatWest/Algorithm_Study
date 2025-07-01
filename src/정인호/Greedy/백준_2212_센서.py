# N: 센서의 개수
# K: 집중국의 개수
# N_elemnet: 각 센서들의 좌표
# N_diff: 각 센서들 좌표간의 거리 차이
# result: 최종 결과

N_diff = []
result = 0
N = int(input())
K = int(input())
N_element = list(map(int, input().split()))

#만약 K>=N 이면 0 출력
if K >= N:
    print(0)
else: 
#K_element를 오름차순 정렬
    N_element.sort()

#각 집중국들 간의 거리 차이를 요소로 갖는 리스트 생성
    for i in range(0,N-1):
        diff = N_element[i+1] - N_element[i]
        N_diff.append(diff)

#K_diff를 내림차순 정렬
    N_diff.sort(reverse=True)

#각 집중국 간의 수신 길이의 최소합 계산
#N_diff의 K-1번째부터의 요소의 합을 구하면 정답
    result = sum(N_diff[K-1:])

#최종 결과 출력
    print(result)