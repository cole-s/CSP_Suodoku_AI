/**
Class: Constraints
@author Cole Schaar
Controls all of the different types of constrants needed for the AI to function
*/

class Constraints{
    private HorizontalConstraint[] horizontal;
    private VerticalConstraint[] vertical;
    private BoxConstraint[] box;

    public Constraints(){

    }

    public HorizontalConstraint[] getHorizontal() { return this.horizontal; }
    public VerticalConstraint[] getVertical() { return this.vertical; }
    public BoxConstraint[] getBox() { return this.box; }
    public void setHorizontal(HorizontalConstraint[] horizontal) { this.horizontal = horizontal; }
}