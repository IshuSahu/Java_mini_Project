import java.util.*;

public class TicTocToeGame {
    static String[] board;
    static String currentPlayer;

    // Define the winning combinations
    static final int[][] WINNING_COMBINATIONS = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
        {0, 4, 8}, {2, 4, 6}             // Diagonals
    };

    static void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
    }

    // Check winning
    static boolean checkWin() {
        for (int[] combo : WINNING_COMBINATIONS) {
            if (board[combo[0]].equals(currentPlayer) &&
                board[combo[1]].equals(currentPlayer) &&
                board[combo[2]].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    // Check if the game is a draw
    static boolean checkDraw() {
        for (String cell : board) {
            if (!cell.equals("X") && !cell.equals("O")) {
                return false; 
            }
        }
        return true; 
    }

    // Print game Board
    static void printBoard() {
        System.out.println("|---|---|---|");
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("| %s | %s | %s |\n", board[i], board[i + 1], board[i + 2]);
            System.out.println("|---|---|---|");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        currentPlayer = "X";
        String winner = null;

        System.out.println("Welcome to 3x3 Tic Tac Toe.");

        while (true) {
            initializeBoard();
            printBoard();
            System.out.println(currentPlayer + " will play first.");

            while (winner == null) {
                int move;
                do {
                    System.out.print("Enter a slot number to place " + currentPlayer + " in: ");
                    while (!in.hasNextInt()) {
                        System.out.println("Invalid input; re-enter slot number:");
                        in.next();
                    }
                    move = in.nextInt();
                } while (move < 1 || move > 9 || !board[move - 1].matches("[1-9]"));

                board[move - 1] = currentPlayer;
                printBoard();

                if (checkWin()) {
                    winner = currentPlayer;
                } else if (checkDraw()) {
                    winner = "draw";
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            }

            if (winner.equals("draw")) {
                System.out.println("It's a draw!");
            } else {
                System.out.println("Congratulations! " + winner + " has won!");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = in.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }

        System.out.println("Thanks for playing!");
        in.close();
    }
}

