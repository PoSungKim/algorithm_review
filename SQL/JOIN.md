## 없어진 기록 찾기
```sql
# MYSQL
SELECT ANIMAL_OUTS.ANIMAL_ID, ANIMAL_OUTS.NAME
    FROM ANIMAL_INS RIGHT OUTER JOIN  ANIMAL_OUTS
        ON ANIMAL_OUTS.ANIMAL_ID = ANIMAL_INS.ANIMAL_ID
    WHERE ANIMAL_INS.ANIMAL_ID is null;
```

```sql
# MYSQL
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_OUTS
    WHERE ANIMAL_ID not in 
        (SELECT ANIMAL_ID
            FROM ANIMAL_INS);
```

```sql
# MYSQL
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_OUTS
    WHERE not exists  
        (SELECT ANIMAL_ID
            FROM ANIMAL_INS
            WHERE ANIMAL_OUTS.ANIMAL_ID = ANIMAL_INS.ANIMAL_ID);
```

```sql
# Oracle
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_OUTS
MINUS
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_INS
```

## 있었는데요 없었습니다
```sql
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_INS
    WHERE exists (SELECT * 
                  FROM ANIMAL_OUTS 
                  WHERE ANIMAL_OUTS.ANIMAL_ID = ANIMAL_INS.ANIMAL_ID
                    AND ANIMAL_OUTS.DATETIME < ANIMAL_INS.DATETIME)
    ORDER BY DATETIME
```

```sql
SELECT a.ANIMAL_ID, a.NAME
    FROM ANIMAL_INS as a JOIN ANIMAL_OUTS as b
        ON a.ANIMAL_ID = b.ANIMAL_ID
    WHERE a.DATETIME > b.DATETIME
    ORDER BY a.DATETIME;
```

```sql
SELECT a.ANIMAL_ID, a.NAME
    FROM ANIMAL_INS as a, ANIMAL_OUTS as b
    WHERE a.DATETIME > b.DATETIME
        AND a.ANIMAL_ID = b.ANIMAL_ID
    ORDER BY a.DATETIME;
```

## 오랜 기간 보호한 동물(1)
```sql
SELECT NAME, DATETIME
    FROM ANIMAL_INS
    WHERE ANIMAL_ID not in 
        (SELECT ANIMAL_ID
            FROM ANIMAL_OUTS)
    ORDER BY DATETIME
    LIMIT 3;
```

## 보호소에서 중성화한 동물
```sql
SELECT a.ANIMAL_ID, a.ANIMAL_TYPE, a.NAME
    FROM ANIMAL_INS as a INNER JOIN ANIMAL_OUTS as b
        ON a.ANIMAL_ID = b.ANIMAL_ID
    WHERE 
    (a.SEX_UPON_INTAKE = "Intact Female" and (b.SEX_UPON_OUTCOME = "Spayed Female" or b.SEX_UPON_OUTCOME = "Neutered Female"))
    or (a.SEX_UPON_INTAKE = "Intact Male" and (b.SEX_UPON_OUTCOME = "Spayed Male" or b.SEX_UPON_OUTCOME = "Neutered Male"));
```
* 문자열 그대로 모두 기입
```sql
SELECT a.ANIMAL_ID, a.ANIMAL_TYPE, a.NAME
    FROM ANIMAL_INS as a INNER JOIN ANIMAL_OUTS as b
        ON a.ANIMAL_ID = b.ANIMAL_ID
    WHERE a.SEX_UPON_INTAKE like 'Intact%'
        AND (b.SEX_UPON_OUTCOME like 'Spayed%' or b.SEX_UPON_OUTCOME like 'Neutered%');
```
* like 와 와일드카드 '%' 사용
* '%' 가변 길이, '_' 한 자리

```sql
SELECT a.ANIMAL_ID, a.ANIMAL_TYPE, a.NAME
    FROM ANIMAL_INS as a INNER JOIN ANIMAL_OUTS as b
        ON a.ANIMAL_ID = b.ANIMAL_ID
    WHERE a.SEX_UPON_INTAKE like 'Intact%'
        AND (b.SEX_UPON_OUTCOME like 'Spayed%' or b.SEX_UPON_OUTCOME like 'Neutered%');
```
* 정규표현식 REGEXP 
