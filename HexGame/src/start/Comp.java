package start;

import javafx.scene.paint.Color;

public class Comp {
	
	static boolean AIFirstMove = true;
	
	public static void compMove() throws Exception {
		if (Player.IsRed) {
			if (AIFirstMove) {
				if (Hex.isValidMove(6,6)) {
					Hex.board.getHexTile(6, 6).setFill(Color.DARKBLUE);
					AIFirstMove = false;
				}
				else if (Hex.isValidMove(6,7)) {
					Hex.board.getHexTile(6, 7).setFill(Color.DARKBLUE);
					AIFirstMove = false;
				}
			}
			/*if (HexTile.playerQuadrant() == 1 && HexTile.playerDirection() == 'u') {
				if ()
			}
			*/
		}
	}
		
}
