# Connect4

Connect Four is a game in which the players choose a color and then take turns dropping colored tokens into a six-row, seven-column vertically suspended grid. The pieces fall straight down, occupying the lowest available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own pieces.

## 📋 Problem Statement

Design a Connect Four game LLD that allows two players to play the game against each other. The game should handle the following operations:

- Create a new game
- Make a move in a game
- Get the current state of a game
- Get the winner of a game

## 🎯 Functional Requirements

1. Create a new game
3. Make a move in a game
4. Get the current state of a game
5. Get the winner of a game

## Entities

1. Game
    - Board
    - Player[]
    - status
    - winner

    + Game(player1, player2, board)
    + move(player, col)
    + getWinner()
    + getCurrentState() -> getCurrentGameState

2. Player
    - disc
    - name

3. Board
    - row
    - col
    - disc

    + Board(row, col)
    + move(disc) -> bool
    + getCurrentState() -> getCurrentBoardState

4. Disc
    - color

