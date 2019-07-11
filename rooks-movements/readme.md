#Statement
##Goal

In chess, the board is split in 8 rows and 8 columns.<br>
Considering the view of the game to be from the white side, the utmost top left cell<br> 
is called a8 and the utmost top rightcell is called h8.<br>
Decreasing from top to bottom of the chessboard, the utmost down left cell<br> 
is then called a1 and the utmost down right cell is called h1.<br>

A rook is a piece than can move as many cells as it want to vertically or <br>
horizontally. A rook can't go past an ally unit but can replace an opponent one.<br>
<br>
Each position must be a valid chessboard-position notation (Algebraic notation) such <br>
that it is identified by a column identifier from a to h and a row identifier from 1 to 8.
<br>
Given a white rook and a set of chess pieces that can be yours (white) or not (black), <br>
print all available movements for the rook in the current configuration. 