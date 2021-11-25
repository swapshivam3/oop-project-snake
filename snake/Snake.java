package snake;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Rectangle> body;
	private int w = Game.width;
	private int h = Game.height;
	private int sm = Game.sizemultiplier;
	
	public String state; 
	
	public Snake() {
		w=Game.width;
		h=Game.height;
		sm=Game.sizemultiplier;
		
		body = new ArrayList<>();
		
		Rectangle temp = new Rectangle(sm, sm);
		temp.setLocation(w / 2 * sm, h / 2 * sm);
		body.add(temp);
		
		temp = new Rectangle(sm, sm);
		temp.setLocation((w / 2 - 1) * sm, (h / 2) * sm);
		body.add(temp);
		
		temp = new Rectangle(sm, sm);
		temp.setLocation((w / 2 - 2) * sm, (h / 2) * sm);
		body.add(temp);
		
		state = "null";
	}
	
	public void move() {
		if(state != "null") {
			Rectangle first = body.get(0);
			Rectangle temp = new Rectangle(sm, sm);
			if(state == "UP") {
				temp.setLocation(first.x, first.y - sm);
			}
			else if(state == "DOWN") {
				temp.setLocation(first.x, first.y + sm);
				
			}
			else if(state == "LEFT") {
				temp.setLocation(first.x - sm, first.y);
			}
			else{
				temp.setLocation(first.x + sm, first.y);
			}
			
			body.add(0, temp);
			body.remove(body.size()-1);
			
		}
	}
	
	public void grow() {
		Rectangle first = body.get(0);
		
		Rectangle temp = new Rectangle(sm, sm);
		
		if(state == "UP") {
			temp.setLocation(first.x, first.y - sm);
		}
		else if(state == "DOWN") {
			temp.setLocation(first.x, first.y + sm);
		}
		else if(state == "LEFT") {
			temp.setLocation(first.x - sm, first.y);
		}
		else{
			temp.setLocation(first.x + sm, first.y);
		}
		
		body.add(0, temp);
	}

	public ArrayList<Rectangle> getBody() {
		return body;
	}
	
	public boolean selfCollision() {
		for (int i = 1; i < body.size(); i++) {
			if (body.get(0).x == body.get(i).x && body.get(0).y == body.get(i).y) {
				return true;
			}
		}
		return false;
	}
	
	public String getMove() {
		return state;
	}
}
