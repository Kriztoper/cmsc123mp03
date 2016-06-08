package cmsc123.mp03.game;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JOptionPane;

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
    private int[] lastMove;
    
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
        player2 = new CPUPlayer(PlayerInterface.PLAYER_2);
        
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
                    
                    lastMove = ((CPUPlayer) player1).getLastMove();
                    
                    // Check if winning condition
                    if (isGameOver()!=0) {
                    	broadcast("update");
                    	if(isGameOver() == 1){
                    		
                    		JOptionPane.showMessageDialog(null, "P1 wins", "P1 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}else{
                    		
                    		JOptionPane.showMessageDialog(null, "P2 wins", "P2 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}
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

                	lastMove = ((CPUPlayer) player2).getLastMove();
                	
                    if (isGameOver()!=0) {
                    	broadcast("update");
                    	if(isGameOver() == 1){
                    		
                    		JOptionPane.showMessageDialog(null, "P1 wins", "P1 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}else{
                    		
                    		JOptionPane.showMessageDialog(null, "P2 wins", "P2 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}
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
                    
                    lastMove = ((CPUPlayer) player2).getLastMove();
                    ((Player) player1).setSpecificInsert(lastMove[0], lastMove[1]);
                    
                    if (isGameOver()!=0) {
                    	broadcast("update");
                    	if(isGameOver() == 1){
                    		
                    		JOptionPane.showMessageDialog(null, "P1 wins", "P1 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}else{
                    		
                    		JOptionPane.showMessageDialog(null, "P2 wins", "P2 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}
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
                    
                	lastMove = ((Player) player1).getLastMove();
                	
                    if (isGameOver()!=0) {
                    	broadcast("update");
                    	if(isGameOver() == 1){
                    		
                    		JOptionPane.showMessageDialog(null, "P1 wins", "P1 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}else{
                    		
                    		JOptionPane.showMessageDialog(null, "P2 wins", "P2 wins", JOptionPane.INFORMATION_MESSAGE);
                    	}broadcast("end");
                        
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
                
                lastMove = ((Player) currentPlayer).getLastMove();
                
                insertRow  = ((Player) currentPlayer).getInserts();
                
                if (isGameOver()!=0) {
                	broadcast("update");
                	if(isGameOver() == 1){
                		
                		JOptionPane.showMessageDialog(null, "P1 wins", "P1 wins", JOptionPane.INFORMATION_MESSAGE);
                	}else{
                		
                		JOptionPane.showMessageDialog(null, "P2 wins", "P2 wins", JOptionPane.INFORMATION_MESSAGE);
                	}broadcast("end");
                    
                    // TODO: Cleanup Resources or create destroy method
                }
            }
            
        }
        
        state = STATE_AWAITING_MOVE;
        ((BroadcasterInterface)currentPlayer).broadcast("move");
    }
    
    public PlayerInterface getPlayer1(){
    	
    	return player1;
    }
    
    public void setHeight(int height){
    	
    	this.height = height;
    }
    
    public void setWidth(int width){
    	
    	this.width = width;
    }
    
    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
    
    /**
     * Checks if the game is over.
     * 
     * @return int
     */
    public int isGameOver() {
        // TODO: Check winning conditions here
        int i, j;
        int counterHorizontal = 0;
        int counterVertical = 0;
        int counterLeftDiagonal = 0;
        int counterRightDiagonal = 0;
        
        boolean flagEast = true;
        boolean flagWest = true;
        boolean flagSouth = true;
        boolean flagNorthWest = true;
        boolean flagSouthEast = true;
        boolean flagNorthEast = true;
        boolean flagSouthWest = true;
        
        j = lastMove[0]; // max is 7
        i = lastMove[1]; // max is 6

        for (int x = 1; x <= 3; x++ ) {
        	if (checkIndexBound(j + x, 7)) { // east
        		if (boardArray[j][i] == boardArray[j + x][i] && flagEast) {
        			counterHorizontal++;
        		} else {
        			flagEast = false;
        		}
        	}
        	if (checkIndexBound(j - x, 7)) { // west
        		if (boardArray[j][i] == boardArray[j - x][i] && flagWest) {
        			counterHorizontal++;
        		} else {
        			flagWest = false;
        		}
        	}
        	if (checkIndexBound(i + x, 6)) { // south
         		if (boardArray[j][i] == boardArray[j][i + x] && flagSouth) {
        			counterVertical++;
        		} else {
        			flagSouth = false;
        		}
        	}
        	if (checkIndexBound(j - x, 7) && checkIndexBound(i - x, 6)) { // northwest
         		if (boardArray[j][i] == boardArray[j - x][i - x] && flagNorthWest) {
         			counterLeftDiagonal++;
         		} else {
         			flagNorthWest = false;
         		}
        	}
        	if (checkIndexBound(j + x, 7) && checkIndexBound(i + x, 6)) { // southeast
         		if (boardArray[j][i] == boardArray[j + x][i + x] && flagSouthEast) {
         			counterLeftDiagonal++;
         		} else {
         			flagSouthEast = false;
         		}
        	}
        	if (checkIndexBound(j + x, 7) && checkIndexBound(i - x, 6)) { // northeast
         		if (boardArray[j][i] == boardArray[j + x][i - x] && flagNorthEast) {
         			counterRightDiagonal++;
         		} else {
         			flagNorthEast = false;
         		}
        	}
        	if (checkIndexBound(j - x, 7) && checkIndexBound(i + x, 6)) { // southwest
         		if (boardArray[j][i] == boardArray[j - x][i + x] && flagSouthWest) {
         			counterRightDiagonal++;
         		} else {
         			flagSouthWest = false;
         		}
        	}
        }
        
        if ((counterHorizontal == 3 && (flagEast || flagWest)) || (counterVertical == 3 && flagSouth)
        		|| (counterLeftDiagonal == 3 && (flagNorthWest || flagSouthEast))
        		|| (counterRightDiagonal == 3 && (flagNorthEast || flagSouthWest))) {
        	return boardArray[j][i];
        }
        
    	return 0;
    }

    private boolean checkIndexBound(int x, int upperBound) {
    	if (0 <= x && x < upperBound) {
    		return true;
    	}
    	
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


