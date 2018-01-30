import javax.swing.JComponent;
import java.util.*;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.Graphics;

class Board extends JComponent {
    boolean isRedTurn; //player
    boolean gameOver; // becomes true after a win

    private final int rows = 6;
    private final int columns = 7;
    private final int diameter = 90;
    private GamePiece lastPieceAdded;

    //set up array of circle to keep track of status (filled or not filled/ color/ etc.)
    public GamePiece[][] board = new GamePiece[rows][columns];
   
    public Board() {
        isRedTurn = true;
        gameOver = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new GamePiece(i, j);
            }
        }
        lastPieceAdded = board[0][0]; //dummy variable
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //draws and fills board with circles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GamePiece test = board[i][j];

                g2.setColor(test.getColor());

                //TODO print a win statement

                Ellipse2D.Double circle = new Ellipse2D.Double(j * 100 + 5, i * 100 + 10, diameter, diameter);
                g2.fill(circle);
                g2.draw(circle);
            }
        }
    }

    //add a piece, declare red or yellow, update last piece added, update player turn
    public void setGamePiece(int row, int column) {
        board[row][column].setFilled();
        if(!isRedTurn){
            board[row][column].setYellow();
        }
        lastPieceAdded = board[row][column];
        isRedTurn = !isRedTurn; // switch player's turn 
        //isGameOver();

    }
   

    public void isGameOver()  {
        int i = lastPieceAdded.getRow();
        int j = lastPieceAdded.getColumn();

        ArrayList<GamePiece> tests = new ArrayList<>();// to check win conditions

        //vertical 
        for (int k = i; k < rows; k++) {
            tests.add(board[k][j]);
        }
        isFourInARow(tests);
        tests.clear();

        // horizontal 
        for (int k = 0; k < columns; k++) {
            tests.add(board[i][k]);
        }
        isFourInARow(tests);
        tests.clear();

        //ascending diagonal
        
        int diagonalRow = i;
        int diagonalColumn = j;

        while(diagonalRow != 5 || diagonalColumn != 0) {
            diagonalRow++;
            diagonalColumn--;
        }
        
        while (diagonalRow >= 0 && diagonalColumn <= 6) {
            tests.add(board[diagonalRow][diagonalColumn]);
            diagonalRow--;
            diagonalColumn++;
        }
        isFourInARow(tests);
        tests.clear();

        // descending diagonal 
        diagonalRow = i;
        diagonalColumn = j;

        while(diagonalRow != 0 || diagonalColumn != 0) {
            diagonalRow--;
            diagonalColumn--;
        }
        
        while (diagonalRow <= 5 && diagonalColumn <= 6) {
            tests.add(board[diagonalRow][diagonalColumn]);
            diagonalRow++;
            diagonalColumn++;
        }
        isFourInARow(tests);
        tests.clear();
    }

    // check for 4 same colors in a row 
    public void isFourInARow(ArrayList<GamePiece> test) {
        if (test.size() >= 4) {
            int streak = 1; // same streak variable used in horizontal win condition
            for (int k = 0; k < test.size()-1; k++) {
                GamePiece current = test.get(k);
                GamePiece next = test.get(k + 1);
                if(current.isFilled() && next.isFilled() && current.isRed() == next.isRed()) {
                    streak++;
                } else {
                    streak = 1;
                }
                if (streak == 4) {
                    gameOver = true;
                }
            }
        }
    }

    public GamePiece getGamePiece(int r, int c) {
        return board[r][c];
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