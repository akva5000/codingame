# Statement
## Goal
Finally, we have received the first messages from aliens! Unfortunately we cannot understand them because they have a very unique way of speaking.

Here is how their messages are encoded:  
abcdefghi  

becomes  

ghibcadef  

First you take the first `1` character : a   
Then you take the following `2` characters and place it in the front of the string: bc -> a  
Then you take the following `3` characters and place it in the end of the string: bca <- def  
Repeat by taking more and more characters then complete with what remains: ghi -> bcadef  

Some messages have been transformed using the above method more than once.  

Your job here is to decode or encode the messages to discuss with aliens.
 