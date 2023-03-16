import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class WalkingEnemy extends Enemy{

    BufferedImage spritesheetRight = null;
    BufferedImage spritesheetLeft = null;
    BufferedImage[] walkingRight = null;
    BufferedImage[] walkingLeft = null;
    Animation animationRight;
    Animation animationLeft;
    private Rectangle upRect, downRect, leftRect, rightRect;
    private double startXspeed;
    private double xspeed = 0.5;
    private double yspeed = 0;
    private double gravity = 0.01;
    private boolean left = false;
    private boolean right = true;
    
    public WalkingEnemy(double x, double y, int w, int h, String ID){
        super(x,y,w,h,ID);
        this.startXspeed = 0.5;
        loadImages();
        animationRight = new Animation(25, walkingRight[0],walkingRight[1],walkingRight[2],walkingRight[3],walkingRight[4]);
        animationLeft = new Animation(25, walkingLeft[0],walkingLeft[1],walkingLeft[2],walkingLeft[3],walkingLeft[4]);
    }
 
    public WalkingEnemy(double x, double y, int w, int h, String ID, double startXspeed){
        super(x,y,w,h,ID);
        this.startXspeed = startXspeed;
        loadImages();
        animationRight = new Animation(25, walkingRight[0],walkingRight[1],walkingRight[2],walkingRight[3],walkingRight[4]);
        animationLeft = new Animation(25, walkingLeft[0],walkingLeft[1],walkingLeft[2],walkingLeft[3],walkingLeft[4]);
    }
    
    public void paint(Graphics g){
        if(right){
            g.drawImage(animationRight.currentImage,(int)x,(int)y,null);
        }
        if(left){
            g.drawImage(animationLeft.currentImage,(int)x,(int)y,null);
        } 
        update();
    }
    
    public void update(){
        setBounds(new Rectangle((int)getX()+24, (int)getY(), getW()-50,getH()));
        upRect = new Rectangle((int)getX()+4, (int)getY(), 58,32);
        downRect = new Rectangle((int)getX()+4, (int)getY()+48, 58,16);
        leftRect = new Rectangle((int)getX()+36, (int)getY()+4, 16,58);
        rightRect = new Rectangle((int)getX()+48, (int)getY()+4, 16,58);
        collision();
        gravity();
        movement();
        if(right){
            animationRight.animate();
        }
        if(left){
            animationLeft.animate();
        }
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
                x+=3;
                xspeed = startXspeed;
                left = false;
                right = true;
            }
            if(rightRect.intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK")){
                x-=3;
                xspeed = -startXspeed;
                right = false;
                left = true;
            }
        }    
    }
    
    public void movement(){
        x+=xspeed;
    }
    
    public void loadImages(){
        walkingRight = new BufferedImage[5];
        walkingLeft = new BufferedImage[5];
        try{
            spritesheetRight = ImageIO.read(new File("monster_spritesheet.png")); 
            walkingRight[0] = spritesheetRight.getSubimage(0,0,90,64);
            walkingRight[1] = spritesheetRight.getSubimage(90,0,90,64);
            walkingRight[2] = spritesheetRight.getSubimage(180,0,90,64);
            walkingRight[3] = spritesheetRight.getSubimage(270,0,90,64);
            walkingRight[4] = spritesheetRight.getSubimage(360,0,90,64);
            
            spritesheetLeft = ImageIO.read(new File("monster_spritesheet2.png")); 
            walkingLeft[0] = spritesheetLeft.getSubimage(0,0,90,64);
            walkingLeft[1] = spritesheetLeft.getSubimage(90,0,90,64);
            walkingLeft[2] = spritesheetLeft.getSubimage(180,0,90,64);
            walkingLeft[3] = spritesheetLeft.getSubimage(270,0,90,64);
            walkingLeft[4] = spritesheetLeft.getSubimage(360,0,90,64);
        }catch(IOException e){
            e.printStackTrace();
        }  
    }
    
}
