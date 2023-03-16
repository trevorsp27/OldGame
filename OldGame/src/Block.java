import java.awt.Graphics;
import java.awt.Color;

public class Block extends GameObject{

    public Block(double x, double y, int w, int h, String ID){
        super(x,y,w,h,ID);
    }
    
    public void paint(Graphics g){    
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int)y,w,h);
    }

}