import java.util.Scanner;
import java.util.Random;

public class Tictactoe {
    static char[][] board = new char[3][3];
    static boolean gameOver = false;
    static char human = 'X';
    static char computer = 'O';
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initBoard();
        boolean humanTurn = true;
        while (!gameOver && !isBoardFull()) {
            printBoard();
            if (humanTurn) {

                int[] move = getValidHumanMove();
                makeMove(move[0], move[1], human);
            } else {
                // Computer move (random valid)
                int[] move = getComputerMove();
                makeMove(move[0], move[1], computer);
                System.out.println("Computer plays at (" + move[0] + "," + move[1] + ")");
            }


            if (hasWon(humanTurn ? human : computer)) {
                printBoard();
                System.out.println((humanTurn ? human : computer) + " wins!");
                gameOver = true;
                break;
            }
            humanTurn = !humanTurn;
        }

        if (!gameOver) {
            printBoard();
            System.out.println("Draw!");
        }
        scanner.close();
    }

    static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] getValidHumanMove() {
        int row, col;
        while (true) {
            System.out.print("Your move (row 0-2, col 0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                return new int[]{row, col};
            }
            System.out.println("Invalid! Try again.");
        }
    }

    static int[] getComputerMove() {

        java.util.ArrayList<int[]> empty = new java.util.ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    empty.add(new int[]{i, j});
                }
            }
        }

        return empty.get(random.nextInt(empty.size()));
    }

    static void makeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }


    static boolean hasWon(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }

    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') return false;
            }
        }
        return true;
    }
}