import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public int platSpawnY=0;
    public int platSpawn1x;
    public int platSpawn2x;
    public int platSpawn3x;
    public int platSpawn4x;
    public int platSpawn5x;
    private Counter actCounter;
    public int timer =0;
    public int n = 0;
    private Player main;
    public int platformWidth;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        
        Platform bottom = new Platform(1, 400, 1);
        addObject(bottom, 300, 400);
        actCounter = new Counter("Coins: ");
        addObject(actCounter, 520, 20);
        GreenfootImage image = bottom.getImage();
        int height = image.getHeight();
        image.scale(600,height);
        int n = 0;
        for(int i = 0; i < 3; i++){
            Life l = new Life(i);
            addObject(l, 30+n, 20);
            n+=25;
        }
        main = new Player();
        GreenfootImage mainImg = main.getImage();
        int playerHeight = mainImg.getHeight()/2;
        addObject(main, 300,400 - playerHeight );

    }

    public void act(){
        if(timer == 60* n){
            int l = Greenfoot.getRandomNumber(600);
            int s = Greenfoot.getRandomNumber(2) + 1;
            int rand = Greenfoot.getRandomNumber(51)+150;
            double scale = (double)rand/100;
            Coin c = new Coin(s);
            actCounter.setValue(main.getCoins());
            // if(Greenfoot.getRandomNumber(5)==1){
            // addObject(newPlat,200,0);
            //}
            Platform p = new Platform(s, 0, scale);
            GreenfootImage cimg = c.getImage();
            addObject(p , l ,0);

            if(Greenfoot.getRandomNumber(100) > 50) addObject(c, l, 20- cimg.getHeight());
            else addObject(new Enemy(s), l - p.getImage().getHeight()/2, -20);
            n++;
        }
        timer++;
    }
}
