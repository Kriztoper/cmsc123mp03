package cmsc123.mp03.game;

public class BoardNode {

    private int[][] board;
    private int value;
    private int currentPlayer;
    
    public BoardNode(int[][] board) {
        this.board = board;
        value = 0;
    }
    
    public int[][] getBoard() {
        return board;
    }
    
    public void setBoard(int[][] board) {
        this.board = board;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
}