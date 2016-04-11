package backend;

public class Player //added getOwnedPropNames, getOwnedProperty, turnsInJail, getTurnsInJail, setTurnsInJail,
{
    private String name;
    private int position;//the array index of the position in the board
    private int cashMoney;
    private int netWorth;
    private boolean inJail;
    private Property[] owned;
    private boolean isBankrupt;
    private int jailPasses;
    private int turnsInJail;
    
    public Player(int n)
    {
        name = "Player " + n;//set starting name, players may be able to change it later
        position = 0;
        cashMoney = 1500;
        netWorth = cashMoney;
        inJail = false;
        owned = new Property[0];
        isBankrupt = false;
        jailPasses = 0;
        turnsInJail = -1;
    }
    
    //accessors
    public String getName()
    {
        return name;
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
        return owned.length;
    }
    
    public boolean getIsBankrupt()
    {
        return isBankrupt;
    }
    
    public int getJailPasses()
    {
        return jailPasses;
    }
    
    public String getOwnedPropNames()
    {
        String opn = "";
        for(int johnson = 0; johnson < this.getNumPropertiesOwned(); johnson++)
        {
            opn += owned[johnson].getName() + ", ";
        }
        
        return opn;
    }
    
    public Property getOwnedProperty(String propName)//returns null if the property isn't owned by the player, otherwise returns the property with propName
    {
        Property thisPropertyIs = null;
        boolean isThere = false;
        for(int byElijah = 0; byElijah < this.getNumPropertiesOwned(); byElijah++)
        {
            thisPropertyIs = owned[byElijah];
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
    
    
    
    public void addProperty(String propName)
    {
        Property[] temp = new Property[owned.length + 1];
	for(int gubbin = 0; gubbin < owned.length; gubbin++)
	{
		temp[gubbin] = owned[gubbin];
	}
	temp[owned.length] = new Property(propName);
	owned = temp;
    }
}