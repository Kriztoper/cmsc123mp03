package cmsc123.mp03.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cmsc123.mp03.ai.Minimax;
import cmsc123.mp03.framework.ds.Node;
import cmsc123.mp03.framework.ds.NodeInterface;
import cmsc123.mp03.game.BoardNode;
import cmsc123.mp03.game.ConnectFourChildGenerator;
import cmsc123.mp03.game.ConnectFourEvaluator;

public class MinimaxTest {

    NodeInterface<BoardNode> node;
    
    @Before
    public void setUp() throws Exception {
        int[][] board = {
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 2},
                {0, 0, 0, 0, 0, 0}
        };
        BoardNode boardNode = new BoardNode(board);
        boardNode.setCurrentPlayer(1);
        node = new Node<BoardNode>(boardNode);
    }

    @Test
    public void test() {
    	int bestMove;
        Minimax minimax = new Minimax(new ConnectFourEvaluator(), new ConnectFourChildGenerator());
        System.out.println("best move "+(bestMove = minimax.getBestMove(node.getValue(), 2).getValue()));
    }

}
