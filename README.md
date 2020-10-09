# Algorithm Review

### [C++ 문법](https://github.com/PoSungKim/algorithm_review/blob/master/c%2B%2B/0.note.md)
> 복잡해보이는 C++ 문법을 이해하기 위해 정리해놓는 노트
* C++의 경우, 소소한 팁들을 통해 보다 효율적으로 문법 작성 가능
<hr>

### [SQL 문법](https://github.com/PoSungKim/algorithm_review/tree/master/SQL)
> SQL 문법에 익숙해지기 위해 정리해놓는 노트
* Table 간의 관계 연결도 중요, 문법도 중요
<hr>

### [Greedy Algorithm](https://github.com/PoSungKim/algorithm_review/blob/master/greedy/0.note.md)
> 각 스테이지마다 Local Optimum을 선택해서 Global Solution이 되도록 만드는 알고리즘

* 주의사항
  * Local Optimum이 모인다고 해서, 항상 Global Solution로 이어지지 않는다.
  * 따라서, 그리디 알고리즘의 정당성을 먼저 "확실히" 확인한 이후에, 코딩을 시작하는 것이 중요.
<hr>

### [구현 (완전 탐색과 시뮬레이션)](https://github.com/PoSungKim/algorithm_review/blob/master/%EA%B5%AC%ED%98%84/0.note.md)
> 지문에 나온 상황 혹은 가이드라인을 이해해서 머릿속에서 구현하는 알고리즘

* 주의사항
  * 충분한 이해를 기반으로 코드를 작성하지 않으면, 코드가 간결해지지 않고 매우 길어질 수 있음을 주의!

<hr>

### [DFS | BFS](https://github.com/PoSungKim/algorithm_review/blob/master/DFS%7CBFS/0.note.md)
> 스택을 사용하는 깊이 우선 탐색 (DFS), 큐를 사용하는 넓이 우선 탐색 (BFS)

* 주의사항
  * 한 Source에서 다른 모든 지점까지 한 번씩만 탐색하는 것이 DFS와 BFS!
  * 코드 조정을 통해 모든 방법으로 지점들을 탐색하는 것은 DFS와 BFS가 아니라, 완전 탐색!

<hr>

### [Binary Search](https://github.com/PoSungKim/algorithm_review/blob/master/Binary%20Search/0.note.md)
> 순차 탐색으로 풀기에는 비효율적일 때 사용하는 알고리즘

* 주의사항
  * 이진 탐색 전에 배열이 먼저 정렬되어 있어야 한다.
  * 탐색 범위의 크기를 보고, 이진 탐색이 필요한지 고민해보는 것이 현명! 

<hr>

### [Shortest Path (Dijkstra, Floyd-Warshall Algorithm)](https://github.com/PoSungKim/algorithm_review/blob/master/Shortest%20Path/0.note.md)
> 특정한 노드에서 출발해서 다른 노드로 가는 각각의 최단 경로: Dijkstra Algorithm

> 모든 지점에서 다른 모든 지점까지의 최단 경로: Floyd-Warshall Algorithm

* 주의사항
  * Dijkstra | Greedy Algorithm | 1차원 Distance Vector
  * Floyd-Warshall | Dynamic Programming | 2차원 Distance Matrix

<hr>
