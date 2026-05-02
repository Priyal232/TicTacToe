import java.util.Random;
import java.util.Scanner;
git
public class Tictactoe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static boolean gameOver = false;
    static char humanSymbol;
    static char computerSymbol;
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    static {
        resetBoard();
    }

    public static void main(String[] args) {

        uc1DisplayInitialBoard();


        uc2Toss();


        while (!gameOver) {
            printBoardWithSlots();

            if (isHumanTurn) {
                humanMoveSlot();
            } else {
                computerMoveSlot();
            }


            if (checkWin(humanSymbol)) {
                printBoardWithSlots();
                System.out.println(humanSymbol + " (You) wins!");
                gameOver = true;
            } else if (checkWin(computerSymbol)) {
                printBoardWithSlots();
                System.out.println(computerSymbol + " (Computer) wins!");
                gameOver = true;
            } else if (isDraw()) {  // UC10: Draw check
                printBoardWithSlots();
                System.out.println("It's a draw!");
                gameOver = true;
            }

            if (!gameOver) isHumanTurn = !isHumanTurn;
        }
        sc.close();
    }


    static void uc1DisplayInitialBoard() {
        System.out.println("=== Tic-Tac-Toe ===");
        System.out.println("Slot layout:");
        System.out.println("  1 2 3");
        System.out.println("  4 5 6");
        System.out.println("  7 8 9\n");
        System.out.println("Starting board:");
        printBoardWithSlots();
    }


    static void uc2Toss() {
        boolean humanStarts = rand.nextBoolean();
        if (humanStarts) {
            humanSymbol = 'X';
            computerSymbol = 'O';
            isHumanTurn = true;
            System.out.println("Toss: You start first! You are X.\n");
        } else {
            humanSymbol = 'O';
            computerSymbol = 'X';
            isHumanTurn = false;
            System.out.println("Toss: Computer starts first! Computer is X, you are O.\n");
        }
    }


    static void humanMoveSlot() {
        System.out.print("Your turn (" + humanSymbol + "). Enter slot (1-9): ");
        int slot = sc.nextInt();


        while (!isValidSlot(slot)) {
            System.out.print("Invalid or occupied! Try again (1-9): ");
            slot = sc.nextInt();
        }


        int[] pos = slotToPosition(slot);


        placeMove(pos[0], pos[1], humanSymbol);
    }


    static void computerMoveSlot() {
        System.out.println("Computer's turn (" + computerSymbol + ")...");
        while (true) {
            int slot = rand.nextInt(9) + 1;
            if (isValidSlot(slot)) {
                int[] pos = slotToPosition(slot);
                placeMove(pos[0], pos[1], computerSymbol);
                System.out.println("Computer chose slot " + slot + "\n");
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
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }


    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    static boolean checkWin(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
                return true;
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;
        return false;
    }

    static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') return false;
            }
        }
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        gameOver = false;
    }
}