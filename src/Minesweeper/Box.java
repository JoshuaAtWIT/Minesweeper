package Minesweeper;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


public class Box extends StackPane {
	int x;
	int y;
	boolean hasBomb;
	boolean isSafe = false;
	private boolean hasFlag = false;
	

	
	private Rectangle box = new Rectangle(Minesweeper.SIZE - 2, Minesweeper.SIZE - 2);
	Text bomb = new Text();
	Image flag = new Image("https://i.redd.it/zdhjzhyn68py.png");
	Image trophy = new Image("https://adamhg2411.github.io/minesweeper-ag/trophy.ed1c5d4e.png");
	ImageView winTrophy = new ImageView(trophy);
	
	public void showFlag() {
		if(hasFlag) {
			hasFlag = false;
		} else {
			hasFlag = true;
		}
	}
	
	public boolean isFlagged() {
		return hasFlag;
	}
	
	
	
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
		
		box.setOnMouseClicked(e ->
		{
		if(e.getButton() == MouseButton.SECONDARY) {
			if(!hasFlag) {
				hasFlag = true;
				box.setFill(new ImagePattern(flag));
				if(this.hasBomb) {
					Minesweeper.bombs--;
					if(Minesweeper.bombs == 0) {
						Alert win = new Alert(Alert.AlertType.CONFIRMATION);
						win.setTitle("You win!");
						win.setGraphic(winTrophy);
						win.setHeaderText("Congrats!");
						win.showAndWait();
						Minesweeper.scene.setRoot(Minesweeper.createScreen());
					}
				}
			} else {
				if(hasBomb) {
					Minesweeper.bombs++;
				}
				box.setFill(Color.LIGHTGREY);
				hasFlag = false;
			}
				
				
			
			
			
			
		} if(e.getButton() == MouseButton.PRIMARY) {
			if(!hasFlag) {
			open();
			}
		}
		
		});
		
		
	}
	
	public void open() {
		if(isSafe || hasFlag) {
			return;
		}
		if(hasBomb) {
			box.setFill(Color.RED);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Game Over");
			alert.setHeaderText(null);
			alert.setContentText("You hit a mine! Game over!");
			alert.showAndWait();
			Minesweeper.scene.setRoot(Minesweeper.createScreen());
			
			
		}
		
		isSafe = true;
		bomb.setVisible(true);
		box.setFill(null);
		
		if(bomb.getText().isEmpty()) {
			Minesweeper.surround(this).forEach(Box::open);
		}
	}
	
	public static void main(String[] args) {
		
		Application.launch(Minesweeper.class, args);
		
	}
	
	
	
	
	
	

}

	



