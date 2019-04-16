# Tetris Kata #

This kata is intended to simulate a game of [Tetris](https://en.wikipedia.org/wiki/Tetris).

The point of this kata to to provide a larger than trivial exercise that can be used to practice TDD. A significant portion of the effort will be in determining what tests should be written and, more importantly, written next.

Considerations:
- Drawing the board is a bonus feature, and is not required in order to solve the problem.
- You do not need to implement these stories in the order in which they are listed.  Part of the complexity of this kata is deciding the order in which to implement the stories.

_As the game_<br>
_In order to restrict play area_<br>
_I want to create a board with dimensions 10 by 24_<br>

The tetris board should be created with 10 columns and 24 rows.  This board is empty to begin with, and will be filled as the game is played based on the placement of pieces by the player.

_As the game_<br>
_In order to add complexity to the Game_<br>
_I want different pieces of 4 contiguous blocks_<br>

The player will have the ability to manipulate different pieces during the course of the game.  There are a set number of possible configurations for the pieces, the shapes for which are listed below.

Pieces:
<pre>
x
x           x      x        x    x        x
x    x x    x x    x x    x x    x        x
x    x x    x        x    x      x x    x x</pre>

_As the game_<br>
_In order to start the game_<br>
_I want to create a new piece_<br>

At the beginning of the game a new piece will be created at the top center of the board.  The shape of the piece will be random.

_As the game_<br>
_In order to advance the game_<br>
_I want to move a piece down the board_<br>

At a regular interval (1s) the game will move the active piece one unit towards the bottom of the board.

_As the game_<br>
_In order to advance the game_<br>
_I want to stop a piece from advancing further_<br>

If a piece collides with another occupied block that piece will no longer be active. It will stop advancing and will retain it's current rotation and previous position.  Concurrently, if a piece collides with the bottom of the board it will also stop advancing (maintaining it's current rotation and previous position). Stopped pieces can no longer be moved by the player and become a part of the board.

_As the game_<br>
_In order to advance the game_<br>
_I want to create a new piece if there is no active piece._<br>

If the game is not over and no active piece exists (i.e. a piece that can still be moved by the player according to the previous rule), then a new active piece will be created at the top center of the board.

_As the game_<br>
_In order to advance the game_<br>
_I want to clear complete lines_<br>

When a piece comes to rest and it and any part of that piece completes a line (meaning that all 10 columns of that line are now occupied) then that line is cleared, meaning that no columns in that line are now occupied. Multiple lines may be completed at once.

_As the game_<br>
_In order to advance the game_<br>
_I want to collapse cleared lines_<br>

When a line is cleared, then the rows above it move down one unit.  Multiple lines may be affected at once.

_As the player_<br>
_In order to place pieces_<br>
_I want to rotate pieces_<br>

At every interval the player may decide to rotate the active piece 90 degrees clockwise. Rotation should not be allowed if the rotation would cause any part of the piece to collide with another piece or the edge of the board. Rotation is not required, but in order to complete lines may be necessary.

_As the player_<br>
_In order to place pieces_<br>
_I want to move pieces horizontally_<br>

At every interval the player may decide to move the piece horizontally by one unit. Movement can be left or right unless that movement would cause any part of the piece to collide with another piece or the edge of the board. Movement is not required, but in order to complete lines may be necessary.

_As the game_<br>
_In order to end the game_<br>

I want to stop execution when a piece rests above the top of the board</pre>
If pieces have stacked up to the top of the board, if the next active piece comes to rest on top of the board, then the game is over.

## Bonus features - choose what you want ;) ##
     
_As the player_<br>
_In order to be better than my friends_<br>
_I want to keep track of my score_<br>

A single line cleared is 100 points. A Tetris (four lines cleared simultaneously) is worth 800. A [back-to-back](https://harddrop.com/wiki/Back-to-Back) Tetris is worth 1200.

_As the player_<br>
_In order to play the game_<br>
_I want to view the board_<br>

After every interval, print out the state of the board by drawing the board and the active piece location as well as the resting pieces.

_As the game_<br>
_In order to increase difficulty_<br>
_I want to increase level_<br>

Each 10 lines cleared, the level is increased. It starts at 1. Each level increase reduce the time interval by 10 ms. 

_As the player_<br>
_In order to play the game quickly_<br>
_I want to move pieces down quickly_<br>

If the player chooses, they can advance the piece down until it collides with the bottom edge of the board or another resting piece.
