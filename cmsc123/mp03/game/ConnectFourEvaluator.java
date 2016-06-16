package cmsc123.mp03.game;

import cmsc123.mp03.ai.EvaluatorInterface;

public class ConnectFourEvaluator implements EvaluatorInterface {

	private int nodeBlockCounter = 0;
	private BoardNode boardNode;
	
    @Override
    public int evaluate(Object obj) {
        boardNode = (BoardNode) obj;
        
        int[][] board = boardNode.getBoard();
        
        int blockFourCounter = 0;
        int blockThreeCounter = 0;
        int blockTwoCounter = 0;
        
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
                	
                	nodeBlockCounter = 0;
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
                	
                	nodeBlockCounter = 0;
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
                	
                	nodeBlockCounter = 0;
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
                	
                	nodeBlockCounter = 0;
        		}
        	}
        }
        
        return ((3 * blockThreeCounter) + (2 * blockTwoCounter))
        		+ ((boardNode.getCurrentPlayer() == Player.PLAYER_1) ? (-10000000) : (100000 * blockFourCounter) );
    }
    
    private boolean checkChipValidity(int value) {
    	
    	if (value == boardNode.getCurrentPlayer() || value == 0) {
	    	if (value == boardNode.getCurrentPlayer()) {
	    		nodeBlockCounter++;
	    	}
	    	return true;
    	}
    	
    	return false;
    }
}
