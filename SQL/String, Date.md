## 루시와 엘라 찾기
```sql
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
    FROM ANIMAL_INS
    WHERE NAME IN ("Lucy", "Ella", "Pickle", "Rogan", "Sabrina", "Mitty");
```
* IN 사용 풀이

```sql
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
    FROM ANIMAL_INS
    WHERE NAME REGEXP "^Lucy$|^Ella$|^Pickle$|^Rogan$|^Sabrina$|^Mitty$";
``
* REGEXP 사용 풀이
