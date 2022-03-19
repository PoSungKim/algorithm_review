## 고양이와 개는 몇 마리 있을까
```sql
SELECT
animal_type "ANIMAL_TYPE",
COUNT(1) "count"
FROM ANIMAL_INS
group by animal_type
order by animal_type
```

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
SELECT
    animal_type "ANIMAL_TYPE",
    COUNT(1) "count"
FROM 
    ANIMAL_INS
group by 
    animal_type
order by 
    animal_type
```

## 동명 동물 수 찾기
```sql
select 
    name,
    count(1) "count"
from
    ANIMAL_INS
where 
    name is not null
group by
    name
having 
    count(1) >= 2
order by
    name;
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

```sql
select 
    TO_CHAR(datetime, 'HH24') hour,
    count(1) count
from 
    ANIMAL_OUTS
where
    TO_CHAR(datetime, 'HH24') between 9 and 19
group by
    TO_CHAR(datetime, 'HH24')
order by
    TO_CHAR(datetime, 'HH24')
;
```

```sql
select 
    hour,
    count(1) count
from 
(
    select 
        TO_CHAR(datetime, 'HH24') hour
    from
        ANIMAL_OUTS
)
where
    hour between 9 and 19
group by
    hour
order by
    hour
;
```

## 입양 시각 구하기(2)
```sql
with 
    a as (
        select level - 1 hour
        from dual
        connect by level <= 24),
    b as (
        select to_char(datetime, 'HH24') hour, count(1) count
        from ANIMAL_OUTS 
        group by to_char(datetime, 'HH24')
    )

select 
    a.hour, nvl(count, 0) count
from 
    a left join b on (a.hour = to_number(b.hour))
order by
    hour
;
```

```sql
with 
    a as (
        select level - 1 hour
        from dual
        connect by level <= 24),
    b as (
        select to_char(datetime, 'HH24') hour, count(1) count
        from ANIMAL_OUTS 
        group by to_char(datetime, 'HH24')
    )

select 
    a.hour, decode(count, null, 0, count) count
from 
    a left join b on (a.hour = to_number(b.hour))
order by
    hour
;
```
