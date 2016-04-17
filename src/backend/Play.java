
import java.util.ArrayList;
import java.util.Random;

public class Play {

    private static int DOUBLESCOUNTER;
    private static int TAX;
    private static boolean ROLLEDDOUBLES;
    private static int PLAYERTURN;

    public static void main(String[] args) {
                
        int numberOfPlayers = 0;
        ArrayList<Player> players = new ArrayList<>();// Array list of players
        PLAYERTURN = 0;//Keeps track of who's turn it is 0 = player1
        
        numberOfPlayers = input; //Input for the number of players 1 - 6
        
        Board board = new Board(numberOfPlayers); // Initalize the board class
        players = getPlayers();
        
        int answer = 0, inJailAnswer = 0;
        
        while(players.size() > 1){ // while the is more than one player
            
            if(PLAYERTURN > numberOfPlayers)//If the players trun exceeds the last player set back to player1
                PLAYERTURN = 0;
            
            do {
                // check to see if bankrupt
                if(players.get(PLAYERTURN).getNetWorth() <= 0){
                    players.remove(PLAYERTURN);
                    PLAYERTURN--;
                    numberOfPlayers--;
                    break;
                }
                
                
                answer = input; //Input from user to choose what he/she wants to do
                inJailAnswer = input; // Input for if in jail, TO BE USED LATER
                
                switch(answer){ //switches between the different options the player has to choose from
                    case 1: //Player wishes to roll
                        if (players.get(PLAYERTURN).isInJail()) {
                            players.get(PLAYERTURN).setTurnsInJail(players.get(PLAYERTURN).getTurnsInJail() + 1);//Increases the number of turns in jail
                            switch(inJailAnswer){ // Switch between roll for doubles or pay $50 or use a GOJCF card if they have one else don't show this option
                                case 1: // If player wishes to roll for doubles
                                    Random first = new Random();
                            
                                    int die1 = first.nextInt(6) + 1;
                                    int die2 = first.nextInt(6) + 1;
                                    
                                    if(die1 == die2){ // If player did roll doubles
                                        DOUBLESCOUNTER++;
                                        ROLLEDDOUBLES = true;
                                        players.get(PLAYERTURN).changeJailStatus();//Changes status to false
                                        Move(die1+die2, players);// Updates the position
                                    } else if(players.get(PLAYERTURN).getTurnsInJail() == 3){//Turns in jail is 3 and did NOT roll doubles have to pay or use card to get out of jail
                                        inJailAnswer = imput;
                                        switch(inJailAnswer){
                                            case 1://Paying $50
                                                CostCheck(50, players);
                                                players.get(PLAYERTURN).changeJailStatus();
                                                Move(die1+die2, players);
                                                break;
                                            case 2://Using a card to get out of jail. SHOULD NOT SHOW IF THEY DON'T OWN A CARD
                                                if(players.get(PLAYERTURN).getJailPasses() > 0){//Has a card
                                                    players.get(PLAYERTURN).useJailPass();//Uses the card
                                                    players.get(PLAYERTURN).changeJailStatus();//Player is no longer in jail
                                                    Move(die1+die2, players);                                                    
                                                } else{
                                                    //NEED TO ADD A BACK BUTTON IN CASE THEY DON'T HAVE A CARD
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case 2:// If player wishes to pay $50
                                    CostCheck(50, players);
                                    players.get(PLAYERTURN).changeJailStatus();//Changes status to false
                                    Roll(players);//Allows the player to roll
                                    break;
                                case 3:// Check to see if the player has a get out of jail free card and player wishes to use it, if they don't own one this opition should not show up
                                    if(players.get(PLAYERTURN).getJailPasses() > 0){
                                        players.get(PLAYERTURN).useJailPass();
                                        players.get(PLAYERTURN).changeJailStatus();
                                        Roll(players);
                                    } else{
                                        //need to add a back button if they don't have a get out of jail free card
                                    }
                                    break;
                            }
                            
                        } else {
                            Roll(players);
                        }
                        break;
                        
                    case 2: //Player wishes to Build (a) house(s)
                        TODO; //Make sure they can only build on a monopoly that they own all of the color group
                        buyHouses(inputForNumberOfHouses, inputForWhichProperty);
                        ROLLEDDOUBLES = true;// so they can roll/accsses the main menu again
                        break;
                        
                    case 3: //Player wishes to Sell (a) house(s)
                        TODO;
                        sellHouses(inputForNumberOfHouses, inputForWhichPorperty);
                        ROLLEDDOUBLES = true;
                        break;
                        
                    case 4: //Player wishes to mortgage 
                        TODO;
                        mortgage(input);
                        ROLLEDDOUBLES = true;
                        break;
                        
                    case 5: //Player wishes to unmortgage
                        TODO;
                        unmortgage(input);
                        ROLLEDDOUBLES = true;
                        break;
                        
                    case 6: //Player wishes to trade with another player
                        TODO;
                        ROLLEDDOUBLES = true;
                        break;
                        
                    //case "end":  //Ends the Game if Wanted  
                        //break;
                }
                
            } while (ROLLEDDOUBLES);

            // End Turn
            DOUBLESCOUNTER = 0;
            ROLLEDDOUBLES = false;
            
            // check to see if bankrupt
            if (players.get(PLAYERTURN).getNetWorth() <= 0 || players.get(PLAYERTURN).getIsBankrupt()) {//If player is bankrupt remove them from the arraylist
                players.remove(PLAYERTURN);
                PLAYERTURN--;
                numberOfPlayers--;
            }
            
            //Updates Players Turn
            PLAYERTURN++;
        }

    }

    public static void Roll(ArrayList<Player> player) {
        Random first = new Random();

        int die1, die2;

        //TEMP VARIABLES
//        int position = 0, money = 1500, netWorth = 1500, input = 0, playerOfTurn = 0, rent = 0, propertyOwned = 0;
//        boolean inJail = false, mortgaged = false;
        //Delete and replace with class variables/methods

        die1 = first.nextInt(6) + 1; // generate a die 1-6
        die2 = first.nextInt(6) + 1; // generate a die 1-6

        if (die1 == die2) { // if doubles

            DOUBLESCOUNTER++; // increase the doubles counter
            ROLLEDDOUBLES = true;

            if (DOUBLESCOUNTER == 3) { // if three doubles in a row go to jail, end turn, and reset doubles counter

                DOUBLESCOUNTER = 0;
                ROLLEDDOUBLES = false;
                player.get(PLAYERTURN).setPosition(10);
                player.get(PLAYERTURN).changeJailStatus();
                return;
                //exit
            } 
        }
        
        Move(die1+die2, player); // moves the icon
        
    }
    
    public static void CostCheckToBuy(int property, ArrayList<Player> player) {//checks to see if player can pay the cost to buy the property

        //TEMP VARIABLES
//        int money = 1500, netWorth = 1500, toPlayerMoney = 0, toPlayerNetWorth = 0;
//        boolean bankrupt = false;
        int costToBuy = Properties.getPrice(property);

        if (player.get(PLAYERTURN).getCashMoney() >= costToBuy) {//able to pay the cost            
            player.get(PLAYERTURN).setCashMoney(player.get(PLAYERTURN).getCashMoney() - costToBuy);
            player.get(PLAYERTURN).setNetWorth(player.get(PLAYERTURN).getNetWorth() - costToBuy);
            Properties.updateOwner(player.get(PLAYERTURN).getPlayerID(), property);//Sets the owner of the property to the current player 
            TODO; // Addit to the array of porperties that the player owns
        } else if (player.get(PLAYERTURN).getCashMoney() < costToBuy) {//Not enough cash to pay for the cost
            if (player.get(PLAYERTURN).getNetWorth() <= costToBuy) {//Not enough value to the player to buy the property
                return;
            }                                
            else {//not enough cash but can mortgage to cover cost or sell houses
                switch(input){ // Maybe have a menu that includes the option to sell houses and mortgage                     
                    case 1:// Wishes to Mortgage Some properties
                        //menu to select a property to mortgage
                        mortgage(input);
                        break;
                    case 2:// Wishes to sell some houses. DO NOT SHOW IF NO HOUSES TO BE SOLD
                        //menu to select whoch properties actually have houses and whicch one the player wishes to sell
                        sellHouses(inputForNumberOfHouses, inputForWhichProperty);
                        break;
                        
                }
            }
        }
    }

    public static void CostCheckToPlayer(int cost, int property, ArrayList<Player> player) {//checks to see if player can pay the cost for rent

        //TEMP VARIABLES
//        int money = 1500, netWorth = 1500, toPlayerMoney = 0, toPlayerNetWorth = 0;
//        boolean bankrupt = false;
        int playerOwed = Properties.getOwner(property);

        if (player.get(PLAYERTURN).getCashMoney() >= cost) {//able to pay the cost            
            player.get(PLAYERTURN).setCashMoney(player.get(PLAYERTURN).getCashMoney() - cost);
            player.get(PLAYERTURN).setNetWorth(player.get(PLAYERTURN).getNetWorth() - cost);
            for(int i = 0; i < player.size(); i++){//Searches for the player who ownes the property
                if(player.get(i).getPlayerID() == playerOwed){
                    player.get(i).setCashMoney(player.get(i).getCashMoney() + cost);// Adds the rent cost to their money
                    player.get(i).setNetWorth(player.get(i).getNetWorth() + cost);// Adds the rent cost to their net worth
                    break;
                }
            }           
        } else if (player.get(PLAYERTURN).getCashMoney() < cost) {//Not enough cash to pay for the cost
            if (player.get(PLAYERTURN).getNetWorth() <= cost) {//Not enough value to the player to cover the cost
                player.get(PLAYERTURN).setIsBankrupt(true);
                for(int j = 0; j < player.size(); j++){
                    if (player.get(j).getPlayerID() == playerOwed) {
                        player.get(j).setCashMoney(player.get(j).getCashMoney() + player.get(PLAYERTURN).getCashMoney());// Adds all the money that the bankrupt player had to the player who is owed rent
                        player.get(j).setNetWorth(player.get(j).getNetWorth() + player.get(PLAYERTURN).getCashMoney());// Adds the money to their net worth
                        break;
                    }
                }
                TODO;//properties of bankrupt player go to the owner of the property landed on
                player.get(PLAYERTURN).setCashMoney(0);// Sets player's money to 0;
                TODO;//token removed from board                
            } else {//not enough cash but can mortgage to cover cost or sell houses
                switch(input){ // Maybe have a menu that includes the option to sell houses and mortgage                     
                    case 1:// Wishes to Mortgage Some properties
                        //menu to select a property to mortgage
                        mortgage(input);
                        break;
                    case 2:// Wishes to sell some houses. DO NOT SHOW IF NO HOUSES TO BE SOLD
                        //menu to select whoch properties actually have houses and whicch one the player wishes to sell
                        sellHouses(inputForNumberOfHouses, inputForWhichProperty);
                        break;
                        
                }
            }
        }
    }
    
    public static void CostCheck(int cost, ArrayList<Player> player) {//checks to see if player can pay the cost, For a tax not to a player

        //TEMP VARIABLES
//        int money = 1500, netWorth = 1500, toPlayerMoney = 0, toPlayerNetWorth = 0;
//        boolean bankrupt = false;

        if (player.get(PLAYERTURN).getCashMoney() >= cost) {//able to pay the cost               
            player.get(PLAYERTURN).setCashMoney(player.get(PLAYERTURN).getCashMoney() - cost);//Subtracts the cost from the players money
            player.get(PLAYERTURN).setNetWorth(player.get(PLAYERTURN).getNetWorth() - cost);//Subtracts the cost from the players net worth
            TAX += cost;//Adds amount to TAX

        } else if (player.get(PLAYERTURN).getCashMoney() < cost) {//Not enough cash to pay for the cost
            if (player.get(PLAYERTURN).getNetWorth() <= cost) {//Not enough value to the player to cover the cost
                player.get(PLAYERTURN).setIsBankrupt(true);
                TAX += player.get(PLAYERTURN).getCashMoney();//Add all of players cash to the TAX
                player.get(PLAYERTURN).setCashMoney(0);
                TODO;//properties go to bank and can be bought again from the board
                TODO;//token is removed from board

            } else {//not enough cash but can mortgage to cover cost or sell houses
                switch(input){ // Maybe have a menu that includes the option to sell houses and mortgage                     
                    case 1:// Wishes to Mortgage Some properties
                        //menu to select a property to mortgage
                        mortgage(input);
                        break;
                    case 2:// Wishes to sell some houses. DO NOT SHOW IF NO HOUSES TO BE SOLD
                        //menu to select whoch properties actually have houses and whicch one the player wishes to sell
                        sellHouses(inputForNumberOfHouses, inputForWhichProperty);
                        break;
                        
                }                
            }
        }
    }
    
    public static void Move(int roll, ArrayList<Player> player){
        //TEMP VARIABLES
//        int position = 0, money = 1500, netWorth = 1500, input = 0, playerOfTurn = 0, rent = 0, propertyOwned = 0;
//        boolean inJail = false, mortgaged = false;
        //Delete and replace with class variables/methods
         
        player.get(PLAYERTURN).setPosition(player.get(PLAYERTURN).getPosition() + roll);// Update the position

        if (player.get(PLAYERTURN).getPosition() >= 40) { // passing go
            player.get(PLAYERTURN).setPosition(player.get(PLAYERTURN).getPosition() - 40);
            player.get(PLAYERTURN).setCashMoney(player.get(PLAYERTURN).getCashMoney() + 200);
            player.get(PLAYERTURN).setNetWorth(player.get(PLAYERTURN).getNetWorth() + 200);            
        }

        if (player.get(PLAYERTURN).getPosition() == 4) { // on income tax

            if (input == 1) { // decided to pay 200

                CostCheck(200, player);
                
            } else if (input == 2) { // decided to pay 10% of net wealth

                CostCheck(player.get(PLAYERTURN).getNetWorth() / 10, player);		
            }
            //exit
        } else if (player.get(PLAYERTURN).getPosition() == 20) { // on Free Parking
            player.get(PLAYERTURN).setCashMoney(player.get(PLAYERTURN).getCashMoney() + 500 + TAX);// player gains 500 plus any tax collected
            player.get(PLAYERTURN).setNetWorth(player.get(PLAYERTURN).getNetWorth() + 500 + TAX);
            TAX = 0;
            //exit
        } else if (player.get(PLAYERTURN).getPosition() == 30) { // landed on "Go To Jail"
            player.get(PLAYERTURN).setPosition(10);//Put player in jail
            player.get(PLAYERTURN).changeJailStatus();//Change status to In Jail
            //exit
        } else if (player.get(PLAYERTURN).getPosition() == 10) { // on "Just Visiting"
            return;
            //exit
        } else if (player.get(PLAYERTURN).getPosition() == 38) { // on "Luxury Tax"

            CostCheck(75, player);					
            //exit
        } else if (player.get(PLAYERTURN).getPosition() == 2 || player.get(PLAYERTURN).getPosition() == 18 || player.get(PLAYERTURN).getPosition() == 33) { // landed on a "Community Chest" space
            TODO;
            /*
             * draw a card 
             * does the effects on the card 
             * puts card onbottom
             * 
             * if number of cards played > max cards 
             suffle cards
             */
            //exit
        } else if (player.get(PLAYERTURN).getPosition() == 8 || player.get(PLAYERTURN).getPosition() == 22 || player.get(PLAYERTURN).getPosition() == 36) { // landed on a "Chance" space
            TODO;
            /*
             * draw a card 
             * does the effects on the card 
             * puts card on bottom
             * 
             * if number of cards played > max cards 
             * 		suffel cards
             */
            //exit               
        } else { // landed on a property
            if (Properties.getOwner(player.get(PLAYERTURN).getPosition()) == 0) { // property is unowned

                if (input == 1) { // Does NOT want to buy the property                    
                    return;
                    //exit
                } else if (input == 2) { // Wants to buy the property
                    CostCheckToBuy(player.get(PLAYERTURN).getPosition(), player);                    
                }
            } else if (Properties.getOwner(player.get(PLAYERTURN).getPosition()) == player.get(PLAYERTURN).getPlayerID()) { // property is owned by the current player, no rent
                return;
                //exit
            } else // property is owned by another player, rent is due
                if (mortgaged) {// if property is mortgaged no rent is due
                    return;
                    //exit
                } else// property is unmortgaged
                {
                    CostCheckToPlayer(Properties.getRent(player.get(PLAYERTURN).getPosition()), player.get(PLAYERTURN).getPosition(), player);
               }
            //exit
        }
    }
 
//    private static void Trade(){
//        TODO
//        //player class should have this covered
//    }

}
