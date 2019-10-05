# Statement

Forest Fire

A forest fire has broken out! The nearby Fire Station Service asked your help to manage the dispatch of units on the scene.  
The Fire Chief sent you the coordinates where the fires are located, you must decide which unit is best to send using the least amount of water possible.

The available units are:

- C Canadair: covers a 3x3 area and uses 2100 Liters of water
- H Fire Helicopter: covers a 2x2 area and uses 1200 Liters
- J Smoke Jumpers Squad: covers a 1x1 area and uses 600 Liters

# Goal

Your goal is to put out ALL the fires by using the least amount of water possible.  
On each turn you'll have to output the unit that should be called in and on which coordinates 

# Rules

**Coordinate system**  
The top-left corner has position (0,0). x goes to the right, y increases downwards  
To attack an area you must give the top-left coordinate of the 3x3 or 2x2 area you want to attack

    Sending a Canadair to (0;0): C 0 0 will extinguish:
    0;0 0;1 0;2
    1;0 1;1 1;2
    2;0 2;1 2;2
    
    Sending a Fire Helicopter to (0;0): H 0 0 will extinguish:
    0;0 0;1
    1;0 1;1
    
    Sending a Smoke Jumpers Squad to (0;0): J 0 0 will extinguish:
    0;0


**Be careful!**  
The attacking area MUST be inside the forest area, you can't exceed it or you would damage the nearby areas!

# Extra Detail

All maps are square shaped (L x L)
