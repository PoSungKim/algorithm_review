### 문제: Two Sum
> Map 자료구조를 사용해서 합을 더 효율적으로 계산 가능

```C++
vector<int> twoSum(vector<int>& nums, int target) {
    vector<int> temp;
    for(int i = 0; i < nums.size() - 1; i++) {
        for(int j = i + 1; j < nums.size(); j++) {
            if (nums[i] + nums[j] == target) {
                temp = {i, j};
                break;
            }
        }
    }
    return temp;
}
```

* Time Complexity = O(n^2)  // 592 ms
* Space Complexity = O(1)   // 9.4 MB 

```C++
vector<int> twoSum(vector<int>& nums, int target) {
    unordered_map<int, int> M;
    for(int i = 0; i < nums.size(); i++)
        M[nums[i]] = i;

    for(int i = 0; i < nums.size(); i++) {
        if (M[target - nums[i]] && M[target - nums[i]] != i) 
            return {i, M[target - nums[i]]};
    }
    return {0, 0};
}
```
 
* Time Complexity = O(n)  // 20 ms
* Space Complexity = O(n) // 10.8 MB

```C++
vector<int> twoSum(vector<int>& nums, int target) {
    unordered_map<int, int> M;

    for(int i = 0; i < nums.size(); i++) {
        int complement = target - nums[i];
        if (M.find(complement) != M.end()) 
            return {M[complement], i};
            
        M[nums[i]] = i;
    }

    return {};
}
```

* Time Complexity = O(n)  // 12 ms
* Space Complexity = O(n) // 10 MB
