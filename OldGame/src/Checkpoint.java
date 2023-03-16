import java.awt.Graphics;
import java.awt.Color;

public class Checkpoint extends GameObject{

    public Checkpoint(double x, double y, int w, int h, String ID){
        super(x,y,w,h,ID);
    }
    
    public void paint(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect((int)x, (int)y, w, h);
    }
    
}