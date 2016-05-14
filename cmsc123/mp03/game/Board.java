package cmsc123.mp03.game;

import java.awt.Color;
import java.awt.Graphics2D;
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
	
    private HashMap<String, LinkList<ListenerInterface>> listeners;
    private int width, height;
    private int[][] boardArray;
    private int[] insertRow;
    
    private PlayerInterface player1, player2, currentPlayer;
    
    public Board() {
        listeners = new HashMap<>();
        
        int[][] boardArrayTemp = {
        		{0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0}
        };
        
        int[] insertRowTemp = {5, 5, 5, 5, 5, 5, 5};
        
        boardArray = boardArrayTemp;
        insertRow  = insertRowTemp;
        
        width  = 640;
        height = 640;
        
        // TODO: Assign depending on game mode
        player1 = new Player(PlayerInterface.PLAYER_1);
        player2 = new Player(PlayerInterface.PLAYER_2);
        
        currentPlayer = player1;
    }

    @Override
    public void react(Object event) {
        if (event instanceof MouseEvent) {
            if (currentPlayer instanceof Player) {
                ((Player) currentPlayer).setEvent((MouseEvent)event);
                ((Player) currentPlayer).setInserts(insertRow);
                
                boardArray = currentPlayer.move(boardArray);
                
                insertRow  = ((Player) currentPlayer).getInserts();
            } else if (currentPlayer instanceof CPUPlayer) {
                boardArray = currentPlayer.move(boardArray);
            }
        }
        
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        
        // Check if winning condition
        if (isGameOver()) {
        	broadcast("end");
        	
        	// TODO: Cleanup Resources or create destroy method
        } else {
        	broadcast("update");
        }
        
    }

    /**
     * Checks if the game is over.
     * 
     * @return boolean
     */
    public boolean isGameOver() {
        // TODO: Check winnng conditions here
    	int i,j,k,count;

    	//horizontal
        for(i = 0;i < boardArray.length; i++)
            for(j = 0; j < boardArray[0].length - 3; j++)
                if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[i][j+1] && boardArray[i][j] == boardArray[i][j+2] && boardArray[i][j] == boardArray[i][j+3])
                    return true;

        //vertical
        for(i = 0; i < boardArray.length - 3; i++)
            for(j = 0;j < boardArray[0].length; j++)
                if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[i+1][j] && boardArray[i][j]==boardArray[i+2][j] && boardArray[i][j] == boardArray[i+3][j])
                	return true;

        //right diagonal
        for(i = 0;i < boardArray.length - 3; i++)
            for(j = 0;j < boardArray[0].length - 3; j++) {
            	if (boardArray[i][j] != 0 && boardArray[i][j] == boardArray[i+1][j+1] && boardArray[i][j] == boardArray[i+2][j+2] && boardArray[i][j] == boardArray[i+3][j+3])
                	return true;
            }

        //left diagonal
        for(i = 0; i < boardArray.length - 3; i++)
            for(j = 3;j < boardArray[0].length; j++)
                if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[i+1][j-1] && boardArray[i][j] == boardArray[i+2][j-2] && boardArray[i][j] == boardArray[i+3][j-3])
                    return true;
    	
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
				} else if (boardArray[i][j] == PlayerInterface.PLAYER_1) {
					graphics.setColor(Color.RED);
					graphics.fillOval(i * radius, j * radius, radius, radius);
				} else if (boardArray[i][j] == PlayerInterface.PLAYER_2) {
					graphics.setColor(Color.BLUE);
					graphics.fillOval(i * radius, j * radius, radius, radius);
				}
			}
		}
	}

}
