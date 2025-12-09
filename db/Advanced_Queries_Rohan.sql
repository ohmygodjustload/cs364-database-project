-- Rohan's advanced queries

-- GROUP 1: Find the number of tenants living in each property, but also show properties
--          who's address contains "St", and sorted by tenant
SELECT p.PID, p.Address, COUNT(l.SSN) AS NumTenants
    FROM Property p JOIN LivesIn l ON p.PID = l.PID
    WHERE p.Address LIKE '%St%'
    GROUP BY p.PID, p.Address
    ORDER BY NumTenants DESC
    LIMIT 10;

-- GROUP 2: Find the first 20 landlords with properties that have more than 2 tenants.
--          Skip the first 5 because they are hoarders probably.
SELECT ll.LLID, ll.Name, COUNT(t.SSN) AS TotalTenants
    FROM Landlord ll LEFT JOIN Property p ON ll.LLID = p.LLID
        LEFT JOIN LivesIn li ON p.PID = li.PID
        LEFT JOIN Tenant t ON li.SSN = t.SSN
    GROUP BY ll.LLID, ll.Name
    HAVING COUNT(t.SSN) > 2
    ORDER BY TotalTenants DESC;
    LIMIT 20
    OFFSET 5;

-- GROUP 3: Find tenants who have a higher budget than the average budget of all the tenants in their properties
SELECT t.FName, t.LName, t.Budget, li.PID
    FROM Tenant t JOIN LivesIn li ON t.SSN = li.SSN
    WHERE t.Budget > (SELECT AVG(t2.Budget)
                            FROM LivesIn li2 JOIN Tenant t2 ON li2.SSN = t2.SSN
                            WHERE li2.PID = li.PID)
    ORDER BY t.Budget DESC;