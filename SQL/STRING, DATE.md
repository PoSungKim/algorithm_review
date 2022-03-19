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
# Oracle 
select animal_id, name
from ANIMAL_INS
where 1=1
    and regexp_like(name, '.*el.*', 'i')
    and animal_type = 'Dog'
order by name;
```
* REGEXP 사용 풀이

## 중성화 여부 파악하기

```sql
SELECT  ANIMAL_ID, 
        NAME, 
        IF(SEX_UPON_INTAKE REGEXP "^Neutered|^Spayed", 'O', 'X') as "중성화"
    FROM ANIMAL_INS;
```
* IF문 사용 풀이

```sql
# Oracle
SELECT
    animal_id,
    name,
    case when regexp_like(SEX_UPON_INTAKE, 'Neutered|Spayed') then 'O'
         else 'X' 
    end 중성화
from 
    ANIMAL_INS
order by
    animal_id
```

## 오랜 기간 보호한 동물(2)

```sql
SELECT ANIMAL_INS.ANIMAL_ID, ANIMAL_INS.NAME
    FROM ANIMAL_OUTS INNER JOIN ANIMAL_INS
        ON ANIMAL_OUTS.ANIMAL_ID = ANIMAL_INS.ANIMAL_ID
    ORDER BY ANIMAL_OUTS.DATETIME - ANIMAL_INS.DATETIME desc
    LIMIT 2;
```

```sql
# Oracle

select
    a.animal_id, a.name
from
    ANIMAL_OUTS a inner join ANIMAL_INS b on a.animal_id = b.animal_id
order by 
    a.datetime - b.datetime desc
fetch first 2 rows only
```
* ORDER BY에서도 연산이 가능함

## DATETIME에서 DATE로 형 변환
```sql
SELECT ANIMAL_ID, NAME, substring(DATETIME, 1, 10) as 날짜
    FROM ANIMAL_INS;
```
* substring 사용 풀이

```sql
SELECT ANIMAL_ID, NAME, date_format(DATETIME, '%Y-%m-%d') as 날짜
    FROM ANIMAL_INS;
```
* date_format() 사용 풀이
    * date_format(DATETIME, concat('%Y-%m-%d ','%H:%i:%s')) as 날짜 // format 단축키 숙지 필요! 
    * 특히, 분 단위 단축키 --> %i
