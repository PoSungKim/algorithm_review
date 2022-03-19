## 최댓값 구하기
```mysql
SELECT MAX(DATETIME) "시간"
  FROM ANIMAL_INS;
```

## 최솟값 구하기
```sql
SELECT MIN(DATETIME) 
  FROM ANIMAL_INS;
```

## 동물 수 구하기
```sql
SELECT COUNT(*) 
  FROM ANIMAL_INS;
```

## 중복 제거하기
```sql
SELECT COUNT(A.NAME) 
  FROM (
    SELECT DISTINCT NAME 
    FROM ANIMAL_INS
    ) AS A;
```
```sql
SELECT COUNT(DISTINCT NAME) 
  FROM ANIMAL_INS WHERE NAME is not null; 
```
* WHERE 절로 좀 더 복잡한 조건들을 넣을 수 있음
```sql
SELECT COUNT(DISTINCT NAME) 
  FROM ANIMAL_INS; 
```
* DISTINCT는 NULL 포함, COUNT(DISTINCT) NULL 미포함
