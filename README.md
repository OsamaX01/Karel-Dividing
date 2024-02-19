# Karel-Dividing
## Overview: 
Karel-Dividing project is an app that divides a map into 4 equal chambers. And dividing small maps that can’t be divided into 4 chambers equally into the maximum possible number of equal chambers.

## Process of thinking:
I want to go back to the moment I wanted to start working on this project. The first and the
main question was how to divide maps in the minimum number of moves (minimum
complexity) with a simple algorithm. The trade-off I found was if I want to cover every case in
the best time complexity, I’ll end up having a large code that’s hard to maintain. So, I intended
to find a general algorithm that runs well concerning time complexity with a clean
code in most cases.
Of course, I will not brute force the hole grid to have the simplest code, so I was thinking of dividing either vertically/horizontally or diagonally but before doing anything, we need to examine the grid dimensions, so the first move is to go to the top-right grid corner and get them. 
A good point to notice was we can’t benefit from any graph algorithms such as DFS, BFS, etc because Karel can’t move diagonally directly. For example, Karel always must walk two steps to reach the top-right corner (one step right and one step up). Also applying such algorithms will cost us extra moves. So, I’ll go with greedy algorithms.
When I started to think about diagonals, I discovered that it would not work except if the grid height was equal to its width (N*N grid). So, I decided to ignore it and go with dividing vertically and horizontally. It’s obvious that if the width is even, we must fill two columns of beepers instead of one column in the odd case. The same applies to height and rows. I thought that filling two columns in a zigzag move might cost fewer moves so I chose to implement it. 

Now I’m about to finish planning but a few ideas are still walking around:
-   Diagonal dividing in N*N grids might minimize the number of moves if N is Even since I must fill two rows and two columns. However, dividing one diagonal also requires 2N moves. The only optimization is that Karel's initial position is already in the top-right corner, which leads to starting to divide immediately in the diagonal case. But it will not affect you too much. So, I decided to ignore it because the case is rare and to simplify the code as much as possible.
 - Small cases are obvious, we can’t divide if N is 1 or 2 where N is the grid height or width. But a special case 2*2 grid can be divided into 2 equal chambers diagonally. I’ll handle it separately.

## Code optimizations:
At first, I looked for the states I needed to keep track of to set as private members. One main thing I had to consider was making generic movement functions to keep everything simple and general and dividing functions to complete our task. Also, I decided at first to represent directions as integers (0 for right, and 1 for up, etc.) then replaced this choice with the Enumeration data type for more readability saving the speed.
```java
enum Direction {RIGHT, UP, LEFT, DOWN};
```

All Functions used only in Homework class are private to support encapsulation.
Here are the movement functions:
```java
private void turnFaceDirectionTo(Direction direction);
private void moveTo(Direction direction);
private void moveTo(Point destination, boolean putBeepersOnTheWay)
private void zigzagMove(Direction mainDirection, Direction swappingDirection)
```

Here are some dividing functions:
```java
private void divideVertically()
private void divideHorizontally()
private void divideSpecial2x2()
```
