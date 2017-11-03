import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speedX;
    int speedY;
    public Bullet(int sX, int sY)
    {
        speedX = sX;
        speedY = sY;
    }

    public void act() 
    {
        
        setLocation(getX()+speedX, getY()-speedY);
        
        
        hitEnemyOrBounds();
    }   

    public void hitEnemyOrBounds(){
        Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        if (e != null){
            getWorld().addObject(new Coin(1), e.getX(), e.getY());
            getWorld().removeObject(e);
            
            getWorld().removeObject(this);
        }
        else if (getX()> 600 ){
            getWorld().removeObject(this);
        } else if (getX() < 0){
            getWorld().removeObject(this);
        }
    }
}
