package cmsc123.mp03.game;

import cmsc123.mp03.ai.PrunerInterface;
import cmsc123.mp03.framework.ds.NodeInterface;

public class ConnectFourPruner implements PrunerInterface<NodeInterface<BoardNode>>{

    @Override
    public NodeInterface<BoardNode>[] prune(NodeInterface<BoardNode>[] objects) {
        // Add pruning logic here
        
        return objects;
    }

}
