
# What will I learn?

> ConditionsLoops

This puzzle makes you use nested loops and combine multiple associative arrays in order to build an efficient solution.

**External resources** `Associative arrayHash Table`

# Statement

The goal of this problem is to find a word from a dictionary to get the best score at the popular scrabble game.  
The solving of this problem relies on associative arrays and the methodology used to compute the best score.  
A good choice of the data structure is often the key to correctly solve a puzzle.

# Story

When you play scrabble on sundays with your family, the situation quickly goes out of hand.  
What's the best move ? Should you play "pool" or "loop" ?  
What will grandma do ? Here we propose a new puzzle to calculate the best word to play out of a list.


# Goal

When playing Scrabble©, each player draws 7 letters and must find a word that scores the most points using these letters.

A player doesn't necessarily have to make a 7-letter word; the word can be shorter.  
The only constraint is that the word must be made using the 7 letters which the player has drawn.

For example, with the letters  etaenhs, some possible words are: ethane, hates, sane, ant.

Your objective is to find the word that scores the most points using the available letters (1 to 7 letters).

![altText](scrabble.jpg "title")

# Rules



In Scrabble©, each letter is weighted with a score depending on how difficult it is to place that letter in a word.  
You will see below a table showing the points corresponding to each letter:
 
Letters -> Points  
`e, a, i, o, n, r, t, l, s, u` -> 	1  
`d, g` 	-> 2  
`b, c, m, p` -> 	3  
`f, h, v, w, y` -> 	4  
`k` -> 5  
`j, x` -> 	8  
`q, z` -> 	10

The word banjo earns you 3 + 1 + 1 + 8 + 1 = 14 points.

A dictionary of authorized words is provided as input for the program.  
The program must find the word in the dictionary which wins the most points for the seven given letters (a letter can only be used once).  
If two words win the same number of points, then the word which appears first in the order of the given dictionary should be chosen.

All words will only be composed of alphabetical characters in lower case. There will always be at least one possible word.
 