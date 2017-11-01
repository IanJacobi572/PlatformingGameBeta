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
    public int platSpawn0x = 100;
    public int platSpawn1x = 200;
    public int platSpawn2x = 300;
    public int platSpawn3x = 400;
    public int platSpawn4x = 500;
    public int platSpawn5x = 600;
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
        addObject(actCounter, 540, 20);
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
        addObject(main, 300,400 - playerHeight);
       

    }

    public void act(){
        if(timer == 60* n){
            actCounter.setValue(main.getCoins());
            platSpawn();
            n++;
        }
        timer++;
    }

    public void platSpawn(){
        int s = Greenfoot.getRandomNumber(2) + 1;
        int rand = Greenfoot.getRandomNumber(51)+150;
        double scale = (double)rand/100;
        int spawnLocation = Greenfoot.getRandomNumber(5);
        Platform platSpawn = new Platform(s,0,scale);
        Coin c = new Coin(s);
        Enemy e = new Enemy(s);
        int spawn = Greenfoot.getRandomNumber(100);
        int spawnY = platSpawnY - 30;
        if (spawnLocation == 0){
            addObject( platSpawn, platSpawn0x, platSpawnY);
            if (spawn > 70){
                addObject(c, platSpawn0x, spawnY);
            }else if (spawn < 30){
                addObject(e, platSpawn0x, spawnY);
            }
        }else if (spawnLocation ==1){
            addObject(platSpawn, platSpawn1x, platSpawnY);
            if (spawn > 70){
                addObject(c, platSpawn1x, spawnY);
            }else if (spawn < 30){
                addObject(e, platSpawn1x, spawnY);
            }
        }else if (spawnLocation == 2){
            addObject(platSpawn, platSpawn2x, platSpawnY);
            if (spawn > 70){
                addObject(c, platSpawn2x, spawnY);
            }else if (spawn < 30){
                addObject(e, platSpawn2x, spawnY);
            }
        }else if ( spawnLocation == 3){
            addObject(platSpawn, platSpawn3x, platSpawnY);
            if (spawn > 70){
                addObject(c, platSpawn3x, spawnY);
            }else if (spawn < 30){
                addObject(e, platSpawn3x, spawnY);
            }
        }else if (spawnLocation == 4){
            addObject(platSpawn,platSpawn4x,platSpawnY);
            if (Greenfoot.getRandomNumber(100)> 70){
                addObject(c, platSpawn4x, spawnY);
            }else if (spawn < 30){
                addObject(e, platSpawn4x, spawnY);
            }
        }else if (spawnLocation == 5){
            addObject(platSpawn,platSpawn5x,platSpawnY);
            if (spawn > 70){
                addObject(c, platSpawn5x, spawnY);
            }else if (spawn < 30){
                addObject(e, platSpawn5x, spawnY);
            }
        }
    }

}
