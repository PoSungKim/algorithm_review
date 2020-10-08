## 이름이 없는 동물의 아이디
```sql
SELECT ANIMAL_ID
    FROM ANIMAL_INS
    WHERE NAME is null
    ORDER BY ANIMAL_ID;
```

## 이름이 있는 동물의 아이디
```sql
SELECT ANIMAL_ID 
    FROM ANIMAL_INS 
    WHERE NAME is not null
    ORDER BY ANIMAL_ID;
```

## NULL 처리하기
```sql
SELECT ANIMAL_TYPE, IFNULL(NAME, "No name") as NAME, SEX_UPON_INTAKE
    FROM ANIMAL_INS
```
* MySql이면 IFNULL, Oracle이면 NVL
