package cmsc123.mp03.game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import cmsc123.mp03.framework.BroadcasterInterface;
import cmsc123.mp03.framework.Event;
import cmsc123.mp03.framework.ListenerInterface;
import cmsc123.mp03.framework.ds.Link;
import cmsc123.mp03.framework.ds.LinkList;

public class Player implements PlayerInterface, BroadcasterInterface {

    private MouseEvent event;
    private int[] inserts;
    private int type;
    
    private HashMap<String, LinkList<ListenerInterface>> listeners;
    
    public Player(int type) {
        this.type = type;
        
        listeners = new HashMap<>();
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
    
    @Override
    public int[][] move(int[][] board) {
        int radius = 640 / board.length;
        
        Point location = event.getPoint();
        
        int column = (int) (location.getX()/radius);
        
        if (inserts[column] < 0) {
            // TODO: Insert warning or something
        } else {
            board[column][inserts[column]] = type;
            inserts[column] = inserts[column] - 1;
        }
        
        return board;
    }
    
    public void setEvent(MouseEvent event) {
        this.event = event;
    }
    
    public void setInserts(int[] inserts) {
        this.inserts = inserts;
    }
    
    public int[] getInserts() {
        return inserts;
    }

    public int getType() {
        return type;
    }
    
}
