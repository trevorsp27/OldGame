import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Map{
    
    private int level = 1;
    private double levelStartX,levelStartY;
    
    Player player1 = new Player(200,200, 64, 64,"PLAYER");
    ArrayList<GameObject> levelObjects = new ArrayList<GameObject>();
    
    BufferedImage level_1 = null;
    BufferedImage level_2 = null;
    
    public Map(){
        loadMapImages();
        loadLevel(level_1);
    }
    
    public void paint(Graphics g){
        paintObjects(g);
        player1.paint(g);
    }
    
    public void update(){    
        player1.update();  
        checkLevel();
        checkDead();
    }
    
    public void loadLevel(BufferedImage img){ 
        int pixel, red, green, blue;
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){           
                pixel = img.getRGB(i,j);
                red = (pixel >> 16) & 0xff;
                green = (pixel >> 8) & 0xff;
                blue = pixel & 0xff;                
                
                //Grassblock = 0 0 0
                //Grassblock = 75 75 75
                //checkpoint = 125 125 125
                //player = 195 195 195
                //spikeshooter = 225 225 225
                //jumpingenemy = 55 55 55
                //walkingenemy = 35 35 35
                
                if(red >= 190 && red < 200 && green >= 190 && green < 200 && blue >= 190 && blue < 200){
                    player1.setX(i*32);
                    player1.setY(j*32);
                    levelStartX = i*32;
                    levelStartY = j*32;
                }
                if(red >= 0 && red < 15 && green >= 0 && green < 15 && blue >= 0 && blue < 15){
                    levelObjects.add(new GrassBlock(i*32, j*32, 32, 32, "BLOCK"));
                }
                if(red >=65 && red < 90 && green >= 65 && green < 90 && blue >= 65 && blue < 90){
                    levelObjects.add(new DirtBlock(i*32, j*32, 32, 32, "BLOCK"));
                }
                if(red >= 120 && red < 130 && green >= 120 && green < 130 && blue >= 120 && blue < 130){
                    levelObjects.add(new Checkpoint(i*32, j*32, 32, 64, "CHECKPOINT"));
                }
                if(red >= 220 && red < 230 && green >= 220 && green < 230 && blue >= 220 && blue < 230){
                    levelObjects.add(new SpikeShooter(i*32, j*32, 32, 32, "BLOCK", 0.5));
                }
                if(red >= 50 && red < 60 && green >= 50 && green < 60 && blue >= 50 && blue < 60){
                    levelObjects.add(new JumpingEnemy(i*32, j*32, 64, 64, "ENEMY"));
                }
                if(red >= 30 && red < 40 && green >= 30 && green < 40 && blue >= 30 && blue < 40){
                    levelObjects.add(new WalkingEnemy(i*32, j*32, 90, 64, "ENEMY"));
                }
            }
        }        
    }

    public void paintObjects(Graphics g){
        for(int i = 0; i < levelObjects.size(); i++){
            levelObjects.get(i).paint(g);
        }
    }
    
    //load all map images
    public void loadMapImages(){    
        try{
            level_1 = ImageIO.read(new File("level_1.png"));
            level_2 = ImageIO.read(new File("level_2.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }    
    }
    
    public void checkLevel(){
        //checkpoint collision
        for(int i = 1; i < player1.getObjectList().size(); i++){
            if(player1.getBounds().intersects(player1.getObjectList().get(i).getBounds()) && player1.getObjectList().get(i).getID().equals("CHECKPOINT")){
                levelObjects.clear();
                player1.removeObjects();
                level++;
                loadNextLevel();
            }
        }
    }
    
    public void checkDead(){
        if(player1.getDead()){
            player1.setX(levelStartX);
            player1.setY(levelStartY);
        }
        player1.setDead(false);
    }
    
    public void loadNextLevel(){
        if(level == 2){
            loadLevel(level_2);
        }
        if(level == 3){
            
        }     
        if(level == 4){
            
        }     
        if(level == 5){
            
        }     
    }
    
}