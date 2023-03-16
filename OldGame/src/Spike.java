import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Spike extends GameObject{

    BufferedImage spike_image;
    private double velY;
    
    public Spike(double x, double y, int w, int h, String ID, double velY){
        super(x,y,w,h,ID);
        this.velY = velY;
        loadImage();
    }
    
    public void paint(Graphics g){
    
        //g.setColor(Color.RED);
        //g.fillRect((int)x,(int)y,16,16);
        g.drawImage(spike_image,(int)x,(int)y,null);
        update();
        
    }

    public void update(){
        setY(getY()+velY);
        setBounds(new Rectangle((int)getX(),(int)getY(),getW(),getH()));
    }
    
    public double getVelY(){
        return this.velY;
    }
    public void setVelY(double velY){
        this.velY = velY;
    }
    
    public void loadImage(){
        try{
            if(velY < 0)
                spike_image = ImageIO.read(new File("spikeup.jpg"));
            else if(velY > 0)    
                spike_image = ImageIO.read(new File("spikedown.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
