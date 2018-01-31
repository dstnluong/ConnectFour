import javax.swing.JComponent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Random;

class Board extends JComponent {
    boolean isRedTurn; //player
    boolean gameOver; // becomes true after a win

    private static final Color red = new Color(255, 80, 80);
    private static final Color yellow = new Color(255, 255, 102);
    private static final Color white = new Color(255, 255, 255);
    private final int rows = 6;
    private final int columns = 7;
    private final int diameter = 90; // gamepiece dimension
    private GamePiece lastPieceAdded; //keep track of last piece played; used in isGameOver()
    private int playerOneWins, playerTwoWins; //track wins

    //set up array of circle to keep track of status (filled or not filled/ color/ etc.)
    public GamePiece[][] board = new GamePiece[rows][columns];
   
    public Board() {
        isRedTurn = (Math.random() < 0.5); //first player is random
        gameOver = false; 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new GamePiece(i, j);
            }
        }
        lastPieceAdded = board[0][0]; //dummy variable
        playerOneWins = 0;
        playerTwoWins = 0;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Impact", Font.PLAIN, 30));

        //draws and fills board with circles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GamePiece test = board[i][j];

                Ellipse2D.Double circle = new Ellipse2D.Double(j * 100 + 5, i * 100 + 10 + 80, diameter, diameter);
                g2.setColor(test.getColor());
                g2.fill(circle);
                g2.draw(circle);
            }
        }

        //draw and color player names
        if(isRedTurn){
            g2.setColor(white);
            g2.drawString("Player 2", 585, 50);
            g2.setColor(red); 
            g2.drawString("Player 1", 20, 50);
           
        } else {
            g2.setColor(white);
            g2.drawString("Player 1", 20, 50);
            g2.setColor(yellow); 
            g2.drawString("Player 2", 585, 50);
        }

        //print player who wins, otherwise print score
        if(gameOver){
            if(isRedTurn){
                g2.drawString("Player 1 wins!!", 260, 50);
            } else {
                g2.drawString("Player 2 wins!!", 260, 50);
            }
        } else {
            String player1 = Integer.toString(playerOneWins);
            String player2 = Integer.toString(playerTwoWins);
            g2.setColor(white);
            g2.drawString(player1 + " - " + player2, 323, 50);
        }
    }

    //add a piece, declare red or yellow, update last piece added, update player turn
    public void setGamePiece(int row, int column) {
        board[row][column].setFilled();
        if(!isRedTurn){ //determine color
            board[row][column].setYellow();
        }
        lastPieceAdded = board[row][column]; //update last gamepiece played
        isGameOver();
        if(!gameOver) { //don't switch turns if game is over to make finding the winner easier
            isRedTurn = !isRedTurn; // switch player's turn 
        } else {
            incrementWins(isRedTurn); //if winner has been decided, update score
        }
        repaint();
    }
    
    //reset pieces, loser goes first, reset last piece added (not needed tbh)
    public void newGame(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                board[r][c].resetPiece();
            }
        }
        lastPieceAdded = board[0][0];
        gameOver = false;
        isRedTurn = !isRedTurn; //loser goes first
        repaint();
    }
    //check if there is a winner
    public void isGameOver()  {
        int i = lastPieceAdded.getRow();
        int j = lastPieceAdded.getColumn();

        ArrayList<GamePiece> tests = new ArrayList<>();// to check win conditions

        //vertical 
        for (int k = i; k < rows; k++) {
            tests.add(board[k][j]);
        }
        isFourInARow(tests);

        // horizontal 
        for (int k = 0; k < columns; k++) {
            tests.add(board[i][k]);
        }
        isFourInARow(tests);

        //ascending diagonal    
        int diagonalRow = i;
        int diagonalColumn = j;

        while(diagonalRow < rows - 1 && diagonalColumn > 0) {
            diagonalRow++;
            diagonalColumn--;
        }
        while (diagonalRow >= 0 && diagonalColumn < columns) {
            tests.add(board[diagonalRow][diagonalColumn]);
            diagonalRow--; 
            diagonalColumn++;
        }
        isFourInARow(tests);

        // descending diagonal 
        diagonalRow = i;
        diagonalColumn = j;

        while(diagonalRow > 0 && diagonalColumn > 0) {
            diagonalRow--;
            diagonalColumn--;
        }
        while (diagonalRow < rows && diagonalColumn < columns) {
            tests.add(board[diagonalRow][diagonalColumn]);
            diagonalRow++;
            diagonalColumn++;
        }
        isFourInARow(tests);
    }

    // check for 4 same colors in a row 
    public void isFourInARow(ArrayList<GamePiece> test) {
        if (test.size() >= 4) {
            int streak = 1; // same streak variable used in horizontal win condition
            for (int k = 0; k < test.size() - 1; k++) {
                GamePiece current = test.get(k);
                GamePiece next = test.get(k + 1);
                if(current.isFilled() && next.isFilled() && current.isRed() == next.isRed()) { //must be filled and same color
                    streak++;
                } else {
                    streak = 1;
                }
                if (streak == 4) {
                    gameOver = true;
                }
            }
        }
        test.clear();
    }

    //update standings
    public void incrementWins(boolean redWins){
        if(redWins){
            playerOneWins++;
        } else {
            playerTwoWins++;
        }
    }

    public GamePiece getGamePiece(int r, int c) {
        return board[r][c];
    }

    public boolean getIsRedTurn() {
        return isRedTurn;
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public int getDiameter() {
        return diameter;
    }
}
