# What will I learn?

>>Physics

# Statement
## Goal
You are at a frog contest. The frog with the longest jump wins.  
Your frog participates in the contest.

You want to know in which place your frog is, but you are impatient and you find it is too long to wait for the results.

The ranking of the contest is as follows:
* The further the frog lands, the better the frog's place.
* The best place is the First place.
* If two frogs jump exactly to the same distance, they have the same place and that means that the next place does not exist.  
For example, if there are two frogs in the fifth position, there is no sixth position, we directly take the seventh position.
* Distances are in meters, rounded to the nearest centimeter. That is, distances are rounded to the nearest multiple of 0.01


`############################################################`


The frog is in a two dimensional world.  
There is only one external force which is the gravity. Gravity only applies on the y axis, x coordinate is always 0.  
The ground is flat at y = 0.

We will place ourselves in a two-dimensional orthonormal frame, in Galilean land frame of reference.  
Given are the initial position of the frog, (`x`, `y`), her mass `m` (in gram), the angle `alpha` (in degrees) of the speed vector  
of the frog at time = 0, the initial `speed` magnitude (in m/s) and the gravity intensity vector (`a`, `b` each in m/s²).  
You have to do some mechanics and some math calculus to find the position of the frog after her bound.

I should explain the way to solve this puzzle as it was explained in Physics.
* Step 1: Calculate the velocity components horizontal and vertical.
* Step 2: Integrate the velocity to calculate the position components.
* Step 3: Calculate the time when the frog landed after her bound. That is when the vertical expression of the position is equal to 0 meters.
* Step 4: Inject the time in the horizontal expression to find the x position of the frog after her bound.


For people who do not want to make physics calculations, here is the formula to know the distance of the jump of your frog. All units here are the same as in the specifications.

>> `speed_x` = cos(`alpha`) * speed  
>> `speed_y` = sin(`alpha`) * speed

>> `delta` = `speed_y` ^2 - 4 `*` ( b `*` 1/2) `*` y  
>> `time` = ( - `speed_y` - sqrt( `delta` )) / (2 `*` `b` `*` 1/2)

>> `x final` = ( `a` `*` 1/2 `*` `time` ^2) + ( `speed_x` `*` `time` ) + `x`