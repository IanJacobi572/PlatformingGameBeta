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
    private int coinsToEnd;
    int bossTimer;
    private int twoSpawnsAgo;
    //int bossTimeSpawn;
    public int spawnLocationLast = 0;
    int timesSpawned = 0;
    private Counter actCounter;
    public int timer =0;
    int count = 0;
    public int n = 0;
    private Player main;
    private boolean endless;
    public int platformWidth;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld(int boss, int c)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        coinsToEnd = c;
        setPaintOrder(Life.class,Counter.class,Player.class,Coin.class,Bullet.class,Enemy.class);
        bossTimer = boss;
        Platform bottom = new Platform(1, 400, 1);
        addObject(bottom, 300, 395);
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
        int playerHeight = (mainImg.getHeight());
        addObject(main, 300, 395-playerHeight);

    }

    public void act(){
        if(timer == 60* n){
            platSpawn();
            n++;
        }

        actCounter.setValue(main.getCoins());
        if(timer == bossTimer){
            //health+=10;
            Greenfoot.setWorld(new BossWorld(main));
        }
        if(coinsToEnd == main.getCoins()){
            Greenfoot.setWorld(new BossWorld(main));
        }
        timer++;
    }

    public void platSpawn(){ //Spawns platforms radomy at set positions every second
        int s = 1;
        //double rand = 0.0;
        if(n == 2*timesSpawned){
            twoSpawnsAgo = spawnLocationLast;
            timesSpawned++;
        }
        int r = Greenfoot.getRandomNumber(100);
        double scale;
        if(r <20) scale = 1.5;
        else if(r<40) scale = 2;
        else scale = 1.3;
        //scale = 1;
        int spawnLocation = Greenfoot.getRandomNumber(4);
        Platform platSpawn = new Platform(s,0,scale);
        //platBefore = spawnLocationLast;
        Coin c = new Coin(s);
        Enemy e = new Enemy(s);
        int spawn = Greenfoot.getRandomNumber(100);
        int spawnE = Greenfoot.getRandomNumber(100);
        int spawnY = platSpawnY - 30;
        if(spawn < 50 && spawnLocationLast!= 0){
            spawnLocation = spawnLocationLast-1;
            if(spawnLocation == twoSpawnsAgo) spawnLocation +=2;
        }
        else if(spawnLocationLast == 0){
            spawnLocation = spawnLocationLast +1;
        }
        else if(spawnLocationLast == 4){
            spawnLocation = spawnLocationLast-1;
        }
        else{
            spawnLocation = spawnLocationLast+1;
            if(spawnLocation == twoSpawnsAgo) spawnLocation -=2;
        }
        if (spawnLocation == 0){
            addObject( platSpawn, platSpawn0x, platSpawnY);
            if (spawnE < 50){
                addObject(e, platSpawn0x, spawnY);
            }
        }else if (spawnLocation ==1){
            addObject(platSpawn, platSpawn1x, platSpawnY);
            if (spawnE < 50){
                addObject(e, platSpawn1x, spawnY);
            }
        }else if (spawnLocation == 2){
            addObject(platSpawn, platSpawn2x, platSpawnY);
            if (spawnE < 50){
                addObject(e, platSpawn2x, spawnY);
            }
        }else if ( spawnLocation == 3){
            addObject(platSpawn, platSpawn3x, platSpawnY);
            if (spawnE < 50){
                addObject(e, platSpawn3x, spawnY);
            }
        }else if (spawnLocation == 4){
            addObject(platSpawn,platSpawn4x,platSpawnY);
            if (spawnE < 50){
                addObject(e, platSpawn4x, spawnY);
            }
            /*}else if (spawnLocation == 5){
            addObject(platSpawn,platSpawn5x,platSpawnY);
            if (spawn < 30){
            addObject(e, platSpawn5x, spawnY);
            }*/
        }
        //}
        spawnLocationLast = spawnLocation;
    }

}
