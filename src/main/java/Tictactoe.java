
public class Tictactoe {

    static char[][] board = new char[3][3];

    static {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(isValidMove(1, 1));
        System.out.println(isValidMove(3, 1));
        board[1][1] = 'X';
        System.out.println(isValidMove(1, 1));
    }

    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return board[row][col] == '-';
    }
}