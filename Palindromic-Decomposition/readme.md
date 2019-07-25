
# What will I learn?
	Strings
	
# Statement
## Goal
We say a tuple of three strings (`P`, `Q`, `R`) is a palindromic decomposition of string `S`  
if `P` + `Q` + `R` = `S` and all of `P`, `Q` and `R` are palindrome.  
Note that `P`, `Q` and `R` can be the empty string `ε`.

Given string `S`, calculate the number of the palindromic decompositions of `S`.

For example, if you are given `abab`, you should answer `6` because    
- (`ε`, `a`, `bab`)
- (`ε`, `aba`, `b`)
- (`a`, `ε`, `bab`)
- (`a`, `bab`, `ε`)
- (`aba`, `ε`, `b`)
- (`aba`, `b`, `ε`) 

are the palindromic decompositions of `abab`. 

# Goal

