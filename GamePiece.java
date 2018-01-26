import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class GamePiece {
    private int player;
    private int xcoord;
    private int ycoord;
    private int radius;

    public GamePiece(int xcoord, int ycoord) {
        player = 0;
        this.xcoord = xcoord * 100 + 10;
        this.ycoord = ycoord * 90 + 10;
        radius = 80;
    }
    public int getRadius() {
        return radius;
    }
    public int getXCoord() {
        return xcoord;
    }
    public int getYCoord(){
        return ycoord;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int playerNumber) {
        player = playerNumber; 
    }

    public Color getColor() { //60 95 122
        switch (player) {
            case 0: return new Color(255, 255, 255); //white
            case 1: return new Color(255, 80, 80); // red
            case 2: return new Color(255, 255, 102); // yellow
            default: return null;
        }
    }
}
