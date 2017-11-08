import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CoinTotal1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoinTotal1 extends Actor
{
    /**
     * Act - do whatever the CoinTotal1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    String coinsString;
    int coinsInt;
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            coinsString = Greenfoot.ask("How many coins? (enter a number greater than 0)");
            coinsInt = parseWithDefault(coinsString, -1);

        }
        if(coinsString != null){
            GreenfootImage img = new GreenfootImage(coinsString, 48, Color.BLACK, Color.WHITE);
            setImage(img);
        }
    }    

    public int getCoins(){
        return coinsInt;
    }

    public static int parseWithDefault(String number, int defaultVal) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
