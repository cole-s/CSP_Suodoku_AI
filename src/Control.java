import java.util.Hashtable;
import java.io.*;

public class Control {
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

    public static void printBoard() {
        root.printBoard();
    }
} // end of Control Class