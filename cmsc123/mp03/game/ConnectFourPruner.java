package cmsc123.mp03.game;

import java.util.ArrayList;

import cmsc123.mp03.ai.PrunerInterface;
import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;

public class ConnectFourPruner implements PrunerInterface<NodeInterface<BoardNode>>{

    @Override
    public NodeInterface<BoardNode>[] prune(NodeInterface<BoardNode>[] objects, int mode) {

    	int size = objects.length;
    	int currentPlayer = objects[0].getValue().getCurrentPlayer();
    	
    	Node[] newNode = new Node[size];

    	if (mode == 1) { // if we encounter a win for CPUPlayer and mode is MAX we prune nodes to only winning nodes
	    	for (int i = 0, j = 0; i < size; i++) {
	    		if (objects[i].getValue().getValue() >= 100000) {
	    			newNode[j++] = (Node) objects[i];
	    		}
	    	}
    	} else if (mode == 2) { // if we encounter a win for opponent and mode is MIN we prune nodes to only losing nodes
    		for (int i = 0, j = 0; i < size; i++) {
	    		if (objects[i].getValue().getValue() < 0) {
	    			newNode[j++] = (Node) objects[i];
	    		}
	    	}
    	}
    	
    	if (newNode.length != 0) {
    		return newNode;
    	}
    	
    	return objects;
    }

}