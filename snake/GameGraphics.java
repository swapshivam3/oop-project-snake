package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameGraphics
extends JPanel
implements ActionListener{
	private Timer t = new Timer(100, this);
	public String state;
	
	public Snake s;
	public Food f;
	private Game game;
	private int w = Game.width;
	private int h = Game.height;
	private int sm = Game.sizemultiplier;

	Graphics2D g2d;
	
	public GameGraphics(Game g, int x) {
		t=new Timer(x, this);
		w = Game.width;
		h = Game.height;
		sm = Game.sizemultiplier;
		t.start();
		state = "START";
		setBorder(BorderFactory.createLineBorder(Color.white));

		game = g;
		s = g.getSnake();
		f = g.getFood();
		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
	}
	
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, 800,800);
		if(state == "START") {
			g2d.setColor(Color.white);
			g2d.drawString("Press W to start", w/2 * sm - 40, h / 2 * sm - 20);
		}
		else if(state == "RUNNING") {
			

			g2d.setColor(Color.red);
			for(Rectangle r: f.getFoodList())
				g2d.fill(r);
			
		
			g2d.setColor(Color.green);
			for(Rectangle r : s.getBody()) {
				g2d.fill(r);
			}
			g2d.setColor(Color.white);
			g2d.drawString("Score: " + (s.getBody().size() - 3) * game.multiplier, w / 2, h / 2 + 10);
		}
		else if(state=="END")
		{
			game.mainMenu.drawGameOver(game);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		game.update();
	}
	
}
