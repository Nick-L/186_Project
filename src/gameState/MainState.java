package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import backend.Board;
import backend.Card;
import backend.Player;
import backend.Property;
import main.GamePanel;
import images.Background;

public class MainState extends GameState {

	private Background board;
	private boolean isAProperty = false;
	private Board backendBoard;
	private String drawnCardString;
	private String flavorText1;
	private String flavorText2;
	private String flavorText3;
	private int playerIndex;
	private Card drawnCard;
	private Font cardTextFont = new Font("Arial", Font.PLAIN, 14);
	private ArrayList<Integer> mortgagingPropList = new ArrayList<Integer>();
	private boolean mortgaging = false;
	private boolean buyingHouses = false;
	private boolean cardDrawn = false;
	private String[] mainChoices = new String[] {"Roll", "Buy Property", "Mortgage", "Trade", "Buy House", "End Turn"};
	private String[] tradeChoices;
	private String[] tradeChoices2;
	private Font buttonFont = new Font("Arial", Font.BOLD, 20);
	private int tradeChoice = 0;
	private int tradeStage = 0;
	private int currentChoice = 0;
	private long previousTime = System.nanoTime();
	private final int WAIT = 100000000;
	private Font playerInfoFont = new Font("Arial", Font.ROMAN_BASELINE, 20);
	private Font tradeTitleFont = new Font("Arial", Font.ROMAN_BASELINE, 40);
	private Font tradeChoicesFont = new Font("Arial", Font.PLAIN, 20);
	private int currentPlayer = 0;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int numPlayers;
	private int[] xCoords = new int[40];
	private int[] yCoords = new int[40];
	private boolean canRoll = true;
	private Random dice = new Random();
	private int dice1, dice2;
	private boolean tradeSelected = false;
	private ArrayList<String> offer = new ArrayList<String>();
	private ArrayList<String> offer2 = new ArrayList<String>();
	private String[] pieceLocation = new String[] { 
													"/gamePieces/CAFinalNWS.png",
													"/gamePieces/DFinalNWS.png",
													"/gamePieces/FFinalNWS.png",
													"/gamePieces/PFinalNWS.png",
													"/gamePieces/GCFinalNWS.png",
													"/gamePieces/BBFinalNWS.png"
												};
	private Image[] playerPieces;

	
	
