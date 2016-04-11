/*
 * Board class needs
 * * array of position DONE
 * * deck class for chance DONE
 * * deck class for community chest DONE
 * * players turn
 * * passed go method
 * * purchase property
 * */
package backend;



public class Board 
{
    private Deck chance;
    private Deck communityChest;
	private Property[] props = new Property[28];
    private Position[] path = new Position[40]; // 40 positions, 0 - 39
	
    //private int playerPosition; //There will probably be a variable for this in the player class
    
    
    
    public Board()//TODO: number of players
    {
        int i;
		int j = 0;
		chance = new Deck("c");
        communityChest = new Deck("cc");
        
        chance.shuffle();
        communityChest.shuffle();
		
		for (i = 0; i < 28; i++)
		{
			props[i] = new Property(i);
		}
		
		for (i = 0; i < 40; i++) //assigns values to board spaces; currently using Nick's spreadsheet to determine where stuff is located.
		{
			if (i == 0)
			{
				path[i] = new Position("Go");
			}
			else if (i == 2 || i == 17 || i == 33)
			{
				path[i] = new Position("Community Chest");
			}
			else if (i == 7 || i == 22 || i == 36)
			{
				path[i] = new Position("Chance");
			}
			else if (i == 4)
			{
				path[i] = new Position("Panda Express");
			}
			else if ( i == 10)
			{
				path[i] = new Position("Academic Probation");
			}
			else if (i == 20)
			{
				path[i] = new Position("Scholarship Fund");
			}
			else if (i == 30)
			{
				path[i] = new Position("Go to Academic Probation");
			}
			else if (i == 38)
			{
				path[i] = new Position("Tuition Time");
			}
			else
			{
				path[i] = new Position(props[j].getName());
				j++;
			}
			
		}    
    }
   
   public String getPositionName(int pos)
   {
	   if (pos > 39 || pos < 0)
	   {
		   return null;
	   }
	   else
	   {
		return path[pos].getName();   
	   }
   }
   
   //my additions
   public Card drawCommunity(){
	   return communityChest.draw();
   }
   
   public Card drawChance(){
	   return chance.draw();
   }
}

