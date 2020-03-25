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

    public int countAvailableMoves(int rowindex, int colindex) {
        int count = 0;

        count += this.horizontal[rowindex][colindex].countAvailable();
        count += this.vertical[rowindex][colindex].countAvailable();
        count += this.box[rowindex][colindex].countAvailable();

        return count;
    } // end of countAvailableMoves

    public void removeMove(int rowindex, int colindex, int number) {
        this.horizontal[rowindex][colindex].removeAvailable(number);
        this.vertical[rowindex][colindex].removeAvailable(number);
        this.box[rowindex][colindex].removeAvailable(number);
    } // end of removeMove

    public void updateValidMoves(int row, int col, int num) {
        if(this.horizontal[row][col].updateValidMoves(num)) {
            for(int index = 0; index < MAX_SIZE; index++) {
                if(index != col) {
                    this.horizontal[row][index].getAvailable()[num] = -1;
                }
            }
        }

        if(this.vertical[row][col].updateValidMoves(num)) {
            for(int index = 0; index < MAX_SIZE; index++) {
                if(index != row) {
                    this.vertical[index][col].getAvailable()[num] = -1;
                }
            }
        }

        if(this.box[row][col].updateValidMoves(num)) {
          
        }
    }
} // end of Constraints Class