package SystemDesign.LLD.Problems.Connect4;

public class Board {

    private int row;
    private int col;
    private int[][] board;

    public Board(int row, int col) {
        this.col = col;
        this.row = row;
        this.board = new int[row][col];
    }

    public Board getCurrentState() {
        return this;
    }

    private static final int[][] DIRECTIONS = {
            { 0, 1 }, // horizontal
            { 1, 0 }, // vertical
            { 1, 1 }, // diagonal down-right
            { 1, -1 } // diagonal down-left
    };

    public boolean checkWin(int row, int col) {
        for (int[] dir : DIRECTIONS) {
            int count = 1;
            int player = board[row][col];

            // Check positive direction
            count += countInDirection(row, col, dir[0], dir[1], player);

            // Check negative direction
            count += countInDirection(row, col, -dir[0], -dir[1], player);

            if (count >= 4) {
                return true;
            }
        }

        return false;
    }

    private int countInDirection(int row, int col, int rowDir, int colDir, int player) {
        int count = 0;
        int r = row + rowDir;
        int c = col + colDir;

        while (r >= 0 && r < this.row && c >= 0 && c < this.col && board[r][c] == player) {
            count++;
            r += rowDir;
            c += colDir;
        }

        return count;
    }

    public int move(Player player, int column) {
        if (column < 0 || column > this.col)
            return -1;

        // using binary search
        int l = 0, h = this.row - 1, result = -1;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (this.board[mid][column] == 0) {
                result = mid;
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        if (result == -1)
            return -1;

        this.board[result][column] = player.getColor();
        return result;
    }
}
