package cmsc123.mp03.game;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class Player implements PlayerInterface {

    private MouseEvent event;
    private int[] inserts;
    private int type;
    private int[] lastMove;
    
    public Player(int type) {
        this.type = type;
        lastMove = new int[2];
    }
    
    @Override
    public int[][] move(int[][] board) {
        int radius = 640 / board.length;
        
        Point location = event.getPoint();
        
        int column = (int) (location.getX()/radius);
        
        if (inserts[column] < 0) {
            // TODO: Insert warning or something
        } else {
            lastMove[0] = column; // column
            lastMove[1] = inserts[column]; // row
            board[column][inserts[column]] = type;
            inserts[column] = inserts[column] - 1;
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
    
    public int[] getInserts() {
        return inserts;
    }

    public int getType() {
        return type;
    }
    
}
