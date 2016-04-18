package backend;

import java.util.ArrayList;

public class Player //
{
    private String name;
    private int position;//the array index of the position in the board
    private int cashMoney;
    private int netWorth;
    private boolean inJail;
    private ArrayList<Property> owned;
    private boolean isBankrupt;
    private int jailPasses;
    private int turnsInJail;
    private int numHouses;
    private int numHotels;
    
    public Player(int n)
    {
        name = "Player " + n;//set starting name, players may be able to change it later
        position = 0;
        cashMoney = 1500;
        netWorth = cashMoney;
        inJail = false;
        owned = new ArrayList<Property>();
        isBankrupt = false;
        jailPasses = 0;
        turnsInJail = 0;
        numHouses = 0;
        numHotels = 0;
    }
    
    //accessors
    public String getName()
    {
        return name;
    }
    
    public int getHouses()//TODO: calculate by looking at the properties
    {
        return numHouses;    
    }
    
    public int getHotels()//TODO: calculate by looking at the properties
    {
        return numHotels;
    }
    
    public void buyAHouse()
    {
        numHouses++;
    }
    
    public void buyAHotel()
    {
    	numHouses = numHouses - 4;
        numHotels++;
    }
    
    public void sellAHouse()
    {
        if (numHouses > 0)
            numHouses--;
    }
    
    public void sellAHotel()
    {
        if (numHotels > 0)
            numHotels--;
    }
    public int getPosition()
    {
        return position;
    }
    
    public int getCashMoney()
    {
        return cashMoney;
    }
    
    public int getNetWorth()
    {
        return netWorth;
    }
    
    public boolean isInJail()
    {
        return inJail;
    }
    
    public boolean owns(Property rubbin)
    {
        for(Property holding : owned)//iterates through the owned properties
        {
            if(rubbin.getName().equals(holding.getName()))//if the names of the 2 properties are the same, returns true
                return true;
        }
        
        return false;
    }
    
    public int getNumPropertiesOwned()
    {
        return owned.size();
    }
    
    public boolean getIsBankrupt()
    {
        return isBankrupt;
    }
    
    public int getJailPasses()
    {
        return jailPasses;
    }
    
    public String[] getOwnedPropNames()
    {
        String[] opn = new String[this.getNumPropertiesOwned()];
        for(int johnson = 0; johnson < this.getNumPropertiesOwned(); johnson++)
        {
            opn[johnson] = owned.get(johnson).getName();
        }
        
        return opn;
    }
    
    public Property getOwnedProperty(String propName)//returns null if the property isn't owned by the player, otherwise returns the property with propName
    {
        Property thisPropertyIs = null;
        boolean isThere = false;
        for(int byElijah = 0; byElijah < this.getNumPropertiesOwned(); byElijah++)
        {
            thisPropertyIs = owned.get(byElijah);
            if(propName.equals(thisPropertyIs.getName()))
            {
                isThere = true;
                break;
            }
        }
        if(isThere)
        {
            return thisPropertyIs;
        }
        
        return null;
    }
    
    public int getTurnsInJail()
    {
        return turnsInJail;
    }
    
    
    //modifiers
    public void setName(String n)
    {
        name = n;
    }
    
    public void setPosition(int p)
    {
        position = p;
    }
    
    public void setCashMoney(int c)
    {
        cashMoney = c;
    }
    
    public void setNetWorth(int n)
    {
        netWorth = n;
	if(n <= 0)
            isBankrupt = true;
    }
    
    public void changeJailStatus()
    {
        boolean past = inJail;
        inJail = !past;
    }
    
    public void addJailPass()
    {
        jailPasses++;
    }
    
    public void useJailPass()//might need check
    {
        jailPasses--;
    }
    
    public void setTurnsInJail(int t)
    {
        turnsInJail = t;
    }
    
    public void addProperty(Property prop, Player player)
    {
    	prop.setOwner(player.getName());
    	owned.add(prop);
    }
    
    public void removeProperty(Property toRemove){
    	if(toRemove == null){
    		System.out.println("toRemove is null");
    		return;
    	}
    	for(int i = 0; i < owned.size(); i++){
    		if(owned.get(i).getName() == toRemove.getName()){
    			System.out.println("removed");
    			owned.remove(i);
    		}
    	}
    }
    
}