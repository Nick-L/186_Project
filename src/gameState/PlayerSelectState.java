package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;

public class PlayerSelectState extends GameState {

	private int currentChoice = 0;
	private long previousTime = System.nanoTime();
	private final int WAIT = 100000000;
	private final Color CYCLONE_CARDINAL = new Color(167, 25, 48);
	private final Color CYCLONE_GOLD = new Color(253, 200, 47);
	
	public static int numberOfPlayers;
	
	public PlayerSelectState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		final int OFFSET = 160;
		final int VERTICAL_OFFSET = 80 + 40;
		final int OPTIONS = 5;
		final Font buttonFont = new Font("Arial", Font.BOLD, 48);
		final Font font = new Font("Arial", Font.BOLD, 25);
		g.setColor(CYCLONE_CARDINAL);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for(int i = 0; i < OPTIONS; i++){
			if(currentChoice == i){
				g.setColor(CYCLONE_GOLD);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.fillRect(GamePanel.WIDTH / 2 - OFFSET - 10, 50 + (i * VERTICAL_OFFSET) - 20, OFFSET * 2 + 20, 100);
			
			if(currentChoice == i){
				g.setColor(Color.BLACK);
			}
			else{
				g.setColor(CYCLONE_GOLD);
			}
			g.fillRect(GamePanel.WIDTH / 2 - OFFSET, 40 + (i * VERTICAL_OFFSET), OFFSET * 2, 80);
			
			g.setColor(CYCLONE_CARDINAL);
			g.setFont(buttonFont);
			g.drawString("" +(i + 2) + " - Players",  40 + GamePanel.WIDTH / 2 - OFFSET , 95 +(i * VERTICAL_OFFSET));
		}
		
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Press Escape to return to the previous menu", 0, GamePanel.HEIGHT - 40);

	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_UP) {
			if(System.nanoTime() > previousTime + WAIT){
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = 4;
				}
				previousTime = System.nanoTime();
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			if(System.nanoTime() > previousTime + WAIT){
				currentChoice++;
				if (currentChoice == 5) {
					currentChoice = 0;
				}
				previousTime = System.nanoTime();
			}
		}
		if(k == KeyEvent.VK_ESCAPE){
			gsm.setState(GameStateManager.MENU_STATE);
		}

	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
	}
	
	private void select() {
		if(currentChoice == 0) {
			
			//set players to 2
			numberOfPlayers = 2;
		}
		if(currentChoice == 1) {
			
			//set players to 3
			numberOfPlayers = 3;
		}
		if(currentChoice == 2) {
			
			//set players to 4
			numberOfPlayers = 4;
		}
		if(currentChoice == 3) {
			
			//set players to 5
			numberOfPlayers = 5;
		}
		if(currentChoice == 4) {
			
			//set players to 6
			numberOfPlayers = 6;
		}
		if(currentChoice == 5) {
			
			//set players to 7
			numberOfPlayers = 7;
		}
		
		gsm.setState(GameStateManager.MAIN_STATE);
	}

}
