/**
Class: Constraints
@author Cole Schaar
CS 481 - Artifical Intelligence
Controls all of the different types of constrants needed for the AI to function
*/

class Constraints {
    private final int MAX_SIZE = 9; // max size of board

    // constraints for each spot on board
    private HorizontalConstraint[][] horizontal;
    private VerticalConstraint[][] vertical;
    private BoxConstraint[][] box;

    // Helper Variables for the box constraints
    // Boxes 1 - 3
    private final int BOX1_ROW_START = 0;
    private final int BOX1_COL_START = 0;
    private final int BOX1_ROW_END = 2;
    private final int BOX1_COL_END = 2;

    private final int BOX2_ROW_START = 0;
    private final int BOX2_COL_START = 3;
    private final int BOX2_ROW_END = 2;
    private final int BOX2_COL_END = 5;

    private final int BOX3_ROW_START = 0;
    private final int BOX3_COL_START = 6;
    private final int BOX3_ROW_END = 2;
    private final int BOX3_COL_END = 8;

    // Boxes 4 - 6
    private final int BOX4_ROW_START = 3;
    private final int BOX4_COL_START = 0;
    private final int BOX4_ROW_END = 5;
    private final int BOX4_COL_END = 2;

    private final int BOX5_ROW_START = 3;
    private final int BOX5_COL_START = 3;
    private final int BOX5_ROW_END = 5;
    private final int BOX5_COL_END = 5;

    private final int BOX6_ROW_START = 3;
    private final int BOX6_COL_START = 6;
    private final int BOX6_ROW_END = 5;
    private final int BOX6_COL_END = 8;

    // Boxes 7 - 9
    private final int BOX7_ROW_START = 6;
    private final int BOX7_COL_START = 0;
    private final int BOX7_ROW_END = 8;
    private final int BOX7_COL_END = 2;

    private final int BOX8_ROW_START = 6;
    private final int BOX8_COL_START = 3;
    private final int BOX8_ROW_END = 8;
    private final int BOX8_COL_END = 5;

    private final int BOX9_ROW_START = 6;
    private final int BOX9_COL_START = 6;
    private final int BOX9_ROW_END = 8;
    private final int BOX9_COL_END = 8;

    public Constraints() {
        this.constructHorizontal();
        this.constructVertical();
        this.constructBox();
    } // end of constructor

    public Constraints(Constraints con) {
        this.constructHorizontal();
        this.constructVertical();
        this.constructBox();
        this.setHorizontal(con.getHorizontal());
        this.setVertical(con.getVertical());
        this.setBox(con.getBox());
    } // end of constructor

    // helper constructor methods:

