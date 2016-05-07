package cmsc123.mp03.game;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import cmsc123.mp03.framework.GameInterface;
import cmsc123.mp03.framework.ListenerInterface;

public class Game implements GameInterface {

    private GameFrame frameContainer;
    private Board board;
    
    public Game(GameFrame frameContainer) {
        this.frameContainer = frameContainer;
        
        setMenuListeners();
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
     * Initializes game.
     */
    private void initializeGame() {
        board = new Board();
        
        board.addListener("end", new ListenerInterface() {
            
            @Override
            public void obey(Object event) {
                frameContainer.getFrame().setCurrentPanel(frameContainer.getMenuPanel());
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

        // TODO: Add actual board drawing
        BufferedImage gameImage = new BufferedImage(640, 640, BufferedImage.TYPE_INT_RGB);
        Graphics2D gameGraphics = (Graphics2D) gameImage.getGraphics();
        
        // TODO: Pass gameGraphics to game entities
        
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