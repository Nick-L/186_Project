package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;
import images.MenuBackground;

public class MenuState extends GameState {

	private MenuBackground bg;

	private int currentChoice = 0;
	private String[] options = { "Play", "Help", "Quit" };

	private Font titleFont;
	private Font font;

	
	private long previousTime = System.nanoTime();
	private final int WAIT = 100000000;

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;

		try {
			bg = new MenuBackground("/backgrounds/Campanile.jpg");

			titleFont = new Font("Arial", Font.CENTER_BASELINE, 72);
			font = new Font("Arial", Font.BOLD, 48);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {

	}

	@Override
	public void update() {
		bg.update();

	}

	@Override
	public void draw(Graphics2D g) {
		// draw bg
		bg.draw(g);

		FontMetrics fm = g.getFontMetrics();
		// draw title
		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.drawString("Cy-opoly", GamePanel.WIDTH / 2 - fm.stringWidth("Cy-opoly") / 2, 200);

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.YELLOW);
			}
			g.drawString(options[i], GamePanel.WIDTH / 2, GamePanel.HEIGHT - 500 + i * 60);
		}
	}

	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.PLAYER_SELECT_STATE);
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.HELP_STATE);
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int k) {


		
		if (k == KeyEvent.VK_UP) {
			if(System.nanoTime() > previousTime + WAIT){
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = options.length - 1;
				}
				previousTime = System.nanoTime();
			}
		}
		
		if (k == KeyEvent.VK_DOWN) {
			if(System.nanoTime() > previousTime + WAIT){
				currentChoice++;
				if (currentChoice == options.length) {
					currentChoice = 0;
				}
				previousTime = System.nanoTime();
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}

	}
}
