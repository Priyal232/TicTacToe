import java.util.Random;
import java.util.Scanner;

public class Tictactoe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn = true;
    static boolean gameOver = false;
    static char humanSymbol = 'X';
    static char computerSymbol = 'O';
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    static {
        resetBoard();
    }

    public static void main(String[] args) {
        printBoardWithSlots();  // Initial board

        while (!gameOver) {
            if (isHumanTurn) {
                humanMoveSlot();
            } else {
                computerMoveSlot();
            }
            printBoardWithSlots();

            if (checkWin(humanSymbol)) {
                System.out.println("Human (X) wins!");
                gameOver = true;
            } else if (checkWin(computerSymbol)) {
                System.out.println("Computer (O) wins!");
                gameOver = true;
            } else if (isDraw()) {
                System.out.println("Draw!");
                gameOver = true;
            }

            if (!gameOver) isHumanTurn = !isHumanTurn;
        }
        sc.close();
    }

    static void humanMoveSlot() {
        System.out.print("Your move (1-9): ");
        int slot = sc.nextInt();
        while (!isValidSlot(slot)) {
            System.out.print("Invalid/occupied (1-9): ");
            slot = sc.nextInt();
        }
        int[] pos = slotToPosition(slot);
        placeMove(pos[0], pos[1], humanSymbol);
    }

    static void computerMoveSlot() {
        while (true) {
            int slot = rand.nextInt(9) + 1;
            if (isValidSlot(slot)) {
                int[] pos = slotToPosition(slot);
                placeMove(pos[0], pos[1], computerSymbol);
                System.out.println("Computer picks slot " + slot);
                return;
            }
        }
    }

    static boolean isValidSlot(int slot) {
        if (slot < 1 || slot > 9) return false;
        int[] pos = slotToPosition(slot);
        return board[pos[0]][pos[1]] == '-';
    }

    static int[] slotToPosition(int slot) {
        return new int[]{(slot - 1) / 3, (slot - 1) % 3};
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    static boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }

    static boolean isDraw() {
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (board[i][j] == '-') return false;
        return true;
    }

    static void printBoardWithSlots() {
        for (int i = 0; i < 3; i++) {
            System.out.print("  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void resetBoard() {
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) board[i][j] = '-';
        isHumanTurn = true; gameOver = false;
    }
}