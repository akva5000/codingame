#Statement
##Goal
Good ol' Master Mason wants to build a wall.<br>
He's got N bricks lying on the floor, each of them with different weights. The height of the bricks is 6.5 cm.<br>
<br>
The wall is built from the bottom; in every row can be no more bricks than in the row below it.<br>
Bricks can be put right on top, if the previous condition is satisfied.<br>
<br>
In every row there’s a place for maximum X bricks.<br>
<br>
Master Mason wants to minimize his work.<br>
Remembering old school days and physics lessons, he calculates the work as follows.<br>
If a brick is built into the L-th row from the floor, the work needed to place this brick is:<br>
* W = ((L-1) * 6.5 / 100) * g * m,<br>
where m is the weight of the brick measured in kilograms and g = 10 m/s² is<br>
the (not too precise value of the) gravitational acceleration. (L-1) * 6.5 / 100 measures<br>

the distance the brick needs to be lift in meters. Note that the most bottom row ist the 1st one.<br>
<br>
The task is to calculate the minimal work Master Mason can build all the bricks into a (not necessarily rectangular) wall. 