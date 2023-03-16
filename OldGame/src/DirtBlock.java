import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class DirtBlock extends Block{

    BufferedImage dirt = null;
    
    public DirtBlock(double x, double y, int w, int h, String ID){
        super(x,y,w,h,ID);
        try{
            dirt = ImageIO.read(new File("dirt_block.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }  
    }
    
    public void paint(Graphics g){    
        g.drawImage(dirt,(int)x,(int)y,null);  
    }

}