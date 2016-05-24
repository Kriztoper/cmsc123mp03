package cmsc123.mp03.game;

import java.util.Random;

import cmsc123.mp03.ai.EvaluatorInterface;

public class ConnectFourEvaluator implements EvaluatorInterface {

    @Override
    public int evaluate(Object obj) {
        BoardNode boardNode = (BoardNode) obj;
        
        Random rand = new Random();
        
        return rand.nextInt(100);
    }

}
