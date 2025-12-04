/*show tables;
describe Property;
describe Tenant;
describe Landlord;
select count(*) from Property;
select count(*) from Tenant;
select count(*) from Landlord;
select * from Property;
select * from Tenant;
select * from Landlord;

select * from LivesIn;
insert into LivesIn
	values ('102-21-2769', 1);
delete from LivesIn
	where PID=1;
	
INSERT INTO LeasesFrom (SSN, PID, RentAgreement)
SELECT SSN, PID, 'Standard Lease'
FROM LivesIn;

SELECT * FROM LeasesFrom;

*/

-- Derive availability of each apartment
SELECT p.PID, p.Bed, 
	COUNT(li.SSN) AS Occupancy, 
	(COUNT(li.SSN) < p.Bed) AS Available
    FROM Property p
    LEFT JOIN LivesIn li ON p.PID = li.PID
    GROUP BY p.PID;