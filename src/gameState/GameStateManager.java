package gameState;

import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;

	public static final int MENU_STATE = 0;
	public static final int PLAYER_SELECT_STATE = 1;
	public static final int HELP_STATE = 2;
	public static final int MAIN_STATE = 3;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState = MENU_STATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new PlayerSelectState(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new MainState(this));
	}

	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}

	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
}
