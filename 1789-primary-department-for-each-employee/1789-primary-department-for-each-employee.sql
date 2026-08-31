WITH CountTable AS (
    SELECT
        employee_id,
        department_id,
        primary_flag,
        COUNT(*) OVER (
            PARTITION BY employee_id
        ) AS cnt
    FROM Employee
)

SELECT DISTINCT
    employee_id,
    SUM(
        CASE
            WHEN cnt = 1 THEN department_id
            WHEN cnt > 1 AND primary_flag = 'Y' THEN department_id
            ELSE 0
        END
    ) OVER (
        PARTITION BY employee_id
    ) AS department_id
FROM CountTable;