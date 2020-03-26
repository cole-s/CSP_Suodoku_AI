/**
 * Class: Control
 * @author Cole Schaar
 * CS 481 - Artifical Intelligence
 * Purpose: Controls the CSP functionality and Backtracking for the search Algorithm along with handling user input/files
 */

import java.util.Hashtable;
import java.io.*;

public class Control {
    private static final int MAX_SIZE = 9; // size of board
    private static Hashtable<String, String> exploredset; // used to avoid duplicate states
    private static SudokuBoard root; // first node to search

    /**
     * Method: importBoard
     * Purpose: reads from a given file to place into root
     * @param filename - name of file to read for board/puzzle
     * @throws Error - file not found or input is invalid from file
     */
    public static void importBoard(String filename) throws Error {
        root = new SudokuBoard();
        File infile;
        BufferedReader br;
        try {
            infile = new File(filename);
            br = new BufferedReader(new FileReader(infile));
        } catch (FileNotFoundException err) {
            System.out.println("File Not Found");
            throw new Error();
        } // end of try-catch statements

        try {
            int row = 0, col = 0;
            String line;
            while ((line = br.readLine()) != null) {
                for(int index = 0; index < line.length(); index++) {
                    char number = line.charAt(index);
                    if(number != ' ') {
                        root.setBoardValueAt(row, col, (number - 48));
                        col++;
                    } // end of if statement
                } // end of for loop
                col = 0;
                row++;
            } // end of while loop
        } catch (IOException err) {
            System.out.println("Input mismatch");
            throw new Error();
        } // end of try-catch statements
    } // end of importBoard

    /**
     * Method: startSolving
     * Purpose: to be called by main to start the program search algorithm
     */
    public static void startSolving() {
        exploredset = new Hashtable<>(); // initializes exploredset
        recSolving(root);
    } // end of startSolving

    /**
     * Method: recSolving
     * Purpose: recursive function used to iterate through the different states of the board to find a solution
     * @param node - current board state being searched through
     * @return boolean - true if solved - false if not solved
     */
    private static boolean recSolving(SudokuBoard node) {
        // base cases
        if(exploredset.containsKey(node.boardToString())) {
//            System.out.println("DUPLICATE");
            return false;
        } else {
//            node.printBoard();
//            System.out.println();
            exploredset.put(node.boardToString(), "E");
        } // end of if-else statements

        if(isSolved(node)) {
            node.printBoard();
            System.out.println();
            return true;
        }
        // end of base cases

        // calculate all possible moves from each spot
        int rowleast = 0, colleast = 0, leastmoves = 0;
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int col = 0; col < MAX_SIZE; col++) {
                if(node.getBoard()[row][col] < 1) {
                    node.getValidBoard()[row][col] = node.getConstraints().getAvailableMoveList(row, col);
                } // end of if statement
            } // end of for loop
        } // end of for loop

        do {
            leastmoves = 10; // default value
            for (int row = 0; row < MAX_SIZE; row++) {
                for (int col = 0; col < MAX_SIZE; col++) {
                    if (node.getBoard()[row][col] == 0) {
                        // find spot on board with least number of valid moves
                        if (node.countValidMoves(row, col) > 0 && (node.countValidMoves(row, col) < leastmoves)) {
                            leastmoves = node.countValidMoves(row, col);
                            rowleast = row;
                            colleast = col;
                        } // end of if statement
                    } // end of if statement
                } // end of for loop
            } // end of for loop

            int move = -1; // which number to place in spot
            for (int index = 0; index < MAX_SIZE; index++) {
                if(node.getValidBoard()[rowleast][colleast][index] > 0) {
                    // make next node move here
                    node.setNext(new SudokuBoard(node));
                    move = node.getValidBoard()[rowleast][colleast][index];
                    node.getNext().setBoardValueAt(rowleast, colleast, move);
                    node.getValidBoard()[rowleast][colleast][index] = -1;

                    if(recSolving(node.getNext())) { // recursive call
                        return true;
                    } // end of if statement
                } // end of if statement
            } // end of for loop
        } while (leastmoves != 10); // moves have been found

        return false; // nothing worked
    } // end of recSolving()

    /**
     * Method: isSolved
     * @param node - current board state to be checked if all spots are filled
     * @return boolean - true if filled - false if not
     */
    private static boolean isSolved(SudokuBoard node) {
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int col = 0; col < MAX_SIZE; col++) {
                if(node.getBoard()[row][col] == 0) {
                    return false;
                } // end of if statement
            } // end of for loop
        } // end of for loop

        return true;
    } // end of isSolved
} // end of Control Class