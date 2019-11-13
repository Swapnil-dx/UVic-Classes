/*The questions in this assignment are about doing soccer analytics using SQL. 
The data is in tables England, France, Germany, Italy, and Spain.
These tables contain more than 100 years of soccer game statistics.
Follow the steps below to create your tables and familizarize yourself with the data. 
Then write SQL statements to answer the questions. 
The first time you access the data you might experience a small delay while DBeaver 
loads the metadata for the tables accessed. 

Submit this file after adding your queries. 
Replace "Your query here" text with your query for each question. 
Submit one spreadsheet file with your visualizations for questions 2, 7, 9, 10, 12 
(one sheet per question named by question number, e.g. Q2, Q7, etc).  
*/

/*Create the tables.*/

create table England as select * from THOMO.ENGLAND;
create table France as select * from THOMO.FRANCE;
create table Germany as select * from THOMO.GERMANY;
create table Italy as select * from THOMO.ITALY;
create table Spain as select * from THOMO.SPAIN;

/*Familiarize yourself with the tables.*/ 
SELECT * FROM england;
SELECT * FROM germany;
SELECT * FROM france;
SELECT * FROM italy;
SELECT * FROM spain;

/*Q1 (1 pt)
Find all the games in England between seasons 1920 and 1999 such that the total goals are at least 13. 
Order by total goals descending.*/

SELECT *
FROM ENGLAND
WHERE SEASON >= 1920 AND SEASON <= 1999 AND TOTGOAL >= 13
ORDER BY TOTGOAL DESC

/*Sample result
1935-12-26	1935	Tranmere Rovers      Oldham Athletic    13-4 ...
1958-10-11	1958	Tottenham Hotspur    Everton            10-4 ...
...*/


/*Q2 (2 pt)
For each total goal result, find how many games had that result. 
Use the england table and consider only the seasons since 1980.
Order by total goal.*/ 

SELECT TOTGOAL, COUNT(TOTGOAL) AS numGames
FROM ENGLAND
WHERE SEASON >= 1980
GROUP BY TOTGOAL
ORDER BY TOTGOAL

/*Sample result
0  6085
1  14001
...*/

/*Visualize the results using a barchar.*/


/*Q3 (2 pt)
Find for each team in England in tier 1 the total number of games played since 1980. 
Report only teams with at least 300 games. 

Hint. Find the number of games each team has played as "home". 
Find the number of games each team has played as "visitor".
Then union the two and take the sum of the number of games.   */


SELECT Team, SUM(GAMES) AS GAMES
FROM	(SELECT HOME AS TEAM, COUNT(HOME) AS Games
		FROM ENGLAND
		WHERE TIER = 1 AND SEASON >= 1980
		GROUP BY HOME
		UNION ALL
		SELECT VISITOR AS Team, COUNT(VISITOR) AS Games
		FROM ENGLAND
		WHERE TIER = 1 AND SEASON >= 1980
		GROUP BY VISITOR)
GROUP BY Team
ORDER BY GAMES DESC


/*Sample result
Everton	   1451
Liverpool	   1451
...*/ 


/*Q4 (1 pt)
For each pair team1, team2 in England find the number of home-wins since 1980 of team1 versus team2.
Order the results by the number of home-wins in descending order.

Hint. After selecting the tuples needed (... WHERE tier=1 AND ...) do a GROUP BY home, visitor. 
*/

SELECT Home, Visitor,  COUNT(*) AS HomeWins
FROM ENGLAND
WHERE tier = 1 AND "result" = 'H' AND SEASON >= 1980
GROUP BY HOME, VISITOR
ORDER BY HomeWins DESC

/*Sample result
Manchester United	Tottenham Hotspur	27
Arsenal	Everton	26
...*/


/*Q5 (1 pt)
For each pair team1, team2 in England find the number of away-wins since 1980 of team1 versus team2.
Order the results by the number of away-wins in descending order.*/

SELECT Home, Visitor,  COUNT(*) AS AwayWins
FROM ENGLAND
WHERE tier = 1 AND "result" = 'A' AND SEASON >= 1980
GROUP BY HOME, VISITOR
ORDER BY AwayWins DESC

/*Sample result
Aston Villa	 Manchester United	 18
Aston Villa	 Arsenal	         17
...*/


/*Q6 (2 pt)
For each pair team1, team2 in England report the number of home-wins and away-wins 
since 1980 of team1 versus team2. 
Order the results by the number of away-wins in descending order. 

Hint. Join the results of the two previous queries. To do that you can use those 
queries as subqueries. Remove their ORDER BY clause when making them subqueries.  
Be careful on the join conditions. */

SELECT X.home, X.Visitor, X.HomeWins, Y.AwayWins
FROM 	(SELECT Home, Visitor,  COUNT(*) AS HomeWins
		FROM ENGLAND
		WHERE tier = 1 AND "result" = 'H' AND SEASON >= 1980
		GROUP BY HOME, VISITOR) X,
		(SELECT Home, Visitor,  COUNT(*) AS AwayWins
		FROM ENGLAND
		WHERE tier = 1 AND "result" = 'A' AND SEASON >= 1980
		GROUP BY HOME, VISITOR) Y
WHERE X.Home = Y.visitor AND X.visitor = Y.Home
ORDER BY Y.AwayWins DESC

/*Sample result
Manchester United	   Aston Villa	  26	18
Arsenal	           Aston Villa	  20	17
...*/

