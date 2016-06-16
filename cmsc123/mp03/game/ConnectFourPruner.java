package cmsc123.mp03.game;

import java.util.ArrayList;

import cmsc123.mp03.ai.EvaluatorInterface;
import cmsc123.mp03.ai.PrunerInterface;
import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;

public class ConnectFourPruner implements PrunerInterface<NodeInterface<BoardNode>>{

	private EvaluatorInterface evaluator;
	
    @Override
    public NodeInterface<BoardNode>[] prune(NodeInterface<BoardNode>[] objects, int mode) {

    	evaluator = new ConnectFourEvaluator();
    	int size = objects.length;
    	ArrayList<Node> decisiveNodes = new ArrayList<Node>();
    	
    	for (int i = 0; i < size; i++) {
    		objects[i].getValue().setValue(evaluator.evaluate(objects[i].getValue()));
    		int value = objects[i].getValue().getValue();
    		
    		if ((mode == 1 && value >= 100000) // if we encounter a win for CPUPlayer and mode is MAX we prune nodes to only winning nodes
    				|| (mode == 2 && value < 0)) { // if we encounter a win for opponent and mode is MIN we prune nodes to only losing nodes
    			decisiveNodes.add((Node) objects[i]);
    		}
    	}
    		
    	return objects;
    }

}