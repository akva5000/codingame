
# What will I learn?

	ConditionsSetsLogic


# Goal

Alice and Bob are playing a guessing game. Alice thinks of a number between 1 and 100 (both inclusive).  
Bob guesses what it is. Alice then replies "too high", "too low" or "right on".  
After repeated rounds of guessing and replying, Bob should be able to hit right on the number.

After some games, Bob suspects Alice is cheating - that she changed the number in the middle of the game,  
or just gave a false response. To collect evidence against Alice, Bob recorded the transcripts of several games.  
You are invited to help Bob to determine whether Alice cheated in each game.

An example game between Bob and Alice:

A game of 3 rounds

`┌───┬──────────┐`  
`│ B │    A     │`  
`├───┼──────────┤`  
`│ 5 │ too high │`  
`│ 1 │ too high │<-- round 2`  
`│ 2 │ right on │`  
`└───┴──────────┘`

Alice cheated in round 2

(...because numbers below 1 are not allowed in the game)