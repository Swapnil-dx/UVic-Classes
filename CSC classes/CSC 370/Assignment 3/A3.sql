--EXERCISE 1
--Q1
CREATE TABLE Classes(class VARCHAR(30) PRIMARY KEY, shiptype CHAR(2), country VARCHAR(20), numGuns INT, bore INT, displacement INT);
CREATE TABLE Ships(name VARCHAR(30) PRIMARY KEY, class VARCHAR(30), launched INT);
CREATE TABLE Battles(name VARCHAR(30) PRIMARY KEY, date_fought DATE);
CREATE TABLE Outcomes(ship VARCHAR(30), battle VARCHAR(30), shipResult VARCHAR(7), PRIMARY KEY(ship, battle));

--Q2
INSERT INTO Classes VALUES('Bismarck','bb','Germany',8,15,42000);
INSERT INTO Classes VALUES('Kongo','bc','Japan',8,14,32000);
INSERT INTO Classes VALUES('North Carolina','bb','USA',9,16,37000);
INSERT INTO Classes VALUES('Renown','bc','Gt. Britain',6,15,32000);
INSERT INTO Classes VALUES('Revenge','bb','Gt. Britain',8,15,29000);
INSERT INTO Classes VALUES('Tennessee','bb','USA',12,14,32000);
INSERT INTO Classes VALUES('Yamato','bb','Japan',9,18,65000);

INSERT INTO Ships VALUES ('California','Tennessee',1921);
INSERT INTO Ships VALUES ('Haruna','Kongo',1915);
INSERT INTO Ships VALUES ('Hiei','Kongo',1914);
INSERT INTO Ships VALUES ('Iowa','Iowa',1943);
INSERT INTO Ships VALUES ('Kirishima','Kongo',1915);
INSERT INTO Ships VALUES ('Kongo','Kongo',1913);
INSERT INTO Ships VALUES ('Missouri','Iowa',1944);
INSERT INTO Ships VALUES ('Musashi','Yamato',1942);
INSERT INTO Ships VALUES ('New Jersey','Iowa',1943);
INSERT INTO Ships VALUES ('North Carolina','North Carolina',1941);
INSERT INTO Ships VALUES ('Ramillies','Revenge',1917);
INSERT INTO Ships VALUES ('Renown','Renown',1916);
INSERT INTO Ships VALUES ('Repulse','Renown',1916);
INSERT INTO Ships VALUES ('Resolution','Revenge',1916);
INSERT INTO Ships VALUES ('Revenge','Revenge',1916);
INSERT INTO Ships VALUES ('Royal Oak','Revenge',1916);
INSERT INTO Ships VALUES ('Royal Sovereign','Revenge',1916);
INSERT INTO Ships VALUES ('Tennessee','Tennessee',1920);
INSERT INTO Ships VALUES ('Washington','North Carolina',1941);
INSERT INTO Ships VALUES ('Wisconsin','Iowa',1944);
INSERT INTO Ships VALUES ('Yamato','Yamato',1941);

INSERT INTO Battles VALUES ('North Atlantic','27-May-1941');
INSERT INTO Battles VALUES ('Guadalcanal','15-Nov-1942');
INSERT INTO Battles VALUES ('North Cape','26-Dec-1943');
INSERT INTO Battles VALUES ('Surigao Strait','25-Oct-1944');

INSERT INTO Outcomes VALUES ('Bismarck','North Atlantic', 'sunk');
INSERT INTO Outcomes VALUES ('California','Surigao Strait', 'ok');
INSERT INTO Outcomes VALUES ('Duke of York','North Cape', 'ok');
INSERT INTO Outcomes VALUES ('Fuso','Surigao Strait', 'sunk');
INSERT INTO Outcomes VALUES ('Hood','North Atlantic', 'sunk');
INSERT INTO Outcomes VALUES ('King George V','North Atlantic', 'ok');
INSERT INTO Outcomes VALUES ('Kirishima','Guadalcanal', 'sunk');
INSERT INTO Outcomes VALUES ('Prince of Wales','North Atlantic', 'damaged');
INSERT INTO Outcomes VALUES ('Rodney','North Atlantic', 'ok');
INSERT INTO Outcomes VALUES ('Scharnhorst','North Cape', 'sunk');
INSERT INTO Outcomes VALUES ('South Dakota','Guadalcanal', 'ok');
INSERT INTO Outcomes VALUES ('West Virginia','Surigao Strait', 'ok');
INSERT INTO Outcomes VALUES ('Yamashiro','Surigao Strait', 'sunk');

--EXERCISE 2

--Q1

SELECT name, class, launched
FROM Ships JOIN Classes USING(class)
WHERE displacement > 35000 AND launched >= 1921

--Q2
SELECT ship, displacement, numGuns
FROM Classes RIGHT OUTER JOIN 
	(SELECT ship, class
	FROM Outcomes LEFT OUTER JOIN Ships
	ON outcomes.ship = Ships.name 
	WHERE outcomes.battle = 'Guadalcanal')
	USING (class)
	
--Q3
SELECT name AS Ship
FROM Ships
UNION
SELECT Ship
FROM Outcomes 

--Q4
SELECT Country
FROM Classes X JOIN Classes Y USING(country)
WHERE X.shiptype = 'bc' AND Y.shiptype = 'bb' 

--Q5
CREATE VIEW OutcomesWithDate AS
	SELECT Outcomes.ship, Outcomes.battle, Outcomes.shipResult, Battles.date_fought
	FROM Outcomes JOIN Battles ON Outcomes.battle=Battles.name;

SELECT ship
FROM OutcomesWithDate X JOIN OutcomesWithDate Y USING(ship)
WHERE X.shipResult = 'damaged' AND Y.date_fought > X.date_fought

