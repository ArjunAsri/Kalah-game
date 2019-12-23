package kalah;

/*The store class is used for tracking the number of stones in a player's store*/
public class Store extends GameBoardObject {
	private int StoreValue;
	public Store(int ID){
		objectID = ID;
		StoreValue = 0;
	};
	
	@Override
	public void incrementObjectValue() {
		StoreValue++;
	}

	@Override
	public int getObjectValue() {
		return StoreValue;
	}
	
	public void addToStoreValue(int value){
		StoreValue += value;
	}

	@Override
	public void setObjectValue(int value) {
		StoreValue = value;
	}


}
