import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class GrassBlock extends Block{

    BufferedImage grass = null;
    
    public GrassBlock(double x, double y, int w, int h, String ID){
        super(x,y,w,h,ID);
        try{
            grass = ImageIO.read(new File("grass_block.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }  
    }
    
    public void paint(Graphics g){    
        g.drawImage(grass,(int)x,(int)y,null);  
    }

}