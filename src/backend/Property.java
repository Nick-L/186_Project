package backend;

//TODO: add mortgage method, addHouse()
public class Property //added accessors for hotels and houses, Zach's overloaded constructor, isMortgaged,
{
    //instance data
    private String name;
    private String type;//hall, bus, utility
    private int purchasePrice;
    private int cRent;//current rent
    private int bRent;//base rent
    private int rent1;//rent w/ one house
    private int rent2;//rent w/ two houses
    private int rent3;//rent w/ 3 houses
    private int rent4;// rent w/ 4 houses
    private int hRent;// rent w/ hotel or high rent in case of owning both utilities
    private int houseCost;//cost of house/hotel
    private int mortgagePrice;
    private String owner;//owner name
    private int numHouses;
    private boolean hasHotel;
    private int hood;//the color (for monopolies)
    private boolean isMortgaged;
    
    
    //constructors
    
    
    public Property(String n)
    {
        name = n;
        owner = "None";
        numHouses = 0;
        hasHotel = false;
        isMortgaged = false;
        
        if(name.equals("Friley Hall"))//1
        {
            type = "hall";
            purchasePrice = 60;
            bRent = 2;
            rent1 = 10;
            rent2 = 30;
            rent3 = 90;
            rent4 = 160;
            hRent = 250;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 30;
            hood = 1;
        }
        else if(name.equals("Helser Hall"))//2
        {
            type = "hall";
            purchasePrice = 60;
            bRent = 4;
            rent1 = 20;
            rent2 = 60;
            rent3 = 180;
            rent4 = 320;
            hRent = 450;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 30;
            hood = 1;
        }
        else if(name.equals("Cyride 6 Brown"))//3
        {
            type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(name.equals("Welch Hall"))//4
        {
            type = "hall";
            purchasePrice = 100;
            bRent = 6;
            rent1 = 30;
            rent2 = 90;
            rent3 = 270;
            rent4 = 400;
            hRent = 550;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 50;
            hood = 2;
        }
        else if(name.equals("Robert Hall"))//5
        {
            type = "hall";
            purchasePrice = 100;
            bRent = 6;
            rent1 = 30;
            rent2 = 90;
            rent3 = 270;
            rent4 = 400;
            hRent = 550;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 50;
            hood = 2;
        }
        else if(name.equals("Lyon Hall"))//6
        {
            type = "hall";
            purchasePrice = 120;
            bRent = 8;
            rent1 = 40;
            rent2 = 100;
            rent3 = 300;
            rent4 = 450;
            hRent = 600;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 60;
            hood = 2;
        }
        else if(name.equals("Freeman Hall"))//7
        {
            type = "hall";
            purchasePrice = 140;
            bRent = 10;
            rent1 = 50;
            rent2 = 150;
            rent3 = 450;
            rent4 = 625;
            hRent = 750;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 70;
            hood = 3;
        }
        else if(name.equals("Tutoring"))//8
        {
            type = "utility";
            purchasePrice = 150;
            bRent = 0;//should be somthing
            rent1 = -1;
            rent2 = -1;
            rent3 = -1;
            rent4 = -1;
            hRent = 0;//should be something
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 75;
            hood = 22;
        }
        else if(name.equals("Barton Hall"))//9
        {
            type = "hall";
            purchasePrice = 140;
            bRent = 10;
            rent1 = 50;
            rent2 = 150;
            rent3 = 450;
            rent4 = 625;
            hRent = 750;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 70;
            hood = 3;
        }
        else if(name.equals("Birch Hall"))//10
        {
            type = "hall";
            purchasePrice = 160;
            bRent = 12;
            rent1 = 60;
            rent2 = 180;
            rent3 = 500;
            rent4 = 700;
            hRent = 900;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 80;
            hood = 3;
        }
        else if(name.equals("Cyride 23 Orange"))//11
        {
            type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(name.equals("Larch Hall"))//12
        {
            type = "hall";
            purchasePrice = 180;
            bRent = 14;
            rent1 = 70;
            rent2 = 200;
            rent3 = 500;
            rent4 = 700;
            hRent = 900;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 90;
            hood = 4;
        }
        else if(name.equals("Willow"))//13
        {
            type = "hall";
            purchasePrice = 180;
            bRent = 14;
            rent1 = 70;
            rent2 = 200;
            rent3 = 500;
            rent4 = 700;
            hRent = 950;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 90;
            hood = 4;
        }
        else if(name.equals("Maple Hall"))//14
        {
            type = "hall";
            purchasePrice = 200;
            bRent = 16;
            rent1 = 80;
            rent2 = 220;
            rent3 = 600;
            rent4 = 800;
            hRent = 1000;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 100;
            hood = 4;
        }
        else if(name.equals("Buchanan Hall"))//15
        {
            type = "hall";
            purchasePrice = 220;
            bRent = 18;
            rent1 = 90;
            rent2 = 250;
            rent3 = 700;
            rent4 = 875;
            hRent = 1050;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 110;
            hood = 5;
        }
        else if(name.equals("Eaton Hall"))//16
        {
            type = "hall";
            purchasePrice = 220;
            bRent = 18;
            rent1 = 90;
            rent2 = 250;
            rent3 = 700;
            rent4 = 875;
            hRent = 1050;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 110;
            hood = 5;
        }
        else if(name.equals("Martin Hall"))//17
        {
            type = "hall";
            purchasePrice = 240;
            bRent = 20;
            rent1 = 100;
            rent2 = 300;
            rent3 = 750;
            rent4 = 925;
            hRent = 1100;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 120;
            hood = 5;
        }
        else if(name.equals("Cyride 1 Red"))//18
        {
            type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(name.equals("ISU West Apartments"))//19
        {
            type = "hall";
            purchasePrice = 260;
            bRent = 22;
            rent1 = 110;
            rent2 = 330;
            rent3 = 800;
            rent4 = 975;
            hRent = 1150;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 130;
            hood = 6;
        }
        else if(name.equals("Frederickson Court"))//20
        {
            type = "hall";
            purchasePrice = 260;
            bRent = 22;
            rent1 = 110;
            rent2 = 330;
            rent3 = 800;
            rent4 = 975;
            hRent = 1150;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 130;
            hood = 6;
        }
        else if(name.equals("Laundry"))//21
        {
            type = "utility";
            purchasePrice = 150;
            bRent = 0;//should be somthing
            rent1 = -1;
            rent2 = -1;
            rent3 = -1;
            rent4 = -1;
            hRent = 0;//should be something
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 75;
            hood = 22;
        }
        else if(name.equals("Legacy Apartments"))//22
        {
            type = "hall";
            purchasePrice = 280;
            bRent = 24;
            rent1 = 120;
            rent2 = 360;
            rent3 = 850;
            rent4 = 1025;
            hRent = 1200;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 140;
            hood = 6;
        }
        else if(name.equals("Parks Library"))//23
        {
            type = "hall";
            purchasePrice = 300;
            bRent = 26;
            rent1 = 130;
            rent2 = 390;
            rent3 = 900;
            rent4 = 1100;
            hRent = 1275;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 150;
            hood = 7;
        }
        else if(name.equals("Bearshear Hall"))//24
        {
            purchasePrice = 300;
            bRent = 26;
            rent1 = 130;
            rent2 = 390;
            rent3 = 900;
            rent4 = 1100;
            hRent = 1275;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 150;
            hood = 7;
        }
        else if(name.equals("Memorial Union"))//25
        {
            type = "hall";
            purchasePrice = 320;
            bRent = 28;
            rent1 = 150;
            rent2 = 450;
            rent3 = 1000;
            rent4 = 1200;
            hRent = 1400;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 160;
            hood = 7;
        }
        else if(name.equals("Cyride 3 Blue"))//26
        {
            type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(name.equals("Jack Trice Stadium"))//27
        {
            type = "hall";
            purchasePrice = 350;
            bRent = 35;
            rent1 = 175;
            rent2 = 500;
            rent3 = 1100;
            rent4 = 1300;
            hRent = 1500;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 175;
            hood = 8;
        }
        else if(name.equals("Hilton Coliseum"))//28
        {
            type = "hall";
            purchasePrice = 400;
            bRent = 50;
            rent1 = 200;
            rent2 = 600;
            rent3 = 1400;
            rent4 = 1700;
            hRent = 2000;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 200;
            hood = 8;
        }
        
        
        
    }
    
    public Property(int propNum)//overloaded constructor
    {
        owner = "None";
        numHouses = 0;
        hasHotel = false;
        isMortgaged = false;
        
        if(propNum == 0)//1
        {
            name = "Friley Hall";
			type = "hall";
            purchasePrice = 60;
            bRent = 2;
            rent1 = 10;
            rent2 = 30;
            rent3 = 90;
            rent4 = 160;
            hRent = 250;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 30;
            hood = 1;
        }
        else if(propNum == 1)//2
        {
            name = "Helser Hall";
			type = "hall";
            purchasePrice = 60;
            bRent = 4;
            rent1 = 20;
            rent2 = 60;
            rent3 = 180;
            rent4 = 320;
            hRent = 450;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 30;
            hood = 1;
        }
        else if(propNum == 2)//3
        {
            name = "Cyride 6 Brown";
			type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(propNum == 3)//4
        {
            name = "Welch Hall";
			type = "hall";
            purchasePrice = 100;
            bRent = 6;
            rent1 = 30;
            rent2 = 90;
            rent3 = 270;
            rent4 = 400;
            hRent = 550;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 50;
            hood = 2;
        }
        else if(propNum == 4)//5
        {
            name = "Robert Hall";
			type = "hall";
            purchasePrice = 100;
            bRent = 6;
            rent1 = 30;
            rent2 = 90;
            rent3 = 270;
            rent4 = 400;
            hRent = 550;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 50;
            hood = 2;
        }
        else if(propNum == 5)//6
        {
            name = "Lyon Hall";
			type = "hall";
            purchasePrice = 120;
            bRent = 8;
            rent1 = 40;
            rent2 = 100;
            rent3 = 300;
            rent4 = 450;
            hRent = 600;
            cRent = bRent;
            houseCost = 50;
            mortgagePrice = 60;
            hood = 2;
        }
        else if(propNum == 6)//7
        {
            name = "Freeman Hall";
			type = "hall";
            purchasePrice = 140;
            bRent = 10;
            rent1 = 50;
            rent2 = 150;
            rent3 = 450;
            rent4 = 625;
            hRent = 750;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 70;
            hood = 3;
        }
        else if(propNum == 7)//8
        {
            name = "Tutoring";
			type = "utility";
            purchasePrice = 150;
            bRent = 0;//should be somthing
            rent1 = -1;
            rent2 = -1;
            rent3 = -1;
            rent4 = -1;
            hRent = 0;//should be something
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 75;
            hood = 22;
        }
        else if(propNum == 8)//9
        {
			name = "Barton Hall";
            type = "hall";
            purchasePrice = 140;
            bRent = 10;
            rent1 = 50;
            rent2 = 150;
            rent3 = 450;
            rent4 = 625;
            hRent = 750;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 70;
            hood = 3;
        }
        else if(propNum == 9)//10
        {
            name = "Birch Hall";
			type = "hall";
            purchasePrice = 160;
            bRent = 12;
            rent1 = 60;
            rent2 = 180;
            rent3 = 500;
            rent4 = 700;
            hRent = 900;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 80;
            hood = 3;
        }
        else if(propNum == 10)//11
        {
			name = "Cyride 23 Orange";
            type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(propNum == 11)//12
        {
            name = "Larch Hall";
			type = "hall";
            purchasePrice = 180;
            bRent = 14;
            rent1 = 70;
            rent2 = 200;
            rent3 = 500;
            rent4 = 700;
            hRent = 900;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 90;
            hood = 4;
        }
        else if(propNum == 12)//13
        {
            name = "Willow Hall";
			type = "hall";
            purchasePrice = 180;
            bRent = 14;
            rent1 = 70;
            rent2 = 200;
            rent3 = 500;
            rent4 = 700;
            hRent = 950;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 90;
            hood = 4;
        }
        else if(propNum == 13)//14
        {
            name = "Maple Hall";
			type = "hall";
            purchasePrice = 200;
            bRent = 16;
            rent1 = 80;
            rent2 = 220;
            rent3 = 600;
            rent4 = 800;
            hRent = 1000;
            cRent = bRent;
            houseCost = 100;
            mortgagePrice = 100;
            hood = 4;
        }
        else if(propNum == 14)//15
        {
            name = "Buchanan Hall";
			type = "hall";
            purchasePrice = 220;
            bRent = 18;
            rent1 = 90;
            rent2 = 250;
            rent3 = 700;
            rent4 = 875;
            hRent = 1050;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 110;
            hood = 5;
        }
        else if(propNum == 15)//16
        {
            name = "Eaton Hall";
			type = "hall";
            purchasePrice = 220;
            bRent = 18;
            rent1 = 90;
            rent2 = 250;
            rent3 = 700;
            rent4 = 875;
            hRent = 1050;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 110;
            hood = 5;
        }
        else if(propNum == 16)//17
        {
            name = "Martin Hall";
			type = "hall";
            purchasePrice = 240;
            bRent = 20;
            rent1 = 100;
            rent2 = 300;
            rent3 = 750;
            rent4 = 925;
            hRent = 1100;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 120;
            hood = 5;
        }
        else if(propNum == 17)//18
        {
            name = "Cyride 1 Red";
			type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(propNum == 18)//19
        {
            name = "ISU West Apartments";
			type = "hall";
            purchasePrice = 260;
            bRent = 22;
            rent1 = 110;
            rent2 = 330;
            rent3 = 800;
            rent4 = 975;
            hRent = 1150;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 130;
            hood = 6;
        }
        else if(propNum == 19)//20
        {
            name = "Frederickson Court";
			type = "hall";
            purchasePrice = 260;
            bRent = 22;
            rent1 = 110;
            rent2 = 330;
            rent3 = 800;
            rent4 = 975;
            hRent = 1150;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 130;
            hood = 6;
        }
        else if(propNum == 20)//21
        {
            name = "Laundry";
			type = "utility";
            purchasePrice = 150;
            bRent = 0;//should be somthing
            rent1 = -1;
            rent2 = -1;
            rent3 = -1;
            rent4 = -1;
            hRent = 0;//should be something
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 75;
            hood = 22;
        }
        else if(propNum == 21)//22
        {
            name = "Legacy Apartments";
			type = "hall";
            purchasePrice = 280;
            bRent = 24;
            rent1 = 120;
            rent2 = 360;
            rent3 = 850;
            rent4 = 1025;
            hRent = 1200;
            cRent = bRent;
            houseCost = 150;
            mortgagePrice = 140;
            hood = 6;
        }
        else if(propNum == 22)//23
        {
            name = "Parks Library";
			type = "hall";
            purchasePrice = 300;
            bRent = 26;
            rent1 = 130;
            rent2 = 390;
            rent3 = 900;
            rent4 = 1100;
            hRent = 1275;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 150;
            hood = 7;
        }
        else if(propNum == 23)//24
        {
            name = "Beardshear Hall";
			purchasePrice = 300;
            bRent = 26;
            rent1 = 130;
            rent2 = 390;
            rent3 = 900;
            rent4 = 1100;
            hRent = 1275;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 150;
            hood = 7;
        }
        else if(propNum == 24)//25
        {
            name = "Memorial Union";
			type = "hall";
            purchasePrice = 320;
            bRent = 28;
            rent1 = 150;
            rent2 = 450;
            rent3 = 1000;
            rent4 = 1200;
            hRent = 1400;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 160;
            hood = 7;
        }
        else if(propNum == 25)//26
        {
            name = "Cyride 3 Blue";
			type = "bus";
            purchasePrice = 200;
            bRent = 25;
            rent1 = 50;
            rent2 = 100;
            rent3 = 200;
            rent4 = -1;
            hRent = -1;
            cRent = bRent;
            houseCost = -1;
            mortgagePrice = 100;
            hood = 11;
        }
        else if(propNum == 26)//27
        {
            name = "Jack Trice Stadium";
			type = "hall";
            purchasePrice = 350;
            bRent = 35;
            rent1 = 175;
            rent2 = 500;
            rent3 = 1100;
            rent4 = 1300;
            hRent = 1500;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 175;
            hood = 8;
        }
        else if(propNum == 27)//28
        {
            name = "Hilton Coliseum";
			type = "hall";
            purchasePrice = 400;
            bRent = 50;
            rent1 = 200;
            rent2 = 600;
            rent3 = 1400;
            rent4 = 1700;
            hRent = 2000;
            cRent = bRent;
            houseCost = 200;
            mortgagePrice = 200;
            hood = 8;
        }
        
        
        
    }
    
    //accessors
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getPurchasePrice()
    {
        return purchasePrice;
    }
    
    public int getCurrentRent()
    {
        return cRent;//might need to qualify for utilities
    }
    
    public int getBaseRent()
    {
        return bRent;
    }
    
    public int getRent1()
    {
        return rent1;
    }
    
    public int getRent2()
    {
        return rent2;
    }
    
    public int getRent3()
    {
        return rent3;
    }
    
    public int getRent4()
    {
        return rent4;
    }
    
    public int getHRent()
    {
        return hRent;
    }
    
    public int getHouseCost()
    {
        return houseCost;
    }
    
    public int getMortgagePrice()
    {
        return mortgagePrice;
    }
    
    public String getOwner()
    {
        return owner;
    }
    
    public int getNumHouses()
    {
        return numHouses;
    }
    
    public boolean getNumHotels()
    {
        return hasHotel;
    }
    public boolean getIsMortgaged()
    {
        return isMortgaged;
    }
    
    
    
    //modifiers -- might not be necessary (might be used to do a trade)
    

    public void setOwner(String ownerName)
    {
        owner = ownerName;
    }
    
    //other methods
    public void addHouse(Player player)
    {
        numHouses++;
        if(numHouses == 1){
        	cRent = rent1;
        	player.buyAHouse();
        	player.setCashMoney(player.getCashMoney() - houseCost);
        }
        if(numHouses == 2){
        	cRent = rent2;
        	player.buyAHouse();
        	player.setCashMoney(player.getCashMoney() - houseCost);
        }
        if(numHouses == 3){
        	cRent = rent3;
        	player.buyAHouse();
        	player.setCashMoney(player.getCashMoney() - houseCost);
        }
        if(numHouses == 4){
        	cRent = rent4;
        	player.buyAHouse();
        	player.setCashMoney(player.getCashMoney() - houseCost);
        }
        if(numHouses == 5){
        	hasHotel = true;
        	cRent = hRent;
        	player.buyAHotel();
        	player.setCashMoney(player.getCashMoney() - houseCost);
        }
    }
    
    public void mortgage(Player tiddlywinks)
    {
        tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() + this.getMortgagePrice());
        isMortgaged = true;
    }
    
    public void buyBack(Player tiddlywinks)
    {
        tiddlywinks.setCashMoney(tiddlywinks.getCashMoney() - this.getMortgagePrice());
        isMortgaged = false;
    }
}