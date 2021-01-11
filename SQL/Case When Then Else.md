## Case When Then Else
```sql
SELECT NAME,
    CASE WHEN ANIMAL_TYPE = 'Dog' THEN 'Doggy'
         WHEN ANIMAL_TYPE = 'Cat' THEN 'Catty'
         ELSE 'others'
    END AS nickname
    FROM ANIMAL_INS;
```
