package SystemDesign.LLD.Problems.Connect4;

import java.util.Scanner;

public class Game {

    private Board board;
    private String status; // ENUM -> "IN_PROGRESS", "DRAW", "COMPLETED"
    private Player player1;
    private Player player2;
    private Player winner;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.status = "IN_PROGRESS";
    }

    public Boolean move(Player player, int column) {
        if (status != "IN_PROGRESS") {
            return false;
        }

        int row = board.move(player, column);

        if (row == -1) {
            return false; // Invalid move, row already filled
        }

        if (board.checkWin(row, column)) {
            this.status = "COMPLETED";
            this.winner = player;
            return true;
        }

        return true;
    }

    public Player getWinner() {
        if (status != "COMPLETED") {
            return null;
        }

        return this.winner;
    }

    public String getCurrentState() {
        return this.status;
    }

    public void start() {
        this.status = "IN_PROGRESS";

        try (Scanner scanner = new Scanner(System.in)) {
            while (status == "IN_PROGRESS") {
                System.out.println("Player " + player1.getColor() + " turn");
                int column = scanner.nextInt();
                move(player1, column);

                if (status == "IN_PROGRESS") {
                    System.out.println("Player " + player2.getColor() + " turn");
                    column = scanner.nextInt();
                    move(player2, column);
                }
            }
        }
    }
}
