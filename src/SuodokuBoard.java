/**
 * @author Cole Schaar
 * Purpose: keeps track of all the constraints, board state, and next move of the puzzle search algorithm
 */

public class SuodokuBoard {
    private final int MAX_SIZE = 9;
    private int[][] board;
    private SuodokuBoard next;
    private Constraints constraints;
    private int[][][] validboard; // keeps track of all valid moves a spot has

    public SuodokuBoard() {
        this.next = null;
        this.constraints = new Constraints();
        this.board = new int[MAX_SIZE][MAX_SIZE];
        this.validboard = new int[MAX_SIZE][MAX_SIZE][MAX_SIZE];

        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.board[rowindex][colindex] = 0;
            } // end of for loop
        } // end of for loop
    } // end of constructor

    public SuodokuBoard(SuodokuBoard board) {
        this.board = new int[MAX_SIZE][MAX_SIZE];
        this.validboard = new int[MAX_SIZE][MAX_SIZE][MAX_SIZE];
        this.next = null;
        this.constraints = new Constraints(board.getConstraints());

        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.board[rowindex][colindex] = board.getBoard()[rowindex][colindex];
                for(int index = 0; index < MAX_SIZE; index++) {
                    this.validboard[rowindex][colindex][index] = board.getValidBoard()[rowindex][colindex][index];
                } // end of for loop
            } // end of for loop
        } // end of for loop
    } // end of constructor

    // getters and setters
    public int[][] getBoard() { return this.board; }
    public int[][][] getValidBoard() { return this.validboard; }
    public Constraints getConstraints() { return this.constraints; }
    public SuodokuBoard getNext() { return this.next; }
    public void setNext(SuodokuBoard next) { this.next = next; }
    // end of getters and setters

    /**
     * Method: boardToString
     * Purpose: takes the board and turns the current state of it into a string to be used as a key for the explored set
     * @return - String that is to be the key for the hashtable explored set
     */
    public String boardToString() {
        String key = "";

        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                key += String.valueOf(this.board[rowindex][colindex]);
            } // end of for loop
        } // end of for loop

        return key;
    } // end of boardToString

    /**
     * Method: setBoardValueAt
     * Purpose: sets the current board spot at row and col to num and updates the constraints as needed
     * @param row - row which update is to occur
     * @param col - column which update is to occur
     * @param num - number which is to be placed on the board
     */
    public void setBoardValueAt(int row, int col, int num) {
        this.board[row][col] = num;
        if(num > 0 ) {
            num--;
            this.constraints.updateValidMoves(row, col, num);
            this.constraints.removeMove(row, col, num);
        } // end of if statement
    } // end of setBoardValueAt

    /**
     * Method: countValidMoves
     * Purpose: returns a count of all possible moves on a spot
     * @param row - row spot is located
     * @param col - column spot is located
     * @return int - count of all valid moves on the spot
     */
    public int countValidMoves(int row, int col) {
        int count = 0;

        for(int num = 0; num < MAX_SIZE; num++) {
            if(this.validboard[row][col][num] > 0) {
                count++;
            } // end of if statement
        } // end of for loop

        return count;
    } // end of countValidMoves

    /**
     * Method: printBoard
     * Purpose: prints the board state as is with some helping identifiers
     */
    public void printBoard() {
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int  col = 0; col < MAX_SIZE; col++) {
                System.out.print(this.board[row][col]);
                if((col+1)%3 == 0 && (col + 1) < MAX_SIZE) {
                    System.out.print(" || ");
                } else {
                    System.out.print(" ");
                }
            } // end of for loop
            if((row + 1) % 3 == 0 && (row + 1) < MAX_SIZE) {
                System.out.println();
                System.out.println("=======================");
            } else {
                System.out.println();
            }
        } // end of for loop
    } // end of printBoard
} // end of SuodokuBoard Class