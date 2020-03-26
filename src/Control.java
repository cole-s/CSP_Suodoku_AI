import java.util.Hashtable;
import java.io.*;

public class Control {
    private static final int MAX_SIZE = 9;
    private static Hashtable<String, String> exploredset;
    private static SuodokuBoard root;

    public static void importBoard(String filename) throws Error {
        root = new SuodokuBoard();
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


    public static void startSolving() {
        exploredset = new Hashtable<>();
        recSolving(root);
    }

    private static boolean recSolving(SuodokuBoard node) {
        // base cases
        if(exploredset.containsKey(node.boardToString())) {
//            System.out.println("DUPLICATE");
            return false;
        } else {
//            node.printBoard();
//            System.out.println();
            exploredset.put(node.boardToString(), "E");
        }
        if(isSolved(node)) {
            node.printBoard();
            System.out.println();
            return true;
        }
        // end of base cases

        int rowleast = 0, colleast = 0, leastmoves = 0;
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int col = 0; col < MAX_SIZE; col++) {
                if(node.getBoard()[row][col] < 1) {
                    node.getValidBoard()[row][col] = node.getConstraints().getAvailableMoveList(row, col);
                }
            }
        }

        boolean flag = false;
        do {
            leastmoves = 10;
            for (int row = 0; row < MAX_SIZE; row++) {
                for (int col = 0; col < MAX_SIZE; col++) {
                    if (node.getBoard()[row][col] == 0) {
                        if (node.countValidMoves(row, col) > 0 && (node.countValidMoves(row, col) < leastmoves)) {
                            leastmoves = node.countValidMoves(row, col);
                            rowleast = row;
                            colleast = col;
                        }
                    }
                }
            }
//            if(leastmoves == 1) {
//                node.printBoard();
//                System.out.println();
//            }
            int move = -1;
            for (int index = 0; index < MAX_SIZE; index++) {
                if(node.getValidBoard()[rowleast][colleast][index] > 0) {
                    node.setNext(new SuodokuBoard(node));
                    move = node.getValidBoard()[rowleast][colleast][index];
                    node.getNext().setBoardValueAt(rowleast, colleast, move);
                    node.getValidBoard()[rowleast][colleast][index] = -1;
                    flag = recSolving(node.getNext());
                    if(flag) {
                        return true;
                    }
                }
            }
        } while (leastmoves != 10);

        return false;
    }

    private static boolean isSolved(SuodokuBoard node) {
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int col = 0; col < MAX_SIZE; col++) {
                if(node.getBoard()[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
} // end of Control Class