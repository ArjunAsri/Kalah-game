package kalah;

import com.qualitascorpus.testsupport.IO;

import kalah.GameState.State;

/*The Controller class instantiates various objects and binds the view and model part of the code
 * The sequence of commands follow an FSM (Finite State Machine) logic for the game logic to work*/
public class Controller {
	
	/*Function statGame starts the game, this function is called from the main function*/
	public void startGame(IO io) {
		
		/*Instantiate objects*/
		House[] houses = new House[12];		
		//creating 12 house objects 
		for(int i = 0; i < 6; i++){
			houses[i] = new House(1);
		}		
		for(int i = 6; i < 12; i++){
			houses[i] = new House(2);
		}		
		GameState gameState = new GameState();
		Player Player1 = new Player(1);
		Player Player2 = new Player(2);
		Store Store1 = new Store(1);
		Store Store2 = new Store(2);
		
		Store[] stores = new Store[2];
		stores[0] = Store1;
		stores[1] = Store2;
		
		//create stones Movement of object
		MovementOfStones stonesMovement = new MovementOfStones();
		
		PrintToScreen.printBoard(io, houses, Store1, Store2);
		gameState.setGameInitialState();
		boolean anotherMove, boardUpdated;
		int playerTurn = 1;
		
	    String suppliedInput; //= io.readFromKeyboard("Player P1's turn - Specify house number or 'q' to quit:"); // io is of type IO
	    
	    while(true){
	    	if(gameState.getCurrentState() == State.INITIAL){
				   suppliedInput = PrintToScreen.inputPlayerTurn(io,playerTurn);
					if(suppliedInput.equals("q")){
			    		PrintToScreen.printGameOver(io);
			    		PrintToScreen.printBoard(io, houses, Store1, Store2);
						break;
		    		}else{
		    			boardUpdated = stonesMovement.updateHouseValues(houses,Integer.parseInt(suppliedInput), playerTurn, stores);
		    			if(boardUpdated){
			    			anotherMove = Rules.CheckAllowedAnotherMove(stonesMovement); //check who's move it is
							gameState.setStateInProgress();
							stonesMovement.updateHouseValues(houses,Integer.parseInt(suppliedInput), playerTurn, stores);
		    	    		PrintToScreen.printBoard(io, houses, Store1, Store2);
		    				if(!anotherMove){
			    	    		playerTurn = 2;
			    	    	}
		    			}else{
							PrintToScreen.printEmptyHouse(io);
		    	    		PrintToScreen.printBoard(io,houses,Store1, Store2);

		    			}
		    		}
	    	}else if(gameState.getCurrentState() == State.IN_PROGRESS){
	    	
					int status = nextMove( stonesMovement, playerTurn,  houses,  io, Player1, Player2, stores);
					if(status == 0){
						break;
					}else{
						if(status == 1){
							playerTurn = 1;
						}else{
							playerTurn = 2;
						}
					}
	    	}
	    }
	}
	

	/*For the next player move once the game has passed the initial start stage*/
	public static int nextMove(MovementOfStones stonesMovement,int playerTakingTurn, House[] gameBoard, IO io, Player player1, Player player2, Store[] stores){
		String playerInput = "";
		playerInput = PrintToScreen.inputPlayerTurn(io, playerTakingTurn);
		boolean anotherMove, capture, boardUpdated;
		int PlayerHouseEmpty;
		int nextPlayerTurn = playerTakingTurn;
		//if player said quit then quit the game
			if(playerInput.equals("q")){
				PrintToScreen.printGameOver(io);
				PrintToScreen.printBoard(io, gameBoard, stores[0], stores[1]);
				return 0;
			}else{
    			boardUpdated = stonesMovement.updateHouseValues(gameBoard,Integer.parseInt(playerInput), playerTakingTurn, stores);
				PlayerHouseEmpty = Rules.CheckAllHousesEmpty(gameBoard);
				/*Checking if the game has ended*/
				if(PlayerHouseEmpty>0){
					PrintToScreen.printBoard(io, gameBoard,stores[0], stores[1]);
					PrintToScreen.printGameOver(io);
					PrintToScreen.printBoard(io, gameBoard,stores[0], stores[1]);
					int Player1Score = player1.getPlayerScore(gameBoard, stores[0]); //player 1 turn
					int Player2Score = player2.getPlayerScore(gameBoard, stores[1]); //player 2 turn
					PrintToScreen.printScore(io, Player1Score,Player2Score );
					if(Player1Score> Player2Score){
						PrintToScreen.printPlayer1Wins(io);
					}else if (Player2Score > Player1Score){
						PrintToScreen.printPlayer2Wins(io);
					}else{
						PrintToScreen.prinGameTied(io);
					}
					return 0;
				}
				if(boardUpdated){
					capture = Rules.CheckCapture(gameBoard, stonesMovement.getLastHouse(), playerTakingTurn, stonesMovement);
					if(capture){
						if(playerTakingTurn == 1){
							stonesMovement.setCaptureStones(gameBoard, stores[0]);
						}else{
							stonesMovement.setCaptureStones(gameBoard, stores[1]);
						}
					}
					PrintToScreen.printBoard(io, gameBoard,stores[0], stores[1]);
					anotherMove = Rules.CheckAllowedAnotherMove(stonesMovement);
					if(!anotherMove){
						if(playerTakingTurn == 1){
							nextPlayerTurn = 2;
						}else{
							nextPlayerTurn = 1;
						}
					}
				}else{
					PrintToScreen.printEmptyHouse(io);
					PrintToScreen.printBoard(io, gameBoard,stores[0], stores[1]);
				}
			}
			return nextPlayerTurn;
	}
}
