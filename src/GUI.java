import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    // state of the board after clearing
    private final char[][] clearBoard = {
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' },
            {'.', '.' , '.', '.', '.', '.', '.', '.' ,'.' }

    };
    private final JTextField[][] square;

    // create all the buttons needed
    JButton reset, clear, solve, check, puzzle;

    JLabel  b1, b2;
    private char[][] resetBoard = new char[9][9];

    // board line thicknesses
    Border exteriorBorder = new LineBorder(Color.BLACK, 2);
    Border dividerBorder = new LineBorder(Color.BLACK, 1);

    private char[][] solvedBoard = new char[9][9];
    char[][] board = new char[9][9];
    private final Sudoku s = new Sudoku();

    {
        JFrame frame = new JFrame();
        frame.setTitle("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int i, j;
        square=new JTextField[9][9];
        // Font size and style
        Font font = new Font("Verdana", Font.BOLD, 30);

        // create JTextField for every position on the grid
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

        // button for reset
        reset = new JButton("Reset");
        reset.setSize(new Dimension(10, 40));
        reset.setBorder(new RoundedBorder(10));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "you have pressed the Reset button");
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        char c = resetBoard[i][j];
                        if (c == '.') {
                            JTextField field = square[i][j];
                            field.setText("");
                        } else {
                            String s = String.valueOf(c);
                            char c1 = s.charAt(0);
                            board[i][j] = c1;
                            JTextField jTextField = square[i][j];
                            jTextField.setText(s);
                        }
                    }
                }
                repaint();
            }
        });

        // button for clear
        clear = new JButton("Clear Everything");
        clear.setSize(new Dimension(10, 40));
        clear.setBorder(new RoundedBorder(10));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        JTextField field = square[i][j];
                        field.setText("");

                    }
                }
                board = clearBoard;
                repaint();
            }
        });

        // button for Solve
        solve=new JButton("Solve");
        solve.setSize(new Dimension(10, 40));
        solve.setBorder(new RoundedBorder(10));
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "you have pressed the Solve button");




                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        char c = solvedBoard[i][j];
                        int num =  c - '0';
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

        //button for check
        check = new JButton("Check Result");
        check.setSize(new Dimension(10, 40));
        check.setBorder(new RoundedBorder(10));
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        JTextField jTextField = square[i][j];
                        String s = jTextField.getText();
                        if (!s.isEmpty()) {

                            Integer integer = Integer.parseInt(s);
                            char c = solvedBoard[i][j];
                            Integer integer2 = Character.getNumericValue(c);
                            if (integer != integer2) {
                                JOptionPane.showMessageDialog(null, "not correct");
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "you did not finish !");
                            return;
                        }



                    }
                }

                JOptionPane.showMessageDialog(null, "correct!");
            }
        });

        // button for puzzle
        puzzle=new JButton("Create New Puzzle");
        puzzle.setSize(new Dimension(10, 40));
        puzzle.setBorder(new RoundedBorder(10));
        puzzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "you have pressed the Create New Puzzle button");


                resetBoard = new char[9][9];
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        JTextField t = square[i][j];
                        String text = t.getText();
                        if (text.length() > 0) {
                            int k = Integer.parseInt(text);
                            char c = (char) (k + '0');
                            board[i][j] = c;
                            resetBoard[i][j] = c;
                        } else {
                            board[i][j] = '.';
                            resetBoard[i][j] = '.';
                        }

                    }
                }

                solvedBoard =  s.solve(board);
            }
        });





        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6,1));
        p2.add(reset);
        p2.add(clear);
        p2.add(solve);
        p2.add(check);
        p2.add(puzzle);


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


}