import java.util.ArrayList;

public class Sudoku {

    public static final int n = 3;
    public static final int N = n * n;

    //represents a 9 by 9 board. first bracket is column, second is row
    private char[][] board;

    public Sudoku(char[][] board) {
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
            if (itemsContainedInBox(board, startRow, startColumn).contains((char) num)) return false;
        }

        return true;
    }

    //EFFECT: returns true if the number is contained in the box
    private ArrayList<Character> itemsContainedInBox(char[][] board, int startRow, int startColumn) {
        int endRow = startRow + 2;
        int endColumn = startColumn + 2;
        ArrayList<Character> items = new ArrayList<>();

        for (int i = startRow ; i <= endRow ; i++) {
            for (int j = startColumn ; j <= endColumn ; j++) {
                if (board[i][j] != '.') {
                    items.add(board[i][j]);
                }
            }
        }

        return items;
    }


    //effect: get the start column
    private int getStartColumn(int boxIndex) {
        if (boxIndex % 3 == 0) {
            return 0;
        } else if (boxIndex % 3 == 1) {
            return 3;
        } else { // (boxIndex % 3 == 2)
            return 6;
        }
    }


    //effect: get the box index
    private int getBoxIndex(int row, int column) {
        return (row / n) * n + (column / n) * n;
    }

    //effect: get the start row
    private int getStartRow(int boxIndex) {

        if (boxIndex % 3 == 0) {
            return boxIndex;
        } else if (boxIndex % 3 == 1) {
            return boxIndex - 1;
        } else { //(boxIndex % 3 == 2)
            return boxIndex - 2;
        }
    }

    //EFFECT: place a valid number at the specified row and column only if the placement is allowed
    public void placeNewNum(int row, int column, int num, char[][] board) {
        if (isValidPlacement(row, column, num, board)) {
            board[row][column] = (char) num;
        }
    }


    //EFFECT: remove the number placed at specified row and column .
    public void removePlacement(int row, int column, char[][] board) {
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