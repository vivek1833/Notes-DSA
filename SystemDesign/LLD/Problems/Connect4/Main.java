package SystemDesign.LLD.Problems.Connect4;

public class Main {
    public static void main(String[] args) {
        int row = 6;
        int col = 7;

        Disc disc1 = new Disc("RED");
        Disc disc2 = new Disc("BLACK");

        Board board = new Board(row, col);
        Player player1 = new Player(disc1);
        Player player2 = new Player(disc2);

        Game game = new Game(board, player1, player2);
        game.start();
    }
}