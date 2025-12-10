/**
 * Andrew's advanced queries for this assignment
 */
 
 -- GROUP 1: Top 10 Most Expensive Properties (w/ Vacancy)
 SELECT p.PID, p.Address, p.Price, p.Bed, COUNT(li.SSN) AS CurrentOccupancy, (p.Bed - COUNT(li.SSN)) AS VacantBeds
    FROM Property AS p LEFT JOIN LivesIn AS li ON p.PID = li.PID
	GROUP BY p.PID, p.Address, p.Price, p.Bed
    HAVING VacantBeds > 0
    ORDER BY p.Price DESC
    LIMIT 10;
    
-- GROUP 2: Landlords Ranked by Number of Total Tenants (w/ Tie-breakers)
SELECT l.LLID, l.Name AS LandlordName, COUNT(t.SSN) AS TotalTenants
	FROM Landlord AS l JOIN Property AS p ON l.LLID = p.LLID
		LEFT JOIN LivesIn AS li ON p.PID = li.PID
		LEFT JOIN Tenant AS t ON li.SSN = t.SSN
    GROUP BY l.LLID, l.Name
    HAVING TotalTenants >= 1
    ORDER BY TotalTenants DESC, LandlordName ASC
    limit 10;
    
-- GROUP 3: Tenants Paying Above the Average Rent-Per-Bed for Their Property Type
SELECT t.SSN, CONCAT(t.FName, ' ', t.LName) AS TenantName, p.PID, p.Address, p.Bed, p.Price, (p.Price / p.Bed) AS RentPerBed
	FROM Tenant AS t JOIN LivesIn AS li ON t.SSN = li.SSN
		JOIN Property AS p ON li.PID = p.PID
	WHERE (p.Price / p.Bed) > (SELECT AVG(p2.Price / p2.Bed)
									FROM Property AS p2 
									WHERE p2.Bed = p.Bed)
	ORDER BY RentPerBed DESC;
