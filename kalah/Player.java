package kalah;

/*Player class is used to calculate and track player score*/
public class Player {
	private int PlayerScore = 0;
	private int PlayerID;
	private static int count = 0;
	
	public Player(int id){
		PlayerID = id;
	}
	
	public int getPlayerScore( House[] gameBoard, Store store){
		int startIndex = 0; 
		int endIndex = 6;
		
		if(store.getObjectID() ==2 ){
			startIndex = 6;
			endIndex = 12;
		}
		for(int i = startIndex; i < endIndex; i++){
			PlayerScore += gameBoard[i].getObjectValue();
		}
		PlayerScore += store.getObjectValue();
		return PlayerScore;
	}

	public int getPlayerID(){
		return PlayerID;
	}
}
