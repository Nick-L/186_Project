package backend;

import java.util.Random;

public class Play {

    private static int DOUBLESCOUNTER;
    private static int TAX;
    private static boolean ROLLEDDOUBLES;

    public static void main(String[] args) {
                
        int numberOfPlayers = 0;
        
        //numberOfPlayers = input; //Input for the number of players
        for(int i=0; i<=numberOfPlayers; i++){
            
        }
        
        int answer, inJailAnswer;
        
        while(numberOfPlayers > 1){ // while the is more than one player
       
            do {
                ROLLEDDOUBLES = false;
                // check to see if bankrupt
                if(playersTurnNetWorth <= 0){
                    break;
                }
                
                
                answer = 0; //Input from user to choose what he/she wants to do
                inJailAnswer = 0; // Input for if in jail, TO BE USED LATER
                
                switch(answer){ //switches between the different options the player has to choose from
                    case 1: //Player wishes to roll
                        if (playerIsInJail) {
                            switch(inJailAnswer){ // Switch between roll for doubles or pay $50 or use a GOJCF card if they have one else don't show this option
                                case 1: // If player wishes to roll for doubles
                                    Random first = new Random();
                            
                                    int die1 = first.nextInt(6) + 1;
                                    int die2 = first.nextInt(6) + 1;
                                    
                                    if(die1 == die2){ // If player did roll doubles
                                        DOUBLESCOUNTER++;
                                        ROLLEDDOUBLES = true;
                                        playerIsInJail = false;
                                        Move(die1+die2);// Updates the position
                                    }
                                    break;
                                case 2:// If player wishes to pay $50
                                    CostCheck(50, false);
                                    playerIsInJail = false;
                                    Roll();
                                    break;
                                case 3:// Check to see if the player has a get out of jail free card and player wishes to use it, if they don't own one this opition should not show up
                                    getOutOfJailFreeCards--;
                                    Roll();
                                    break;
                            }
                            
                        } else {
                            Roll();
                        }
                        break;
                        
                    case 2: //Player wishes to Build (a) house(s)
                        ROLLEDDOUBLES = true;
                        break;
                        
                    case 3: //Player wishes to Sell (a) house(s)
                        ROLLEDDOUBLES = true;
                        break;
                        
                    case 4: //Player wishes to mortgage 
                        ROLLEDDOUBLES = true;
                        break;
                        
                    case 5: //Player wishes to unmortgage
                        ROLLEDDOUBLES = true;
                        break;
                        
                    case 6: //Player wishes to trade with another player
                        ROLLEDDOUBLES = true;
                        break;
                        
                    //case "end":  //Ends the Game if Wanted  
                        //break;
                }
                
            } while (ROLLEDDOUBLES);

            // End Turn
            DOUBLESCOUNTER = 0;
            ROLLEDDOUBLES = false;
            
            //Updates Players Turn
            if(playersTurn == numberOfPlayers){ //reached the last player
                playerTurn = 1;
            }
            else{
                playersTurn++;
            }
        }

    }

    public static void Roll() {
        Random first = new Random();

        int die1, die2;

        //TEMP VARIABLES
        int position = 0, money = 1500, netWorth = 1500, input = 0, playerOfTurn = 0, rent = 0, propertyOwned = 0;
        boolean inJail = false, mortgaged = false;
        //Delete and replace with class variables/methods

        die1 = first.nextInt(6) + 1; // generate a die 1-6
        die2 = first.nextInt(6) + 1; // generate a die 1-6

        if (die1 == die2) { // if doubles

            DOUBLESCOUNTER++; // increase the doubles counter
            ROLLEDDOUBLES = true;

            if (DOUBLESCOUNTER == 3) { // if three doubles in a row go to jail, end turn, and reset doubles counter

                DOUBLESCOUNTER = 0;
                ROLLEDDOUBLES = false;
                position = 10;
                inJail = true;
                return;
                //exit
            } 
        }
        
        Move(die1+die2); // moves the icon
        
    }

