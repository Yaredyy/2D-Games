package My2dGame.main;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
    
    // Screen Settings
    final int originalTileSize = 16; //16*16 tile
    final int scale = 3;
    
    final int tileSize = originalTileSize * scale; // 48*48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize*maxScreenCol; //768 pixels
    final int screenHeight = tileSize*maxScreenRow; //576 pixels

    


}