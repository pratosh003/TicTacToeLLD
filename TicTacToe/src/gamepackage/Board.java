package gamepackage;

import java.util.ArrayList;
import java.util.List;

public class Board {
	int size;
	PlayingPiece[][] board;
	
	public Board(int size) {
		this.size = size;
		board = new PlayingPiece[size][size];
	}
	
	public boolean addPiece(int row, int col, PlayingPiece piece) {
		if(board[row][col] != null) {
			return false;
		}
		board[row][col] = piece;
		return true;
	}
	
	public List<Pair> getFreeCells(){
		List<Pair> freeCells = new ArrayList<>();
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if(board[i][j] == null)
					freeCells.add(new Pair(i, j));
			}
		}
		
		return freeCells;
	}
	
	
	public void printBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if(board[i][j] != null) {
					System.out.print(board[i][j].pieceType.name() + "   ");
				}
				else {
					System.out.print("    ");
				}
				System.out.print("  |  ");
			}
			System.out.println();
		}
	}
	
}
