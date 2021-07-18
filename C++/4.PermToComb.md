### 문제: PermToComb
> next_permutation과 bool 타입 vector를 활용해서 조합만들기

```C++
vector<bool> comb(nCr의 n, 1);

for(int i = 0; i < nCr의 r; i++)
  comb[i] = 0;

do {

  for(int i = 0; i < comb.size(); i++) {
    if (comb[i] == 0) {
      ... 내부 처리 로직
    }
  }
  
} while (next_permutation(comb.begin(), comb.end()));
```
* 주의: 사전순으로 next_permutation이 진행되기 때문에, 00111 형식으로 comb 백터를 구성해야 함
