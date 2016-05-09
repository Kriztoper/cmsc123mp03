package cmsc123.mp03.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
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
    private int[] insertRow;
    
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
        
        int[] insertRowTemp = {5, 5, 5, 5, 5, 5, 5};
        
        boardArray = boardArrayTemp;
        insertRow  = insertRowTemp;
        
        width  = 640;
        height = 640;
    }

    @Override
    public void react(Object event) {
        if (event instanceof MouseEvent) {
        	int radius = width / boardArray.length;
        	
        	Point location = ((MouseEvent) event).getPoint();
        	
        	int column = (int) (location.getX()/radius);
        	
        	if (insertRow[column] < 0) {
        		// TODO: Insert warning or something
        	} else {
        		boardArray[column][insertRow[column]] = 1;
        		insertRow[column] = insertRow[column] - 1;
        	}
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
	public void draw(Graphics2D graphics) {
		int radius = width / boardArray.length;
		
		graphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		
		// Draw Background
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		
		// Draw Circles
		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				if (boardArray[i][j] == 0) {
					graphics.setColor(Color.BLACK);
					graphics.drawOval(i * radius, j * radius, radius, radius);
				} else if (boardArray[i][j] == 1) {
					graphics.setColor(Color.RED);
					graphics.fillOval(i * radius, j * radius, radius, radius);
				} else if (boardArray[i][j] == 2) {
					graphics.setColor(Color.BLUE);
					graphics.fillOval(i * radius, j * radius, radius, radius);
				}
			}
		}
	}

}
