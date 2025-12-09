-- List landlords with three or more availavle properties
SELECT l.LLID, l.Name, l.Email, COUNT(p.PID) AS AvailableProperties
	FROM Landlord AS l JOIN Property AS p ON l.LLID = p.LLID
					   LEFT JOIN LivesIn AS li ON p.PID = li.PID
	WHERE li.PID IS NULL   -- property has no tenant living in it
	GROUP BY l.LLID, l.Name, l.Email
	HAVING COUNT(p.PID) > 3;

-- List the number of tenants each landlord has ordered by how many tenants
SELECT l.LLID, l.Name AS LandlordName, COUNT(t.SSN) AS NumTenants
	FROM Landlord AS l JOIN Property AS p ON l.LLID = p.LLID
			   JOIN LivesIn AS li ON p.PID = li.PID
                           JOIN Tenant AS t ON li.SSN = t.SSN
	GROUP BY l.LLID, l.Name
    	ORDER BY NumTenants DESC;

-- List properties whose rent is higher that the landlords average
SELECT p.PID, p.Address, p.Price, l.LLID, l.Name AS LandlordName
	FROM Property AS p JOIN Landlord AS l ON p.LLID = l.LLID
	WHERE p.Price > (SELECT AVG(p2.Price)
				FROM Property AS p2
				WHERE p2.LLID = p.LLID);
