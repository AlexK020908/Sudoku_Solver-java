
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui extends JFrame{


    private JTextField square[][];

    private JFrame frame;
    private JPanel p1,p2,panel[];

    JButton reset,solve,puzzle;

    JLabel difficulty,b1,b2;
    JComboBox difficultyBox;

    Border exteriorBorder = new LineBorder(Color.BLACK, 2);
    Border dividerBorder = new LineBorder(Color.BLACK, 1);

    private char[][] board;
    private Sudoku s = new Sudoku();





    {
        frame=new JFrame();
        frame.setTitle("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int i,j,k;
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

        p1=new JPanel();
        panel=new JPanel[9];
        p1.setLayout(new GridLayout(3,3));
        for(i=0;i<9;i++)
        {
            panel[i]=new JPanel();
            panel[i].setLayout(new GridLayout(3,3));
            for(j=0;j< 9;j++)
            {
                panel[i].add(square[i][j]);

            }
            panel[i].setBorder(exteriorBorder);
            p1.add(panel[i]);
        }
        p1.setBorder(exteriorBorder);

        class RoundedBorder implements Border
        {
            private int radius;
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


        reset=new JButton("Reset");
        reset.setSize(new Dimension(10, 40));
        reset.setBorder(new RoundedBorder(10));
        solve=new JButton("Solve");
        solve.setSize(new Dimension(10, 40));
        solve.setBorder(new RoundedBorder(10));
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "you have pressed the solve button");
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        JTextField t = square[i][j];
                        String text = t.getText();
                        if (text.length() > 0) {
                            int k = Integer.parseInt(text);
                            char c = (char) (k + '0');
                            board[i][j] = c;
                        }

                    }
                }

               char[][] solvedBoard =  s.solve(board);
                for (int i = 0 ; i < 9 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        char c = solvedBoard[i][j];
                        String s = Character.toString(c);
                        JTextField t = new JTextField(s);
                        square[i][j] = t;
                    }


                }

            }
        });


        puzzle=new JButton("New Puzzle");
        puzzle.setSize(new Dimension(10, 40));
        puzzle.setBorder(new RoundedBorder(10));

        difficulty=new JLabel("Difficulty: ");
        difficulty.setHorizontalAlignment(JLabel.CENTER);

        String[] difficulties = { "Easy", "Medium", "Hard" };
        difficultyBox = new JComboBox(difficulties);
        difficultyBox.setSelectedIndex(1);


        p2=new JPanel();
        p2.setLayout(new GridLayout(6,1));
        p2.add(reset);
        p2.add(solve);
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
    public static void main(String args[])
    {
        new gui();
    }
}


