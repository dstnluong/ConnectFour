import java.awt.Color;
import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import java.awt.event.*;


public class main {
    private static Game game;
    private static Board board;
    private JFrame frame;
    private static final String titleText = "ConnectFour";
    private static final Color background = new Color(60,95,122);

    private main(){ 
        final int minWindowWidth = 710;
        final int minWindowHeight = 730;

        /* Main JFrame */
        frame = new JFrame();
        //frame.setMinimumSize(new Dimension(minWindowWidth, minWindowHeight));
        frame.setSize(minWindowWidth, minWindowHeight);
        //frame.pack();
        frame.setTitle(titleText);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(background);

        frame.add(board = new Board());
        frame.setVisible(true);

        
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                int x = event.getX(); //returns x-coordinate of the current location of the mouse
                int y = event.getY(); //returns y-coordinate of the current location of the mouse
                game.makeMove(y);
            }
        });
    }
    public static void main(String [] args){
        SwingUtilities.invokeLater(main::new); 
    }
}