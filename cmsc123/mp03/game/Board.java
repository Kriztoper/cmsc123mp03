package cmsc123.mp03.game;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
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

    public static final int MODE_PVP = 1;
    public static final int MODE_CVP = 2;
    public static final int MODE_CVC = 3;
    
    private static final int STATE_AWAITING_MOVE = 1;
    private static final int STATE_MAKING_MOVE   = 2; 
    
    private HashMap<String, LinkList<ListenerInterface>> listeners;
    private int width, height;
    private int[][] boardArray;
    private int[] insertRow;
    
    private int state;
    
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
        state = STATE_AWAITING_MOVE;
        
        initPlayers();
    }

    private void initPlayers() {
        if (player1 instanceof CPUPlayer) {
            ((BroadcasterInterface)player2).addListener("move", new ListenerInterface() {
                
                @Override
                public void obey(Object event) {
                    state = STATE_MAKING_MOVE;
                    boardArray = player1.move(boardArray);
                    
                    // Check if winning condition
                    if (isGameOver()) {
                        broadcast("end");
                        
                        // TODO: Cleanup Resources or create destroy method
                    } else {
                        broadcast("update");
                        ((BroadcasterInterface)player1).broadcast("move");
                        currentPlayer = player2;
                        state = STATE_AWAITING_MOVE;
                    }
                }
            });
        } else {
            ((BroadcasterInterface)player2).addListener("move", new ListenerInterface() {
                
                @Override
                public void obey(Object event) {
                    if (isGameOver()) {
                        broadcast("end");
                        
                        // TODO: Cleanup Resources or create destroy method
                    } else {
                        broadcast("update");
                        
                        currentPlayer = player1;
                        state = STATE_AWAITING_MOVE;
                    }
                }
            });
        }
        
        if (player2 instanceof CPUPlayer) {
            ((BroadcasterInterface)player1).addListener("move", new ListenerInterface() {
                
                @Override
                public void obey(Object event) {
                    state = STATE_MAKING_MOVE;
                    boardArray = player2.move(boardArray);
                    
                    
                    if (isGameOver()) {
                        broadcast("end");
                        
                        // TODO: Cleanup Resources or create destroy method
                    } else {
                        broadcast("update");
                        
                        ((BroadcasterInterface)player2).broadcast("move");
                        currentPlayer = player1;
                        state = STATE_AWAITING_MOVE;
                    }
                }
            });
        } else {
            ((BroadcasterInterface)player1).addListener("move", new ListenerInterface() {
                
                @Override
                public void obey(Object event) {
                    if (isGameOver()) {
                        broadcast("end");
                        
                        // TODO: Cleanup Resources or create destroy method
                    } else {
                        broadcast("update");
                        
                        currentPlayer = player2;
                        state = STATE_AWAITING_MOVE;
                    }
                }
            });
        }
    }
    
    @Override
    public void react(Object event) {
        if (event instanceof MouseEvent) {
            if (state == STATE_AWAITING_MOVE && currentPlayer instanceof Player) {
                state = STATE_MAKING_MOVE;
                
                ((Player) currentPlayer).setEvent((MouseEvent)event);
                ((Player) currentPlayer).setInserts(insertRow);
                
                boardArray = currentPlayer.move(boardArray);
                
                insertRow  = ((Player) currentPlayer).getInserts();
            }
            
        }
        
        state = STATE_AWAITING_MOVE;
        ((BroadcasterInterface)currentPlayer).broadcast("move");
        
        if (event instanceof ComponentEvent) {
            
        	height = ((ComponentEvent) event).getComponent().getHeight();
        	width = ((ComponentEvent) event).getComponent().getWidth();
        }
    }

    /**
     * Checks if the game is over.
     * 
     * @return boolean
     */
    public boolean isGameOver() {
        // TODO: Check winnng conditions here
        
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
		
		graphics.setPaint((Paint) new GradientPaint(10,0,Color.blue,1000, 10,Color.WHITE,true));
		//graphics.setColor(Color.blue);
		graphics.fillRect(0, 0, width, height);
		
		// Draw Circles
		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				if (boardArray[i][j] == 0) {
					graphics.setColor(Color.BLACK);
					graphics.drawOval(i * radius, j * radius, radius, radius);
				} else if (boardArray[i][j] == PlayerInterface.PLAYER_1) {
					graphics.setColor(Color.decode("#f9cf00"));
					graphics.fillOval(i * radius, j * radius, radius, radius);
					graphics.setColor(Color.decode("#a18700"));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.3), (int) (radius-radius*0.3));
					graphics.setColor(Color.decode("#ffd919"));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.3), (int) (radius-radius*0.3));
					graphics.setColor(Color.decode("#a18700"));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.75), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.75), (j * radius)+(int)(radius*.75), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.75), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.46), (j * radius)+(int)(radius*.88), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.46), (j * radius)+(int)(radius*.05), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.05), (j * radius)+(int)(radius*.46), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.87), (j * radius)+(int)(radius*.46), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					
				} else if (boardArray[i][j] == PlayerInterface.PLAYER_2) {
					graphics.setColor(Color.decode("#dd000a"));
					graphics.fillOval(i * radius, j * radius, radius, radius);
					graphics.setColor(Color.decode("#7b0005"));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.3), (int) (radius-radius*0.3));
					graphics.setColor(Color.decode("#ff000b"));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.3), (int) (radius-radius*0.3));
					graphics.setColor(Color.decode("#7b0005"));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.75), (j * radius)+(int)(radius*.16), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.75), (j * radius)+(int)(radius*.75), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.16), (j * radius)+(int)(radius*.75), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.46), (j * radius)+(int)(radius*.88), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.46), (j * radius)+(int)(radius*.05), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.05), (j * radius)+(int)(radius*.46), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
					graphics.fillOval((i * radius)+(int)(radius*.87), (j * radius)+(int)(radius*.46), 
							(int) (radius-radius*0.9), (int) (radius-radius*0.9));
				}
			}
		}
	}
}