    public static void CostCheck(int cost, boolean toPlayer) {//checks to see if player can pay the cost

        //TEMP VARIABLES
        int money = 1500, netWorth = 1500, toPlayerMoney = 0, toPlayerNetWorth = 0;
        boolean bankrupt = false;

        if (money >= cost) {//able to pay the cost
            if (toPlayer == false) { //for tax not to a player
                money -= cost;
                netWorth -= cost;
                TAX += cost;
            } else { //to a player
                money -= cost;
                netWorth -= cost;
                toPlayerMoney += cost; //rent to the owner of property
                toPlayerNetWorth += cost;
            }
        } else if (money < cost) {//Not enough cash to pay for the cost
            if (netWorth <= cost) {//Not enough value to the player to cover the cost
                bankrupt = true;
                if (toPlayer == false) {//bankrupt due to tax
                    TAX += money;
                    money = 0;
                    //properties go to bank and can be bought again from the board
                    //token is removed from board
                } else { //bankrupt due to rent
                    toPlayerMoney = money; //bankrupt player's money goes to owner of property
                    //properties of bankrupt player go to the owner of the property landed on
                    money = 0;
                    //token removed from board
                }

            } else {//not enough cash but can mortgage to cover cost
                //***********************************************
                //Needs to mortgage to cover cost
                //***********************************************
                Mortgage();
            }
        }
    }
    
    public static void Move(int roll){
        //TEMP VARIABLES
        int position = 0, money = 1500, netWorth = 1500, input = 0, playerOfTurn = 0, rent = 0, propertyOwned = 0;
        boolean inJail = false, mortgaged = false;
        //Delete and replace with class variables/methods
         
        position += roll; // Update the position

        if (position >= 40) { // passing go

            position -= 40;
            money += 200;
            netWorth += 200;
        }

        if (position == 4) { // on income tax

            if (input == 1) { // decided to pay 200

                CostCheck(200, false);
//						money -= 200;
//						TAX += 200;
//						netWorth -= 200;
            } else if (input == 2) { // decided to pay 10% of net wealth

                CostCheck(netWorth / 10, false);
//						money -= netWorth / 10;
//						TAX += netWorth / 10;
//						netWorth -= netWorth / 10;
            }
            //exit
        } else if (position == 20) { // on Free Parking

            money += 500 + TAX; // player gains 500 plus any tax collected
            netWorth += 500 + TAX;
            TAX = 0;
            //exit
        } else if (position == 30) { // landed on "Go To Jail"

            position = 10;
            inJail = true;
            //exit
        } else if (position == 10) { // on "Just Visiting"

            //exit
        } else if (position == 38) { // on "Luxury Tax"

            CostCheck(75, false);
//					money -= 75;
//					netWorth -= 75;
//					TAX += 75;
            //exit
        } else if (position == 2 || position == 18 || position == 33) { // landed on a "Community Chest" space

            /*
             * draw a card 
             * does the effects on the card 
             * puts card onbottom
             * 
             * if number of cards played > max cards 
             suffle cards
             */
            //exit
        } else if (position == 8 || position == 22 || position == 36) { // landed on a "Chance" space

            /*
             * draw a card 
             * does the effects on the card 
             * puts card on bottom
             * 
             * if number of cards played > max cards 
             * 		suffel cards
             */
            //exit
//                } else if(position == 5 || position == 15 || position == 25 || position == 35){ // landed on a railroad
//                    
//                } else if(position == 12 || position == 28){ // landed on a utility
//                    
        } else // landed on a property
        if (propertyOwned == 0) { // property is unowned

            if (input == 1) { // Does NOT want to buy the property
                //*************************************
                return;
                //exit???
            } else if (input == 2) { // Wants to buy the property
                        /*
                 * reduse money by buying price 
                 * networth -= buying price 
                 * add to properties of player 
                 * update propertyOwned int value to player i.e. 1 for player1
                 */
            }
        } else if (propertyOwned == playerOfTurn) { // property is owned by the current player, no rent
            //exit
        } else if (propertyOwned != playerOfTurn) { // property is owned by another player, rent is due
            if (mortgaged) {// if property is mortgaged no rent is due
                //exit
            } else// property is unmortgaged
            {
                CostCheck(rent, true);
            }
//						money -= rent;
//						netWorth -= rent;
//						playerOfPorperty += rent;// player who owns property, money += rent
//						playerOfPropertyNetWorht += rent;// player who owns property, netWorth += rent
        } //exit
    }
 
    private static void Trade(){
        //player class should have this covered
    }

}
