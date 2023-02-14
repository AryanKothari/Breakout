# Breakout Plan
### Aryan Kothari


#### Examples

[comment]: <> (You need to put blank lines to write some text)

[comment]: <> (in separate paragraphs.)


[comment]: <> (Emphasis, aka italics, with *asterisks* or _underscores_.)

[comment]: <> (Strong emphasis, aka bold, with **asterisks** or __underscores__.)

[comment]: <> (Combined emphasis with **asterisks and _underscores_**.)


[comment]: <> (You can also make lists:)

[comment]: <> (* Bullets are made with asterisks)

[comment]: <> (1. You can order things with numbers.)


[comment]: <> (You can put links in like this: [Duke CompSci]&#40;https://www.cs.duke.edu&#41;)



## Interesting Breakout Variants

 * Super Breakout: I thought this version was interesting because it incorporated points with each block, and as seen 
   in the demo it is hard to dodge them. It is not just a game of moving up levels, but scoring the highest points

 * Brick Breaker Hero: I really liked that there was a "boss" in this version of the game. Added an additional feature
that makes the game really exciting and unique compared to other types.


## Paddle Ideas

 * "Sticky": There will be a small section on the paddle that will make the ball stick to the paddle instead of 
   immediately bouncing off. It can then be released by the player hitting the Spacebar. The ball will follow the 
   direction of the cursor. 

 * Paddle will teleport from one side of screen to the other when it reaches the edge


## Block Ideas

 * Death block: if you hit this block three times, you lose a life

 * Dropping a power-up when the block gets destroyed 

 * Harder blocks to break down (require more hits)


## Power-up Ideas

 * Slowing down the ball

 * Increasing paddle length 

 * Adding an extra ball


## Cheat Key Ideas

 * Three lives

 * Power-up that deletes all the blocks in the lowest row

 * The paddle covers the entire bottom row for 10 seconds so the player can take a breather

 * The ball has an explosive effect. When it hits a brick, all adjacent bricks also get destroyed


## Level Descriptions

 1111111
 1111111
 1111111
   
*very simple level. No power ups, slow ball, destroy each block

 1111111
 2222222
 3333333

    lower level blocks are harder to destroy + the ball picks up speed as the round continues. Power ups are introduced to 
this level.

1111111
1313131
3131313

*This level, in addition to being a lot faster, includes "death blocks", that, if hit more than three times will result 
in a lost life

## Class Ideas

 * Ball - one method it would have is setSpeed, because that is going to be variable and we'd have to change it 
depending on the level

 * Paddle - one method this would have is setSize(), because we are going to vary the size of the paddle depending 
on level

 * Blocks - one method this will have is setAlive(), to indicate whether it is alive or destroyed

 * Power ups - one method this will have is setPowerUp(), which will be what power up is contained in that object

