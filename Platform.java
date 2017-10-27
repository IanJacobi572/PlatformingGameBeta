import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    /**
     * Act - do whatever the Platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int speed;
    public int timer;
    public int start;
    public double scale;
    public Platform(int s, int st, double sc){
        speed = s;
        start = st;
        scale = sc;
    }
    public void addedToWorld(World world){
        GreenfootImage img = getImage();
        int newWidth = (int) (img.getWidth() * scale);
        img.scale(newWidth, img.getHeight());
    }
    public void act() 
    {
        if(timer > start){
            int X = getX();
            int Y = getY();
            setLocation(X,Y+1);
        }
        timer++;
    }    
}
