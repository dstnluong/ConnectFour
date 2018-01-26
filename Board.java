import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;


public class Board extends JComponent {
    private final int columns = 7;
    private final int rows = 6;
    private final int padding = 130;
    private final GamePiece[][] slots = new GamePiece[columns][rows];

    public Board(){
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                GamePiece p = new GamePiece(i, j);
                slots[i][j] = p;
            }
        }
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        Rectangle rec = new Rectangle (0, 0, 710, 110);
        g2.setColor(new Color(51, 102, 255));
        g2.draw(rec);
        g2.fill(rec);
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                GamePiece c = slots[i][j];

                Ellipse2D.Double circle = new Ellipse2D.Double(c.getXCoord(), c.getYCoord() + padding, c.getRadius(), c.getRadius()); 
                g2.draw(circle);
                g2.setColor(c.getColor());
                g2.fill(circle);
            }
        }
    }
    public GamePiece[][] getSlots(){
        return slots;
    }
    public void updateColor(){
        repaint();
    }
    public void addGamePiece(int column, int player) {
        for(int i = 5; i > 0; i--) {
            if(slots[column][i].getPlayer() == 0){
                slots[column][i].setPlayer(player);
                repaint();
                break;
            }
        }
    }
    public boolean isColumnFull(int column) {
        int counter = 0;
        for(int i = 0; i < 6; i++){
            if(slots[i][column].getPlayer() == 0){
                continue;
            } else {
                counter++;
            }
        }
        return counter == 6;
    }

    public void resetBoard(){
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                slots[i][j].setPlayer(0);
            }
        }
    }
     public boolean checkWinner(){

        // horizontal check 
        for (int j = 0; j < rows - 3 ; j++ ){
            for (int i = 0; i < columns; i++){
                int temp = slots[i][j].getPlayer();
                if (temp != 0 && temp == slots[i][j + 1].getPlayer() && temp == slots[i][j + 2].getPlayer() && temp == slots[i][j + 3].getPlayer()){
                    return true;
                }           
            }
        }
        //vertical check
        for (int j = 0; j < rows ; j++ ){
            for (int i = 0; i < columns - 3; i++){
                int temp = slots[i][j].getPlayer();
                if (temp != 0 && temp == slots[i + 1][j].getPlayer() && temp == slots[i + 2][j].getPlayer() && temp == slots[i + 3][j].getPlayer()){
                    return true;
                }           
            }
        }
        // ascendingDiagonalCheck 
        for (int i = 3; i < rows; i++){
            for (int j = 0; j < columns - 3; j++){
                int temp = slots[i][j].getPlayer();
                if (temp != 0 && temp == slots[i + 1][j + 1].getPlayer() && temp == slots[i + 2][j + 2].getPlayer() && temp == slots[i + 3][j + 3].getPlayer()){
                    return true;
                }
            }
        }
        // descendingDiagonalCheck
        for (int i = 0; i < rows - 3; i++){
            for (int j = 0; j < columns - 3; j++){
                int temp = slots[i][j].getPlayer();
                if (temp != 0 && temp == slots[i - 1][j + 1].getPlayer() && temp == slots[i - 2][j + 2].getPlayer() && temp == slots[i - 3][j + 3].getPlayer()){
                    return true;
                }
            }
        }
        return false;
    } 
    public void resetGame(){
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++){ 
                slots[i][j].setPlayer(0); 
            }
        }
    }
    
}
