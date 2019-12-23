package kalah;

/*GameState class is for different states the game will progress through*/
public class GameState {

	enum State {
		  INITIAL,
		  IN_PROGRESS,
		  ENDED
		}
	
	private State gameState;
	
	public GameState(){
		gameState = State.INITIAL;
	}
	
	public void setGameInitialState(){
		gameState = State.INITIAL;
	}
	
	public void setStateInProgress(){
		gameState = State.IN_PROGRESS;
	}
	
	public void gameEnded(){
		gameState = State.ENDED;
	}
	
	public State getCurrentState(){
		return gameState;
	}
	public void setStateInProgress(State gameState){
		gameState = State.IN_PROGRESS;
	}
}
