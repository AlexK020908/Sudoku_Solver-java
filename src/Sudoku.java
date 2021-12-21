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

    public Sudoku(char[][] board) {
        box = new int[N];
        rows = new int[N];
        columns = new int[N];
        this.board = board;
    }


    //EFFECT: returns true if placement of num is valid in the given board cell
    public boolean isValidPlacement(int row, int column, int num, char[][] board) {

        int boxIndex = getBoxIndex(row, column);
        int startRow = getStartRow(boxIndex);
        int startColumn = getStartColumn(boxIndex);


        for (int i = 0 ; i < N ; i++) {
            if (board[row][i] == num) return false;
            if (board[i][column] == num) return false;
            if (containedInBox(board, startRow, startColumn,num)) return false;
        }

        return true;
    }

    //EFFECT: returns true if the number is contained in the box
    private boolean containedInBox(char[][] board, int startRow, int startColumn, int num) {
        int endRow = startRow + 2;
        int endColumn = startColumn + 2;

        for (int i = startRow ; i <= endRow ; i++) {
            for (int j = startColumn ; j <= endColumn ; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }

        return false;
    }


    //effect: get the start column
    private int getStartColumn(int boxIndex) {
        int start= 0;
        if (boxIndex % 3 == 0) {
            start = 0;
        } else if (boxIndex % 3 == 1 || boxIndex == 1) {
            start = 3;
        } else if (boxIndex % 3 == 2) {
            start = 6;
        }
        return start;
    }


    //effect: get the box index
    private int getBoxIndex(int row, int column) {
        return (row / n) * n + (column / n) * n;
    }

    //effect: get the start row
    private int getStartRow(int boxIndex) {
        int startRow = 0;
        if (boxIndex % 3 == 0) {
            startRow = boxIndex;
        } if (boxIndex % 3 == 1) {
            startRow = boxIndex - 1;
        } if (boxIndex % 3 == 2) {
            startRow = boxIndex - 2;
        }
        return startRow;
    }

    //EFFECT: place a valid number at the specified row and column only if the placement is allowed
    public void placeNewNum(int row, int column, int num, char[][] board) {
        if (isValidPlacement(row, column, num, board)) {
            board[row][column] = (char) num;
        }
    }


    //EFFECT: remove the number placed at specified row and column .
    public void removePlacement(int row, int column, int num, char[][] board) {
        board[row][column] = '.';
    }


    public void backTrack(int row, int column) {

    }



    //EFFECT: solve the given board
    public char[][] solve() {

        //base case


        //recursion




        return new char[][]{};
    }

}
