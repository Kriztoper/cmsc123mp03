package cmsc123.mp03.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import cmsc123.mp03.framework.BroadcasterInterface;
import cmsc123.mp03.framework.DrawableInterface;
import cmsc123.mp03.framework.Event;
import cmsc123.mp03.framework.ListenerInterface;
import cmsc123.mp03.framework.ReactorInterface;
import cmsc123.mp03.framework.ds.Link;
import cmsc123.mp03.framework.ds.LinkList;

public class Board implements ReactorInterface, BroadcasterInterface, DrawableInterface {

	private static final int X = 7;
	private static final int Y = 6;
	
    private HashMap<String, LinkList<ListenerInterface>> listeners;
    private int width, height;
    private int[][] boardArray;
    
    public Board() {
        listeners = new HashMap<>();
        
        int[][] boardArrayTemp = {
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0}
        };
        
        boardArray = boardArrayTemp;
        
        width  = 640;
        height = 640;
    }

    @Override
    public void react(Object event) {
        if (event instanceof MouseEvent) {
        	event = (MouseEvent) event;
        	
        	
        }
        
        // Check if winning condition
        if (isGameOver()) {
        	broadcast("end");
        	
        	// TODO: Cleanup Resources or create destroy method
        } else {
        	broadcast("update");
        }
        
    }

    public boolean isGameOver() {
    	return false;
    }
    
    @Override
    public void broadcast(String eventName) {
        LinkList<ListenerInterface> list = listeners.get(eventName);
        
        if (list != null) {
            Link<ListenerInterface> link = list.getFirst();
            link.getValue().obey(new Event());
            
            while (link.hasNext()) {
                link = link.getNext();
                link.getValue().obey(new Event());
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
	public void draw(Graphics2D graphics) {
		int radius = width / boardArray.length;
		
		// Draw Background
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		
		// Draw Circles
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				if (boardArray[i][j] == 0) {
					graphics.drawOval(i * radius, j * radius, radius, radius);
				}
			}
		}
	}

}
