## FETCH FIRST 
```oracle
  SELECT 
      * 
  FROM 
      table_name
  ORDER BY
      attr
  FETCH FIRST N ROWS ONLY;
```
* 출력하는 행의 숫자 N

## OFFSET + FETCH NEXT 
```orcacle
    SELECT 
        *
    FROM
        table_name
    ORDER BY
        attr
    OFFSET M ROWS
    FETCH NEXT N ROWS ONLY;
```
* 출력하는 행의 숫자 N, 시작 위치 M + 1
