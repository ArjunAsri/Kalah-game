package kalah;

public class MovementOfStones {

	private int lastPlantInStore;
	private int lastHouse;
	private int numberOfHouses = 12;
	/*Function UpdateHomesValue - This function is to plant seeds in houses and
	 *the players own store. The function takes 3 inputs which is the house number
	 *playerNumber who is taking turn and the array of store objects.
	 *The addStone variable is only used at the start in case the player picks a
	 *house right before their own Store at the start of their turn */
	public boolean updateHouseValues(House[] houses, int startHouseNumber, int playerNumber, Store[] stores){
		if(playerNumber == 2){
			startHouseNumber += 5; 	//so if house number 6 is selected it will be 11 on index
		}else{
			startHouseNumber--; 	//because index starts at 0 so if startHouseNumber was 1 it will be 0 for array
		}
		
		int stonesToAdd = houses[startHouseNumber].getObjectValue();
		houses[startHouseNumber].setObjectValue(0); //make the stones zero
		int numOfTimesToIterate = stonesToAdd;
		int startIndex = startHouseNumber+1; //careful about the last element of the array
		lastPlantInStore= 0;
		int addStone = 0;
		if((startIndex == 6) || (startIndex == 12)){//only for initial start, should not be needed afterwards, when player selects the last house in our row
			addStone = 1;
		}
		
		//if there are any stones
		if(stonesToAdd > 0){
			for(int i = 0; i < numOfTimesToIterate; i++){
				//reconfigure index
				if ((startIndex+i) >= numberOfHouses){
					//add stone to the player position
					int newIndex = (startIndex+i)%numberOfHouses;
					startIndex = newIndex - i;
				}
				//if we are starting from the last square
				if(playerNumber == 1){
					if((((startIndex+i) == (numberOfHouses/2)-1)|| (addStone==1)) && stonesToAdd > 1){
						stores[0].incrementObjectValue();
						numOfTimesToIterate--; // 1 less time to iterate as we have already in advance added the stone to player's house
						stonesToAdd--; //reduce stone for the one that will be added to the store
						addStone = 0;
						if((i+1) == numOfTimesToIterate){
							lastPlantInStore = 1;
						}
					}else{
						if((((addStone==1)&& stonesToAdd == 1))&&((startIndex+i)!= (numberOfHouses/2)-1)){
							stores[0].incrementObjectValue();
							lastPlantInStore = 1;
							lastHouse = (numberOfHouses/2)-1;
							return true;
						}
					}
				}else{
					if((((startIndex+i) == (numberOfHouses-1)) || (addStone==1)) && stonesToAdd > 1){
						stores[1].incrementObjectValue();
						numOfTimesToIterate--;
						stonesToAdd--;
						addStone = 0;
						if(stonesToAdd == 1){ //if only 1 stone to add
							lastPlantInStore = 1;
						}
						
					}else{
						if((((addStone==1) && stonesToAdd == 1))&&((startIndex+i)!= (numberOfHouses-1))){
							stores[1].incrementObjectValue();
							lastPlantInStore = 1;
							lastHouse = numberOfHouses-1;
							return true;
						}
					}
				
				}
				if(((startIndex+i) == 0) || ((startIndex+i) == (numberOfHouses/2))){ //this fixes the edge cases of the game board, where the last stone is not in the player's store
					lastPlantInStore = 0;
				}
				if((stonesToAdd>0)){
					houses[startIndex+i].incrementObjectValue();
					stonesToAdd--;
				}
				lastHouse = startIndex+i;
			}//end of for loop
			return true; //after adding stones
		}else{
			return false; //house is empty
		}
	}
	
	
	public int getLastPlantInStore(){
		return lastPlantInStore;
	}
	
	public int getLastHouse(){
		return lastHouse;
	}
	
	public void setCaptureStones(House[] houses, Store store){
		store.addToStoreValue( houses[lastHouse].getObjectValue() + houses[(numberOfHouses-1)-lastHouse].getObjectValue());
		houses[lastHouse].setObjectValue(0);
		houses[11-lastHouse].setObjectValue(0);
		
	}

}
