package cmsc123.mp03.framework;

public class GameController implements ControllerInterface {

    private GameInterface game;
    
    public GameController(GameInterface game) {
        this.game = game;
    }

    @Override
    public void start() {
        game.start();
    }
    
}
