
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
        placeMove(0, 0, 'X');
        System.out.println(board[0][0]);
    }
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }
}