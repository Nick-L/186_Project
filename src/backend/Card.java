package backend;

import java.util.ArrayList;

public class Card 
{
    private int effect;
    private String flavorText;
    
    public Card()
    {
        int e = (int)(Math.random() * 31) + 1; //generates a random number one through 31.
        effect = e;
    }
    
    public Card(int caseNum)
    {
    	effect = caseNum;
    }
    
    public int getEffect()
    {
        return effect;
    }
    
    public String getFlavorText()
    {
        return flavorText;
    }
    
    public void play(Player tiddlywinks, int e, ArrayList<Player> everyPlayer)
    {
        switch(e)
        {
        //COMMUNITY CHEST______________COMMUNITY CHEST
            case 0: //"Advance to go, collect $200." Update position to 0. Add 200 to money.
                flavorText = "Advance to go, collect $200";
                tiddlywinks.setPosition(0);
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 200);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 200);
                break;
            case 1: //"You have recieved a scholarship, collect $200!" Add 200 to money
                flavorText = "You recieved a scholarship, collect $200!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 200);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 200);
                break;
            case 2: //"You sprained your ankle, bribe the nurse for $50." Remove 50 from money.
                flavorText = "You sprained your ankle, bribe the nurse for $50 so she doesn't tell the doctor you don't have insurance!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - 50);
                break;
            case 3: //"From selling your hopes and dreams, you get $50!" Add 50 to money
                flavorText = "From selling your hopes and dreams, you get a whopping $50!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 50);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 50);
                break;
            case 4: //Get out of jail free card. Add 1 to number of G.O.O.J.F. cards.
                flavorText = "Get a free get out of Academic Probation card!";
                tiddlywinks.addJailPass();
                break;
            case 5: //"You failed a class, go to Academic Probation. You disgust me." Update position to 10. Update isInJail to True.
                flavorText = "You failed a class, go to Academic Probation. You disgust me.";
                tiddlywinks.setPosition(10);
                tiddlywinks.changeJailStatus();
                break;
            case 6: //"You succesfully guilt tripped your friends into paying for your frivilous lifestyle of eating flavor-blasted goldfish and drinking Mountain Dew. Everyone pays you $50." Add 50 x numberOfPlayers - 1 to money. Reduce every other player's money by 50.
                flavorText = "You succesfully guilt tripped your friends into paying for your frivilous lifestyle of eating flavor-blasted goldfish and drinking Mountain Dew. Every other player pays you $50!";
                int looper = everyPlayer.size() - 1;
                for (int i = 0; i < looper; i++)
                {
                    if (!everyPlayer.get(i).equals(tiddlywinks))
                    {
                        everyPlayer.get(i).setCashMoney(everyPlayer.get(i).getCashMoney() - 50);
                        everyPlayer.get(i).setNetWorth(everyPlayer.get(i).getNetWorth() - 50);
                        tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 50);
                        tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 50);
                    }
                }
                break;
            case 7: //"Your parents remembered that you exist. You recieve $100!" Add 100 to money
                flavorText = "Your parents remembered that you exist. You recieve $100!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 100);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 100);
                break;
            case 8: //"You won a raffle. Collect $20!" Add 20 to money.
                flavorText = "You won a raffle. Collect $20!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 20);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 20);
                break;
            case 9: //"It's your birthday. You know what that means. Collect $10 in guilt money from every other player." Add 10 x numberOfPlayers - 1 to money. Reduce every other player's money by 10.
                flavorText = "It's your birthday, and you know what that means. Collect $10 in guilt money from every other player!";
                int looper1 = everyPlayer.size() - 1;
                for (int i = 0; i < looper1; i++)
                {
                    if (!everyPlayer.get(i).equals(tiddlywinks))
                    {
                        everyPlayer.get(i).setCashMoney(everyPlayer.get(i).getCashMoney() - 10);
                        everyPlayer.get(i).setNetWorth(everyPlayer.get(i).getNetWorth() - 10);
                        tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 10);
                        tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 10);
                    }
                }
                break;
            case 10: //"Your previously unknown yet rediculously wealthy distance relative dies. Recieve $100!" Add 100 to money.
                flavorText = "Your previously unknown yet somehow rediculously wealthy distant relative passes away. Collect $100!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 100);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 100);
                break;
            case 11: //"You sprained ankle and failed to bribe the nurse. Pay $100 in hospital fees." Remove 100 from money.
                flavorText = "You sprained your ankle and have no insurance. Pay $100 in hospital fees.";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - 100);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() - 100);
                break;
            case 12: //"You remembered to pay your U-Bill on time. Pay $150." Remove 150 from money
                flavorText = "You actually remembered to pay your U-Bill on time. Pay $150!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - 150);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() - 150);
                break;
            case 13: //"You found a rare yet beautiful 25 dollar bill on the street. Recieve $25!" Add 25 to money
                flavorText = "You found a rare yet beauiful 25 dollar bill on the street. Recieve $25!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 25);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 25);
                break;
            case 14: //"You forgot to pay your U-Bill. Pay $40 for each house, and $115 for each hotel you own. Cry and realize that you just lost this game." Remove 40 x numberOfHouses from money. Remove 115 x numberOfHotels from money.
                flavorText = "You forgot to pay your U-Bill. Pay $40 for each house, and $115 for each hotel you own. Cry and realize that you just lost this game!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - tiddlywinks.getHouses() * 40 - tiddlywinks.getHotels() * 115);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() - tiddlywinks.getHouses() * 40 - tiddlywinks.getHotels() * 115);
                break;
            case 15: //"A kind elderly gentleman compliments your appearance and hands you money. Recieve $10!" Add 10 to money.
                flavorText = "A kind elderly gentleman on the street compliments your appearance and hands you money. Recieve a crumpled and sweaty $10!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 10);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 10);
                break;
            case 16: //"You extorted your distant relatives for cash. Receive $100!" Add 100 to money.
                flavorText = "You extorted your distant relatives for cash. Receive $100!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 100);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 100);
                break;
            //CHANCE_________________CHANCE
            case 17: //Advance to Go. Collect $200. Update position to 0. Add 200.
                flavorText = "Advance to Go. Collect $200!";
                tiddlywinks.setPosition(0);
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 200);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 200);
                break;
            case 18: //Advance to equivalent of Illinois Avenue. If you pass go, collect $200. Update position to 24. If currentPosition > 24, add 200 to money.
                flavorText = "Advance to Martin Hall. If you pass Go, collect $200!";
                if (tiddlywinks.getPosition() > 24)
                {
                    tiddlywinks.setPosition(24);
                    tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 200);
                    tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 200);
                }
                else
                {
                    tiddlywinks.setPosition(24);
                }
                break;
            case 19: //Advance to equivalent of St. Charles Place. If you pass go, collect $200. Update position to 11. If currentPosition > 11, add 200 to money.
                flavorText = "Advance to Birch Hall. If you pass Go, collect $200!";
                if (tiddlywinks.getPosition() > 11)
                {
                    tiddlywinks.setPosition(11);
                    tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 200);
                    tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 200);
                }
                else
                {
                    tiddlywinks.setPosition(11);
                }
                break;
            case 20: //Advance to nearest railroad. If owned, pay twice the fee.
                flavorText = "Advance to nearest Bus Line. If it's already owned, pay twice the fee!";
                        //if position > 35 or < 5, update position to 5. If position > 5 or < 15, update position to 15. 
                        //if position > 15 or < 25, update position to 25. if position > 25 or < 25, update position to 35.
                        //if positionMovedTo is owned, remove rentOfProperty x 2 from money, and add rentOfProperty x 2 to money of
                        //player who owns the property.
                if (tiddlywinks.getPosition() > 35 || tiddlywinks.getPosition() < 5)
                {
                    if (tiddlywinks.getPosition() > 35)
                    {
                        tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 200);
                        tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 200);
                    }
                    tiddlywinks.setPosition(5);
                }
                else if (tiddlywinks.getPosition() > 5 && tiddlywinks.getPosition() < 15)
                {
                    tiddlywinks.setPosition(15);
                }
                else if (tiddlywinks.getPosition() > 15 && tiddlywinks.getPosition() < 25)
                {
                    tiddlywinks.setPosition(25);
                }
                else if (tiddlywinks.getPosition() > 25 && tiddlywinks.getPosition() < 35)
                {
                    tiddlywinks.setPosition(35);
                }
                break;
            case 21: //"You took on a part-time job to pay for the rediculously high tuition prices. Recieve $50!" Add 50 to money.
                flavorText = "You took on a part-time job to help pay for your crippling level of student debt. Receive your pitiful wages of $50!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 50);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 50);
                break;
            case 22: //Get out of jail free card. Add 1 to number of G.O.O.J.F. cards.
                flavorText = "Get a free get out of Academic Probation card!";
                tiddlywinks.addJailPass();
                break;
            case 23: //"You forgot to eat yesterday. Go back three spaces." Update position by -3
                flavorText = "You forgot to eat yesterday. Go back three spaces!";
                tiddlywinks.setPosition(tiddlywinks.getPosition() - 3);
                break;
            case 24: //"Go to academic probation. Go directly to academic probation. You filthy animal." Update position to 10. Update condition isInJail to true.
                flavorText = "Go to academic probation. Go directly to academic probation. You filthy animal.";
                tiddlywinks.setPosition(10);
                tiddlywinks.changeJailStatus();
                break;
            case 25: //"Time to pay for school! Pay $25 for each house, and $100 for each hotel that you own." Remove numberOfHouses x 25 from money. Remove numberOfHotels x 100 from money.
                flavorText = "Reality check! It's time to pay for school! Pay $25 for each house, and $100 for each hotel that you own!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - tiddlywinks.getHouses() * 25 - tiddlywinks.getHotels() * 100);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() - tiddlywinks.getHouses() * 25 - tiddlywinks.getHotels() * 100);
                break;
            case 26: //"You underpaid on your U-Bill. Pay $15." Remove 15 from money.
                flavorText = "You underpaid on your U-Bill. Pay $15";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - 15);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() - 15);
                break;
            case 27: //Go to equivalent of Reading Railroad. If you pass Go, collect $200. Update position to 5. If currentPosition > 5, add 200 to money.
                flavorText = "Go to 6 Brown. If you pass Go, collect $200!";
                if (tiddlywinks.getPosition() > 5)
                {
                    tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 200);
                    tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 200);
                }
                tiddlywinks.setPosition(5);
                break;
            case 28: //Go to equivalent of Boardwalk. Update position to 39.
                flavorText = "Go to Hilton Colliseum!";
                tiddlywinks.setPosition(39);
                break;
            case 29: //"You have been elected student body president. Time to bribe all those school officials! Pay each player $50." Remove 50 x numberOfPlayers - 1 from money. Add 50 to every other player's money.
                flavorText = "You have been elected student body president. Time to bribe all those school officials! Pay each player $50!";
                int looper11 = everyPlayer.size() - 1;
                for (int i = 0; i < looper11; i++)
                {
                    if (!everyPlayer.get(i).equals(tiddlywinks))
                    {
                        everyPlayer.get(i).setCashMoney(everyPlayer.get(i).getCashMoney() + 50);
                        everyPlayer.get(i).setNetWorth(everyPlayer.get(i).getNetWorth() + 50);
                        tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - 50);
                        tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() - 50);
                    }
                }
                break;
            case 30: //"You somehow managed to make money on the stock market. Collect $150." Add 150 to money.
                flavorText = "From a sale of cherished childhood items, you made $150!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 150);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 150);
                break;
            case 31: //"You won a Ruth Bader Ginsburg look-alike contest. Collect $100!" Add 100 to money.
                flavorText = "You won a Ruth Bader Ginsburg look-alike contest. Collect $100!";
                tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + 100);
                tiddlywinks.setNetWorth(tiddlywinks.getNetWorth() + 100);
                break;
            
        }
    }
    
    public boolean equals(Card obj)
    {
        if (this.getEffect() == obj.getEffect())
        {
            return true;
        }
        else
            return false;
    }
}