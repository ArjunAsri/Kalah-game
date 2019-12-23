package kalah;

import com.qualitascorpus.testsupport.IO;

/*PrintToScreen class is used to output data to the screen*/
public class PrintToScreen {

	public static void printBoard(IO io, House[] gameBoard, Store store1, Store store2){

		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println(ParseIO.P2_Output(gameBoard,store1));
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println(ParseIO.P1_Output(gameBoard,store2));
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	}
	
	public static String inputPlayerTurn(IO io,int PlayerNumber){
		String output  = String.format("Player P%d's turn - Specify house number or 'q' to quit: ", PlayerNumber);
		return io.readFromKeyboard(output); // io is of type IO
	}
	
	public static void printPlayer1Wins(IO io){
		io.println("Player 1 wins!"); // io is of type IO
	}
	
	public static void printPlayer2Wins(IO io){
		io.println("Player 2 wins!"); // io is of type IO
	}
	
	public static void prinGameTied(IO io){
		io.println("A tie!"); // io is of type IO
	}
	
	public static void printScore(IO io, int player1score, int player2score){
		String player1 = "	player 1:"+ player1score;
		String player2 = "	player 2:"+ player2score;

		io.println(player1);
		io.println(player2);
	}
	public static void printGameOver(IO io){
		io.println("Game over");
	}
	
	public static void printEmptyHouse(IO io){
		io.println("House is empty. Move again.");
	}
}
