package by.itransition.game.main;

import java.security.SecureRandom;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        String[] moves = reading();
        int computerMove = choosingComputerMove(moves.length);
        String keys = Key.encripting("" + computerMove);
        launchingGame(moves, computerMove, keys);
    }

    public static String[] reading() {
        String[] input = readingInput();
        boolean ruleCheck = checkingRules(input);
        while (!ruleCheck) {
            input = readingInput();
            ruleCheck = checkingRules(input);
        }
        return input;
    }

    public static String[] readingInput() {
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        return in.nextLine().split("\\s");
    }

    public static boolean checkingRules(String[] input) {
        if (!checkingNumberOfMoves(input) || !checkingOddMoves(input) || !checkingUniqueMoves(input)) {
            return false;
        }
        return true;
    }

    public static boolean checkingNumberOfMoves(String[] input) {
        if (input.length < 3) {
            System.out.println("More than two moves required. Example: Rock Paper Scrissors");
            return false;
        }
        return true;
    }

    public static boolean checkingOddMoves(String[] input) {
        if (input.length % 2 == 0) {
            System.out.println("An odd number of moves is required. Example: Rock Paper Scrissors");
            return false;
        }
        return true;
    }

    public static boolean checkingUniqueMoves(String[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i].equals(input[j])) {
                    System.out.println("Unique moves required. Example: Rock Paper Scrissors");
                    return false;
                }
            }
        }
        return true;
    }

    public static int choosingComputerMove(int numberOfMoves) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(numberOfMoves) + 1;
    }

    public static void launchingGame(String[] moves, int computerMove, String key) {
        int playersMove = -2;
        while (playersMove == -2) {
            playersMove = readingPlayersMove(moves);
        }
        gettingResult(playersMove, computerMove, moves, key);
    }

    public static int readingPlayersMove(String[] moves) {
        Scanner in = new Scanner(System.in);
        String input;
        showingMenu(moves);
        while (!in.hasNextInt()) {
            input = in.nextLine();
            if (input.equals("?")) {
                return -1;
            }
            showingMenu(moves);
        }
        input = in.nextLine();
        return checkingPlayersMove(Integer.parseInt(input), moves.length);
    }

    public static int checkingPlayersMove(int playersMove, int nuberOfMoves) {
        if (playersMove > -1 && playersMove <= nuberOfMoves) {
            return playersMove;
        }
        return -1;
    }

    public static void gettingResult(int playersMove, int computersMove, String[] moves, String key) {
        if (playersMove == 0) {
            return;
        } else if (playersMove == -1) {
            Table.printingTable(Table.gettingHeader(moves), Table.gettingData(moves));
        } else {
            System.out.println("You: " + moves[playersMove - 1] + "\n" + "Computer: " + moves[computersMove - 1]
                    + "\nYou " + Rules.gettingWinner(moves.length, playersMove, computersMove) + " \nHMAC key: " + key);
        }
    }

    public static void showingMenu(String[] words) {
        System.out.println("Available moves:");
        for (int i = 0; i < words.length; i++) {
            System.out.println((i + 1) + " - " + words[i]);
        }
        System.out.print("0 - exit \n? - help \nEnter your move: ");
    }
}
