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

    private int shootCount;
    private int jumpHeight = 10;
    private int doubleJumpHeight = 7;
    private int walkSpeed = 5;
    private double fallSpeed = .6;
    private boolean inTheAir = false;
    private double dX = 0;
    private boolean jumped;
    private double dY = 0;
    Platform platBelow;
    private boolean shootL;
    private boolean shootR;
    int bossTimer = 0;
    private boolean shootUp;
    private boolean isLeft;
    private boolean canMoveL = true;
    private boolean canMoveR = true;
    private int groundHeight = getImage().getHeight()/2;
    private int sideWidth = getImage().getWidth()/2;
    private World myWorld;
    int worldHeight;
    int worldWidth;
    int invulnTime;
    private int health;
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
         getKey();  
        move(); 
        
        hit();
        shootDirection();
        //if(inTheAir) fall();
        if(!inTheAir) jumped = false;
        pickUpCoins();
        checkForNewLife();
        shootCount++;
        if(bossTimer == 3600){
            //health+=10;
            Greenfoot.setWorld(new BossWorld(this));
        }
        bossTimer++;
    }

    private void wrap(){
        
    }

    private void shoot(int speedX, int speedY){
        if ( shootCount >= 25){

            if(shootL){
                setImage("wizL.png");
                if(!shootUp){
                    getWorld().addObject(new Bullet(speedX, speedY, true, false), getX()-getImage().getWidth()/2, getY());

                }
                else {
                    getWorld().addObject(new Bullet(speedX, speedY, true, true), getX()-getImage().getWidth()/2, getY());
                }
            }
            if (shootR){
                setImage("wizR.png");
                if(!shootUp){
                    getWorld().addObject(new Bullet(speedX, speedY, true, false), getX()+getImage().getWidth()/2, getY());
                }
                else{
                    getWorld().addObject(new Bullet(speedX, speedY, true, true), getX()+getImage().getWidth()/2, getY());
                }
                if(shootUp){}
            }
            if(shootUp && !shootL && !shootR){

                getWorld().addObject(new Bullet(speedX, speedY, false, true), getX()+getImage().getWidth()/2, getY());

            }
            shootCount = 0;
        }
    }

    private void run ()
    {
        if(isLeft && canMoveL)
            dX = walkSpeed * -1;
        else if(!isLeft && canMoveR)
            dX = walkSpeed;
    }

    private void stop()
    {
        dX = 0;
    }

    private void jump()
    {
        dY= jumpHeight;
        inTheAir = true;
        //jumped = false;
    }
    private void doubleJump(){
        dY = doubleJumpHeight;
        inTheAir = true;
        jumped = true;
    }

    private void fall()
    {
        dY -= fallSpeed;
        //inTheAir = true;
    }
    //checks for a platform and falls if it doesnt find one
    private void move()
    {
        double newX = getX() + dX;
        double newY = getY() - dY;
        if(getX() >= 600) newX = 0 + dX;
        else if(getX() <= 0) newX = 600 + dX;
        platBelow = (Platform) getOneObjectAtOffset(0,groundHeight + 5,Platform.class);
        if (platBelow != null)
        {
            GreenfootImage platImage = platBelow.getImage();
            int w = (int) (platImage.getWidth()/2 * platBelow.getScale());
            if (dY <0){
                if(getX() < platBelow.getX()+w && getX() > platBelow.getX()-w){
                dY = -1;
                inTheAir = false;
                
                int topOfPlat = platBelow.getY()- platImage.getHeight()/2;
                newY = topOfPlat - groundHeight;
             }
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
         Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        if(Greenfoot.isKeyDown("A"))
        {
            isLeft = true;
            setImage("wizL.png");
            run();
        } else if (Greenfoot.isKeyDown("D"))
        {
            isLeft = false;
            setImage("wizR.png");
            run();
        }else if(e== null)
        {
            stop();
        }
        if (Greenfoot.getKey() == ("space") )
        {
            if(!inTheAir) jump();
            else if(inTheAir && !jumped) doubleJump();
        }
      

    }

    private void shootDirection(){
        if(Greenfoot.isKeyDown("left")){
            shootL = true;
            shootR = false;
            if(Greenfoot.isKeyDown("up")){
                shootUp = true;
                shoot(-4,4);
            }
            else shoot(-4, 0);
            //shootUp = false;
        }
        else if(Greenfoot.isKeyDown("right")){
            shootL = false;
            shootR = true;
            if(Greenfoot.isKeyDown("up")){
                shootUp = true;
                shoot(4,4);
            }
            else shoot(4, 0);
        }

        if(Greenfoot.isKeyDown("up")){
            shootUp = true;
            shootL = false;
            shootR = false;
            shoot(0, 4);
        }
        else shootUp = false;
    }
    public int getHealth(){
        return health;
    }
    private void hit(){
        Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        if(e!= null ){
            if(e.getX() < this.getX()){
                canMoveL = false;
                dX+=3;
                
            }
            else if(e.getX() >= getX()){
                canMoveR = false;
                dX-=3;
                
            }
            if(invulnTime == 0){
                invulnTime = 45;
                //health--;
                List <Life> lives = getObjectsInRange(700,Life.class);
                for(Life l : lives){
                    if(l.getLife() == health) getWorld().removeObject(l);
                }
                if(health == 0) Greenfoot.setWorld(new GameOver());
            }
        }
        else if( e == null){
            canMoveL = true;
            canMoveR = true;
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

