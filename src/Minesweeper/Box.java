package Minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;


public class Box extends StackPane {
	private Button retry;
	int x;
	int y;
	boolean hasBomb;
	boolean isSafe = false;
	private int numBombs = 0;
	
	private Rectangle box = new Rectangle(Minesweeper.SIZE - 2, Minesweeper.SIZE - 2);
	Text bomb = new Text();
	
	public Box(int x, int y, boolean hasBomb) {
		this.x = x;
		this.y = y;
		this.hasBomb = hasBomb;
		
		
		box.setFill(Color.LIGHTGRAY);
		box.setStroke(Color.BLACK);
		bomb.setFill(Color.BLACK);
		bomb.setFont(Font.font(18));
		bomb.setText(hasBomb ? "X" : "");
		bomb.setVisible(false);
		
		getChildren().addAll(box, bomb);
		
		setTranslateX(x * Minesweeper.SIZE);
		setTranslateY(y * Minesweeper.SIZE);
		
		setOnMouseClicked(e -> open());
		
		
		
	}
	public void open() {
		if(isSafe) {
			return;
		}
		if(hasBomb) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Game Over");
			alert.setHeaderText(null);
			alert.setContentText("You hit a mine! Game over!");
			alert.showAndWait();
			
			
		}
		isSafe = true;
		bomb.setVisible(true);
		box.setFill(null);
		
		if(bomb.getText().isEmpty()) {
			Minesweeper.surround(this).forEach(Box::open);
		}
	}
	
	

}
