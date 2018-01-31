import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class main {

    private Board connect = new Board();
    private static final Color background = new Color(60,95,122);
    private final int titlePanelHeight = 150;
    private final int frameWidth = 700;
    private final int frameHeight = 600 + titlePanelHeight;
    private boolean winsUpdated;
    private Player player1, player2;
    public main(){
        JFrame frame = new JFrame();
        winsUpdated = false;
        frame.setSize(frameWidth,frameHeight);
        frame.setTitle("ConnectFour");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(background);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                int c = event.getX() / 100; // find column
                if(!connect.getGameOver()) {
                    if (c < connect.getRows() + 1) {
                        //colors bottom-most circle 
                        for (int r = connect.getRows() - 1; r >= 0; r--) { // starts at bottom of array, moves up
                            if(!connect.getGamePiece(r, c).isFilled()) {
                                connect.setGamePiece(r, c); //add piece
                                break;
                            }
                        }
                    }
                }
            }
        });
        
        //resets the game, must press to resume after a game ends
        JButton newGame = new JButton("New Game");
        newGame.setBounds(303, 60, 95, 30);
        newGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                connect.newGame();
                winsUpdated = false;
            }
        });

        frame.add(newGame);
        frame.add(connect);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(main::new); 
    }
}
