import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 
     *'Run' button gets pressed in the environment.
     */
    private int jumpHeight = 15;
    private int walkSpeed = 5;
    private double fallSpeed = 0.4;
    private boolean inTheAir = false;
    private double dX = 0;
    private double dY = 0;
    private boolean isLeft;
    private int groundHeight = getImage().getHeight()/2;
    private int sideWidth = getImage().getWidth()/2;
    private World myWorld;
    int worldHeight;
    int worldWidth;
    int invulnTime;
    int health;
    int coins;
    int oldLife;
    boolean gotLife = false;
    int lifeMult = 1;
    public void addedToWorld(World myWorld)
    {
        this.myWorld = myWorld;
        this.worldHeight = myWorld.getHeight();
        this.worldWidth = myWorld.getWidth();
        invulnTime = 0;
        health = 3;
        coins = 0;
    }

    public void act() 
    {
        if(inTheAir)
        {
            fall();
        }else {
            getKey();
        }
        move();
        if(Greenfoot.isKeyDown("left")){
            isLeft = true;
        }
        if(Greenfoot.isKeyDown("right")){
            isLeft = false;
        }
        if(Greenfoot.mouseClicked(null)){
            if(isLeft) shoot(-4);
            else shoot(4);
        }
        hit();
        pickUpCoins();
        checkForNewLife();
    }

    private void shoot(int speed){
        if(isLeft) getWorld().addObject(new Bullet(speed), getX()-getImage().getWidth()/2, getY());
        else getWorld().addObject(new Bullet(speed), getX()+getImage().getWidth()/2, getY());
    }
    
    private void run (String direction)
    {
        if(direction == "left")
            dX = walkSpeed * -1;
        else
            dX = walkSpeed;
    }

    private void stop()
    {
        dX = 0;
    }

    private void jump()
    {
        dY += jumpHeight;
        inTheAir = true;
    }

    private void fall()
    {
        dY -= fallSpeed;
    }
//checks for a platform and falls if it doesnt find one
    private void move()
    {
        double newX = getX() + dX;
        double newY = getY() - dY;

        Actor platBelow = getOneObjectAtOffset(0,groundHeight + 5,Platform.class);
        if (platBelow != null)
        {
            if (dY <0){
                dY = 0;
                inTheAir = false;
                GreenfootImage platImage = platBelow.getImage();
                int topOfPlat = platBelow.getY()- platImage.getHeight()/2;
                newY = topOfPlat - groundHeight;
            }
        }else if (getY() >= worldHeight - groundHeight)
        {
            if(dY<0)
            {
                dY = 0;
                inTheAir = false;
                newY = worldHeight - groundHeight;
            }
        }

        else {
            fall();
        }
        setLocation((int) newX, (int) newY);

    }
//checks for wich key is down
    private void getKey()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            run("left");

        } else if (Greenfoot.isKeyDown("right"))
        {
            run("right");
        }else 
        {
            stop();
        }
        if (Greenfoot.isKeyDown("up"))
        {
            jump();
        }

    }
    private void hit(){
        Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        if(e!= null && invulnTime == 0){
            invulnTime = 45;
            health--;
            List <Life> lives = getObjectsInRange(700,Life.class);
            for(Life l : lives){
                if(l.getLife() == health) getWorld().removeObject(l);
            }
            if(health == 0) Greenfoot.stop();
        }
        if(invulnTime > 0) invulnTime--;
    }
    private void pickUpCoins(){
        Coin c = (Coin) getOneIntersectingObject(Coin.class);
        if(c!= null){
            getWorld().removeObject(c);
            coins++;
        }
    }
    public int getCoins(){
        return coins;
    }
    private void checkForNewLife(){
        oldLife = ((lifeMult-1) * 10) + 1;
        if(oldLife == coins) gotLife = false;
        if(coins == 10*lifeMult && !gotLife){
            gotLife = true;
            List <Life> lives = getObjectsInRange(700,Life.class);
            for(Life l : lives){
                if(l.getLife() == health - 1) {
                    getWorld().addObject(new Life(health), l.getX()+25, l.getY());
                }
                
            }
            health++;
            lifeMult++;
        }
    }
    
}

