/**
Class: Constraints
@author Cole Schaar
Controls all of the different types of constrants needed for the AI to function
*/

class Constraints {
    private final int MAX_SIZE = 9;
    private HorizontalConstraint[][] horizontal;
    private VerticalConstraint[][] vertical;
    private BoxConstraint[][] box;
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
        constructHorizontal();
        constructVertical();
        constructBox();
    } // end of constructor

    // help constructor methods:

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

    public int[] getAvailableMoveList(int row, int col) {
        int[] available = new int[MAX_SIZE];
        for(int num = 1; num <= MAX_SIZE; num++) {
            if(this.horizontal[row][col].getAvailable()[num-1] > 0) {
                if(this.vertical[row][col].getAvailable()[num-1] > 0) {
                    if(this.box[row][col].getAvailable()[num-1] > 0) {
                        available[num-1] = num + 1;
                    }
                }
            }
        }

        return available;
    } // end of countAvailableMoves

    public void removeMove(int rowindex, int colindex, int number) {
        this.horizontal[rowindex][colindex].removeAvailable(number);
        this.vertical[rowindex][colindex].removeAvailable(number);
        this.box[rowindex][colindex].removeAvailable(number);
    } // end of removeMove

    public void updateValidMoves(int row, int col, int num) {
        for (int index = 0; index < MAX_SIZE; index++) {
            if (index != col) {
                this.horizontal[row][index].removeAvailable(num);
            }
        }

        for (int index = 0; index < MAX_SIZE; index++) {
            if (index != row) {
                this.vertical[index][col].removeAvailable(num);
            }
        }


        int boxrowstart, boxrowend, boxcolstart, boxcolend;
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
            }
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
            }
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
            }
        }

        for (int rowindex = boxrowstart; rowindex <= boxrowend; rowindex++) {
            if (rowindex != row) {
                for (int colindex = boxcolstart; colindex <= boxcolend; colindex++) {
                    if (colindex != col) {
                        this.box[row][col].removeAvailable(num);
                    }
                }
            }
        }

    }
} // end of Constraints Class