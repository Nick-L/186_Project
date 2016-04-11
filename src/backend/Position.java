package backend;

public class Position 
{
    private int owner = 0;//maybe this should be a string of the name of the player
    private String name;
    private int numHousesOwned;
    private int numHotelsOwned;
    
    public Position(String n)
    {
        name = n;
    }
    
    public int getOwner()
    {
        return owner;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void addHouse()
    {
        numHousesOwned++;
        if (numHousesOwned == 5)
        {
            numHousesOwned = 0;
            numHotelsOwned = 1;
        }
    }
    public int getHousesOwned()
    {
        return numHousesOwned;
    }
    public int getHotelsOwned()
    {
        return numHotelsOwned;
    }
    public void setOwner(int o)
    {
        owner = o;
    }
    
    public void setName(String n)
    {
        name = n;
    }
}