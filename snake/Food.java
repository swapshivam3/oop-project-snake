package snake;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Food {
	private ArrayList<Rectangle> foodList;
	Snake player;
	private int w = Game.width;
	private int h = Game.height;
	private int sm = Game.sizemultiplier;

	public Food(Snake player) {
		this.player=player;
		w=Game.width;
		h=Game.height;
		sm=Game.sizemultiplier;
		System.out.println(w);
		foodList=new ArrayList<>();
	}
	
	public void createFood(Snake player) {
		boolean onSnake = true;
		Rectangle temp = new Rectangle(sm, sm);
		while(onSnake) {
			onSnake = false;
			temp.setLocation((int)(Math.random() * (w-2))*sm,(int)(Math.random() * (h - 2))*sm);
			
			for(Rectangle r : player.getBody()){
				if(r.x == temp.x && r.y == temp.y) {
					onSnake = true;
				}
			}
		}
		foodList.add(temp);
	}

	public ArrayList<Rectangle> getFoodList()
	{
		return foodList;
	}

	public boolean foodCollision() {
		for (int i = 0; i < getFoodList().size(); i++) {
			if (player.getBody().get(0).x == getFoodList().get(i).getX()
					&& player.getBody().get(0).y == getFoodList().get(i).getY()) {
				getFoodList().remove(i);
				return true;
			}
		}
		return false;
	}
}
