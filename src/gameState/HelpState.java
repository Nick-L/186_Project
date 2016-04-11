package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;

public class HelpState extends GameState {

	public HelpState(GameStateManager gsm) {
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
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g.setColor(Color.MAGENTA);
		g.drawString("WHY ARE YOU HERE!", 0, GamePanel.HEIGHT / 2);

	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE){
			gsm.setState(GameStateManager.MENU_STATE);
		}

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
