package start;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Hex extends Application{
	
	static Board board = new Board();
	public static boolean hasWon = false;
	
	private Parent createRoot() throws Exception {
		BorderPane root = new BorderPane();
		Pane gameBoard = new Pane();
		StackPane boardPane = new StackPane();
		Button newGame = new Button("New Game");
		newGame.setOnMouseClicked(e -> { board.clearBoard(); });
		final ToggleGroup group = new ToggleGroup();
		RadioButton red = new RadioButton("Player Color Red");
		red.setOnMouseClicked(e -> { Player.IsRed = true; board.clearBoard(); });
		red.setSelected(true);
		RadioButton blue = new RadioButton("Player Color Blue");
		blue.setOnMouseClicked(e -> { Player.IsRed = false; board.clearBoard(); });
		red.setToggleGroup(group);
		blue.setToggleGroup(group);
		
		FlowPane UI = new FlowPane();
		newGame.setLayoutX(100);
		newGame.setLayoutY(200);
		
		boardPane.getChildren().add(gameBoard);
		
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				gameBoard.getChildren().add(board.getHexTile(i, j));
			}
		}
		
		Image hexTitle = new Image( new FileInputStream("C:/Users/Peter/Downloads/HexTitle.jpg"));
		ImageView imgView = new ImageView(hexTitle);
		imgView.setFitWidth(200);
		imgView.setFitHeight(100);
		
		UI.getChildren().addAll(imgView, newGame, red, blue);
		root.setCenter(boardPane);
		root.setTop(UI);
		return root;
	}	
	public static boolean isValidMove(int i, int j) throws Exception {
		if (!Player.isTurn)
			return false;
		else if (board.getHexTile(i, j).isFilled())
			return false;
		else if (!inProximity(i, j) && (!Player.isFirstMove || !Comp.AIFirstMove))
			return false;
		else
			return true;
	}
	public static boolean hasRedWon() throws Exception {
		boolean top = false;
		boolean bottom = false;
	
		for (int j = 1; j < 12; j++) {
			if (board.getHexTile(1, j).getFill() == Color.DARKRED)
				top = true;
		}
		for (int j = 1; j < 12; j++) {
			if (board.getHexTile(11, j).getFill() == Color.DARKRED)
				bottom = true;
		}
		
		if (top && bottom)
			return true;
		else
			return false;
			
	}
	public static boolean hasBlueWon() throws Exception {
		boolean left = false;
		boolean right = false;
		
		for (int i = 1; i < 12; i++) {
			if (board.getHexTile(i, 1).getFill() == Color.DARKBLUE)
				left = true;
		}
		for (int i = 1; i < 12; i++) {
			if (board.getHexTile(i, 11).getFill() == Color.DARKBLUE)
				right = true;
		}
	
		if (left && right)
			return true;
		else
			return false;
		
	}
	public static boolean inProximity(int i, int j) throws Exception {
		if (Player.IsRed) {
			if (board.getHexTile(i - 1, j).getFill() == Color.DARKRED) 
				return true;
			else if (board.getHexTile(i - 1, j + 1).getFill() == Color.DARKRED)
				return true;
			else if (board.getHexTile(i, j - 1).getFill() == Color.DARKRED)
				return true;
			else if (board.getHexTile(i, j + 1).getFill() == Color.DARKRED)
				return true;
			else if (board.getHexTile(i + 1, j - 1).getFill() == Color.DARKRED)
				return true;
			else if (board.getHexTile(i + 1, j).getFill() == Color.DARKRED)
				return true;
		}
		if (!Player.IsRed) {
			if (board.getHexTile(i - 1, j).getFill() == Color.DARKBLUE) 
				return true;
			else if (board.getHexTile(i - 1, j + 1).getFill() == Color.DARKBLUE)
				return true;
			else if (board.getHexTile(i, j - 1).getFill() == Color.DARKBLUE)
				return true;
			else if (board.getHexTile(i, j + 1).getFill() == Color.DARKBLUE)
				return true;
			else if (board.getHexTile(i + 1, j - 1).getFill() == Color.DARKBLUE)
				return true;
			else if (board.getHexTile(i + 1, j).getFill() == Color.DARKBLUE)
				return true;
		}
		return false;
	}
	public static void endScreenRed() {
		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		
		Text text = new Text("GAME OVER\n Red Wins!");
		Button button = new Button("New Game");
		button.setOnMouseClicked(e -> {popUp.close(); board.clearBoard(); });
		VBox layout = new VBox(10);
		layout.getChildren().addAll(text, button);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 150, 125);
		popUp.setScene(scene);
		popUp.showAndWait();
	}
	public static void endScreenBlue() {
		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		
		Text text = new Text("GAME OVER\n Blue Wins!");
		Button button = new Button("New Game");
		button.setOnMouseClicked(e -> {popUp.close(); board.clearBoard(); });
		VBox layout = new VBox(10);
		layout.getChildren().addAll(text, button);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 150, 125);
		popUp.setScene(scene);
		popUp.showAndWait();
	}
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createRoot());
		primaryStage.setTitle("Hex");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String args[]) {
		launch(args);
	}
}
