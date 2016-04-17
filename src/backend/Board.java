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
import java.util.ArrayList;
import java.util.Random;

public class Board 
{
    private Deck chance;
    private Deck communityChest;
    private boolean[] isProperty = new boolean[40];
    private int[] propertyIndex = new int[40];
	private Property[] props = new Property[28];
    private Position[] path = new Position[40]; // 40 positions, 0 - 39
	private ArrayList<Player> players;
	
    //private int playerPosition; //There will probably be a variable for this in the player class
    
    
    
    public Board(int numberOfPlayers)//TODO: number of players
    {
        players = new ArrayList<Player>();
		 for(int i=0; i< numberOfPlayers; i++){
            players.add(new Player(i + 1));//Adds a player to the array list of players
        }
		Random next = new Random();
        int player1Order = next.nextInt(numberOfPlayers);// 0 - number of players. Player 1 plays the number + 1
        ArrayList<Player> firstHalf = new ArrayList<>();
        if (player1Order != 0) {// If player 1 does NOT play first
            for (int j = 0; j < numberOfPlayers - player1Order; j++) {
                firstHalf.add(players.remove(j));// Adds the first players to first half until the player who plays first is at playersIndex equals 0
            }
            for (int k = 0; k < firstHalf.size(); k++) {
                players.add(firstHalf.get(k));// Adds the players back to the arraylist
            }
        }
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
			isProperty[i] = false;
			propertyIndex[i] = -1;
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
				propertyIndex[i] = j;
				j++;
				isProperty[i] = true;
			}
			
		}    
    }
	
	public String getPositionName(int position){
		return path[position].getName();
	}
	
	public Card drawCommunity(){
		return communityChest.draw();
		
	}
	
	public boolean isProperty(int position){
		return isProperty[position];
	}
	
	public Property getProperty(int position){
		if(isProperty[position]){
			return props[propertyIndex[position]];
		}
		else{
			return null;
		}
	}
	
	public Card drawChance(){
		return chance.draw();
	}
}

