package cmsc123.mp03.game;

public class BoardNode {

    private int[][] board;
    private int value;
    private int currentPlayer;
    private int level;
    
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

	public int getLevel() {
	    return level;
    }

	public void setLevel(int level) {
	    this.level = level;
    }
    
	public void displayBoard() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(board[j][i]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
