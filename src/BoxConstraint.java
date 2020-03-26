/**
 * Class: BoxConstraint
 * @author Cole Schaar
 * CS 481 - Artifical Intelligence
 * Purpose: keeps track of box constraints on a spot
 */

class BoxConstraint {
    private final int MAX_SIZE = 9;
    private int[] available;

    public BoxConstraint() {
        this.available = new int[MAX_SIZE];
        for(int index = 0; index < MAX_SIZE; index++){
            this.available[index] = index+1;
        } // end of for loop
    } // end of constructor

    public BoxConstraint(int[] available) {
        setAvailable(available);
    } // end of constructor

    // getters and setters for the private variables
    public int[] getAvailable() { return this.available; } // end of getAvailable
    public void setAvailable(int[] available) {
        this.available = new int[MAX_SIZE];
        for(int index = 0; index < MAX_SIZE; index++){
            this.available[index] = available[index];
        } // end of for loop
    } // end of setAvailable

    /**
     * Method: removeAvailable
     * @param index - which number to be removed from available list
     */
    public void removeAvailable(int index) {
        this.available[index] = -1;
    } // end of removeAvailable
} // end of BoxConstraint Class