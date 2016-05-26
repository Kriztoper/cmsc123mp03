package cmsc123.mp03.ai;

import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;
import cmsc123.mp03.game.BoardNode;

public class Minimax {

    private static int MAX = 1;
    private static int MIN = 2;

    private EvaluatorInterface evaluator;
    private ChildGeneratorInterface childGenerator;
    
    public Minimax(EvaluatorInterface evaluator, ChildGeneratorInterface childGenerator) {
        this.evaluator = evaluator;
        this.childGenerator = childGenerator;
    }
    
    public BoardNode getBestMove(BoardNode board, int maxTreeDepth) {
        NodeInterface<BoardNode> node = new Node<BoardNode>(board);
        node.setChildren(childGenerator.generateChildren(node));
        NodeInterface<BoardNode> bestNode = node.getChildren()[0];
        
        for (NodeInterface<BoardNode> currentChild : node.getChildren()) {

        	System.out.println("Enter");
        	
            currentChild = getValue(currentChild, 1, maxTreeDepth, MIN);
            
            if (currentChild.getValue().getValue() > bestNode.getValue().getValue()) {
                bestNode = currentChild;
            }
        }

        return bestNode.getValue();
    }
    
    private NodeInterface<BoardNode> getValue(NodeInterface<BoardNode> node, int level, int maxLevel, int mode) {
        
    	// Base case
    	if (level == maxLevel) {
            node.getValue().setValue(evaluator.evaluate(node.getValue()));
            
            return node;
        } else {
        	// Recursive case
            node.setChildren(childGenerator.generateChildren(node));
            
            for (NodeInterface<BoardNode> n : node.getChildren()) {
                NodeInterface<BoardNode> child = getValue(n, level + 1, maxLevel, mode == MAX ? MIN : MAX);
                n.getValue().setValue(child.getValue().getValue());
            }

            BoardNode value = node.getChildren()[0].getValue();
            
            for (NodeInterface<BoardNode> n : node.getChildren()) {
                if (mode == MAX && n.getValue().getValue() > value.getValue()) {
                    value = n.getValue();
                } else if (mode == MIN && n.getValue().getValue() < value.getValue()) {
                    value = n.getValue();
                }
            }
            
            node.getValue().setValue(value.getValue());
            
            return node;
        }
    }
}