    private void constructHorizontal() {
        this.horizontal = new HorizontalConstraint[MAX_SIZE][MAX_SIZE];

        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++){
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.horizontal[rowindex][colindex] = new HorizontalConstraint();
            } // end of for loop
        } // end of for loop
    } // end of constructHorizontal

    private void constructVertical() {
        this.vertical = new VerticalConstraint[MAX_SIZE][MAX_SIZE];

        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++){
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.vertical[rowindex][colindex] = new VerticalConstraint();
            } // end of for loop
        } // end of for loop
    } // end of constructVertical

    private void constructBox() {
        this.box = new BoxConstraint[MAX_SIZE][MAX_SIZE];

        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++){
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.box[rowindex][colindex] = new BoxConstraint();
            } // end of for loop
        } // end of for loop
    } // end of constructBox

    // end of help constructor methods


    // Getters and Setter (By val)
    public HorizontalConstraint[][] getHorizontal() { return this.horizontal; }
    public VerticalConstraint[][] getVertical() { return this.vertical; }
    public BoxConstraint[][] getBox() { return this.box; }
    public void setHorizontal(HorizontalConstraint[][] horizontal) {
        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++){
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.horizontal[rowindex][colindex] = new HorizontalConstraint(horizontal[rowindex][colindex].getAvailable());
            } // end of for loop
        } // end of for loop
    } // end of setHorizontal

    public void setVertical(VerticalConstraint[][] vertical) {
        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.vertical[rowindex][colindex] = new VerticalConstraint(vertical[rowindex][colindex].getAvailable());
            } // end of for loop
        } // end of for loop
    } // end of setVertical

    public void setBox(BoxConstraint[][] box) {
        for(int rowindex = 0; rowindex < MAX_SIZE; rowindex++) {
            for(int colindex = 0; colindex < MAX_SIZE; colindex++) {
                this.box[rowindex][colindex] = new BoxConstraint(box[rowindex][colindex].getAvailable());
            } // end of for loop
        } // end of for loop
    } // end of setBox

    // End of Getters and Setters

    /**
     * Method: getAvailableMoveList
     * Purpose: returns a list of all possible numbers able tp be placed given a location
     * @param row - which row spot is located
     * @param col - which column spot is located
     * @return int[] - containing list of valid moves
     */
    public int[] getAvailableMoveList(int row, int col) {
        int[] available = new int[MAX_SIZE];
        for(int num = 1; num <= MAX_SIZE; num++) {
            if(this.horizontal[row][col].getAvailable()[num-1] > 0) {
                if(this.vertical[row][col].getAvailable()[num-1] > 0) {
                    if(this.box[row][col].getAvailable()[num-1] > 0) {
                        available[num-1] = num;
                    } // end of if statement
                } // end of if statement
            } // end of if statement
        } // end of for loop

        return available;
    } // end of countAvailableMoves

    /**
     * Method: removeMove
     * Purpose: removes the move given a location and which number to remove from valid move list
     * @param row - row which spot is located
     * @param col - column which spot is located
     * @param number - number to remove from valid move list
     */
    public void removeMove(int row, int col, int number) {
        this.horizontal[row][col].removeAvailable(number);
        this.vertical[row][col].removeAvailable(number);
        this.box[row][col].removeAvailable(number);
    } // end of removeMove

    /**
     * Method: updateValidMoves
     * Purpose: Updates all the corresponding spots affected by the adding of a number to the board
     * @param row - row which number was placed
     * @param col - column which number was placed
     * @param num - number that was added to the board
     */
    public void updateValidMoves(int row, int col, int num) {
        for (int index = 0; index < MAX_SIZE; index++) { // go through horizontal constraints
            if (index != col) {
                this.horizontal[row][index].removeAvailable(num);
            } // end of if statement
        } // end of for loop

        for (int index = 0; index < MAX_SIZE; index++) { // go thorough vertical constraints
            if (index != row) {
                this.vertical[index][col].removeAvailable(num);
            } // end of if statement
        } // end of for loop


        // go through box constraints
        int boxrowstart, boxrowend, boxcolstart, boxcolend; // to track location of box
        if (row < 3) {
            if (col < 3) { // box 1
                boxrowstart = BOX1_ROW_START;
                boxrowend = BOX1_ROW_END;
                boxcolstart = BOX1_COL_START;
                boxcolend = BOX1_COL_END;
            } else if (col < 6) { // box 2
                boxrowstart = BOX2_ROW_START;
                boxrowend = BOX2_ROW_END;
                boxcolstart = BOX2_COL_START;
                boxcolend = BOX2_COL_END;
            } else { // box 3
                boxrowstart = BOX3_ROW_START;
                boxrowend = BOX3_ROW_END;
                boxcolstart = BOX3_COL_START;
                boxcolend = BOX3_COL_END;
            } // end of if-else statements
        } else if (row < 6) {
            if (col < 3) { // box 4
                boxrowstart = BOX4_ROW_START;
                boxrowend = BOX4_ROW_END;
                boxcolstart = BOX4_COL_START;
                boxcolend = BOX4_COL_END;
            } else if (col < 6) { // box 5
                boxrowstart = BOX5_ROW_START;
                boxrowend = BOX5_ROW_END;
                boxcolstart = BOX5_COL_START;
                boxcolend = BOX5_COL_END;
            } else { // box 6
                boxrowstart = BOX6_ROW_START;
                boxrowend = BOX6_ROW_END;
                boxcolstart = BOX6_COL_START;
                boxcolend = BOX6_COL_END;
            } // end of if- else statements
        } else {
            if (col < 3) { // box 7
                boxrowstart = BOX7_ROW_START;
                boxrowend = BOX7_ROW_END;
                boxcolstart = BOX7_COL_START;
                boxcolend = BOX7_COL_END;
            } else if (col < 6) { // box 8
                boxrowstart = BOX8_ROW_START;
                boxrowend = BOX8_ROW_END;
                boxcolstart = BOX8_COL_START;
                boxcolend = BOX8_COL_END;
            } else { // box 9
                boxrowstart = BOX9_ROW_START;
                boxrowend = BOX9_ROW_END;
                boxcolstart = BOX9_COL_START;
                boxcolend = BOX9_COL_END;
            } // end of if-else statements
        } // end of if-else statements

        for (int rowindex = boxrowstart; rowindex <= boxrowend; rowindex++) {
            if (rowindex != row) {
                for (int colindex = boxcolstart; colindex <= boxcolend; colindex++) {
                    if (colindex != col) {
                        this.box[rowindex][colindex].removeAvailable(num);
                    } // end of if statement
                } // end of for loop
            } // end of if statement
        } // end of for loop
    } // end of updateValidMoves
} // end of Constraints Class