import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener , ActionListener {

    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];

    private int[] enemyXpos = new int[]{25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
                                        450,475,500,525,550,575,600,625,650};
    private int[] enemyYpos = new int[]{75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,
                                         475,500,525,550,575,600,625};



    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private int sizeOfSnake = 3;

    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon enemy;

    private int score = 0;

    Random random = new Random();

    int xpos = random.nextInt(26);
    int ypos = random.nextInt(23);


    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeImage;

    private int moves = 0;

    public GamePlay(){

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){

        if(moves == 0){

            snakexlength[2] = 50;
            snakexlength[1] = 75;
            snakexlength[0] = 100;

            snakeylength[2] = 100;
            snakeylength[1] = 100;
            snakeylength[0] = 100;

        }


        //draw title image border
        g.setColor(Color.white);
        g.drawRect(24,10 ,851 ,55 );

        //draw the title image
        ImageIcon titleImage = new ImageIcon(getClass().getResource("snaketitle.jpg"));
        titleImage.paintIcon(this,g ,25 ,11 );

        //draw border for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24, 71,851 ,577 );

        //draw background for gameplay
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);


        //score postion
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Score = "+score,780 ,30 );

        //length of the snake

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Length = "+sizeOfSnake,780 ,50 );

        //default position of the snake
        rightMouth = new ImageIcon(getClass().getResource("rightmouth.png"));
        rightMouth.paintIcon(this,g ,snakexlength[0] ,snakeylength[0] );

        for(int i=0;i<sizeOfSnake;i++){

            if(i==0 && right){
                rightMouth = new ImageIcon(getClass().getResource("rightmouth.png"));
                rightMouth.paintIcon(this,g ,snakexlength[i] ,snakeylength[i] );
            }

            if(i==0 && left){
                leftMouth = new ImageIcon(getClass().getResource("leftmouth.png"));
                leftMouth.paintIcon(this,g ,snakexlength[i] ,snakeylength[i]);
            }

            if(i==0 && down){
                downMouth = new ImageIcon(getClass().getResource("downmouth.png"));
                downMouth.paintIcon(this,g ,snakexlength[i] ,snakeylength[i] );
            }

            if(i==0 && up){
                upMouth = new ImageIcon(getClass().getResource("upmouth.png"));
                upMouth.paintIcon(this,g ,snakexlength[i] ,snakeylength[i] );
            }

            if(i!=0){
                snakeImage = new ImageIcon(getClass().getResource("snakeimage.png"));
                snakeImage.paintIcon(this,g ,snakexlength[i] ,snakeylength[i] );
            }
        }

        enemy = new ImageIcon(getClass().getResource("enemy.png"));
        if(enemyXpos[xpos] == snakexlength[0] && enemyYpos[ypos] == snakeylength[0]){
            score = score + 5;
            sizeOfSnake++;
            xpos = random.nextInt(26);
            ypos = random.nextInt(23);
        }

        enemy.paintIcon(this,g ,enemyXpos[xpos] ,enemyYpos[ypos] );

        for(int i=1;i<sizeOfSnake;i++){
            if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]){
                up = false;
                down = false;
                right = false;
                left = false;
                g.setColor(Color.white);
                g.setFont(new Font("airal",Font.BOLD,50));
                g.drawString("Game Over",300 ,300 );

                g.setFont(new Font("airal",Font.BOLD,20));
                g.drawString("press space to RESTART",350 ,340 );

                g.setColor(Color.GREEN);
                g.setFont(new Font("airal",Font.BOLD,15));
                g.drawString("Your Score = "+score,400 ,380 );


            }
        }

       g.dispose();
    }

    //default position of the snake


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){

            /**
             *  both the functions are doing samething but for snakeYlength is checking
             *  starting from 2 block and shifting all the block one position left and
             *  snakeXlength starting form 3 advance checking weather it reaches towards
             *  end of size.
             */

            for(int i=sizeOfSnake-1;i>=0;i--){
                snakeylength[i+1] = snakeylength[i];
            }
            for(int i=sizeOfSnake;i>=0;i--){
                if(i==0){
                    snakexlength[i] = snakexlength[i] + 25;
                }
                else{
                    snakexlength[i] = snakexlength[i-1];
                }
                if(snakexlength[i] > 850)
                    snakexlength[i] = 25;
            }
            repaint();
        }
        if(left){
            for(int i=sizeOfSnake-1;i>=0;i--){
                snakeylength[i+1] = snakeylength[i];
            }
            for(int i=sizeOfSnake;i>=0;i--){
                if(i==0){
                    snakexlength[i] = snakexlength[i] - 25;
                }
                else{
                    snakexlength[i] = snakexlength[i-1];
                }
                if(snakexlength[i]<25)
                    snakexlength[i] = 850;
            }
            repaint();
        }
        if(up){
            for(int i=sizeOfSnake-1;i>=0;i--){
                snakexlength[i+1] = snakexlength[i];
            }
            for(int i=sizeOfSnake;i>=0;i--){
                if(i==0){
                    snakeylength[i] = snakeylength[i] - 25;
                }
                else{
                    snakeylength[i] = snakeylength[i-1];
                }
                if(snakeylength[i] < 75)
                    snakeylength[i] = 625;
            }
            repaint();
        }
        if(down){
            for(int i=sizeOfSnake-1;i>=0;i--){
                snakexlength[i+1] = snakexlength[i];
            }
            for(int i=sizeOfSnake;i>=0;i--){
                if(i==0){
                    snakeylength[i] = snakeylength[i] + 25;
                }
                else{
                    snakeylength[i] = snakeylength[i-1];
                }
                if(snakeylength[i] > 625)
                    snakeylength[i] = 75;
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moves = 0;
            score = 0;
            sizeOfSnake = 3;
            repaint();
        }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT){
             moves++;
             right = true;
             if(!left){
                 right = true;
             }
             else{
                 right = false;
                 left = true;
             }
             up = false;
             down = false;
         }

         if(e.getKeyCode() == KeyEvent.VK_LEFT){
             moves++;
             left = true;
             if(!right){
                 left = true;
             }else{
                 left = false;
                 right = true;
             }
             up = false;
             down = false;
         }

         if(e.getKeyCode() == KeyEvent.VK_UP){
             moves++;
             up = true;
             if(!down){
                 up = true;
             }else{
                 up = false;
                 down = true;
             }
             left = false;
             right = false;
         }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            if(!up){
                down = true;
            }else{
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
