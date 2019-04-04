					CGAME2.0 a.k.a Circle Game 2


==================
 Game Description
==================

Is a game based on logic and strategy for two. The goal is simple, just collect your buttons! 

The field is made of 7x7 (49) buttons (buttons):
	- 10 blue
	- 10 pink
	- 29 black

The players will take turn after each other and they can choose one button at a time. The first player can choose first only from the black ones and then the following buttons that are avaible are only the last one's neighboors. 

One-by-one the players take turns.

× Who collects all the 10 buttons first is the winner of the game. 
× If there are no more possible steps then the player who has the most buttons wins.
× If both of the players collected the same amount of buttons and there are no more possible steps then indeed it is a draw.


===============
 Point system
===============

Basic:
- game	= 1 point
- win	= 5 point
- draw	= 3 point

Achievements
- wins: 5, 10, 15, 20	= 7 points 
- draws: 1, 7, 11, 30	= 7 points 


=================
 How to start it
=================

From terminal:
- mvn install
- java -jar target/CCGame-1.0-jar-with-dependencies.jar 


=================
 Additional info
=================

Game's repo: www.github.com/kisfiu/CGame2.0
Made by Kristóf Kovács (kisfiu)

Origin: https://arato.inf.unideb.hu/jeszenszky.peter/download/mestint/jatekok.pdf
