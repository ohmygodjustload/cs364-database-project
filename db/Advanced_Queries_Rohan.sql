-- Rohan's advanced queries

SELECT FName, LName, Budget, Email, PID
    FROM Tenant t JOIN LivesIn l ON t.SSN = l.SSN
    WHERE Budget > 1000
    ORDER BY Budget DESC
    LIMIT 10;