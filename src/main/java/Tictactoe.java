import java.util.Random;

public class Tictactoe {

    static char[][] board = new char[3][3];

    static {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    static char computerSymbol = 'O';

    public static void main(String[] args) {
        computerMove();
        printBoard();
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row <= 2 && col >= 0 && col <= 2 && board[row][col] == '-';
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }
    static int[] slotToPosition(int slot) {
        return new int[]{(slot - 1) / 3, (slot - 1) % 3};
    }
    static void computerMove() {
        Random rand = new Random();
        while (true) {
            int slot = rand.nextInt(9) + 1;
            int[] pos = slotToPosition(slot);
            int row = pos[0], col = pos[1];

            if (isValidMove(row, col)) {
                placeMove(row, col, computerSymbol);
                System.out.println("Computer placed 'O' at slot " + slot);
                return;
            }
        }
    }
    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}