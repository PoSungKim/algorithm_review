### 문제: Union-Find

```C++
#include <iostream>
#include <vector>
using namespace std;

vector<int> P(100001);

int Find(int curNode)
{
    if (P[curNode] == curNode)
        return curNode;
    else
        return P[curNode] = Find(P[curNode]); // Find(P[curNode])만 사용가능, 이후에 최종노드를 다시 정리해줘야함
}

void Union(int node1, int node2)
{
    node1 = Find(node1);
    node2 = Find(node2);
    if (node1 < node2)
        P[node2] = node1;
    else
        P[node1] = node2;
}

int main(void)
{
    int V, E;
    cin >> V >> E;
    for (int i = 1; i <= V; i++)
        P[i] = i;

    for (int i = 0; i < E; i++)
    {
        int node1, node2;
        cin >> node1 >> node2;
        Union(node1, node2);
    }

    for (int i = 1; i <= V; i++)
        cout << Find(i) << ' ';
    cout << '\n';

    for (int i = 1; i <= V; i++)
        cout << P[i] << " "; // Find(P[curNode])만 사용했으면, 값이 달랐을 것
    cout << '\n';

    return 0;
}
```

* 서로소 집합을 만들어주는 Union-Find 알고리즘
  * 트리 구조에서는 낮은 번호의 노드가 부모, 높은 번호의 노드가 자식이 되기 때문에, 각각의 서로소 집합의 최종 부모 노드는 해당 집합에서 가장 낮은 번호의 노드가 됌
  * 이에 따라, 같은 집합에 속하면, 같은 최종 노드를 가리키고 있게 만들 수 있음
