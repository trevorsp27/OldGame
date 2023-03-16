import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class GameObject{
    
    protected double x, y;
    protected int w, h;
    protected String ID;
    public Rectangle r;
    protected static ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    
    public GameObject(double x, double y, int w, int h, String ID){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.ID = ID;
        r = new Rectangle((int)x,(int)y,w,h);
        objectList.add(this);
    }
    
    public void remove(){
        objectList.remove(this);
    }
    public void removeObjects(){
        objectList.subList(1, objectList.size()).clear();
    }
    
    public void paint(Graphics g){
    
    }
    public void update(){
    
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public int getW(){
        return this.w;
    }
    public int getH(){
        return this.h;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setW(int w){
        this.w = w;
    }
    public void setH(int h){
        this.h = h;
    }
    public Rectangle getBounds(){
        return this.r;
    }
    public void setBounds(Rectangle r){
        this.r = r;
    }
    public String getID(){
        return this.ID;
    }
    public ArrayList<GameObject> getObjectList(){
        return this.objectList;
    }
    
}