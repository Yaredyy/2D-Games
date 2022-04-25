package My2dGame.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import My2dGame.main.GamePanel;

import java.awt.Graphics2D;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        this.tile=new Tile[10];
        this.mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/My2dGame/res/Map/world01.txt");
        getTileImage();
    }
    public void loadMap(String mapPath){
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col<gp.maxWorldCol&&row<gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col = 0;
                    row++;
                }
                

            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        try{
            tile[0]=new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/Tiles/Old version/grass.png"));
            tile[1]=new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/Tiles/Old version/wall.png"));
            tile[2]=new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/Tiles/Old version/water.png"));
            tile[3]=new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/Tiles/Old version/earth.png"));
            tile[4]=new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/Tiles/Old version/tree.png"));
            tile[5]=new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/My2dGame/res/Tiles/Old version/sand.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX= worldCol * gp.tileSize;
            int worldY = worldRow*gp.tileSize;
            int screenX = worldX-gp.player.worldx + gp.player.screenx;
            int screeny = worldY-gp.player.worldy + gp.player.screeny;

            if(worldX + gp.tileSize > gp.player.worldx - gp.player.screenx 
            && worldX - gp.tileSize < gp.player.worldx + gp.player.screenx 
            && worldY + gp.tileSize > gp.player.worldy - gp.player.screeny 
            && worldY - gp.tileSize < gp.player.worldy + gp.player.screeny){
                g2.drawImage(tile[tileNum].image, screenX, screeny, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol==gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
        
    }
}
