import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.SwingUtilities;

public class main {

    private Board connect = new Board();
    private static final Color background = new Color(60,95,122);
    //private int titlePanelHeight = 150;
    private int frameWidth = 700;
    //private int frameHeight = 600 + titlePanelHeight;
    private int frameHeight = 630;
    public main(){
        JFrame frame = new JFrame();
        frame.setSize(frameWidth,frameHeight);
        frame.setTitle("ConnectFour");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(background);
        frame.add(connect);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if(!connect.getGameOver()) {
                    boolean addedSuccessfully = false; // true if piece was able to be added 
                    int c = event.getX() / 100; // find column

                    if (c < connect.getRows() + 1) {
                        //colors bottom-most circle 
                        for (int r = connect.getRows() - 1; r >= 0; r--) { // starts at bottom of array, moves up
                            if(!connect.getGamePiece(r, c).isFilled()) {
                                connect.setGamePiece(r, c); //add piece
                                addedSuccessfully = true;
                                break;
                            }
                        }
                    }
                    // only redraws if a new circle is colored (avoids double turn)
                    if (addedSuccessfully) {
                        connect.repaint(); //update graphics
                    }
                }
            }
        });
        frame.setVisible(true);
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(main::new); 
    }
}
