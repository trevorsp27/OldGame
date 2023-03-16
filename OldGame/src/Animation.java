import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Animation{

    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;
    
    private BufferedImage[] images;
    public BufferedImage currentImage;
    
    public Animation(int speed, BufferedImage... images){
        this.speed = speed;
        this.images = new BufferedImage[images.length];
        for(int i = 0; i < images.length; i++){
            this.images[i] = images[i];
        }
        frames = images.length;
    }
    
    public void animate(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }
    
    public void nextFrame(){
        for(int i = 0; i < frames; i++){
            if(count == i){
                currentImage = images[i];
            }
        }
        count++;
        if(count > frames){
            count = 0;
        }
    }
    
    public void drawImage(Graphics g, double x, double y){
        g.drawImage(currentImage,(int)x,(int)y,null);
    }
    
}