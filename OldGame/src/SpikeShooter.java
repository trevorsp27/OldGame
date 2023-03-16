import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class SpikeShooter extends Block{

    Spike spike;
    BufferedImage spike_shooter;
    
    public SpikeShooter(double x, double y, int w, int h, String ID, double velY){
        super(x,y,w,h,ID);
        spike = new Spike(x+8, y, 16, 16, "SPIKE", velY);
        if(getObjectList().get(0).getY() < this.getY()){
            spike.setVelY(-0.5);
        }
        try{
            spike_shooter = ImageIO.read(new File("spike_shooter.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        } 
    }

    public void paint(Graphics g){    
        spike.paint(g);
        g.drawImage(spike_shooter,(int)x,(int)y,null);
        update();       
    }
    
    public void update(){
        for(int i = 0; i < getObjectList().size(); i++){
            if(spike.getBounds().intersects(getObjectList().get(i).getBounds()) && getObjectList().get(i).getID().equals("BLOCK") && !getObjectList().get(i).equals(this)){
                spike.setY(y);
                if(getObjectList().get(0).getY() < this.getY()){
                    spike.setVelY(-0.5);
                }else{
                    spike.setVelY(0.5);
                }
                spike.loadImage();
            }
        }
    }
    
}
