/**
 * Andrew's advanced queries for this assignment
 */
 
 -- Top 10 Most Expensive Properties (w/ Vacancy)
 SELECT 
	p.PID,
    p.Address,
    p.Price,
    p.Bed,
	COUNT(li.SSN) AS CurrentOccupancy,
    (p.Bed - COUNT(li.SSN)) AS VacantBeds
    FROM Property p
		LEFT JOIN LivesIn li ON p.PID = li.PID
	GROUP BY p.PID, p.Address, p.Price, p.Bed
    HAVING VacantBeds > 0
    ORDER BY p.Price DESC
    LIMIT 10;
    
-- Landlords Ranked by Number of Total Tenants (w/ Tie-breakers)
SELECT 
	l.LLID,
    l.Name AS LandlordName,
    COUNT(t.SSN) AS TotalTenants
    FROM Landlord l
		JOIN Property p ON l.LLID = p.LLID
		LEFT JOIN LivesIn li ON p.PID = li.PID
		LEFT JOIN Tenant t ON li.SSN = t.SSN
    GROUP BY l.LLID, l.Name
    HAVING TotalTenants >= 1
    ORDER BY TotalTenants DESC, LandlordName ASC
    limit 10;
    
-- Tenants Paying Above the Average Rent-Per-Bed for Their Property Type
