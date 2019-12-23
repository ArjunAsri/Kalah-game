package kalah;

/*Rules class is used for the game Rules*/
public class Rules {
	//Check the Capture
	public static boolean CheckCapture(House[] gameBoard, int lastHouseNumber, int playerNumber, MovementOfStones stonesMovement){
		if((playerNumber == 1 && lastHouseNumber <= 5)||(playerNumber == 2 && lastHouseNumber > 5)){
			//check if the stones in the last house was 1 
				if(gameBoard[lastHouseNumber].getObjectValue() == 1){
					if(gameBoard[11-lastHouseNumber].getObjectValue()>0){//because the function uses index offset
						if(stonesMovement.getLastPlantInStore()!=1){
							return true;
						}
					}
				}
		}
		return false;
	}

	/*Function CheckAllowedAnotherMove:
	 * Static function to check if player gets to have another move*/
	public static boolean CheckAllowedAnotherMove(MovementOfStones stonesMovement){
		if(stonesMovement.getLastPlantInStore() == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/*CheckAllHousesEmpty function is to check whether if all houses are empty or not*/
	public static int CheckAllHousesEmpty(House[] gameBoard){
		int count = 0;
		for(int i = 0; i < 6; i++){
			if(gameBoard[i].getObjectValue() == 0){
				count++;
			}
		}
		
		if(count == 6){
			return 1; //Player 1 houses empty
		}
		count = 0;
		for(int i = 0; i < 6; i++){
			if(gameBoard[i+6].getObjectValue() == 0){
				count++;
			}
		}
		if(count == 6){
			return 2; //player 2 houses empty
		}else{
			return 0;
		}
		
	}
	
}
