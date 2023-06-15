package gamepackage;

public class Main {
	
	public static void main(String[] args) {
		Game game = new Game();
		game.init();
		System.out.println("The Winner is: " + game.startGame());
	}
	
	
}
