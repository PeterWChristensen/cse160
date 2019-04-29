package start;

import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class HexTile extends Polygon{
	
	final static double s = 20.0;
	final static double r = calculateR();
	final static double h = calculateH();
	private Point[] points;
	private int i, j;
	private double mouseX, mouseY;
	public static int lastI, lastJ, secLastI, secLastJ;
	
	
	private void createHex(int i, int j) {
		this.i = i;
		this.j = j;
		points = new Point[6];
		
		points[0] = new Point(j, i);
		points[1] = new Point(j + r, i + h);
		points[2] = new Point(j + r, i + s + h);
		points[3] = new Point(j , i + s + h + h);
		points[4] = new Point(j - r, i + s + h);
		points[5] = new Point(j - r, i + h);
		
		for (int z = 0; z < 6; z++) {
			getPoints().addAll(points[z].x, points[z].y);
		}
	}
	
	public HexTile(int i, int j) {
		createHex(i,j);
		setStroke(Color.BLACK);
		setFill(Color.WHITE);
		 
		if (i % 2 == 0) 
			relocate(j * (HexTile.r * 2), (HexTile.s + HexTile.h * 2) * i);
		else
			relocate(j * (HexTile.r * 2 + HexTile.r),((HexTile.s + HexTile.h * 2) - HexTile.h) * i);
		
		setOnMousePressed(e -> {
			//mouseX = e.getSceneX();
			//mouseY = e.getSceneY();
			try {
				if (Hex.isValidMove(i, j)) {
					if (Player.IsRed) {
						secLastI = lastI;
						secLastJ = lastJ;
						lastI = i;
						lastJ = j;
						
						setFill(Color.DARKRED);
						Comp.compMove();
						Player.isFirstMove = false;
						if (Hex.hasRedWon())
							Hex.endScreenRed();
					}
					else {
						secLastI = lastI;
						secLastJ = lastJ;
						lastI = i;
						lastJ = j;
						
						setFill(Color.DARKBLUE);
						Comp.compMove();
						Player.isFirstMove = false;
						if (Hex.hasBlueWon())
							Hex.endScreenBlue();
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		});
	}
	public boolean isFilled() {
		if (getFill() != Color.WHITE)
			return true;
		else
			return false;
	}
	public static int playerQuadrant() {
		if (lastI <= 6 && lastJ >= 6)
			return 1;
		else if (lastI <= 6 && lastJ <= 6)
			return 2;
		else if (lastI >= 6 && lastJ <= 6)
			return 3;
		else if (lastI >= 6 && lastJ >= 6)
			return 4;
		else 
			return -1;
	}
	public static char playerDirection() {
		if (Player.IsRed) {
			if (lastI >= secLastI)
				return 'd';
			else
				return 'u';
		}
		if (!Player.IsRed) {
			if (lastJ >= secLastJ)
				return 'r';
			else
				return 'l';
		}
		return 'n';
	}
	
	public static double calculateH() {
		return Math.sin(degreesToRadians(30)) * s;
	}
	public static double calculateR() {
		return Math.cos(degreesToRadians(30)) * s;
	}
	public static double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180;
	}
}
