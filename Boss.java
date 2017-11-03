import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Actor
{
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int timer = 0;
    int i = 0;
    public Boss(){
        GreenfootImage img = getImage();
        img.scale(200, 200);
    }

    public void act() 
    {
         fire();// Add your action code here.
        //if(timer > 0) 
        timer --;
    }    

    void fire(){
        if(timer == 0){
            if(i == 4 ){
                getWorld().addObject(new Bullet(-4, 2), getX()-getImage().getWidth()/2, getY());
                timer = 40;
                i--;
            }
            else if(i == 3 ){
                getWorld().addObject(new Bullet(-4, 1), getX()-getImage().getWidth()/2, getY());
                timer = 40;
                i--;
            }
            else if(i == 2 ){
                getWorld().addObject(new Bullet(-4, 0), getX()-getImage().getWidth()/2, getY());
                timer = 40;
                i--;
            }
            else if(i == 1){
                getWorld().addObject(new Bullet(-4, -1), getX()-getImage().getWidth()/2, getY());
                timer = 10;
                i--;
            }
        }
        if(i == 0 && timer <= -10){
            i = 4;
            timer = 10;
        }
    }
}