	public MainState(GameStateManager gsm) {
		this.gsm = gsm;
		
		
		try{
			board = new Background("/backgrounds/CyopolyBoardInitialRotation1.jpg");
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		setPositionCoords();
		numPlayers = PlayerSelectState.numberOfPlayers;
		initializePlayers(numPlayers);
		playerPieces = new Image[numPlayers];
		for(int i = 0; i < numPlayers; i++){
			try{
				playerPieces[i] = ImageIO.read(getClass().getResourceAsStream(pieceLocation[i]));
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		backendBoard = new Board(2);

		
	}

	@Override
	public void update() {
		board.update();

	}

	@Override
	public void draw(Graphics2D g) {
		//clear screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw board
		board.draw(g);
		
		//Draw buttons to interact with game
		drawButtons(g);
		
		//Draw Player Info
		drawPlayerInfo(g);
		
		//DrawPlayers
		drawPlayer(g);
		
		//Draw Trade Menu If Selected
		if(tradeSelected){
			drawTradeMenu(g);
		}
		
		//Draw the card that has been drawn
		if(cardDrawn){
			drawCardDisplay(g);
		}
		
		if(mortgaging){
			drawMortgagingMenu(g);
		}
		
		if(buyingHouses){
			drawBuyHousesMenu(g);
		}
		
		

	}

	@Override
	public void keyPressed(int k) {
		if(tradeSelected){
			tradeMenuKeyOptions(k);
		}
		else if(cardDrawn){
			if(k == KeyEvent.VK_ENTER){
				cardDrawn = false;
			}
		}
		else if(mortgaging){
			mortgagingMenuKeyOprions(k);
		}
		else if(buyingHouses){
			buyingHousesKeyOptions(k);
		}
		else{
			mainGameKeyOptions(k);
		}
		

	}

	private void selectNextPlayer() {
		if(currentPlayer < numPlayers - 1){
			currentPlayer++;
		}
		else{
			currentPlayer = 0;
		}
		
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ENTER){

		}

	}
	
	
	//Helper Method to draw buttons for main game menu
	private void drawButtons(Graphics2D g){
		for(int i = 0; i < mainChoices.length; i++){
			//Button Outer
			if(i == currentChoice){
				g.setColor(Color.RED);
			}
			else{
				g.setColor(Color.YELLOW);
			}
			g.fillRect(GamePanel.WIDTH - 220, 30 + 80 * i, 200, 50);
			
			//Button Inner
			if(i == currentChoice){
				g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.RED);
			}
			g.fillRect(GamePanel.WIDTH - 215, 35 + 80 * i, 190, 40);
			
			// Button Strings 
			g.setColor(Color.BLACK);
			g.setFont(buttonFont);
			g.drawString(mainChoices[i], GamePanel.WIDTH - 210, 60 + 80 * i);
		}
	}
	
	//Helper Method to draw player Info
	private void drawPlayerInfo(Graphics2D g){
		//Player info box outline
		g.setColor(Color.YELLOW);
		g.fillRect(GamePanel.WIDTH - 250, GamePanel.HEIGHT - 200, 250, 200);
		
		//player info box inner
		g.setColor(Color.RED);
		g.fillRect(GamePanel.WIDTH - 245, GamePanel.HEIGHT - 195, 240, 190);
		
		//current player number
		g.setColor(Color.YELLOW);
		g.setFont(playerInfoFont);
		g.drawString(players.get(currentPlayer).getName(), GamePanel.WIDTH - 165, GamePanel.HEIGHT - 175);
		
		g.drawString("$" + players.get(currentPlayer).getCashMoney(), GamePanel.WIDTH - 240, GamePanel.HEIGHT - 150);
		
		g.drawString("Dice 1: " + dice1 + " Dice 2: " + dice2, GamePanel.WIDTH - 240, GamePanel.HEIGHT - 100);
	}
	
	private void initializePlayers(int numberOfPlayers){
		for(int i = 0; i < numPlayers; i++){
			Player newPlayer = new Player(i + 1);
			players.add(newPlayer);
		}
	}
	
	private void drawPlayer(Graphics2D g){
		for(int i = 0; i < PlayerSelectState.numberOfPlayers; i++){
			if(!(players.get(i).getPosition() == players.get(currentPlayer).getPosition())){
				if(players.get(i).isInJail()){
					g.drawImage(playerPieces[i], 30, 690, 50, 50, null);
				}
				else{
					g.drawImage(playerPieces[i], xCoords[players.get(i).getPosition()], yCoords[players.get(i).getPosition()], 50, 50, null);
				}
			}
		}
		if(players.get(currentPlayer).isInJail()){
			g.drawImage(playerPieces[currentPlayer], 30, 690, 50, 50, null);
		}
		else{
			g.drawImage(playerPieces[currentPlayer], xCoords[players.get(currentPlayer).getPosition()], yCoords[players.get(currentPlayer).getPosition()], 50, 50, null);
		}
	}
	
	//initializes x y coordinates for drawing pieces.
	private void setPositionCoords(){
		//Go
		xCoords[0] = 690;
		yCoords[0] = 700;
		//JustVisitingJail
		xCoords[10] = 20;
		yCoords[10] = 730;
		//FreeParking
		xCoords[20] = 20;
		yCoords[20] = 20;
		//GoToJail
		xCoords[30] = 690;
		yCoords[30] = 20;
		for(int i = 0; i < 40; i++){
			if(i < 10 && i != 0){
				xCoords[i] = 615 - (i - 1) * 63; 
				yCoords[i] = 700;
			}
			else if(i > 10 && i < 20){
				xCoords[i] = 16;
				yCoords[i] = 614 - (i - 11) * 63;
			}
			else if(i > 20 && i < 30){
				xCoords[i] = 111 + (i - 21) * 63;
				yCoords[i] = 20;
			}
			else if(i > 30 && i < 40){
				xCoords[i] = 710;
				yCoords[i] = 110 + (i - 31) * 63;
			}
		}
	}
	
	private void drawTradeMenu(Graphics2D g){
		//Draw Menu Boarder
		g.setColor(Color.BLUE);
		g.fillRect(140, 150, 500, 400);
		//Draw Menu Center
		g.setColor(Color.cyan);
		g.fillRect(145, 155, 490, 390);
		//Draw Menu Title
		g.setFont(tradeTitleFont);
		g.setColor(Color.BLACK);
		g.drawString("Trade", 350, 200);
		//Draw subtitle (Instructions)
		g.setFont(tradeChoicesFont);
		if(tradeStage == 0){
			g.drawString("Select the property or properties you wish to trade:", 158, 230);
		}
		else if(tradeStage == 1){
			g.drawString("Select the player you wish to trade with:", 158, 230);
		}
		else if(tradeStage == 2){
			g.drawString("Select the properties you wish to trade for:", 158, 230);
		}
		
		//draw the current players properties as choices to select
		for(int i = 0; i < tradeChoices.length; i++){
			g.setFont(tradeChoicesFont);
			if(tradeChoice == i  && tradeStage == 0|| offer.contains(tradeChoices[i])){
				g.setColor(Color.MAGENTA);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawString(tradeChoices[i], 150, 250 + (20 * i));
		}
		
		//Draw Cancel or Back Button
		g.setColor(Color.BLACK);
		g.fillRect(160, 500, 200, 40);
		if(tradeStage == 0){
			if(tradeChoice == tradeChoices.length){
				g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.ORANGE);		
			}
		}
		if(tradeStage == 1){
			if(tradeChoice == players.size() - 1){
				g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.ORANGE);		
			}
		}
		if(tradeStage == 2){
			if(tradeChoice == tradeChoices2.length){
				g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.ORANGE);		
			}
		}		
		g.fillRect(165, 505, 190, 30);
		g.setColor(Color.BLACK);
		if(tradeStage == 0){
			g.drawString("Cancel", 170, 530);
		}
		else{
			g.drawString("Back", 170, 530);
		}
		
