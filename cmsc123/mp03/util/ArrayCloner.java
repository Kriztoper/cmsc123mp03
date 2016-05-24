package cmsc123.mp03.util;

public class ArrayCloner {

    public static int[][] clone(int[][] array) {
        int[][] copy = new int[array.length][array[0].length];
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                copy[i][j] = array[i][j];
            }
        }
        
        return copy;
    }
    
}
