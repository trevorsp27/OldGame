import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class JumpingEnemy extends Enemy{

    private Rectangle upRect, downRect, leftRect, rightRect;
    private final double X_SPEED = 0.3;
    private double xspeed = 0.3;
    private double yspeed = 0;
    private double gravity = 0.01;
    
    public JumpingEnemy(double x, double y, int w, int h, String ID){
        super(x,y,w,h,ID);
        this.yspeed = yspeed;
    }

    public void paint(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval((int)x,(int)y,w,h);
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
        movement();
        xspeed = X_SPEED;
    }
    
    public void gravity(){   
        y+=yspeed;
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
                yspeed = -1.5;
                xspeed = 0;
                x = getObjectList().get(i).getX()+getObjectList().get(i).getW();
            }
            if(rightRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                xspeed = 0;
                x = getObjectList().get(i).getX()-getW();
                yspeed = -1.5;
            }
        }    
    }
    
    private void movement(){
        double playerX = getObjectList().get(0).getX();
        if(playerX > getX()){
            x+=xspeed;
        }else if(playerX < getX()){
            x-=xspeed;
        }
    }
    
}
