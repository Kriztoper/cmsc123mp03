package cmsc123.mp03.game;

import cmsc123.mp03.ai.EvaluatorInterface;

public class ConnectFourEvaluator implements EvaluatorInterface {

	private int nodeBlockCounter = 0;
	private BoardNode boardNode;
	private int enemyNodeBlockCounter = 0;
	
    @Override
    public int evaluate(Object obj) {
        boardNode = (BoardNode) obj;
        
        int[][] board = boardNode.getBoard();
        
        int blockFourCounter = 0;
        int blockThreeCounter = 0;
        int blockTwoCounter = 0;
        
        int enemyBlockThreeCounter = 0;
        int enemyBlockFourCounter = 0;
        
        // horizontal
        for (int i = 0; i < 6; i++) {
          for (int j = 0; j <= 3; j++) {
                if (checkChipValidity(board[j][i]) && checkChipValidity(board[j+1][i]) && checkChipValidity(board[j+2][i]) && checkChipValidity(board[j+3][i])) {
                	if (nodeBlockCounter == 4) {				// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {			// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {			// block is 2
                		blockTwoCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 3 && nodeBlockCounter == 1) {	// block is 3
                		enemyBlockThreeCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 4) {
                		enemyBlockFourCounter++;
                	}
                	
                	nodeBlockCounter = 0;
                	enemyNodeBlockCounter = 0;
                }
            }
        }
        
        // vertical
        for (int j = 0; j < 7; j++) {
          for (int i = 0; i <= 2; i++) {
                if (checkChipValidity(board[j][i]) && checkChipValidity(board[j][i+1]) && checkChipValidity(board[j][i+2]) && checkChipValidity(board[j][i+3])) {
                	if (nodeBlockCounter == 4) {				// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {			// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {			// block is 2
                		blockTwoCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 3 && nodeBlockCounter == 1) {	// block is 3
                		enemyBlockThreeCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 4) {
                		enemyBlockFourCounter++;
                	}
                	
                	nodeBlockCounter = 0;
                	enemyNodeBlockCounter = 0;
                }
            }
        }
        
        // diagonal (topLeft to bottomRight)
        for (int i = 0; i <= 2; i++) {
        	for (int j = 0; j <= 3; j++) {
        		if (checkChipValidity(board[j][i]) && checkChipValidity(board[j+1][i+1]) && checkChipValidity(board[j+2][i+2]) && checkChipValidity(board[j+3][i+3])) {
                	if (nodeBlockCounter == 4) {				// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {			// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {			// block is 2
                		blockTwoCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 3 && nodeBlockCounter == 1) {	// block is 3
                		enemyBlockThreeCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 4) {
                		enemyBlockFourCounter++;
                	}
                	
                	nodeBlockCounter = 0;
                	enemyNodeBlockCounter = 0;
        		}
        	}
        }

        // diagonal (bottomLeft to topRight)
        for (int i = 5; i >= 3; i--) {
        	for (int j = 0; j <= 3; j++) {
        		if (checkChipValidity(board[j][i]) && checkChipValidity(board[j+1][i-1]) && checkChipValidity(board[j+2][i-2]) && checkChipValidity(board[j+3][i-3])) {
                	if (nodeBlockCounter == 4) {				// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {			// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {			// block is 2
                		blockTwoCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 3 && nodeBlockCounter == 1) {	// block is 3
                		enemyBlockThreeCounter++;
                	}
                	
                	if (enemyNodeBlockCounter == 4) {
                		enemyBlockFourCounter++;
                	}
                	
                	nodeBlockCounter = 0;
                	enemyNodeBlockCounter = 0;
        		}
        	}
        }
        
        int value = ((100000 * blockFourCounter) + (3 * blockThreeCounter) + (2 * blockTwoCounter));
        
        return ((enemyBlockFourCounter != 0 && boardNode.getLevel() % 2 == 1) ? (-1 * value) : (value) );	
    }
    
    private boolean checkChipValidity(int value) {
    	int enemyPlayer = (boardNode.getCurrentPlayer() == 1 ? 2 : 1 );
    	
    	if (value == boardNode.getCurrentPlayer() || value == 0) {
	    	if (value == boardNode.getCurrentPlayer()) {
	    		nodeBlockCounter++;
	    	}
	    	return true;
    	} else if (value == enemyPlayer) {
	    	if (value == enemyPlayer) {
	    		enemyNodeBlockCounter++;
	    	}
	    	return true;
    	}
    	
    	return false;
    }
}