DROP VIEW OutcomesWithDate
--Q6
SELECT country
FROM Classes
WHERE numGuns = (SELECT MAX(numGuns)
				 FROM Classes)

--Q7
SELECT name
FROM Ships JOIN Classes X USING(class)
WHERE numGuns = (SELECT MAX(numGuns)
				 FROM Classes Y
				 WHERE Y.bore = X.bore)

--Q8

CREATE VIEW ClassesWith3 AS
	SELECT ships.class, COUNT(ships.class) AS numShips
	FROM Ships LEFT JOIN Classes ON ships.class = Classes.class
	GROUP BY ships.class
	HAVING COUNT(ships.class) >= 3

CREATE VIEW NumSunkShips AS
	SELECT class, COUNT(*) AS SunkShips
	FROM Ships JOIN Outcomes ON Outcomes.ship = Ships.name
	WHERE Outcomes.shipResult = 'sunk'
	GROUP BY class;

SELECT *
FROM ClassesWith3;

SELECT class, SunkShips
FROM NumSunkShips RIGHT OUTER JOIN ClassesWith3 USING(class);

DROP VIEW ClassesWith3;
DROP VIEW NumSunkShips;

--EXERCISE 2

--Q1
INSERT INTO Classes VALUES ('Vittorio Veneto', 'bb', 'Italy', NULL, 15, 41000);

INSERT INTO Ships VALUES ('Vittorio Veneto', 'Vittorio Veneto', 1940);
INSERT INTO Ships VALUES ('Italia', 'Vittorio Veneto', 1940);
INSERT INTO Ships VALUES ('Roma', 'Vittorio Veneto', 1942);

--Q2

DELETE FROM Classes WHERE class IN (
SELECT class
FROM Classes RIGHT OUTER JOIN Ships USING(class)
GROUP BY class
HAVING COUNT(*) < 3
);

--Q3

UPDATE Classes
SET bore = bore*2.5, displacement = displacement*1.1

--EXERCISE 3

--Q1

CREATE TABLE Exceptions(
	row_id ROWID,
	owner VARCHAR2(30),
	table_name VARCHAR2(30),
	CONSTRAINT VARCHAR2(30)
);

ALTER TABLE Ships ADD CONSTRAINT ship_to_classes_fk
FOREIGN KEY(class) REFERENCES Classes(class)
	EXCEPTIONS INTO Exceptions;


SELECT Ships.*, CONSTRAINT
FROM Ships, Exceptions
WHERE Ships.rowid = Exceptions.row_id

DELETE FROM Ships
WHERE class IN(
	SELECT class
	FROM Ships, Exceptions
	WHERE Ships.rowid = Exceptions.row_id
);


ALTER TABLE Ships ADD CONSTRAINT ship_to_classes_fk
FOREIGN KEY(class) REFERENCES Classes(class) ON DELETE CASCADE

--Q2

DELETE FROM Exceptions;

ALTER TABLE Outcomes ADD CONSTRAINT outcomes_to_battle_fk
FOREIGN KEY(battle) REFERENCES Battles(name)
	EXCEPTIONS INTO Exceptions

--Q3
	
ALTER TABLE Outcomes ADD CONSTRAINT outcomes_to_Ships_fk
FOREIGN KEY(ship) REFERENCES Ships(name)
	EXCEPTIONS INTO Exceptions
	
SELECT Outcomes.*, CONSTRAINT
FROM Outcomes, Exceptions
WHERE Outcomes.rowid = Exceptions.row_id

DELETE FROM Outcomes
WHERE ship IN(
	SELECT ship
	FROM Outcomes, Exceptions
	WHERE Outcomes.rowid = Exceptions.row_id
);

ALTER TABLE Outcomes ADD CONSTRAINT outcomes_to_Ships_fk
FOREIGN KEY(ship) REFERENCES Ships(name) 

--Q4

DELETE FROM Exceptions;

ALTER TABLE Classes ADD CONSTRAINT no_guns_16in
CHECK(bore <= 16)
EXCEPTIONS INTO Exceptions

DELETE FROM Classes
WHERE class IN(
	SELECT class
	FROM Classes, Exceptions
	WHERE Classes.rowid = Exceptions.row_id
);

ALTER TABLE Classes ADD CONSTRAINT no_guns_16in
CHECK(bore <= 16);

--Q5

ALTER TABLE Classes ADD CONSTRAINT q5
CHECK(numGuns <= 9 OR bore <= 14)

--Q6

CREATE OR REPLACE VIEW OutcomesView AS
	SELECT ship, battle, shipResult
	FROM Outcomes O
	WHERE NOT EXISTS (
	SELECT *
	FROM Ships S, Battles B
	WHERE S.name=O.ship AND O.battle=B.name AND
	S.launched > TO_NUMBER(TO_CHAR(B.date_fought, 'yyyy'))
	)
WITH CHECK OPTION;

DROP VIEW OutcomesView;

--Q7


CREATE OR REPLACE VIEW ShipsView AS
	SELECT name, class, launched
	FROM Ships S
	WHERE NOT EXISTS (
	SELECT *
	FROM Ships X
	WHERE X.class=S.class AND X.name = X.class AND
	X.launched > S.launched
	)
WITH CHECK OPTION;

DROP VIEW ShipsView;

--Q8

CREATE OR REPLACE VIEW OutcomesView AS
	SELECT ship, battle, shipResult
	FROM Outcomes O
	WHERE NOT EXISTS (
	SELECT *
	FROM Outcomes X, Battles B, Battles B2
	WHERE X.ship = O.ship AND X.battle = B.name AND X.shipResult = 'sunk' AND B2.name = O.battle AND
	B2.date_fought > B.date_fought
	)
WITH CHECK OPTION;

DROP VIEW OutcomesView;


