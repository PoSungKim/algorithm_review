SELECT 
    select_list
FROM
    table_name
LIMIT [offset,] row_count;


#############################

SELECT select_list
FROM table_name
ORDER BY sort_expression
LIMIT n-1, 1;

#############################

SELECT 
    customerName, 
    creditLimit
FROM
    customers
ORDER BY 
    creditLimit DESC    
LIMIT 1,1;
