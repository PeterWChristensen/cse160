package start;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Board {
	private double xOffset, yOffset;
	private HexTile[][] hexes;
	public final static double hexWidth = HexTile.r * 2;
	public final static double hexHeight = HexTile.s + HexTile.h * 2;
	
	
	void createBoard(double xOffset, double yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		hexes = new HexTile[13][13];
		
		for (int u = 0; u < 13; u++) {
			for (int v = 0; v < 13; v++ ) {
				hexes[u][v] = new HexTile(u, v);
				hexes[u][v].relocate((v * (HexTile.r * 2)) + u * HexTile.r,
						(u * (HexTile.s + HexTile.h * 2)) - (HexTile.h * u));
				if (u == 0 && v == 0)
					hexes[u][v].setFill(Color.BLACK.brighter());
				else if (u == 12 && v == 12)
					hexes[u][v].setFill(Color.BLACK.brighter());
				else if (u == 0 && v == 12)
					hexes[u][v].setFill(Color.DARKRED.brighter());
				else if (u == 12 && v == 0)
					hexes[u][v].setFill(Color.DARKBLUE.brighter());
				else if (u == 0 || u == 12) 
					hexes[u][v].setFill(Color.DARKRED.brighter());
				else if (v == 0 || v == 12) 
					hexes[u][v].setFill(Color.DARKBLUE.brighter());
			}
		}
	}
	public Board() {
		createBoard(5, 5);
	}
	public void clearBoard() {
		for (int i = 1; i < 12; i++) {
			for (int j = 1; j < 12; j++) {
				hexes[i][j].setFill(Color.WHITE);
			}
		}
		Player.isFirstMove = true;
		Comp.AIFirstMove = true;
		Player.isTurn = true;
	}
	public HexTile getHexTile(int i, int j) throws Exception {
		return hexes[i][j];
	}
}
