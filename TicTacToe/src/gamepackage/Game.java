package gamepackage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Game {
	Board board;
	Deque<Player> players;
	
	public void init() {
		players = new LinkedList<>();
		
		PlayerPieceX Xpiece = new PlayerPieceX();
		Player player1 = new Player("player1", Xpiece);
		
		PlayerPieceO Opiece = new PlayerPieceO();
		Player player2 = new Player("player2", Opiece);
		
		players.add(player1);
		players.add(player2);
		
		board = new Board(3);
	}
	
	
	public String startGame() {
		boolean noWinner = true;
		
		
		while(noWinner) {
			Player playerTurn = players.removeFirst();
			board.printBoard();
			
			List<Pair> freeSpaces = board.getFreeCells();
			if(freeSpaces.isEmpty()) {
				noWinner = false;
				continue;
			}
			
			//take cur player input
			System.out.println("Player: " + playerTurn.name + " enter row & column: ");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			String values[] = input.split(",");
			int row = Integer.valueOf(values[0]);
			int col = Integer.valueOf(values[1]);
			
			
			boolean pieceAdded = board.addPiece(row, col, playerTurn.playingPiece);
			if(!pieceAdded) {
				System.out.println("Incorrect position choosen!!!");
				players.addFirst(playerTurn);
				continue;
			}
			
			players.addLast(playerTurn);
			
			boolean winner = isThereWinner(row, col, playerTurn.playingPiece.pieceType);
			if(winner) {
				return playerTurn.name;
			}
		}
		return "tie!!!";
	}
	
	
	boolean isThereWinner(int row, int col, PieceType pieceType) {
		boolean rowMatch = true;
		boolean colMatch = true;
		boolean diaMatch = true;
		boolean antiDiaMatch = true;
		
		for(int i=0;i<board.size;i++) {
			if(board.board[row][i] == null || board.board[row][i].pieceType != pieceType) {
				rowMatch = false;
			}
		}
		
		for(int i=0;i<board.size;i++) {
			if(board.board[i][col] == null || board.board[i][col].pieceType != pieceType) {
				colMatch = false;
			}
		}
		
		for(int i=0;i<board.size;i++) {
			if(board.board[i][i] == null || board.board[i][i].pieceType != pieceType) {
				diaMatch = false;
			}
		}
		
		for(int i=0, j=board.size-1;i<board.size && j>=0;i++, j--) {
			if(board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
				antiDiaMatch = false;
			}
		}
		
		return rowMatch || colMatch || diaMatch || antiDiaMatch;
	}
}










