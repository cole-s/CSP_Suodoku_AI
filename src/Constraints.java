/**
Class: Constraints
@author Cole Schaar
Controls all of the different types of constrants needed for the AI to function
*/

class Constraints {
    private final int MAX_SIZE = 9;
    private HorizontalConstraint[] horizontal;
    private VerticalConstraint[] vertical;
    private BoxConstraint[] box;

    public Constraints() {
        constructHorizontal();
        constructVertical();
        constructBox();
    } // end of constructor

    // help constructor methods:

    private void constructHorizontal() {
        this.horizontal = new HorizontalConstraint[MAX_SIZE];

        for(int index = 0; index < MAX_SIZE; index++){
            this.horizontal[index] = new HorizontalConstraint();
        } // end of for loop
    } // end of constructHorizontal

    private void constructVertical() {
        this.vertical = new VerticalConstraint[MAX_SIZE];

        for(int index = 0; index < MAX_SIZE; index++){
            this.vertical[index] = new VerticalConstraint();
        } // end of for loop
    } // end of constructVertical

    private void constructBox() {
        this.box = new BoxConstraint[MAX_SIZE];

        for(int index = 0; index < MAX_SIZE; index++){
            this.box[index] = new BoxConstraint();
        } // end of for loop
    } // end of constructBox

    // end of help constructor methods

    public HorizontalConstraint[] getHorizontal() { return this.horizontal; }
    public VerticalConstraint[] getVertical() { return this.vertical; }
    public BoxConstraint[] getBox() { return this.box; }
    public void setHorizontal(HorizontalConstraint[] horizontal) {
        for(int index = 0; index < MAX_SIZE; index++){
            this.horizontal[index] = new HorizontalConstraint(horizontal[index].getAvailable());
        } // end of for loop
    } // end of setHorizontal

    public void setVertical(VerticalConstraint[] vertical) {
        for(int index = 0; index < MAX_SIZE; index++){
            this.vertical[index] = new VerticalConstraint(vertical[index].getAvailable());
        } // end of for loop
    } // end of setVertical

    public void setBox(BoxConstraint[] box) {
        for(int index = 0; index < MAX_SIZE; index++) {
            this.box[index] = new BoxConstraint(box[index].getAvailable());
        } // end of for loop
    } // end of setBox
} // end of Constraints Class