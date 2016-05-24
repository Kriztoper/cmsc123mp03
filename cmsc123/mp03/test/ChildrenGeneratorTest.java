package cmsc123.mp03.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cmsc123.mp03.ai.ChildGeneratorInterface;
import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;
import cmsc123.mp03.game.BoardNode;
import cmsc123.mp03.game.ConnectFourChildGenerator;

public class ChildrenGeneratorTest {

    NodeInterface<BoardNode> node;
    
    @Before
    public void setUp() throws Exception {
        int[][] board = {
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 2, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        BoardNode boardNode = new BoardNode(board);
        boardNode.setCurrentPlayer(1);
        node = new Node<BoardNode>(boardNode);
    }

    @Test
    public void test() {
        ChildGeneratorInterface<BoardNode> childGenerator = new ConnectFourChildGenerator();
        NodeInterface<BoardNode>[] children = childGenerator.generateChildren(node);
        
        for (NodeInterface<BoardNode> child: children) {
            System.out.println("child");
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(" "+child.getValue().getBoard()[i][j]);
                }
                System.out.println();
            }
            
        }
    }

}
