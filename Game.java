import java.util.Arrays;
import java.util.Random;
public class Game {
    private Board board;
    private int playerTurn;
    public Game (){
        board = new Board();
        Random random = new Random();
        playerTurn = random.nextInt(1) + 1;
    }
    /*
    public GamePiece[][] getBoard(){
        return board;
    }
    */
    public void makeMove(int yCoord){
        
        int column = convertXCoordinatesToColumn(yCoord);

        if(!board.isColumnFull(column)){
            board.addGamePiece(column, playerTurn);
            if(playerTurn == 1) {
                playerTurn = 2;
            }
            if (playerTurn == 2){
                playerTurn = 1;
            }   
        }
    }
    /*
    public boolean checkDraw(){

        for(int j = 0; j < 7; j++){
            if(board[0][j].getPlayer() == 0){
                return false;
            }
        }
        return true;
    }
    */
    public int convertXCoordinatesToColumn(int xCoord){
        if(xCoord < 110) {
            return 0; 
        } else if(xCoord < 210) {
            return 1; 
        } else if(xCoord < 310) {
            return 2; 
        } else if(xCoord < 410) {
            return 3; 
        } else if(xCoord < 510) {
            return 4; 
        } else if(xCoord < 610) {
            return 5; 
        } else {
            return 6;
        }
        /*
        else if(xCoord < 710) {
            return 6; 
        }
        */
    }

        /*
    public int convertYCoordinatesToRow(int yCoord){
        if(yCoord < 110) {
            return 0; 
        } else if(yCoord < 210) {
            return 1; 
        } else if(yCoord < 310) {
            return 2; 
        } else if(yCoord < 410) {
            return 3; 
        } else if(yCoord < 510) {
            return 4; 
        } else if(yCoord < 610) {
            return 5; 
        } else if(yCoord < 710) {
            return 6; 
        }
    }
    */
}
