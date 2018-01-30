import java.awt.Color;

public class GamePiece {
    private boolean filled = false; // true is circle is claimed
    private boolean isRed = true; //default color is red
    private int row; // row in the board
    private int column; // column in the board

    public GamePiece(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isFilled() {
        return filled;   
    }

    public void setFilled() {
        filled = true;
    }

    public void setYellow() {
        isRed = false;
    }

    public boolean isRed() {
        return isRed;
    }
    public Color getColor(){
        if(!filled){
            return new Color(255,255,255); // white
        } else{ 
            if(isRed){
                return new Color(255, 80, 80); // red
            } else {
                return new Color(255, 255, 102);  //yellow
            }
        }
    }
}
