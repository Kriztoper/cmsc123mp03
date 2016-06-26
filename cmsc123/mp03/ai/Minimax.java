package cmsc123.mp03.ai;

import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;
import cmsc123.mp03.game.BoardNode;
import cmsc123.mp03.game.ConnectFourWinChecker;

public class Minimax {

    private static int MAX = 1;
    private static int MIN = 2;

    private EvaluatorInterface evaluator;
    private ChildGeneratorInterface childGenerator;
    private PrunerInterface<NodeInterface<BoardNode>> pruner;
    private ConnectFourWinChecker winChecker;
    
    public Minimax(EvaluatorInterface evaluator, ChildGeneratorInterface childGenerator, PrunerInterface pruner) {
        this.evaluator = evaluator;
        this.childGenerator = childGenerator;
        this.pruner = pruner;
        winChecker = new ConnectFourWinChecker();
    }
    
    public BoardNode getBestMove(BoardNode board, int maxTreeDepth) {
    	NodeInterface<BoardNode> node = new Node<BoardNode>(board);
        node.setChildren(childGenerator.generateChildren(node));
        NodeInterface<BoardNode> bestNode = node.getChildren()[0];
        
        // 1st level node evaluation to check for win in 1 move
        for (NodeInterface<BoardNode> currentChild : node.getChildren()) {

            currentChild.getValue().setValue(evaluator.evaluate(currentChild.getValue()));
            
            if (currentChild.getValue().getValue() > 100000) {
            	return currentChild.getValue();
            }
        }
        
        //  2 level node evaluation blocking move (prevent opponent to win in 1 move)
        node.getValue().setCurrentPlayer(2);
        int childIndex = 0;
        node.setChildren(childGenerator.generateChildren(node));
        for (NodeInterface<BoardNode> currentChild : node.getChildren()) {

            currentChild.getValue().setValue(evaluator.evaluate(currentChild.getValue()));
            
            if (currentChild.getValue().getValue() > 100000) {
            	node.getValue().setCurrentPlayer(1);
            	node.setChildren(childGenerator.generateChildren(node));
            	Node[] newNode = ((Node[]) node.getChildren());
            	return (BoardNode) newNode[childIndex].getValue();
            }
            childIndex++;
        }
        node.getValue().setCurrentPlayer(1);
        node.setChildren(childGenerator.generateChildren(node));
        bestNode = node.getChildren()[0];
        
        for (NodeInterface<BoardNode> currentChild : node.getChildren()) {

            currentChild = getValue(currentChild, 1, maxTreeDepth, MIN);
            
            if (currentChild.getValue().getValue() > bestNode.getValue().getValue()) {
                bestNode = currentChild;
            }
        }
        
        return bestNode.getValue();
    }
    
    private NodeInterface<BoardNode> getValue(NodeInterface<BoardNode> node, int level, int maxLevel, int mode) {

    	node.getValue().setLevel(level);
    	
    	// Base case
    	if (level == maxLevel || winChecker.isGameOver(node.getValue().getBoard())) {
            node.getValue().setValue(evaluator.evaluate(node.getValue()));
            
            return node;
        } else {
            node.setChildren(pruner.prune(childGenerator.generateChildren(node), mode));
            
            for (NodeInterface<BoardNode> n : node.getChildren()) {
                NodeInterface<BoardNode> child = getValue(n, level + 1, maxLevel, mode == MAX ? MIN : MAX);
                n.getValue().setValue(child.getValue().getValue());
            }

            BoardNode value = null;
            try {
            	value = node.getChildren()[0].getValue();
            } catch (Exception e) {
            	
            }
            	
            for (NodeInterface<BoardNode> n : node.getChildren()) {
                if (mode == MAX && n.getValue().getValue() > value.getValue()) {
                    value = n.getValue();
                } else if (mode == MIN && n.getValue().getValue() < value.getValue()) {
                	value = n.getValue();
                }
            }
            
            try {
            	node.getValue().setValue(value.getValue());
            } catch (Exception e) {
            	
            }
            	
            return node;
        }
    }
}
