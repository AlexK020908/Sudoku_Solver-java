
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class SudokuTest{

    @Test
    public void testSudoku(){
        String[][] board = {{"5","3",".",".","7",".",".",".","."},
                            {"6",".",".","1","9","5",".",".","."},
                            {".","9","8",".",".",".",".","6","."},
                            {"8",".",".",".","6",".",".",".","3"},
                            {"4",".",".","8",".","3",".",".","1"},
                            {"7",".",".",".","2",".",".",".","6"},
                            {".","6",".",".",".",".","2","8","."},
                            {".",".",".","4","1","9",".",".","5"},
                            {".",".",".",".","8",".",".","7","9"}};


        char[][] char_board = new char[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                char_board[i][j] = board[i][j].charAt(0);
            }
        }


        Sudoku actual = new Sudoku();
        actual.solve(char_board);

        String[][] expected_board = {{"5","3","4","6","7","8","9","1","2"},
                                    {"6","7","2","1","9","5","3","4","8"},
                                    {"1","9","8","3","4","2","5","6","7"},
                                    {"8","5","9","7","6","1","4","2","3"},
                                    {"4","2","6","8","5","3","7","9","1"},
                                    {"7","1","3","9","2","4","8","5","6"},
                                    {"9","6","1","5","3","7","2","8","4"},
                                    {"2","8","7","4","1","9","6","3","5"},
                                    {"3","4","5","2","8","6","1","7","9"}};

        char[][] char_expected_board = new char[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                char_expected_board[i][j] = expected_board[i][j].charAt(0);
            }
        }


        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                assertEquals(char_expected_board[i][j], char_board[i][j]);
            }
        }
    }
}