--Create a view, called Wins, with the query for the previous question. 
CREATE VIEW Wins AS 
	SELECT X.home, X.Visitor, X.HomeWins, Y.AwayWins
	FROM 	(SELECT Home, Visitor,  COUNT(*) AS HomeWins
			FROM ENGLAND
			WHERE tier = 1 AND "result" = 'H' AND SEASON >= 1980
			GROUP BY HOME, VISITOR) X,
			(SELECT Home, Visitor,  COUNT(*) AS AwayWins
			FROM ENGLAND
			WHERE tier = 1 AND "result" = 'A' AND SEASON >= 1980
			GROUP BY HOME, VISITOR) Y
	WHERE X.Home = Y.visitor AND X.visitor = Y.Home
	ORDER BY Y.AwayWins DESC

/*Q7 (2 pt)
For each pair ('Arsenal', team2), report the number of home-wins and away-wins 
of Arsenal versus team2 and the number of home-wins and away-wins of team2 versus Arsenal 
(all since 1980).
Order the results by the second number of away-wins in descending order.
Use view Wins.*/

SELECT X.Home AS team1, Y.Home AS team2, X.HomeWins AS ArsenalHwins, X.AwayWins AS ArsenalAwins , Y.HomeWins AS team2Hwins, Y.AwayWins AS team2Awins
FROM Wins X, Wins Y
WHERE X.Home = 'Arsenal' AND Y.visitor = 'Arsenal' AND X.Visitor = Y.Home
ORDER BY team2Awins DESC 

/*Sample result
Arsenal	Manchester United	16	5	19	11
Arsenal	Liverpool	14	8	20	11
...*/

/*Build two bar-charts, one visualizing the two home-wins columns, and the other visualizing the two away-wins columns.*/ 

/*Drop view Wins.*/
DROP VIEW Wins;


/*Q8 (2 pt)
Winning at home is easier than winning as visitor. 
Nevertheless, some teams have won more games as a visitor than when at home.
Find the team in Germany that has more away-wins than home-wins in total.
Print the team name, number of home-wins, and number of away-wins.*/

SELECT HOME, HomeWins, AwayWins
FROM	(SELECT Home, COUNT(*) AS HomeWins
		FROM Germany
		WHERE HGOAL > VGOAL
		GROUP BY HOME),
		(SELECT VISITOR, COUNT(*) AS AwayWins
		FROM Germany
		WHERE VGOAL > HGOAL
		GROUP BY VISITOR)
WHERE AwayWins > HomeWins AND Home = Visitor
	

/*Sample result
Wacker Burghausen	...	...*/


/*Q9 (3 pt)
One of the beliefs many people have about Italian soccer teams is that they play much more defense than offense.
Catenaccio or The Chain is a tactical system in football with a strong emphasis on defence. 
In Italian, catenaccio means "door-bolt", which implies a highly organised and effective backline defence 
focused on nullifying opponents' attacks and preventing goal-scoring opportunities. 
In this question we would like to see whether the number of goals in Italy is on average smaller than in England. 

Find the average total goals per season in England and Italy since the 1970 season. 
The results should be (season, england_avg, italy_avg) triples, ordered by season.

Hint. 
Subquery 1: Find the average total goals per season in England. 
Subquery 2: Find the average total goals per season in Italy 
   (there is no totgoal in table Italy. Take hgoal+vgoal).
Join the two subqueries on season.  */

SELECT *
FROM
	(SELECT Season, AVG(TOTGOAL) AS England_avg
	FROM ENGLAND
	WHERE SEASON >= 1970
	GROUP BY SEASON) JOIN 
	(SELECT Season, AVG(HGOAL + VGOAL) AS Italy_avg
	FROM ITALY
	WHERE SEASON >= 1970
	GROUP BY SEASON) USING(SEASON)
ORDER BY SEASON

--Build a line chart visualizing the results. What do you observe?

/*Sample result
1970	2.5290927021696255	2.1041666666666665
1971	2.592209072978304	2.0125
...*/


/*Q10 (3 pt)
Find the number of games in France and England in tier 1 for each goal difference. 
Return (goaldif, france_games, eng_games) triples, ordered by the goal difference.
Normalize the number of games returned dividing by the total number of games for the country in tier 1, 
e.g. 1.0*COUNT(*)/(select count(*) from france where tier=1)  */ 

SELECT *
FROM
	(SELECT GOALDIF, 1.0*COUNT(*)/(SELECT COUNT(*) FROM FRANCE WHERE Tier = 1) AS France_games
	FROM FRANCE
	WHERE Tier = 1
	GROUP BY GOALDIF)
	JOIN
	(SELECT GOALDIF, 1.0*COUNT(*)/(SELECT COUNT(*) FROM ENGLAND WHERE Tier = 1) AS England_games
	FROM ENGLAND
	WHERE TIER = 1
	GROUP BY GOALDIF) USING (GOALDIF)
ORDER BY GOALDIF

/*Sample result
-8	0.000114	0.000063
-7	0.000114	0.000104
...*/

/*Visualize the results using a barchart.*/


/*Q11 (2 pt)
Find all the seasons when England had higher average total goals than France. 
Consider only tier 1 for both countries.
Return (season,england_avg,france_avg) triples.
Order by season.*/

SELECT *
FROM	(SELECT Season, AVG(TOTGOAL) AS England_avg
		FROM ENGLAND
		WHERE Tier = 1
		GROUP BY SEASON) JOIN 
		(SELECT Season, AVG(HGOAL + VGOAL) AS France_avg
		FROM FRANCE
		WHERE Tier = 1
		GROUP BY SEASON) USING(SEASON)
WHERE England_avg > France_avg
ORDER BY SEASON

/*Sample result
1936	3.365800865800866	3.3041666666666667
1952	3.264069264069264	3.1437908496732025
...*/


/*Q12 (ungraded)
Propose a query for the soccer database and visualize the results. */

/*Sorry, need to study for midterm*/
