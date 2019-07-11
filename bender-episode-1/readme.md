
#What will I learn?

> SimulationState machine

In this puzzle, you have to parse, store and manipulate two-dimensional arrays that are the bases for 2D problems.  
You also have to implement a simple but complete Finite State Machine and learn about this concept.  

**External resources:** _Two dimensional arrayFinite State Machine_

#Statement

The goal of the puzzle is to help Bender the robot to go through the city.  
You have to decide what movement it will do next, based on its previous moves and the environment (obstacles).  
Read carefully the rules for the displacement of the robot and how to modelize the map of the city.

#Story

Bender, depressed since its artificial intelligence has been diminished, roams on the streets to find the nearest suicide booth.  
In order to save the robot, you are entrusted with the task of forecasting the path Bender will take, so we can intercept him.  
You will have at your disposal the logics of the robot's displacements.  

What are you still doing here ? Run to Bender's rescue !


# Goal

Bender is a depressed robot who heals his depression by partying and drinking alcohol.  
To save him from a life of debauchery, his creators have reprogrammed the control system with a more rudimentary intelligence.  
Unfortunately, he has lost his sense of humor and his former friends have now rejected him.

Bender is now all alone and is wandering through the streets of Futurama with the intention of ending it all in a suicide booth.

To intercept him and save him from almost certain death, the authorities have given you a mission:  
write a program that will make it possible to foresee the path that Bender follows.  
To do so, you are given the logic for the new intelligence with which Bender has been programmed as well as a map of the city.


# Rules

The 9 rules of the new Bender system:  

1. Bender starts from the place indicated by the `@` symbol on the map and heads SOUTH.
2. Bender finishes his journey and dies when he reaches the suicide booth marked `$`.
3. Obstacles that Bender may encounter are represented by `#` or `X`.
4. When Bender encounters an obstacle, he **changes direction** using the following priorities:  
   SOUTH, EAST, NORTH and WEST.  
   So he first tries to go SOUTH, if he cannot, then he will go EAST,  
   if he still cannot, then he will go NORTH,  
   and finally if he still cannot, then he will go WEST.
   
5. Along the way, Bender may come across path modifiers that will **instantaneously make him change direction**.  
   `S` modifier will make him turn SOUTH from then on,  
   `E` to the EAST,  
   `N` to the NORTH,  
   `W` to the WEST.
   
6. The circuit inverters (`I` on map) produce a magnetic field which will reverse the direction priorities  
   that Bender should choose when encountering an obstacle.  
   Priorities will become WEST, NORTH, EAST, SOUTH.  
   If Bender returns to an inverter `I`, then **priorities are reset to their original state** (SOUTH, EAST, NORTH, WEST).
     
7. Bender can also find a few beers along his path (`B` on the map) that will give him strength and put him in “Breaker” mode.  
   Breaker mode allows Bender to destroy and automatically pass through the obstacles represented by the character `X` (only the obstacles `X`).  
   When an obstacle is destroyed, it remains so permanently and Bender maintains his course of direction.  
   If Bender is in Breaker mode and passes over a beer again, then he **immediately goes out of Breaker mode**.  
   The beers remain in place after Bender has passed.
   
8. 2 teleporters `T` may be present in the city.  
   If Bender passes over a teleporter, then he is automatically teleported to the position of the other teleporter  
   and he retains his direction and Breaker mode properties.
   
9. Finally, the space characters are blank areas on the map (no special behavior other than those specified above).

Your program must display the sequence of moves taken by Bender according to the map provided as input.

The map is divided into lines (**L**) and columns (**C**).  
The contours of the map are always unbreakable `#` obstacles.  
The map always has a starting point `@` and a suicide booth `$`.

If Bender cannot reach the suicide booth because he is indefinitely looping, then your program must only display `LOOP`.

## Example

Let the map below:  
`######`  
`#@E $#`  
`# N  #`  
`#X   #`  
`######`

In this example, Bender will follow this sequence of moves:

*   SOUTH (initial direction)
*   EAST (because of the obstacle `X`)
*   NORTH (change of direction caused by `N`)
*   EAST (change of direction caused by `E`)
*   EAST (current direction, until end point `$`)


