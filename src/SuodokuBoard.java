import java.lang.reflect.Array;
import java.util.ArrayList;

public class SuodokuBoard {
    private final int MAX_SIZE = 9;
    private int[][] board;
    private SuodokuBoard next;
    private Constraints constraints;
    private int[][][] validboard;

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
                }
            } // end of for loop
        } // end of for loop
    } // end of constructor

    public int[][] getBoard() { return this.board; }
    public void setBoard(int[][] board) {
        for(int row = 0; row < MAX_SIZE; row++){
            for(int col = 0; col < MAX_SIZE; col++) {
                this.board[row][col] = board[row][col];
            }
        }
    }

    public int[][][] getValidBoard() { return this.validboard; }
    public void setValidboard(int[][][] validboard) {
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int col = 0; col < MAX_SIZE; col++) {
                for(int index = 0; index < MAX_SIZE; index++) {
                    this.validboard[row][col][index] = validboard[row][col][index];
                }
            }
        }
    }
    public Constraints getConstraints() { return this.constraints; }
    public void setConstraints(Constraints constraints) { this.constraints = new Constraints(constraints); }

    public SuodokuBoard getNext() { return this.next; }
    public void setNext(SuodokuBoard next) { this.next = next; }
    public String boardToString() {
        String ret = "";

        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                ret += String.valueOf(this.board[rowindex][colindex]);
            } // end of for loop
        } // end of for loop

        return ret;
    } // end of boardToString

    public void setBoardValueAt(int row, int col, int num) {
        this.board[row][col] = num;
        if(num > 0 ) {
            num--;
            this.constraints.updateValidMoves(row, col, num);
            this.constraints.removeMove(row, col, num);
        }
    } // end of setBoardValueAt

    public int countValidMoves(int row, int col) {
        int count = 0;

        for(int num = 0; num < MAX_SIZE; num++) {
            if(this.validboard[row][col][num] > 0) {
                count++;
            }
        }

        return count;
    }

    public void printBoard() {
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int  col = 0; col < MAX_SIZE; col++) {
                System.out.print(this.board[row][col] + " ");
            } // end of for loop
            System.out.println();
        } // end of for loop
    } // end of printBoard
} // end of SuodokuBoard Class