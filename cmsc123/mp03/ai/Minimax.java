package cmsc123.mp03.ai;

import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;
import cmsc123.mp03.game.BoardNode;

public class Minimax {

    private static int MAX = 1;
    private static int MIN = 2;

//    private int losingNodesCounter;
    
    private EvaluatorInterface evaluator;
    private ChildGeneratorInterface childGenerator;
    private PrunerInterface<NodeInterface<BoardNode>> pruner;
    
    public Minimax(EvaluatorInterface evaluator, ChildGeneratorInterface childGenerator, PrunerInterface pruner) {
        this.evaluator = evaluator;
        this.childGenerator = childGenerator;
        this.pruner = pruner;
    }
    
    public BoardNode getBestMove(BoardNode board, int maxTreeDepth) {
//        losingNodesCounter = 0;
    	NodeInterface<BoardNode> node = new Node<BoardNode>(board);
        node.setChildren(childGenerator.generateChildren(node));
        NodeInterface<BoardNode> bestNode = node.getChildren()[0];
        
        System.out.println("##########################################################################################");
        // 1st level node evaluation to check for win in 1 move
        for (NodeInterface<BoardNode> currentChild : node.getChildren()) {

            currentChild.getValue().setValue(evaluator.evaluate(currentChild.getValue()));//getValue(currentChild, 1, 1, MIN);
            
            if (currentChild.getValue().getValue() > 100000) {
            	return currentChild.getValue();
            }
        }
        
        //  2 level node evaluation blocking move (prevent opponent to win in 1 move)
        node.getValue().setCurrentPlayer(2);
        int childIndex = 0;
        node.setChildren(childGenerator.generateChildren(node));
        for (NodeInterface<BoardNode> currentChild : node.getChildren()) {

            currentChild.getValue().setValue(evaluator.evaluate(currentChild.getValue()));//getValue(currentChild, 1, 1, MIN);
            
            if (currentChild.getValue().getValue() > 100000) {
            	node.getValue().setCurrentPlayer(1);
            	node.setChildren(childGenerator.generateChildren(node));
            	Node[] newNode = ((Node[]) node.getChildren());
            	System.out.println("I JUST FOUND A BLOCKING MOVE, RETURNING");
            	return (BoardNode) newNode[childIndex].getValue();
            }
            childIndex++;
        }
        node.getValue().setCurrentPlayer(1);
        node.setChildren(childGenerator.generateChildren(node));
        bestNode = node.getChildren()[0];
        
        int x = 1;
        for (NodeInterface<BoardNode> currentChild : node.getChildren()) {

            currentChild = getValue(currentChild, 1, maxTreeDepth, MIN);
            
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("child "+(x++)+" value "+currentChild.getValue().getValue());
            currentChild.getValue().displayBoard();
            
            System.out.println("Children");
            ((Node<BoardNode>) currentChild).displayChildren();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            
            if (currentChild.getValue().getValue() > bestNode.getValue().getValue()) {
                bestNode = currentChild;
            }
        }
        
        System.out.println("chosen best move and value "+bestNode.getValue().getValue());
        bestNode.getValue().displayBoard();
        return bestNode.getValue();
    }
    
    private NodeInterface<BoardNode> getValue(NodeInterface<BoardNode> node, int level, int maxLevel, int mode) {

    	node.getValue().setLevel(level);
    	
    	// Base case
    	if (level == maxLevel || isGameOver(node.getValue().getBoard())) {
            node.getValue().setValue(evaluator.evaluate(node.getValue()));
            
//            System.out.println("level "+level+" child value "+node.getValue().getValue());
//            node.getValue().displayBoard();
            
            return node;
        } else {
            node.setChildren(pruner.prune(childGenerator.generateChildren(node), mode));
            
            for (NodeInterface<BoardNode> n : node.getChildren()) {
                NodeInterface<BoardNode> child = getValue(n, level + 1, maxLevel, mode == MAX ? MIN : MAX);
                n.getValue().setValue(child.getValue().getValue());
            }

//            if (level == 1) {
//            	if (((Node) node).isAbleToRemoveLosingMoves()) {
//            		node.getValue().setValue(-1000000);
//            		return node;
//            	}
//            }
            
            BoardNode value = null;
//            int possibleBestValue = -10000000;
            try {
            	value = node.getChildren()[0].getValue();
//            	possibleBestValue = value.getValue();
            } catch (Exception e) {
            	
            }
            	
            for (NodeInterface<BoardNode> n : node.getChildren()) {
                if (mode == MAX && n.getValue().getValue() > value.getValue()) {
                    value = n.getValue();
                } else if (mode == MIN && n.getValue().getValue() < value.getValue()) {
//                	if (n.getValue().getValue() < 0) {
//                		if (n.getValue().getValue() > possibleBestValue)
//                			possibleBestValue = n.getValue().getValue();
//                		losingNodesCounter++;
//                	}
                	value = n.getValue();
                }
            }
            
//            if (losingNodesCounter == node.getChildren().length) {
//            	System.out.println("RETURNING HIGHER VALUED NODES INSTEAD");
//            	try {
//                	node.getValue().setValue(possibleBestValue);
//                } catch (Exception e) {
//                	
//                }
//            }
            
            try {
            	node.getValue().setValue(value.getValue());
            } catch (Exception e) {
            	
            }
            	
//            System.out.println("level "+level+" child value "+node.getValue().getValue());
//            node.getValue().displayBoard();
            
            return node;
        }
    }
    
    public boolean isGameOver(int[][] board) {
    	
    	// horizontal
    	for (int i = 0; i < 6; i++) {
    		for (int j = 0; j <= 3; j++) {
    			if (board[j][i] != 0 && board[j][i] == board[j+1][i] && board[j][i] == board[j+2][i] && board[j][i] == board[j+3][i]) {
    				return true;
    			}
    		}
    	}

    	// vertical
    	for (int j = 0; j < 7; j++) {
    		for (int i = 0; i <= 2; i++) {
    			if (board[j][i] != 0 && board[j][i] == board[j][i+1] && board[j][i] == board[j][i+2] && board[j][i] == board[j][i+3]) {
    				return true;
    			}
    		}
    	}

    	// left diagonal
    	for (int i = 0; i <= 2; i++) {
    		for (int j = 0; j <= 3; j++) {
    			if (board[j][i] != 0 && board[j][i] == board[j+1][i+1] && board[j][i] == board[j+2][i+2] && board[j][i] == board[j+3][i+3]) {
    				return true;
    			}
    		}
    	}
    	
    	// right diagonal
    	for (int i = 5; i >= 3; i--) {
    		for (int j = 0; j <= 3; j++) {
    			if (board[j][i] != 0 && board[j][i] == board[j+1][i-1] && board[j][i] == board[j+2][i-2] && board[j][i] == board[j+3][i-3]) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
}
