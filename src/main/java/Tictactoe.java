import java.util.Scanner;

public class Tictactoe {

    public static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int slot;

        System.out.print("Enter a slot number (1-9): ");
        slot = scanner.nextInt();

        return slot;
    }

    public static void main(String[] args) {
        int userChoice = getUserInput();
        System.out.println("You selected slot: " + userChoice);
    }
}