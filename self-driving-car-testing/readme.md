# Statement

## Goal
Display the trajectory of a self-driving car on the road, from its log file.

The self driving car is represented by a hash mark # and the pattern of the road to display is given in the log file.  
On an ASCII vertical scrolling, you display the car on each portion of the road.

Each command logged by the car is represented by an integer and a char (`L` for left, `R` for right, `S` for straight).  
For example: `4S;3R` means the car is moving four times straight ahead, then three times to the right (from a sky point of view) 