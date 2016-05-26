package cmsc123.mp03.game;

import java.util.Random;

import cmsc123.mp03.ai.EvaluatorInterface;

public class ConnectFourEvaluator implements EvaluatorInterface {

	private int nodeBlockCounter = 0;
	private BoardNode boardNode;
	
    @Override
    public int evaluate(Object obj) {
        boardNode = (BoardNode) obj;
        int[] maxCol = {0, 6}; // max column
        int[] maxRow = {0, 5}; // max row
        
        int[][] board = boardNode.getBoard();
        
        int blockFourCounter = 0;
        int blockThreeCounter = 0;
        int blockTwoCounter = 0;
        
//        GraphNode[][] graph = new GraphNode[7][6];
//        
//        // convert board array to graph
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 7; j++) {
//                graph[j][i] = new GraphNode(board[j][i]);
//            }
//        }
//        
//        // assign adjacent nodes
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 7; j++) {
//                if (i-1 >= 0 && i-1 <= 5)                         // set north
//                    graph[j][i].setNorth(graph[j][i+1]);
//                if (i-1 >= 0 && i-1 <= 5 && j-1 >= 0 && j-1 <= 6) // set northwest
//                    graph[j][i].setNorth(graph[j-1][i+1]);
//                if (j-1 >= 0 && j-1 <= 6)                         // set west
//                    graph[j][i].setNorth(graph[j-1][i+1]);
//                if (i+1 >= 0 && i+1 <= 5 && j-1 >= 0 && j-1 <= 6) // set southwest
//                    graph[j][i].setNorth(graph[j-1][i+1]);
//                if (i+1 >= 0 && i+1 <= 5)                         // set south
//                    graph[j][i].setNorth(graph[j-1][i+1]);
//                if (i+1 >= 0 && i+1 <= 5 && j+1 >= 0 && j+1 <= 6) // set southeast
//                    graph[j][i].setNorth(graph[j-1][i+1]);
//                if (j+1 >= 0 && j+1 <= 6)                         // set east
//                    graph[j][i].setNorth(graph[j-1][i+1]);
//                if (i-1 >= 0 && i-1 <= 5 && j+1 >= 0 && j+1 <= 6) // set northeast
//                    graph[j][i].setNorth(graph[j-1][i+1]);
//            }
//        }

        // horizontal
        for (int i = 0; i < 6; i++) {
          for (int j = 0; j <= 3; j++) {
                if (checkChipValidity(board[j][i]) && checkChipValidity(board[j+1][i]) && checkChipValidity(board[j+2][i]) && checkChipValidity(board[j+3][i])) {
                	if (nodeBlockCounter == 4) {		// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {	// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {	// block is 2
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
                	if (nodeBlockCounter == 4) {		// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {	// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {	// block is 2
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
                	if (nodeBlockCounter == 4) {		// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {	// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {	// block is 2
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
                	if (nodeBlockCounter == 4) {		// block is 4
                		blockFourCounter++;
                	} else if (nodeBlockCounter == 3) {	// block is 3
                		blockThreeCounter++;
                	} else if (nodeBlockCounter == 2) {	// block is 2
                		blockTwoCounter++;
                	}	
                	nodeBlockCounter = 0;
        		}
        	}
        }
        
        return ((100000 * blockFourCounter) + (3 * blockThreeCounter) + (2 * blockTwoCounter));
    }
        // horizontal
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 7; j++) {
                
//                if (boardNode.getBoard()[j][i] == boardNode.getCurrentPlayer()) {
//                    int counter = 0;
//                    for(int x = 1; x <= 3; x++) {
//                        if ((j-x >= 0 && boardNode.getBoard()[j-x][i] == boardNode.getCurrentPlayer())
//                                || (j+x <= 6 && boardNode.getBoard()[j+x][i] == boardNode.getCurrentPlayer())) {
//                            counter++;
//                        } else {
//                            if (counter == 4)
//                                blockFourCounter++;
//                            else if (counter == 3)
//                                blockThreeCounter++;
//                            else if (counter == 2)
//                                blockTwoCounter++;
//                            break;
//                        }
//                    }
//                }
//            }
//      Random rand = new Random();
        
//      return rand.nextInt(100);
        
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
