-- List landlords with three or more availavle properties
SELECT l.LLID, l.Name, l.Email, COUNT(p.PID) AS AvailableProperties
	FROM Landlord AS l JOIN Property AS p ON l.LLID = p.LLID
					   LEFT JOIN LivesIn AS li ON p.PID = li.PID
	WHERE li.PID IS NULL   -- property has no tenant living in it
	GROUP BY l.LLID, l.Name, l.Email
	HAVING COUNT(p.PID) > 3;

-- List properties whose rent is higher that the landlords average
SELECT p.PID, p.Address, p.Price, l.LLID, l.Name AS LandlordName
	FROM Property AS p JOIN Landlord AS l ON p.LLID = l.LLID
	WHERE p.Price > (SELECT AVG(p2.Price)
				FROM Property AS p2
				WHERE p2.LLID = p.LLID);


-- for each landlord, show how many properties they own for each bed/bath combination
SELECT l.LID AS LandlordID, l.Name AS LandlordName, p.Beds, p.Baths, COUNT(*) AS PropertyCount
	FROM Landlord AS l JOIN Property AS p ON l.LID = p.LID
	GROUP BY l.LID, l.Name, p.Beds, p.Baths
	ORDER BY l.LID, PropertyCount DESC;