		//Draw Continue Button
		g.setColor(Color.BLACK);
		g.fillRect(420, 500, 200, 40);
		if(tradeStage == 0){
			if(tradeChoice == tradeChoices.length + 1){
				g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.ORANGE);		
			}
		}
		if(tradeStage == 1){
			if(tradeChoice == players.size()){
				g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.ORANGE);		
			}
		}
		if(tradeStage == 2){
			if(tradeChoice == tradeChoices2.length + 1){
				g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.ORANGE);		
			}
		}
		g.fillRect(425, 505, 190, 30);
		g.setColor(Color.BLACK);
		g.drawString("Next", 430, 530);
		
		//draw other players to trade with
		if(tradeStage == 0 || tradeStage == 1){
			ArrayList<String> temp = new ArrayList<String>();
			for(int i = 0; i < players.size(); i++){
				if(i != currentPlayer){
					temp.add(players.get(i).getName());
				}
			}
				
			for(int i = 0; i < temp.size(); i++){
					if(tradeStage == 1 && tradeChoice == i){
						g.setColor(Color.MAGENTA);
					}
					else{
						g.setColor(Color.BLACK);
					}
					g.drawString(temp.get(i), 425, 250 + 20 * i);
			}
		}
		else if(tradeStage == 2){
			for(int i = 0; i < tradeChoices2.length; i++){
				g.setFont(tradeChoicesFont);
				if(tradeChoice == i  && tradeStage == 2|| offer2.contains(tradeChoices2[i])){
					g.setColor(Color.MAGENTA);
				}
				else{
					g.setColor(Color.BLACK);
				}
				g.drawString(tradeChoices2[i], 425, 250 + (20 * i));
			}
		}
		
		if(tradeStage == 3){
			drawTradeConfirmMenu(g);
		}

		
	}
	
	private void mainGameKeyOptions(int k){
		if (k == KeyEvent.VK_UP) {
			if(System.nanoTime() > previousTime + WAIT){
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = mainChoices.length - 1;
				}
				previousTime = System.nanoTime();
			}
		}
		
		if (k == KeyEvent.VK_DOWN) {
			if(System.nanoTime() > previousTime + WAIT){
				currentChoice++;
				if (currentChoice == mainChoices.length) {
					currentChoice = 0;
				}
				previousTime = System.nanoTime();
			}
		}
		if(k == KeyEvent.VK_ENTER){
			if(currentChoice == 0){
				//Roll
				if(canRoll){
					if(players.get(currentPlayer).isInJail()){
						jailRoll();
					}
					else{
						roll();
						checkPosition();
					}
				}
			}
			
			else if(currentChoice == 1){
				//Buy Property
				if(isBuyable()){
					players.get(currentPlayer).setCashMoney(players.get(currentPlayer).getCashMoney() - backendBoard.getProperty(players.get(currentPlayer).getPosition()).getPurchasePrice());
					Property buying = new Property(backendBoard.getPositionName(players.get(currentPlayer).getPosition()));
					players.get(currentPlayer).addProperty(buying, players.get(currentPlayer));
					backendBoard.getProperty(players.get(currentPlayer).getPosition()).setOwner(players.get(currentPlayer).getName());
				}
				else{
					
				}
			}
			
			else if(currentChoice == 2){
				//MortgageProperty
				mortgaging = true;
				tradeChoices = players.get(currentPlayer).getOwnedPropNames();
				
			}
			
			else if(currentChoice == 3){
				//Trade
				tradeSelected = true;
				tradeChoices = players.get(currentPlayer).getOwnedPropNames();
				
				
				
			}
			
			else if(currentChoice == 4){
				//Buy House
				buyingHouses = true;
				tradeChoices = players.get(currentPlayer).getOwnedPropNames();
				
			}
			else if(currentChoice == 5){
				//End Turn
				if(!canRoll){
					selectNextPlayer();
					canRoll = true;
					currentChoice = 0;
				}
			}
		}
	}

	private void tradeMenuKeyOptions(int k){
		if (k == KeyEvent.VK_UP){
			if(System.nanoTime() > previousTime + WAIT){
				tradeChoice--;
				if (tradeChoice == -1){ 
					if(tradeStage == 0){
						tradeChoice = tradeChoices.length + 1;
					}
					else if(tradeStage == 1){
						tradeChoice = players.size() ;
					}
					else if(tradeStage == 2){
						tradeChoice = tradeChoices2.length;
					}
					else if(tradeStage == 3){
						tradeChoice = 1;
					}
				}
				previousTime = System.nanoTime();
			}
		}
		
		if (k == KeyEvent.VK_DOWN){
			if(System.nanoTime() > previousTime + WAIT){
				tradeChoice++;
				if(tradeStage == 0){
					if (tradeChoice == tradeChoices.length + 2) {
						tradeChoice = 0;
					}
				}
				if(tradeStage == 1){
					if(tradeChoice == players.size() + 1){
						tradeChoice = 0;
					}
				}
				if(tradeStage == 2){
					if(tradeChoice == tradeChoices2.length + 2){
						tradeChoice = 0;
					}
				}
				if(tradeStage == 3){
					if(tradeChoice == 2){
						tradeChoice = 0;
					}
				}
				previousTime = System.nanoTime();
			}
		}
		
		if(k == KeyEvent.VK_ENTER){
			//Select properties for offer
			if(tradeStage == 0){
				if(tradeChoice < tradeChoices.length){
					if(offer.contains(tradeChoices[tradeChoice])){
						offer.remove(tradeChoices[tradeChoice]);
					}
					else{
						offer.add(tradeChoices[tradeChoice]);
					}
				}
				else if(tradeChoice == tradeChoices.length){
					tradeSelected = false;
					offer.clear();
					tradeChoice = 0;
				}
				else if(tradeChoice == tradeChoices.length + 1){
					tradeStage += 1;
					tradeChoice = 0;
				}
			}
			//Select player to trade with
			else if(tradeStage == 1){
				if(tradeChoice < players.size()){
					if(tradeChoice >= currentPlayer){
						playerIndex = tradeChoice + 1;
						if(players.get(playerIndex).getOwnedPropNames() == null){
							String[] temp = new String[] {"None"};
							tradeChoices2 = temp;
						}
						else{
							tradeChoices2 = players.get(playerIndex).getOwnedPropNames();
						}
					}
					else{
						playerIndex = tradeChoice;
						if(players.get(playerIndex).getOwnedPropNames() == null){
							String[] temp = new String[] {"None"};
							tradeChoices2 = temp;
						}
						else{
							tradeChoices2 = players.get(playerIndex).getOwnedPropNames();
						}
					}
				}
				else if(tradeChoice == players.size() - 1){
					tradeStage--;
					tradeChoice = 0;
				}
				else if(tradeChoice == players.size()){
					tradeStage++;
					tradeChoice = 0;
				}
			}
			//Select player's properties you want
			else if(tradeStage == 2){
				if(tradeChoice < tradeChoices.length){
					if(offer2.contains(tradeChoices2[tradeChoice])){
						offer2.remove(tradeChoices2[tradeChoice]);
					}
					else{
						offer2.add(tradeChoices2[tradeChoice]);
					}
				}
				else if(tradeChoice == tradeChoices2.length){
					offer2.clear();
					tradeStage--;
					tradeChoice = 0;
				}
				else if(tradeChoice == tradeChoices2.length + 1){
					tradeStage++;
					tradeChoice = 0;
				}
			}
			//Review and accept trade
			else if(tradeStage == 3){
				if(tradeChoice == 1){
					finalizeTrade();
				}
				if(tradeChoice == 0){
					tradeStage--;
					tradeChoice = 0;
				}
			}
			
		}
	}
	
	private void finalizeTrade(){
		//add properties for both players
		for(int i = 0; i < offer2.size(); i++){
			Property adding = new Property(offer2.get(i));
			players.get(currentPlayer).addProperty(adding, players.get(currentPlayer));
		}
		for(int i = 0; i < offer.size(); i++){
			Property adding = new Property(offer.get(i));
			players.get(playerIndex).addProperty(adding, players.get(playerIndex));
		}
		//remove property for both players
		for(int i = 0; i < offer2.size(); i++){
			players.get(playerIndex).removeProperty(players.get(currentPlayer).getOwnedProperty(offer2.get(i)));
		}
		for(int i = 0; i < offer.size(); i++){
			players.get(currentPlayer).removeProperty(players.get(playerIndex).getOwnedProperty(offer.get(i)));
		}
		tradeSelected = false;
		tradeStage = 0;
		offer.clear();
		offer2.clear();
	}
	
	private void drawTradeConfirmMenu(Graphics2D g){
		//Draw Menu Boarder
		g.setColor(Color.BLUE);
		g.fillRect(200, 100, 380, 300);
		//Draw Menu Center
		g.setColor(Color.cyan);
		g.fillRect(205, 105, 370, 290);
		//Draw Menu Title
		g.setFont(tradeTitleFont);
		g.setColor(Color.BLACK);
		g.drawString("Confirm?", 210, 140);
		
		//draw the players offer
		g.setFont(tradeChoicesFont);
		for(int i = 0; i < offer.size(); i++){
			g.drawString(offer.get(i), 210, 160 + (20 * i));
		}
		for(int i = 0; i < offer2.size(); i++){
			g.drawString(offer2.get(i), 410, 160 + (20 * i));
		}
		
		//draw confirm and cancel buttons
		
		//draw cancel button
		g.setColor(Color.BLACK);
		g.fillRect(230, 360, 100, 30);
		if(tradeChoice == 0){
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.ORANGE);
		}
		g.fillRect(235, 365, 90, 20);
		g.setColor(Color.BLACK);
		g.drawString("Cancel", 240, 382);
		
		//draw confirm button
		g.setColor(Color.BLACK);
		g.fillRect(430, 360, 100, 30);
		if(tradeChoice == 1){
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.ORANGE);
		}
		g.fillRect(435, 365, 90, 20);
		g.setColor(Color.BLACK);
		g.drawString("Confirm", 440, 382);
	}

	
	private void roll(){
		if(canRoll){
			dice1 = dice.nextInt(6) + 1;
			dice2 = dice.nextInt(6) + 1;
			int newPosition = players.get(currentPlayer).getPosition() + dice1 + dice2;
			if(newPosition > 39){
				newPosition = newPosition - 39;
				players.get(currentPlayer).setCashMoney(players.get(currentPlayer).getCashMoney() + 200);
			}
			players.get(currentPlayer).setPosition(newPosition);
			canRoll = false;
			currentChoice = 5;
		}
		if(dice1 == dice2){
			canRoll = true;
			currentChoice = 0;
		}
	}

	private void drawCardDisplay(Graphics2D g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(150, 300, 500, 100);
		g.setFont(cardTextFont);
		g.setColor(Color.BLACK);
		
		if(flavorText1 != null){
			g.drawString(flavorText1, 160, 330);
		}
		if(flavorText2 != null){
			g.drawString(flavorText2, 160, 350);
		}
		if(flavorText3 != null){
			g.drawString(flavorText3, 160, 370);
		}
		g.drawString("Press enter to continue", 160, 390);
	}

	private void drawMortgagingMenu(Graphics2D g){
		//Draw Menu Boarder
		g.setColor(Color.BLUE);
		g.fillRect(140, 150, 500, 400);
		//Draw Menu Center
		g.setColor(Color.cyan);
		g.fillRect(145, 155, 490, 390);
		//Draw Menu Title
		g.setFont(tradeTitleFont);
		g.setColor(Color.BLACK);
		g.drawString("Mortgaging", 300, 200);
		
		//draw the current players properties as choices to select
		for(int i = 0; i < tradeChoices.length; i++){
			g.setFont(tradeChoicesFont);
			if(tradeChoice == i || mortgagingPropList.contains(i)){
				g.setColor(Color.MAGENTA);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawString(tradeChoices[i] + " $" + players.get(currentPlayer).getOwnedProperty(tradeChoices[i]).getMortgagePrice(), 150, 250 + (20 * i));
		}
		
		//draw cancel button
		g.setColor(Color.BLACK);
		g.fillRect(160, 500, 200, 40);
		if(tradeChoice == tradeChoices.length){
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.ORANGE);
		}
		g.fillRect(165, 505, 190, 30);
		g.setColor(Color.BLACK);
		g.drawString("Cancel", 170, 530);
						
		//draw confirm button
		g.setColor(Color.BLACK);
		g.fillRect(420, 500, 200, 40);
		if(tradeChoice == tradeChoices.length + 1){
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.ORANGE);
		}
		g.fillRect(425, 505, 190, 30);
		g.setColor(Color.BLACK);
		g.drawString("Confirm", 430, 530);
	}
	
	private void drawBuyHousesMenu(Graphics2D g){
		//Draw Menu Boarder
		g.setColor(Color.BLUE);
		g.fillRect(140, 150, 500, 400);
		//Draw Menu Center
		g.setColor(Color.cyan);
		g.fillRect(145, 155, 490, 390);
		//Draw Menu Title
		g.setFont(tradeTitleFont);
		g.setColor(Color.BLACK);
		g.drawString("Buying Houses", 270, 200);
		
		//draw the current players properties as choices to select
		for(int i = 0; i < tradeChoices.length; i++){
			g.setFont(tradeChoicesFont);
			if(tradeChoice == i || mortgagingPropList.contains(i)){
				g.setColor(Color.MAGENTA);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawString(tradeChoices[i] + " $" + players.get(currentPlayer).getOwnedProperty(tradeChoices[i]).getHouseCost(), 150, 250 + (20 * i));
		}
		
		//draw cancel button
		g.setColor(Color.BLACK);
		g.fillRect(160, 500, 200, 40);
		if(tradeChoice == tradeChoices.length){
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.ORANGE);
		}
		g.fillRect(165, 505, 190, 30);
		g.setColor(Color.BLACK);
		g.drawString("Cancel", 170, 530);
				
		//draw confirm button
		g.setColor(Color.BLACK);
		g.fillRect(420, 500, 200, 40);
		if(tradeChoice == tradeChoices.length + 1){
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.ORANGE);
		}
		g.fillRect(425, 505, 190, 30);
		g.setColor(Color.BLACK);
		g.drawString("Confirm", 430, 530);
		
		
	}

	private void mortgagingMenuKeyOprions(int k){
		if(k == KeyEvent.VK_UP){
			if(System.nanoTime() > previousTime + WAIT){
				tradeChoice--;
				if (tradeChoice == -1){ 
					if(tradeStage == 0){
						tradeChoice = tradeChoices.length + 1;
					}
					else if(tradeStage == 1){
						tradeChoice = players.size() ;
					}
					else if(tradeStage == 2){
						tradeChoice = tradeChoices2.length;
					}
					else if(tradeStage == 3){
						tradeChoice = 1;
					}
				}
				previousTime = System.nanoTime();
			}
		}
		if(k == KeyEvent.VK_DOWN){
			if(System.nanoTime() > previousTime + WAIT){
				tradeChoice++;
				if (tradeChoice == tradeChoices.length + 2) {
					tradeChoice = 0;
				}
			}
			previousTime = System.nanoTime();
		}
		if(k == KeyEvent.VK_ENTER){
			if(tradeChoice < tradeChoices.length){
				mortgagingPropList.add(tradeChoice);
			}
			else if(tradeChoice == tradeChoices.length){
				mortgaging = false;
			}
			else if(tradeChoice == tradeChoices.length + 1){
				mortgageTheProperties();
				mortgaging = false;
			}
		}
	}
	
	private void buyingHousesKeyOptions(int k){
		if(k == KeyEvent.VK_UP){
			if(System.nanoTime() > previousTime + WAIT){
				tradeChoice--;
				if (tradeChoice == -1){ 
					if(tradeStage == 0){
						tradeChoice = tradeChoices.length + 1;
					}
					else if(tradeStage == 1){
						tradeChoice = players.size() ;
					}
					else if(tradeStage == 2){
						tradeChoice = tradeChoices2.length;
					}
					else if(tradeStage == 3){
						tradeChoice = 1;
					}
				}
				previousTime = System.nanoTime();
			}
		}
		if(k == KeyEvent.VK_DOWN){
			if(System.nanoTime() > previousTime + WAIT){
				tradeChoice++;
				if (tradeChoice == tradeChoices.length + 2) {
					tradeChoice = 0;
				}
			}
			previousTime = System.nanoTime();
		}
		if(k == KeyEvent.VK_ENTER){
			if(tradeChoice < tradeChoices.length){
				mortgagingPropList.add(tradeChoice);
			}
			else if(tradeChoice == tradeChoices.length){
				buyingHouses = false;
			}
			else if(tradeChoice == tradeChoices.length + 1){
				buyHouses();
			}
		}
	}
	
	private void checkPosition(){
		isAProperty = false;
		if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Go"){
			//200 is added else where
		}
		else if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Community Chest"){
			drawnCard = backendBoard.drawCommunity();
			drawnCard.play(players.get(currentPlayer), dice.nextInt(31), players);
			drawnCardString = drawnCard.getFlavorText();
			splitFlavorText(drawnCardString);
			cardDrawn = true;
		}
		else if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Chance"){
			drawnCard = backendBoard.drawChance();
			drawnCard.play(players.get(currentPlayer), dice.nextInt(31), players);
			drawnCardString = drawnCard.getFlavorText();
			splitFlavorText(drawnCardString);
			cardDrawn = true;
		}
		else if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Panda Express"){
			//remove $200
			players.get(currentPlayer).setCashMoney(players.get(currentPlayer).getCashMoney() - 200);
		}
		else if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Academic Probation"){
			//do nothing
		}
		else if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Scholarship Fund"){
			//add money plus taxes and stuff
			players.get(currentPlayer).setCashMoney(players.get(currentPlayer).getCashMoney() + 400);
		}
		else if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Go to Academic Probation"){
			//send to jail
			players.get(currentPlayer).changeJailStatus();
		}
		else if(backendBoard.getPositionName(players.get(currentPlayer).getPosition()) == "Tuition Time"){
			//remove $75
			players.get(currentPlayer).setCashMoney(players.get(currentPlayer).getCashMoney() - 75);
		}
		else{
			//on a property and can buy/must pay rent etc. not sure how to access
			isAProperty = true;
			if(backendBoard.getProperty(players.get(currentPlayer).getPosition()).getOwner() == "None" || backendBoard.getProperty(players.get(currentPlayer).getPosition()).getOwner() == players.get(currentPlayer).getName()){
				//able to buy or on own property no action from landing on
			}
			else{
				int rentAmmount = backendBoard.getProperty(players.get(currentPlayer).getPosition()).getCurrentRent();
				String ownerName = backendBoard.getProperty(players.get(currentPlayer).getPosition()).getOwner();
				players.get(currentPlayer).setCashMoney(players.get(currentPlayer).getCashMoney() - rentAmmount);
				for(int i = 0; i < players.size(); i++){
					if(ownerName.equals(players.get(i).getName())){
						players.get(i).setCashMoney(players.get(i).getCashMoney() + rentAmmount);
					}
				}
				
			}
		}
		
	}
	
	private void splitFlavorText(String original){
		int length = original.length();
		if(length < 60){
			flavorText1 = original;
			flavorText2 = null;
			flavorText3 = null;
		}
		else if(length < 120){
			for(int i = 50; i < 60; i++){
				if(original.charAt(i) == ' '){
					flavorText1 = original.substring(0, i);
					flavorText2 = original.substring(i + 1, length - 1);
					flavorText3 = null;
					i = 90;
				}
			}
		}
		else{
			int start = 0;
			for(int i = 50; i < 60; i++){
				if(original.charAt(i) == ' '){
					flavorText1 = original.substring(0, i);
					start = i;
					i = 90;
				}
			}
			for(int j = start + 50; j < 120; j++){
				if(original.charAt(j) == ' '){
					flavorText2 = original.substring(start + 1, j);
					flavorText3 = original.substring(j + 1);
					j = 190;
				}
			}
			
		}
		
	}
	
	private boolean isBuyable(){
		//if it is a property
		if(isAProperty){
			//if it is unowned
			Property temp = backendBoard.getProperty(players.get(currentPlayer).getPosition());
			if(temp.getOwner().equals("None")){
				//if you have the money
				if(players.get(currentPlayer).getCashMoney() > temp.getPurchasePrice()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void jailRoll(){
		dice1 = dice.nextInt(6) + 1;
		dice2 = dice.nextInt(6) + 2;
		
		if(dice1 == dice2){
			players.get(currentPlayer).changeJailStatus();
			players.get(currentPlayer).setPosition(10);
			players.get(currentPlayer).setTurnsInJail(0);
		}
		else{
			players.get(currentPlayer).setTurnsInJail(players.get(currentPlayer).getTurnsInJail() + 1);
		}
		
		if(players.get(currentPlayer).getTurnsInJail() > 3){
			players.get(currentPlayer).changeJailStatus();
			players.get(currentPlayer).setPosition(10);
			players.get(currentPlayer).setTurnsInJail(0);
		}
		canRoll = false;
		currentChoice = 5;
	}
	
	private void mortgageTheProperties(){
		for(int i = 0; i < mortgagingPropList.size(); i++){
			players.get(currentPlayer).getOwnedProperty(tradeChoices[mortgagingPropList.get(i)]).mortgage(players.get(currentPlayer));
			players.get(currentPlayer).removeProperty(players.get(currentPlayer).getOwnedProperty(tradeChoices[mortgagingPropList.get(i)]));
		}
		
		mortgagingPropList.clear();
	}

	private void buyHouses(){
		for(int i = 0; i < mortgagingPropList.size(); i++){		
			players.get(currentPlayer).getOwnedProperty(tradeChoices[mortgagingPropList.get(i)]).addHouse(players.get(currentPlayer));
		}
		
		mortgagingPropList.clear();
	}






}
