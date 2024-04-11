package Minesweeper;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Minesweeper extends Application {
	
	
	static int SIZE = 40;
	static Scene scene;
	private static int H = 600;
	static int bombs = 0;
	private static final int xTiles = H/SIZE;
	private static final int yTiles = H/SIZE;
	private static Box[][] grid = new Box[xTiles][yTiles];
	
	
	static Parent createScreen() {
		Pane root = new Pane();
		root.setPrefSize(H, H);
		
		for(int y = 0; y < yTiles; y++) {
			for(int x = 0; x < xTiles; x++) {
				Box box = new Box(x, y, Math.random() < 0.2);
				grid[x][y] = box;
				root.getChildren().add(box);
			}
		}
		
		for(int y = 0; y < yTiles; y++) {
			for(int x = 0; x < xTiles; x++) {
				Box box = grid[x][y];
				
				
				if(box.hasBomb) {
					bombs++;
					continue;
				}
				
				double bombs = surround(box).stream().filter(b -> b.hasBomb).count();
				if(bombs > 0) {
					box.bomb.setText(String.valueOf(bombs));
				}
				
				
			}
		}
		
		return root;
	}
	
	
	static List<Box> surround(Box box){
		List<Box> surround = new ArrayList<>();
		
		int[] points = new int[] {
				-1, -1,
				-1, 0,
				-1, 1,
				0, -1,
				0, 1,
				1, -1,
				1, 0,
				1, 1,
				
		};
		
		for(int i = 0; i < points.length; i++) {
			int dx = points[i];
			int dy = points[++i];
			
			int newX = box.x + dx;
			int newY = box.y + dy;
			
			if(newX >= 0 && newX < xTiles && newY >= 0 && newY < yTiles) {
				surround.add(grid[newX][newY]);
				
			}
		}
		
		
		return surround;
	}


	@Override
	public void start(Stage st) throws Exception {
		Alert start = new Alert(Alert.AlertType.INFORMATION);
		
		
		scene = new Scene(Minesweeper.createScreen());
		
		st.setScene(scene);
		st.show();
		start.setTitle("Instructions!");
		start.setHeaderText("Welcome to MineSweeper!");
		start.setContentText("Task: You need to find all the bombs that "
				+ "are hidden on the board! Be careful though, if you hit a bomb, you lose! If you come"
				+ " across a place in which you believe a bomb resides, flag it! Once you find all the"
				+ " bombs you will ge given a trophy as the winner!"
				+ "\nGood Luck!! :)"
				+ "\nControls:"
				+ "\nRigth Click: Flag"
				+ "\nLeft Click: Check position on the  board");
		start.showAndWait();
		
	}
		
	
	
	
	


}
