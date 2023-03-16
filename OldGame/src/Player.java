import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Player extends GameObject implements KeyListener{

    private double xspeed = 1;
    private double yspeed = 0;
    private final double GRAVITY = 0.01;
    private boolean jumpable = false;
    private boolean wPressed, aPressed, sPressed, dPressed = false;
    private boolean up,down,left,right;
    private Rectangle upRect, downRect, leftRect, rightRect;
    private boolean dead = false;
    
    public Player(int x, int y, int w, int h, String ID){
        super(x,y,w,h,ID);
        upRect = new Rectangle((int)getX()+4, (int)getY(), 58,32);
        downRect = new Rectangle((int)getX()+4, (int)getY()+48, 58,16);
        leftRect = new Rectangle((int)getX(), (int)getY()+4, 16,58);
        rightRect = new Rectangle((int)getX()+48, (int)getY()+4, 16,58);
    }
    
    public void paint(Graphics g){       
        g.setColor(Color.MAGENTA);
        g.fillOval((int)getX(),(int)getY(),getW(),getH());        
    }
    
    public void update(){        
        checkMovement();
        collision();
        movement();
        gravity();        
    }
    
    public void gravity(){   
        setY(getY()+yspeed);
        yspeed+=GRAVITY;        
    }
    
    public void movement(){
        if(left){
            setX(getX()-xspeed);
        }
        if(right){
            setX(getX()+xspeed);
        }
        if(up && jumpable){
            jumpable = false;
            setY(getY()-4);
            yspeed = -2;
        }
        setBounds(new Rectangle((int)getX(), (int)getY(), getW(),getH()));
        upRect = new Rectangle((int)getX()+4, (int)getY(), 58,32);
        downRect = new Rectangle((int)getX()+4, (int)getY()+48, 58,16);
        leftRect = new Rectangle((int)getX(), (int)getY()+4, 16,58);
        rightRect = new Rectangle((int)getX()+48, (int)getY()+4, 16,58);
    }
    
    public void collision(){
        for(int i = 1; i < getObjectList().size(); i++){
            //block collision
            if(upRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                up = false;
                yspeed = 0.2;
            }
            if(downRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                down = false;
                setY(getObjectList().get(i).getY()-63);
                yspeed = 0;
                jumpable = true;
            }
            if(leftRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                left = false;
            }
            if(rightRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                right = false;
            }
            //spike collision
            if(getBounds().intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("SPIKE")){
                dead = true;
            }
            //enemy collision
            //if(downRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("ENEMY")){
                //getObjectList().get(i).remove();
            //}
            if(getBounds().intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("ENEMY")){
                dead = true;
            }
        }
    }
    
    public void checkMovement(){
        if(wPressed){
            up = true;
        }else{
             up = false;
        }
        if(aPressed){
            left = true;
        }else{
             left = false;
        }
        if(sPressed){
            down = true;
        }else{
             down = false;
        }
        if(dPressed){
            right = true;
        }else{
             right = false;
        }
    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            wPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            aPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            sPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            dPressed = true;
        }
    }
    
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            wPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            aPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            sPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            dPressed = false;
        }
    }
    
    public void keyTyped(KeyEvent e){
   
    }
 
    public boolean getDead(){
        return this.dead;
    }
    public void setDead(boolean dead){
        this.dead = dead;
    }
    
}