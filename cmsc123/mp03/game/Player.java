package cmsc123.mp03.game;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class Player implements PlayerInterface {

    private MouseEvent event;
    private int[] inserts;
    private int type;
    
    public Player(int type) {
        this.type = type;
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
