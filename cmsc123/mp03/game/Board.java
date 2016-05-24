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
    private int[] lastMove;
    
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
        
        lastMove = ((Player) currentPlayer).getLastMove();
        
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
        // TODO: Check winning conditions here
        int i, j, k, count;
        
        i = lastMove[0]; // max is 7
        j = lastMove[1]; // max is 6

        System.out.println("checking ["+i+"]["+j+"]");
        // print the board
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                System.out.print(boardArray[y][x]+" ");
            }
            System.out.println();
        }
        System.out.println();

        // horizontal
        for (int x = 0; x <= 3; x++) {
            if (x <= i && x + 3 >= i) {
                if(boardArray[x][j] != 0 && boardArray[i][j] == boardArray[x][j] && boardArray[i][j] == boardArray[x+1][j] && boardArray[i][j] == boardArray[x+2][j] && boardArray[i][j] == boardArray[x+3][j]) {
                    return true;
                }
            }
        }
        
        // vertical
        for (int x = 0; x <= 2; x++) {
            if (x <= j && x + 3 >= j) {
                if(boardArray[i][x] != 0 && boardArray[i][j] == boardArray[i][x] && boardArray[i][j] == boardArray[i][x+1] && boardArray[i][j] == boardArray[i][x+2] && boardArray[i][j] == boardArray[i][x+3]) {
                    return true;
                }
            }
        }
        
        // diagonal (topLeft to bottomRight)
        int indexGap = i - j;
        if (indexGap == -2) {
            if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[0][2] && boardArray[i][j] == boardArray[1][3] && boardArray[i][j] == boardArray[2][4] && boardArray[i][j] == boardArray[3][5]) {
                return true;
            }
        } else if (indexGap == -1) {
            for (int x = 1; x <= 2; x++) {
                if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[x-1][x] && boardArray[i][j] == boardArray[x][x+1] && boardArray[i][j] == boardArray[x+1][x+2] && boardArray[i][j] == boardArray[x+2][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 0) {
            for (int x = 0; x <= 2; x++) {
                if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[x][x] && boardArray[i][j] == boardArray[x+1][x+1] && boardArray[i][j] == boardArray[x+2][x+2] && boardArray[i][j] == boardArray[x+3][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 1) {
            for (int x = 0; x <= 2; x++) {
                if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[x+1][x] && boardArray[i][j] == boardArray[x+2][x+1] && boardArray[i][j] == boardArray[x+3][x+2] && boardArray[i][j] == boardArray[x+4][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 2) {
            for (int x = 0; x <= 1; x++) {
                if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[x+2][x] && boardArray[i][j] == boardArray[x+3][x+1] && boardArray[i][j] == boardArray[x+4][x+2] && boardArray[i][j] == boardArray[x+5][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 3) {
            if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[3][0] && boardArray[i][j] == boardArray[4][1] && boardArray[i][j] == boardArray[5][2] && boardArray[i][j] == boardArray[6][3]) {
                return true;
            }
        }
        
        // diagonal (topRight to bottomLeft)
        indexGap = i + j;
        if (indexGap == 3) {
            if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[3][0] && boardArray[i][j] == boardArray[2][1] && boardArray[i][j] == boardArray[1][2] && boardArray[i][j] == boardArray[0][3]) {
                return true;
            }
        } else if (indexGap == 4) {
            for (int x = 0; x <= 1; x++) {
                if(boardArray[4-x][x] != 0 && boardArray[i][j] == boardArray[4-(x)][x] && boardArray[i][j] == boardArray[4-(x+1)][x+1] && boardArray[i][j] == boardArray[4-(x+2)][x+2] && boardArray[i][j] == boardArray[4-(x+3)][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 5) {
            for (int x = 0; x <= 2; x++) {
                if(boardArray[5-x][x] != 0 && boardArray[i][j] == boardArray[5-(x)][x] && boardArray[i][j] == boardArray[5-(x+1)][x+1] && boardArray[i][j] == boardArray[5-(x+2)][x+2] && boardArray[i][j] == boardArray[5-(x+3)][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 6) {
            for (int x = 0; x <= 2; x++) {
                if(boardArray[6-x][x] != 0 && boardArray[i][j] == boardArray[6-(x)][x] && boardArray[i][j] == boardArray[6-(x+1)][x+1] && boardArray[i][j] == boardArray[6-(x+2)][x+2] && boardArray[i][j] == boardArray[6-(x+3)][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 7) {
            for (int x = 1; x <= 2; x++) {
                if(boardArray[7-x][x] != 0 && boardArray[i][j] == boardArray[7-(x)][x] && boardArray[i][j] == boardArray[7-(x+1)][x+1] && boardArray[i][j] == boardArray[7-(x+2)][x+2] && boardArray[i][j] == boardArray[7-(x+3)][x+3]) {
                    return true;
                }
            }
        } else if (indexGap == 8) {
            if(boardArray[i][j] != 0 && boardArray[i][j] == boardArray[6][2] && boardArray[i][j] == boardArray[5][3] && boardArray[i][j] == boardArray[4][4] && boardArray[i][j] == boardArray[3][5]) {
                return true;
            }
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
