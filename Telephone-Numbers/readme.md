

# What will I learn?

	SetsTries

In this puzzle, you learn new ways and reasons to store data in optimized structures such as tries and get a better understanding of graph/tree theory.

**External resources** `TreeTrie`


# Statement

The goal of this puzzle is to store multiple phone numbers in a compact way, using a tree data structure,  
then count all the nodes there are in this tree. Be careful that you may need several tree roots.


# Story

Dang, all those telephone numbers are impossible to remember!  
What if we were to invent a new way to store phone numbers so that they take the least memory space?  
It is your task today to achieve that hard work. Then call me, maybe ?


# Goal

By joining the iDroid smartphone development team, you have been given the responsibility of developing the contact manager.  
Obviously, what you were not told is that there are strong technical constraints for iDroid:  
the system doesn’t have much memory and the processor is as fast as a Cyrix from the 90s...

In the specifications, there are two points in particular that catch your attention:

1. Intelligent Assistance for entering numbers  
The numbers corresponding to the first digits entered will be displayed to the user almost instantly.  
![altText](pic1.jpg "title")

2. Number storage optimization  
First digits which are common to the numbers should not be duplicated in the memory.  
Fortunately, the specifications also have this little chart to guide you in the implementation:  
![altText](pic2.png "title")

Your task is to write a program that displays the number of items (which are numbers) required to store a list of telephone numbers with the structure presented above.
 