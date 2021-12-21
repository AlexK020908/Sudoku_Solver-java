public class Sudoku {

    public static final int n = 3;
    public static final int N = n * n;


    //we have a total of 9 boxes
    //each box has 9 components
    private int[] box;
    //9 rows with each row has 9 indexes
    private int[] rows;
    //9 columns, each column with 9 indexes
    private int[] columns;
    //represents a 9 by 9 board. first bracket is column, second is row
    private char[][] board;

    public Sudoku() {
        box = new int[N];
        rows = new int[N];
        columns = new int[N];
    }



    //EFFECT: returns true if placement is valid
    public boolean isValidPlacement(int row, int column, int num) {
        int boxIndex = getBoxIndex(row, column);

        return false;
    }

    private int getBoxIndex(int row, int column) {
        return (row / n) * n + (column / n) * n;
    }

    //EFFECT: place a valid number at the specified row and column
    public boolean placeNewNum(int row, int column) {
        return false;
    }


    //EFFECT: remove the number placed at specified row and column
    public boolean removePlacement(int row, int column) {
        return false;
    }


    public void backTrack(int row, int column) {

    }



    //EFFECT: solve the given board
    public char[][] solve(char[][] board) {
        //base case


        //recursion




        return new char[][]{};
    }

}
