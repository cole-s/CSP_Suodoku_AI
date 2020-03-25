public class SuodokuBoard {
    private final int MAX_SIZE = 9;
    private int[][] board;
    private SuodokuBoard next;
    private Constraints constraints;

    public SuodokuBoard() {
        this.next = null;
        this.constraints = new Constraints();
        this.board = new int[MAX_SIZE][MAX_SIZE];
        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.board[rowindex][colindex] = 0;
            } // end of for loop
        } // end of for loop
    } // end of constructor

    public SuodokuBoard(int[][] board) {
        next = null;
        this.constraints = new Constraints();
        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.board[rowindex][colindex] = board[rowindex][colindex];
            } // end of for loop
        } // end of for loop
    } // end of constructor

    public SuodokuBoard getNext() { return this.next; }
    public void setNext(int[][] next) { this.next = new SuodokuBoard(next); }

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
    } // end of setBoardValueAt

    public void updateConstraints(){
        for(int row = 0; row < MAX_SIZE; row++) {
            for(int col = 0; col < MAX_SIZE; col++) {

            }
        }
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