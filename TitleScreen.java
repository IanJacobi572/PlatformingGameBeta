import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    Two t = new Two();
        Endless e = new Endless();
    int timer;
    boolean chosen;
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        addObject(t, 125, 105);
        addObject(e, 455, 105);
        Greenfoot.start();
    }
    public void act(){
        if(Greenfoot.mouseClicked(t)){
            timer = t.getTimer();
            chosen = true;
        }
        if(Greenfoot.mouseClicked(e)){
            timer = -1;
            chosen = true;
        }
        if(Greenfoot.getKey() == ("enter") && chosen){
            Greenfoot.setWorld(new MyWorld(timer));
        }
    }
}

