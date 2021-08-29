package cpsc2150.extendedConnectX;

import java.util.*;
import java.lang.*;


/**
 * @author Mike Brandin
 * class: CPSC 2150
 * section: 002
 * date: 2/27/2021
 *
 * Description:
 *          Interacts with user to create a Extended ConnectX game experience.
 *
 *
 * defines:
 *           Column:     the column position on the board (y-coordinate)
 *           Row:        the row position on the board (x-coordinate)
 * @invariant
 *           (0 <= Row <= 5)(0 <= Column <= 8)
 */


public class GameScreen {


    /**
     * @pre none
     * @post none
     * @param args     string array for holding command line arguments for the main function
     */
    public static void main(String[] args) {
        boolean loop = true;
        GameBoard masterBoard = new GameBoard();
        Scanner input = new Scanner(System.in);
        int columnSelect = 0;
        boolean invalid = true;

        final int MIN_COLUMN = 0;
        final int MAX_COLUMN = 8;

        int totalMoves = 0;
        Character playAgain;

        game: while (loop) {//while loop runs for length of the game session
            System.out.println(masterBoard.toString());//output the gameboard


            if (totalMoves != 0 && masterBoard.checkForWin(columnSelect)) {//check if player O won and skip the first turn
                System.out.println("Player O Won!");
                while (invalid) {
                    System.out.println("Would you like to play again? Y/N");//prompt to play again
                    playAgain = input.next().charAt(0);
                    playAgain = Character.toUpperCase(playAgain);

                    if (playAgain.equals('Y')) {//if player wants to play again reset values and continue the loop
                        masterBoard = new GameBoard();
                        totalMoves = 0;
                        continue game;
                    }
                    else if (playAgain.equals('N')) {//if player does not want to play again end game (break for loop)
                        break game;
                    }
                }
            }

            if (masterBoard.checkTie()) {//check for tie game
                System.out.println("Tie game!");
                while (invalid) {
                    System.out.println("Would you like to play again? Y/N");//prompt to play again
                    playAgain = input.next().charAt(0);
                    playAgain = Character.toUpperCase(playAgain);


                    if (playAgain.equals('Y')) {//if player wants to play again reset values and continue the loop
                        masterBoard = new GameBoard();
                        totalMoves = 0;
                        continue game;
                    }
                    else if (playAgain.equals('N')) {//if player does not want to play again end game (break for loop)
                        break game;
                    }
                }
            }

            while (invalid) {//player X's turn
                System.out.println("Player X, what column do you want to place your marker in?");
                columnSelect = input.nextInt();
                //check the preconditions for placeToken
                if (columnSelect < MIN_COLUMN) {
                    System.out.println("Column cannot be less than 0");
                } else if (columnSelect > masterBoard.getNumColumns() - 1) {
                    System.out.println("Column cannot be greater than 8");
                } else if (!masterBoard.checkIfFree(columnSelect)) {
                    System.out.println("Column is full");
                } else {//if valid input place X in selected Column
                    masterBoard.placeToken('X', columnSelect);
                    totalMoves++;
                    break;//break from turn and continue game
                }
            }
            System.out.println(masterBoard.toString());

            if (masterBoard.checkForWin(columnSelect)) {//check if player X won
                System.out.println("Player X Won!");
                while (invalid) {
                    System.out.println("Would you like to play again? Y/N");//prompt to play again
                    playAgain = input.next().charAt(0);
                    playAgain = Character.toUpperCase(playAgain);


                    if (playAgain.equals('Y')) {//if player wants to play again reset values and continue the loop
                        masterBoard = new GameBoard();
                        totalMoves = 0;
                        continue game;
                    }
                    else if (playAgain.equals('N')) {//if player does not want to play again end game (break for loop)
                        break game;
                    }
                }
            }

            if (masterBoard.checkTie()) {//check for tie game
                System.out.println("Tie game!");
                while (invalid) {
                    System.out.println("Would you like to play again? Y/N");//prompt to play again
                    playAgain = input.next().charAt(0);
                    playAgain = Character.toUpperCase(playAgain);

                    if (playAgain.equals('Y')) {//if player wants to play again reset values and continue the loop
                        masterBoard = new GameBoard();
                        totalMoves = 0;
                        continue game;
                    }
                    else if (playAgain.equals('N')) {//if player does not want to play again end game (break for loop)
                        break game;
                    }
                }
            }

            while (invalid) {//player O's turn
                System.out.println("Player O, what column do you want to place your marker in?");
                columnSelect = input.nextInt();
                //check the preconditions for placeToken
                if (columnSelect < MIN_COLUMN) {
                    System.out.println("Column cannot be less than 0");
                } else if (columnSelect > masterBoard.getNumColumns() - 1) {
                    System.out.println("Column cannot be greater than 8");
                } else if (!masterBoard.checkIfFree(columnSelect)) {
                    System.out.println("Column is full");
                } else {//if valid input place X in selected Column
                    masterBoard.placeToken('O', columnSelect);
                    totalMoves++;
                    break;//break from turn and continue game
                }
            }

        }//game loop
    }

}
