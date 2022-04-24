package My2dGame.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    
    // Screen Settings
    final int originalTileSize = 16; //16*16 tile
    final int scale = 3;
    
    final int tileSize = originalTileSize * scale; // 48*48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize*maxScreenCol; //768 pixels
    final int screenHeight = tileSize*maxScreenRow; //576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    //onstructor of gamePanel
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //16666667 mili seconds
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread != null){

            //update information
            update();

            // draw the screen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime <0){
                    remainingTime=0;
                }
                
                Thread.sleep((long)remainingTime);

                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if(keyH.upPressed==true){
            playerY-= playerSpeed;
        }
        if(keyH.downPressed==true){
            playerY+= playerSpeed;
        }
        if(keyH.rightPressed==true){
            playerX+= playerSpeed;
        }
        if(keyH.leftPressed==true){
            playerX-= playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);;

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }

}