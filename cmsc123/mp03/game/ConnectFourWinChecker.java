package cmsc123.mp03.game;

public class ConnectFourWinChecker {
	
	public boolean isGameOver(int[][] board) {
		
		// horizontal
    	for (int i = 0; i < 6; i++) {
    		for (int j = 0; j <= 3; j++) {
    			if (board[j][i] != 0 && board[j][i] == board[j+1][i] && board[j][i] == board[j+2][i] && board[j][i] == board[j+3][i]) {
    				return true;
    			}
    		}
    	}

    	// vertical
    	for (int j = 0; j < 7; j++) {
    		for (int i = 0; i <= 2; i++) {
    			if (board[j][i] != 0 && board[j][i] == board[j][i+1] && board[j][i] == board[j][i+2] && board[j][i] == board[j][i+3]) {
    				return true;
    			}
    		}
    	}

    	// left diagonal
    	for (int i = 0; i <= 2; i++) {
    		for (int j = 0; j <= 3; j++) {
    			if (board[j][i] != 0 && board[j][i] == board[j+1][i+1] && board[j][i] == board[j+2][i+2] && board[j][i] == board[j+3][i+3]) {
    				return true;
    			}
    		}
    	}
    	
    	// right diagonal
    	for (int i = 5; i >= 3; i--) {
    		for (int j = 0; j <= 3; j++) {
    			if (board[j][i] != 0 && board[j][i] == board[j+1][i-1] && board[j][i] == board[j+2][i-2] && board[j][i] == board[j+3][i-3]) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
	}
}
