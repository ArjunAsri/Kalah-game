package kalah;

/*ParsIO creates output text for the game*/
public class ParseIO {
	
	/*Player 2 side of board output, inputs are House class and store object*/
	public static String P2_Output(House[] gameBoard, Store store1){
		String PlayerOutput2 = "| P2";
		int[] gameBoardValues = new int[12];

		
		for(int i = 0 ; i < 12 ; i++){
			gameBoardValues[i] = gameBoard[i].getObjectValue();
		}
		int tempValue;
		//Print Games Score, create P2 String
		for (int i =  6; i > 0; i--){
			tempValue =gameBoardValues[i+5];
			if(tempValue > 9){
				PlayerOutput2 = PlayerOutput2 + " | " + i +"[" + tempValue + "]";
			}else{
				PlayerOutput2 = PlayerOutput2 + " | " + i +"[ " + tempValue + "]";
			}
		}
		if((store1.getObjectValue()>9)){
			PlayerOutput2 = PlayerOutput2 + " | " + store1.getObjectValue() + " |";
		}else{
			PlayerOutput2 = PlayerOutput2 + " |  " + store1.getObjectValue() + " |";
		}
		return PlayerOutput2;
	}
	
	/*Player 1 side of board output, inputs are House class and store object*/
	public static String P1_Output(House[] gameBoard, Store store2){
		String PlayerOutput1="";
		if((store2.getObjectValue()>9)){
			 PlayerOutput1 = "| " + store2.getObjectValue();
		}else{
			 PlayerOutput1 = "|  " + store2.getObjectValue();
		}
		int[] gameBoardValues = new int[12];
		
		for(int i = 0 ; i < 12 ; i++){
			gameBoardValues[i] = gameBoard[i].getObjectValue();
		}
		int tempValue;
		for (int i =  1; i < 7; i++){
			tempValue = gameBoardValues[i-1];
			if(tempValue > 9){
				PlayerOutput1 = PlayerOutput1 + " | " + i +"[" + gameBoardValues[i-1] + "]";
			}else{
				PlayerOutput1 = PlayerOutput1 + " | " + i +"[ " + gameBoardValues[i-1] + "]";
	
			}
			
		}
		PlayerOutput1 = PlayerOutput1 + " | P1 |";
		return PlayerOutput1;
	}
	
}
