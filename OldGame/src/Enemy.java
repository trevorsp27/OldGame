import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Enemy extends GameObject{

    private Rectangle upRect, downRect, leftRect, rightRect;
    private double xspeed;
    private double yspeed;
    private double gravity;
    
    public Enemy(double x, double y, int w, int h, String ID){
        super(x,y,w,h,ID);
    }

    public void paint(Graphics g){
        
        update();
    }
    
    public void update(){
        setBounds(new Rectangle((int)getX(), (int)getY(), getW(),getH()));
        upRect = new Rectangle((int)getX()+4, (int)getY(), 58,32);
        downRect = new Rectangle((int)getX()+4, (int)getY()+48, 58,16);
        leftRect = new Rectangle((int)getX(), (int)getY()+4, 16,58);
        rightRect = new Rectangle((int)getX()+48, (int)getY()+4, 16,58);
        collision();
        gravity(); 
    }
    
    public void gravity(){   
        setY(getY()+yspeed);
        yspeed+=gravity;        
    }
    
    public void collision(){
        for(int i = 1; i < getObjectList().size(); i++){
            //block collision
            if(upRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                yspeed = 0.2;
            }
            if(downRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){                
                setY(getObjectList().get(i).getY()-63);
                yspeed = 0;
            }
            if(leftRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                
            }
            if(rightRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                
            }
        }    
    }
}
