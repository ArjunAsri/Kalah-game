package kalah;

public class House extends GameBoardObject{

	private int HouseValue = 4;
	/*Constructor*/
	public House(int playerID){
		objectID = playerID;
	}; 
	
	@Override
	public void incrementObjectValue() {
		HouseValue++;
	}
	
	@Override
	public int getObjectValue() {
		return HouseValue;
	}

	@Override
	public void setObjectValue(int value) {
		HouseValue = value;
	}	

}
