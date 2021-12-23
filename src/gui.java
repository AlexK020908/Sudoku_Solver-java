import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class gui extends JFrame {

    private final JTextField[][] square;

    JButton reset, clear, solve, check, puzzle;

    JLabel difficulty, b1, b2;
    JComboBox difficultyBox;

    Border exteriorBorder = new LineBorder(Color.BLACK, 2);
    Border dividerBorder = new LineBorder(Color.BLACK, 1);

    private char[][] reset_board;
    private char[][] solvedBoard = new char[9][9];

    String[][] board_string = {{"5", "3", ".", ".", "7", ".", ".", ".", "."},
            {"6", ".", ".", "1", "9", "5", ".", ".", "."},
            {".", "9", "8", ".", ".", ".", ".", "6", "."},
            {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
            {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
            {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
            {".", "6", ".", ".", ".", ".", "2", "8", "."},
            {".", ".", ".", "4", "1", "9", ".", ".", "5"},
            {".", ".", ".", ".", "8", ".", ".", "7", "9"}};

    char[][] board = new char[9][9];


    private final Sudoku s = new Sudoku();



    {
        JFrame frame = new JFrame();
        frame.setTitle("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int i, j;
        square=new JTextField[9][9];
        Font font = new Font("Verdana", Font.BOLD, 30);
        for(i=0;i<9;i++)
        {
            for(j=0;j< 9;j++)
            {

                square[i][j]=new JTextField("",1);
                square[i][j].setFont(font);
                square[i][j].setPreferredSize(new Dimension(30,30));
                square[i][j].setHorizontalAlignment(JTextField.CENTER);
                square[i][j].setBorder(dividerBorder);

            }

        }

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(9,9));
        for (i = 0; i < 9 ; i++) {
            for (j = 0 ; j < 9 ; j++) {
                p1.add(square[i][j]);
            }
        }

        p1.setBorder(exteriorBorder);


        class RoundedBorder implements Border
        {
            private final int radius;
            RoundedBorder(int radius) {
                this.radius = radius;
            }
            public Insets getBorderInsets(Component c) {
                return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
            }
            public boolean isBorderOpaque() {
                return true;
            }
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.drawRoundRect(x,y,width-1,height-1,radius,radius);
            }
        }


        reset = new JButton("Reset");
        reset.setSize(new Dimension(10, 40));
        reset.setBorder(new RoundedBorder(10));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "you have pressed the Reset button");
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        char c = board[i][j];
                        if (c == '.') {
                            JTextField field = square[i][j];
                            field.setText("");
                        } else {
                            String s = String.valueOf(c);
                            JTextField jTextField = square[i][j];
                            jTextField.setText(s);
                        }
                    }
                }
                repaint();
            }
        });

        clear = new JButton("Clear Everything");
        clear.setSize(new Dimension(10, 40));
        clear.setBorder(new RoundedBorder(10));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "you have pressed the Clear button");
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        char c = board[i][j];
                        JTextField field = square[i][j];
                        field.setText("");
                    }
                }
                repaint();
            }
        });

        solve=new JButton("Solve");
        solve.setSize(new Dimension(10, 40));
        solve.setBorder(new RoundedBorder(10));
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "you have pressed the Solve button");


                solvedBoard =  s.solve(board);
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        char c = solvedBoard[i][j];
                        Integer num =  c - '0';
                        String s = Integer.toString(num);
                        JTextField jTextField = square[i][j];
                        jTextField.setText(s);
                        jTextField.setVisible(true);
                        repaint();

                    }
                    repaint();
                }
            }
        });

        check = new JButton("Check Result");
        check.setSize(new Dimension(10, 40));
        check.setBorder(new RoundedBorder(10));
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "you have pressed the Check Result button");
                if (solvedBoard == board) {
                    JOptionPane.showMessageDialog(null, "nice! you did it!");
                } else {
                    JOptionPane.showMessageDialog(null, "try again!");
                }
            }
        });


        puzzle=new JButton("Create New Puzzle");
        puzzle.setSize(new Dimension(10, 40));
        puzzle.setBorder(new RoundedBorder(10));
        puzzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "you have pressed the Create New Puzzle button");

                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        JTextField t = square[i][j];
                        String text = t.getText();
                        if (text.length() > 0) {
                            int k = Integer.parseInt(text);
                            char c = (char) (k + '0');
                            board[i][j] = c;
                        } else {
                            board[i][j] = '.';
                        }

                    }
                }
            }
        });

        difficulty=new JLabel("Difficulty: ");
        difficulty.setHorizontalAlignment(JLabel.CENTER);

        String[] difficulties = { "Easy", "Medium", "Hard" };
        difficultyBox = new JComboBox(difficulties);
        difficultyBox.setSelectedIndex(1);


        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6,1));
        p2.add(reset);
        p2.add(clear);
        p2.add(solve);
        p2.add(check);
        p2.add(puzzle);
        p2.add(difficulty);
        p2.add(difficultyBox);

        b1=new JLabel(" ");
        b2=new JLabel(" ");
        frame.setLayout(new BorderLayout());
        frame.add(b1,BorderLayout.NORTH);
        frame.add(p1,BorderLayout.LINE_START);
        frame.add(p2,BorderLayout.LINE_END);
        frame.add(b2,BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        gui gui = (gui) o;
        return Arrays.equals(solvedBoard, gui.solvedBoard) && Arrays.equals(board, gui.board);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(solvedBoard);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }
}