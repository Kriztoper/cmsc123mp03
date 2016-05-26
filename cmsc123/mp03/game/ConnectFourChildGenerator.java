package cmsc123.mp03.game;

import java.util.ArrayList;

import cmsc123.mp03.ai.ChildGeneratorInterface;
import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;
import cmsc123.mp03.util.ArrayCloner;

public class ConnectFourChildGenerator implements ChildGeneratorInterface<BoardNode> {

    @Override
    public NodeInterface<BoardNode>[] generateChildren(NodeInterface<BoardNode> node) {
        
        ArrayList<NodeInterface<BoardNode>> children = new ArrayList<NodeInterface<BoardNode>>();
        
        int currentPlayer = node.getValue().getCurrentPlayer() == PlayerInterface.PLAYER_1 ? PlayerInterface.PLAYER_2 : PlayerInterface.PLAYER_1;
        int[][] board = ArrayCloner.clone(node.getValue().getBoard());
        BoardNode boardNode = null;
        
        for (int i = 0; i < 7; i++) {
            for (int j = 5; j >= 0; j--) {
                board = ArrayCloner.clone(node.getValue().getBoard());
                
                if (board[i][j] == 0) {
                    board[i][j] = currentPlayer;

                    boardNode = new BoardNode(board);
                    boardNode.setCurrentPlayer(currentPlayer);
                    
                    children.add(new Node<BoardNode>(boardNode));
                    
                    break;
                }
            }            
        }
        
//        (for (int i = 0; i < children.size(); i++) {
//        	for () {
//        		
//        	}
//        }
        
        return (NodeInterface<BoardNode>[]) children.toArray(new Node[children.size()]);
    }

}
