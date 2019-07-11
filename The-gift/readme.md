# What will I learn?

	Greedy algorithms

In this puzzle, you have to manipulate large lists and use simple math concepts (e.g. min, max, average) to optimize the value of a variable.

**External resources** `Greedy algorithm`

# Statement
Given a list of persons and their budgets, and the price of the present they wish to buy, you have to find the amount each person gives.  
You have to find this optimal distribution that minimize the highest contribution.


# Story

The TARDIS time-and-space-ship lands on a strange planet.  
The local aliens, the Oods, want to make a present for a fellow Ood, but they can't seem a way to figure out how to manage everyone's budget.  
Help the Doctor find a system to decide the contribution of each Ood.

# Goal
The Oods want to offer a present to one of them.  
The thing is, they all have different budgets to invest in this present.  
Of course, their unique wish is to find the fairest method that will determine the maximum budget that each Ood can afford.  
The Oods have been discussing this issue for days, and up until now, they have not managed to find a totally satisfactory solution.

The Doctor decides to give a helping hand by creating a program. His idea is to check if the Oods have enough money to buy the present,  
and if so, to calculate how much each Ood should pay, according to their respective budget limit.

# Rules
The Doctor has in hand the list of maximum budgets for each Ood.  
The Doctor's aim is to share the cost very precisely.  
To respect the Oods democratic values and to select the best solution, the Doctor decides that:

* no Ood can pay more than his maximum budget
* the optimal solution is the one for which the highest contribution is minimal
* if there are several optimal solutions, then the best solution is the one for which the highest second contribution is minimal, and so on and so forth...

Moreover, to facilitate the calculations, the Doctor wants each financial participation to be an integer of the local currency (nobody should give any cents).


# Examples
For example, the Oods wish to buy a gift that cost 100.  
The first Ood has a budget of 3, the second has a budget of 45 and the third has a budget of 100.

In that case:  
Budget 	Solution  
`|   3   |   3  |`  
`|  45   |  45  |`  
`| 100   |  52  |`  

Second example: they still wish to buy a gift that costs 100 but the second Ood has a budget of 100 this time.

In that case:  
Budget 	Solution  
  3    3  
100 	48  
100 	49  
