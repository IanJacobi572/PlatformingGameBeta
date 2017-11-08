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
    CoinTotal1 c = new CoinTotal1();
    int coinsToEnd;
    //StringInputBox text = new StringInputBox();
    int timer;
    boolean chosen;
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        addObject(c,300,235);
        addObject(t, 125, 105);
        addObject(e, 455, 105);
        //addObject(text, 300, 300);
        Greenfoot.start();
        prepare();
    }

    public void act(){
        if(Greenfoot.mouseClicked(t)){
            timer = t.getTimer();
            coinsToEnd = -1;
            chosen = true;
        }
        else if(Greenfoot.mouseClicked(e)){
            timer = -1;
            coinsToEnd = -1;
            chosen = true;
        }
        else if(Greenfoot.mouseClicked(c)){
            timer = -1;

            chosen = true;
        }
        coinsToEnd = c.getCoins();
        if(Greenfoot.getKey() == ("enter") && chosen && coinsToEnd != 0 && coinsToEnd != -1){
            Greenfoot.setWorld(new MyWorld(timer, coinsToEnd));
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}

