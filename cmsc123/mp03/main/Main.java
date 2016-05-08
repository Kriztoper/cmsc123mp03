package cmsc123.mp03.main;

import cmsc123.mp03.framework.GameController;
import cmsc123.mp03.game.Game;
import cmsc123.mp03.game.GameFrame;

public class Main {

    public static void main(String[] args) {
        GameFrame frame           = new GameFrame();
        Game game                 = new Game(frame);
        GameController controller = new GameController(game);
        
        controller.start();
    }
    
}
