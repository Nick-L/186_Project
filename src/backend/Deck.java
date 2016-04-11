package backend;

import java.lang.Math;

public class Deck 
{
    private Card[] stack = new Card[16];
    
    public Deck (String type)
    {
        if(type.equals("c"))
        {
            for(int i = 0; i < stack.length; i++)
                stack[i] = new Card(i);
        }
        else if(type.equals("cc"))
        {
            for(int i = 0; i < stack.length; i++)
                stack[i] = new Card(i+16);
        }
    }
    
    public void shuffle()
    {
        Card[] temp = new Card[16];
        boolean notFull;
        
        for(int gubbin = 0; gubbin < temp.length; gubbin++)
        {
            notFull = temp[gubbin]==null;
            while(notFull)
            {
                int rnum = (int)(Math.random()*16);
                temp[gubbin] = stack[rnum];
                stack[rnum] = null;
                notFull = temp[gubbin]==null;
            }
        }
        stack = temp;
    }
    
    public void reorganize()
    {
        for(int gubbin = 1; gubbin < stack.length; gubbin++)
        {
            if(stack[gubbin-1] == null)
            {
                stack[gubbin-1] = stack[gubbin];
            }
            
        }
    }
    
    public Card draw()
    {
        Card drawnCard = stack[0];
        stack[0] = null;
        
        this.reorganize();
        
        return drawnCard;
    }
    
    public void insert(Card c)
    {
        //TODO: might want to check that the deck isn't already full.
        stack[stack.length - 1] = c;
        this.reorganize();
    }
    
    //TODO: might need accessors
}
