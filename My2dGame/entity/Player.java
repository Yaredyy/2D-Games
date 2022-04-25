package My2dGame.entity;

// import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import My2dGame.main.GamePanel;
import My2dGame.main.KeyHandler;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenx;
    public final int screeny;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        this.screenx = (gp.screenWidth/2) - (gp.tileSize/2);
        this.screeny = (gp.screenHeight/2) - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldx = gp.tileSize*23;
        worldy = gp.tileSize*21;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/player/Walking sprites/boy_right_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed==true ||keyH.downPressed==true ||keyH.rightPressed==true ||keyH.leftPressed==true){
            if(keyH.upPressed==true){
                direction="up";
                worldy -= speed;
            }
            if(keyH.downPressed==true){
                direction="down";
                worldy+= speed;
            }
            if(keyH.rightPressed==true){
                direction="right";
                worldx+= speed;
            }
            if(keyH.leftPressed==true){
                direction="left";
                worldx-= speed;
            }
    
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1){
                    spriteNum=2;
                }
                else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
        }
        }
    }
    public void draw(Graphics2D g2){
       BufferedImage image = null;

       switch(direction){
           case "up":
            if(spriteNum ==1){
                image = up1;
            }
            if(spriteNum==2){
                image = up2;
            }
            break;
            case "down":
            if(spriteNum ==1){
                image = down1;
            }
            if(spriteNum==2){
                image = down2;
            }
            break;
            case "right":
            if(spriteNum ==1){
                image = right1;
            }
            if(spriteNum==2){
                image = right2;
            }
            break;
            case "left":
            if(spriteNum ==1){
                image = left1;
            }
            if(spriteNum==2){
                image = left2;
            }
            break;

       }
       g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
    }
}