## Cube
```sql
SELECT b.dname, a.job, SUM(a.sal) sal, COUNT(a.empno) emp_count 
    FROM emp a, dept b 
    WHERE a.deptno = b.deptno 
    GROUP BY CUBE(b.dname, a.job)
```

## Rollup
```sql
SELECT
position, subject, SUM(bonus)
FROM
PROFESSOR
GROUP BY ROLLUP(position,  subject)
```

## Grouping Sets
```sql
SELECT 
    customer, 
    category,
    GROUPING(customer) customer_grouping,
    GROUPING(category) category_grouping,
    SUM(sales_amount) 
FROM customer_category_sales
GROUP BY 
    GROUPING SETS(
        (customer,category),
        (customer),
        (category),
        ()
    )
ORDER BY 
    customer, 
    category;
```

## 고양이와 개는 몇 마리 있을까
```sql
SELECT ANIMAL_TYPE, COUNT(ANIMAL_TYPE) as count 
    FROM ANIMAL_INS 
    GROUP BY ANIMAL_TYPE 
    ORDER BY ANIMAL_TYPE;
```

## 동명 동물 수 찾기
```sql
SELECT NAME, COUNT(NAME) as COUNT 
    FROM ANIMAL_INS 
    WHERE NAME is not null 
    GROUP BY NAME 
    HAVING COUNT > 1 
    ORDER BY NAME;
```
* COUNT(column)은 NULL 미포함, COUNT(*)은 NULL 포함

## 입양 시각 구하기(1)
```sql
SELECT hour(DATETIME) as HOUR, count(DATETIME) as COUNT 
    FROM ANIMAL_OUTS 
    GROUP BY HOUR 
    HAVING HOUR between 9 and 19 
    ORDER BY HOUR;
```

## 입양 시각 구하기(2)
```sql
SET @ROWNUM := -1; 

WITH tempTable AS 
    (SELECT (@ROWNUM := @ROWNUM + 1) AS HOUR
     FROM ANIMAL_OUTS 
     WHERE @ROWNUM < 23)
     
SELECT HOUR, count(rt.DATETIME)
    FROM tempTable as tt
        LEFT JOIN ANIMAL_OUTS as rt
        ON tt.HOUR = hour(rt.DATETIME)
    GROUP BY HOUR
    ORDER BY HOUR;
```
