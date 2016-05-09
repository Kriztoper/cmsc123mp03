package cmsc123.mp03.game;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import cmsc123.mp03.framework.GameInterface;
import cmsc123.mp03.framework.ListenerInterface;

public class Game implements GameInterface {

    private GameFrame frameContainer;
    private Board board;
    
    private BufferedImage gameImage;
    
    public Game(GameFrame frameContainer) {
        this.frameContainer = frameContainer;
        gameImage = new BufferedImage(640, 640, BufferedImage.TYPE_INT_RGB);
        
        setMenuListeners();
        setGameListeners();
    }

    /**
     * Sets menu listeners.
     */
    private void setMenuListeners() {

        // Set action for "Start Game" button
        frameContainer.getStartGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        
                // Show Game Panel
                frameContainer.getFrame().setCurrentPanel(frameContainer.getGamePanel());
                initializeGame();
            }
        });
    }

    /**
     * Sets game listeners.
     */
    private void setGameListeners() {
    	frameContainer.getGamePanel().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				board.react(e);
			}
    		
		});
    }
    
    /**
     * Initializes game.
     */
    private void initializeGame() {
        board = new Board();
        
        board.addListener("end", new ListenerInterface() {
            
            @Override
            public void obey(Object event) {
                frameContainer.getFrame().setCurrentPanel(frameContainer.getMenuPanel());
                // TODO: Some sort of congratulations here for the player.
            }
        });
        
        board.addListener("update", new ListenerInterface() {
            
            @Override
            public void obey(Object event) {
                drawBoard();
            }
        });
        
        drawBoard();
    }
    
    /**
     * Draws the board based on the game's current state.
     * 
     * @param Graphics2D graphics Graphics2D object of the game panel
     */
    private void drawBoard() {
        Graphics2D gameGraphics = (Graphics2D) gameImage.getGraphics();
        gameGraphics.clearRect(0, 0, 640, 640);
        
        // Let the board draw itself
        board.draw(gameGraphics);
        
        // Set image to be drawn
        frameContainer.getGamePanel().setGameImage(gameImage);
        
        // Paint the image to panel
        frameContainer.getGamePanel().repaint();
    }
    
    @Override
    public void start() {
        
        // Show Menu
        frameContainer.getFrame().setCurrentPanel(frameContainer.getMenuPanel());
        frameContainer.show();
    }

}
