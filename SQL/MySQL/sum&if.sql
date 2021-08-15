SELECT
          ball, 
          SUM(IF(color = 'blue', count, 0)) AS 'Total Blue'
          SUM(IF(color = 'red', count, 0))  AS 'Total Red'
 FROM     t1
 GROUP BY ball
