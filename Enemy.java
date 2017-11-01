import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int timer = 0;
    int speed;
    Platform plat;
    GreenfootImage pimg;
    int pwidth;
    int stepsL;
    int stepsR;
    int steps;
    public Enemy(int s){
        speed = s;
        plat = null;

        stepsR = 0;
    }

    public void addedToWorld(World world){
        List<Platform> p = getObjectsInRange(40, Platform.class);
        plat = p.get(0);
        pimg = plat.getImage();
        pwidth = pimg.getWidth();
        stepsL = 0;
        move(pwidth/2);
    }

    public void act() 
    {

        if(getX() > plat.getX()-pwidth/2 && stepsL < pwidth-getImage().getWidth()/2){
            move(-2);
            stepsL+=2;
            stepsR-=2;
            steps++;

        }else if(getX() < plat.getX()+pwidth/2 && stepsR < 0){
            move(2);
            //stepsL-=2;
            stepsR+=2;
            steps++;
        }
        if( steps == pwidth-getImage().getWidth()/2){
            stepsL = 0;
            stepsR = 0;
            steps = 0;
        }
        setLocation(getX(), getY()+1);
        if (getY()-getImage().getHeight()/2 > 400){
            getWorld().removeObject(plat);
            getWorld().removeObject(this);
            
        }

    }    
}
