package cmsc123.mp03.game;

import java.util.HashMap;

import cmsc123.mp03.ai.Minimax;
import cmsc123.mp03.framework.BroadcasterInterface;
import cmsc123.mp03.framework.Event;
import cmsc123.mp03.framework.ListenerInterface;
import cmsc123.mp03.framework.ds.Link;
import cmsc123.mp03.framework.ds.LinkList;

public class CPUPlayer implements PlayerInterface, BroadcasterInterface {

    private HashMap<String, LinkList<ListenerInterface>> listeners;
    private int[] lastMove;
    
    private int type;
    
    public CPUPlayer(int type) {
        listeners = new HashMap<>();
        lastMove = new int[2];
        
        this.type = type;
    }
    
    @Override
    public int[][] move(int[][] board) {
        // TODO: Implement Minimax here.
        
        // Sample stupid-ass move selection (not minimax)
//        for (int i = 0; i < board.length; i++) {
//            for (int j = board[i].length - 1; j >= 0; j--) {
//                if (board[i][j] == 0) {
//                    board[i][j] = type;
//
//                    lastMove[0] = i;
//                    lastMove[1] = j;
//                    
//                    return board;
//                }
//            }
//        }
        
    	Minimax minimax = new Minimax(new ConnectFourEvaluator(), new ConnectFourChildGenerator());
    	BoardNode node = new BoardNode(board);
    	node.setCurrentPlayer(1);
    	int[][] newBoard = minimax.getBestMove(node, 5).getBoard();
    	for (int i = 0; i < 6; i++) {
    		for (int j = 0; j < 7; j++) {
    			if (board[j][i] != newBoard[j][i]) {
    				lastMove[0] = j;
    				lastMove[1] = i;
    			}
    		}
    	}
    	return newBoard;
    	
        //return board;
    }

    @Override
    public void broadcast(String eventName) {
        LinkList<ListenerInterface> list = listeners.get(eventName);
        
        if (list != null) {
            Link<ListenerInterface> link = list.getFirst();
            link.getValue().obey(new Event()); // TODO: Replace with a BoardEvent
            
            while (link.hasNext()) {
                link = link.getNext();
                link.getValue().obey(new Event()); // TODO: Replace with a BoardEvent
            }
        }
    }

    @Override
    public void addListener(String eventName, ListenerInterface listener) {
        LinkList<ListenerInterface> list;
        
        if (listeners.get(eventName) == null) {
            list = new LinkList<ListenerInterface>(new Link<ListenerInterface>(listener));
            listeners.put(eventName, list);
        } else {
            list = listeners.get(eventName);
            Link<ListenerInterface> link = new Link<ListenerInterface>(listener);
            link.setNext(list.getFirst());
            list.setFirst(link);
        }
    }

    public int[] getLastMove() {
        return lastMove;
    }
    
}
