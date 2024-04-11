package Minesweeper;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Minesweeper extends Application {
	
	
	static int SIZE = 40;
	static Scene scene;
	private static int H = 600;
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
		scene = new Scene(Minesweeper.createScreen());
		
		st.setScene(scene);
		st.show();
	}
		
	
	
	
	


}
