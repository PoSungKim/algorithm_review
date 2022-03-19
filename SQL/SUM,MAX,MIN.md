## 최댓값 구하기
```mysql
SELECT MAX(DATETIME) "시간"
  FROM ANIMAL_INS;
```

## 최솟값 구하기
```sql
SELECT MINdatetime) as "시간"
  FROM ANIMAL_INS;
```

## 동물 수 구하기
```sql
SELECT COUNT(1) "count
  FROM ANIMAL_INS;
```

## 중복 제거하기
```sql
SELECT COUNT(DISTINCT NAME) 
  FROM ANIMAL_INS; 
```
* DISTINCT는 NULL 포함, COUNT(DISTINCT) NULL 미포함
