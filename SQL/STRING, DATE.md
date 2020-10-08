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
```
* REGEXP 사용 풀이

## 이름에 el이 들어가는 동물 찾기
```sql
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_INS
    WHERE NAME like "%el%" and ANIMAL_TYPE = "Dog"
    ORDER BY NAME;
```
* LIKE 사용 풀이

```sql
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_INS
    WHERE NAME REGEXP "el" and ANIMAL_TYPE = "Dog"
    ORDER BY NAME;
```
* REGEXP 사용 풀이

## 중성화 여부 파악하기

```sql
SELECT ANIMAL_ID, NAME
    FROM ANIMAL_INS
    WHERE NAME REGEXP "el" and ANIMAL_TYPE = "Dog"
    ORDER BY NAME;
```

## 중성화 여부 파악하기

```sql
SELECT  ANIMAL_ID, 
        NAME, 
        IF(SEX_UPON_INTAKE REGEXP "^Neutered|^Spayed", 'O', 'X') as "중성화"
    FROM ANIMAL_INS;
```
