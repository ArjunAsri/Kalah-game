package kalah;

public abstract class GameBoardObject {
	
	protected int objectID;
	public int getObjectID(){
		return objectID;
	};	
	/*This method need to be implemented*/
	public abstract void incrementObjectValue();
	/*return the value of the object*/
	public abstract int getObjectValue();
	public abstract void setObjectValue(int value);
}
