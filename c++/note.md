### C++ 문법
> 복잡해보이는 C++ 문법을 이해하기 위해 정리해놓는 노트

* C++의 경우, 소소한 팁들을 통해 보다 효율적으로 문법 작성 가능

<hr>

### Vector 

* 선언문

```C++
vector<int> v = vector<int>({1,2,3,4,5});
vector<int> v = vector<int>{1,2,3,4,5};
vector<int> v = {1,2,3,4,5};
vector<int> v({1,2,3,4,5});
vector<int> v{1,2,3,4,5};
```
```C++
vector<int> v1{1,2,3,4,5}; 
deque<int> d1{1,2,3,4,5};
set<int> s1{1,2,3,4,5};

vector<int> v2(v1.begin(), v1.end()); //d1, s1 모두 가능
vector<int> v2 = v1 // v1만 가능
```

* 리턴문
```C++
vector<string> solution() {

return vector<string>({"this is possible"});
return vector<string>{"this is possible"};
return {"this is possible"};
}
```

<hr>

### Map

* 선언문

```C++
map<int, int> test({
    {1,2}, {2,3},{3,4},
});
map<int, int> test{
    {1,2}, {2,3},{3,4},
};
```

<hr>

### Split
* Python의 split 기능

```C++
#include <sstream>

vector<string> split(string line, char delimiter) {
    vector<string> internalContainer; 
    stringstream ss(line); 
    string temp;
    
    while(getline(ss, temp, delimiter)) // ss에서 delimiter까지 읽고 temp에 저장
        internalContainer.push_back(temp);
        
    return internalContainer;
}
```
