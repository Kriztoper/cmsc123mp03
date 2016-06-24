package cmsc123.mp03.game;

import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;

public class ChildrenPrinter {
	
	public void print(NodeInterface<BoardNode> bestNode) {
		NodeInterface<BoardNode>[] children = ((Node) bestNode).getChildren();
		int x = 1;
    	if (children != null) {
	    	for (NodeInterface<BoardNode> child: children) {
	    		System.out.println("child "+(x++)+" value "+((BoardNode) child.getValue()).getValue());
	    		((BoardNode) child.getValue()).displayBoard();
	    	}
    	}
	}
}
