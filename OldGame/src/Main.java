import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main extends JComponent implements MouseMotionListener{
    
    public static final int WIDTH = 832;
    public static final int HEIGHT = 832;

    Map map = new Map();
    BufferedImage background = null;
    
    public Main(){
        setFocusable(true);
        new Window(WIDTH,HEIGHT,this);
        addKeyListener(map.player1);
        requestFocusInWindow();
        loadImage();
    }
    
    public static void main(String[] args){
        new Main();
    }

    public void paintComponent(Graphics g){

        //while playing
        update();

        //background image
        g.setColor(new Color(115,194,251));
        g.fillRect(0,0,WIDTH,HEIGHT);
        //g.drawImage(background,0,0,null);
        
        if(map.player1.getX() > WIDTH/2)    
            g.translate(-(int)map.player1.getX()+WIDTH/2,0);

        map.paint(g);
        
        if(map.player1.getX() > WIDTH/2) 
            g.translate((int)map.player1.getX()-WIDTH/2,0);
       
            
            
        repaint();
    }
    
    public void update(){
        map.update();        
    }

    private void loadImage(){
        try{
             background = ImageIO.read(new File("background.jpg"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //buttons for mousemotionlistener
    public void mouseDragged(MouseEvent e){
    
    }
    public void mouseMoved(MouseEvent e){
    
    }
    
}