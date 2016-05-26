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
    private int[] lastMove;
    private int radius;
    
    private HashMap<String, LinkList<ListenerInterface>> listeners;
    
    public Player(int type) {
    	
    	radius = 640;
        this.type = type;
        lastMove = new int[2];
        
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
    
    public void setRadius(int radius){
    	
    	this.radius = radius;
    }
    
    @Override
    public int[][] move(int[][] board) {
        
    	int clickArea = radius / board.length;
        
        Point location = event.getPoint();
        
        int column = (int) (location.getX()/clickArea);
        
        if (inserts[column] < 0) {
            // TODO: Insert warning or something
        } else {
        	if (board[column][inserts[column]] == 0) {
            	lastMove[0] = column; // column
                lastMove[1] = inserts[column]; // row
                board[column][inserts[column]] = type;
                inserts[column] = inserts[column] - 1;
            } else if (board[column][inserts[column]] != 0 && board[column][inserts[column]] != type && inserts[column] != 0) {
	        	lastMove[0] = column; // column
	            lastMove[1] = inserts[column]; // row
	            board[column][inserts[column]-1] = type;
	            inserts[column] = inserts[column] - 1;
            }
        }
        
        return board;
    }
    
    /**
     * Returns the indices of the current move
     * @return int[]
     */
    public int[] getLastMove() {
        return lastMove;
    }
    
    public void setEvent(MouseEvent event) {
        this.event = event;
    }
    
    public void setInserts(int[] inserts) {
        this.inserts = inserts;
    }
    
    public void setSpecificInsert(int column, int row) {
        inserts[column] = row;
        inserts[column] = inserts[column] - 1;
    }
    
    public int[] getInserts() {
        return inserts;
    }

    public int getType() {
        return type;
    }
    
}