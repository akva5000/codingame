
# What will I learn?

>Greedy algorithmsSorting

# Statement
## Goal
Given a list of integers, add them up.  
Wait... it will not be that easy.

Now there is an extra property to the operator ADD.  
When you add a and b, it involves a **cost** of a+b.

If the list of numbers is 1, 2, 3  
you can add them up in several ways.

Approach 1  
1 + 2 = 3 (cost = 3)  
3 + 3 = 6 (cost = 6)  
Total cost = 9

Approach 2  
1 + 3 = 4 (cost = 4)  
4 + 2 = 6 (cost = 6)  
Total cost = 10

Approach 3  
2 + 3 = 5 (cost = 5)  
5 + 1 = 6 (cost = 6)  
Total cost = 11

Find the **lowest cost** to finish the addition.