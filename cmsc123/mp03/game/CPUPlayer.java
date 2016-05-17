package cmsc123.mp03.game;

import java.util.HashMap;

import cmsc123.mp03.framework.BroadcasterInterface;
import cmsc123.mp03.framework.Event;
import cmsc123.mp03.framework.ListenerInterface;
import cmsc123.mp03.framework.ds.Link;
import cmsc123.mp03.framework.ds.LinkList;

public class CPUPlayer implements PlayerInterface, BroadcasterInterface {

    private HashMap<String, LinkList<ListenerInterface>> listeners;
    
    private int type;
    
    public CPUPlayer(int type) {
        listeners = new HashMap<>();
        
        this.type = type;
    }
    
    @Override
    public int[][] move(int[][] board) {
        // TODO: Implement Minimax here.
        
        // Sample stupid-ass move selection (not minimax)
        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                if (board[i][j] == 0) {
                    board[i][j] = type;
                    
                    return board;
                }
            }
        }
        
        return board;
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
    
}
