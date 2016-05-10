package cmsc123.mp03.game;

public interface PlayerInterface {

    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    
    public int[][] move(int[][] board);
    
}
