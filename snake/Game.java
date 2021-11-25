package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Game 
implements KeyListener{
	private Snake player;
	private Food food;
	public GameGraphics graphics;
	private Player currentPlayer;

	public MainMenu mainMenu;
	public static int width = 40;
	public static int height = 30;
	public static int sizemultiplier = 20;
	public float multiplier=1f;
	private File filePoint;
	private Clip clipPoint;
	private AudioInputStream soundPoint;
	private File fileCrash;
	private Clip clipCrash;
	private AudioInputStream soundCrash;
	private File fileMusic;
	private Clip clipMusic;
	private AudioInputStream soundMusic;
	int speed;
	Timer timer; 
	TimerTask task;

	public Game(MainMenu mainmenu, Player currentPlayer, int diff, int w,int h) {
		this.width = w;
		this.height = h;
		if (diff == 0)
			setEasy();
		else if (diff == 1)
			setMedium();
		else if (diff == 2)
			setHard();

		// this.sizemultiplier=sm;
		
		this.currentPlayer = currentPlayer;
		player = new Snake();
		food = new Food(player);
		mainMenu=mainmenu;
		

		graphics = new GameGraphics(this,speed);
		timer = new Timer();
			task= new TimerTask(){
			public void run(){
				food.createFood(player);
			}
		};
		timer.schedule(task, 0, 5000);

	}
	private void playMusic()
	{
		try{
			fileMusic = new File(".//loop.wav");
			filePoint = new File(".//point.wav");
			fileCrash = new File(".//crash.wav");
			soundMusic = AudioSystem.getAudioInputStream(fileMusic);
			soundPoint = AudioSystem.getAudioInputStream(filePoint);
			soundCrash = AudioSystem.getAudioInputStream(fileCrash);
			clipMusic=AudioSystem.getClip();
			clipMusic.open(soundMusic);
			clipPoint = AudioSystem.getClip();
			clipPoint.open(soundPoint);
			clipCrash = AudioSystem.getClip();
			clipCrash.open(soundCrash);
		 	clipMusic.setFramePosition(0);
			clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
			clipMusic.start();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	private void playPointSound()
	{
		clipPoint.setFramePosition(0);
		clipPoint.start();
	}
	private void playCrashSound() {
		clipMusic.stop();
		clipCrash.setFramePosition(0);
		clipCrash.start();
	}

	public void setEasy()
	{
		multiplier=1f;
		speed=100;
		setSizeScore();

	}
	public void setMedium()
	{
		multiplier=1.25f;
		speed=85;
		setSizeScore();

	}

	public void setHard()
	{
		multiplier=1.5f;
		speed=75;
		setSizeScore();
	}
	public void setSizeScore()
	{
		if(width*height<=400)
			multiplier+=1f;
		else if(width*height<=800)
			multiplier+=0.5f;
		else if(width*height<=1200)
			multiplier+=0.25f;
		}
	public void start() {
		graphics.state = "RUNNING";
		player.state="UP";
		playMusic();
	}
	
	public void update() {
		if(graphics.state == "RUNNING") {
			if(food.foodCollision()) {
				player.grow();
				playPointSound();
				food.createFood(player);
			}
			else if(wallCollision() || player.selfCollision()) {
				timer.cancel();
				timer.purge();
				playCrashSound();
				float score=player.getBody().size()-3;
				score=score*this.multiplier;
				currentPlayer.addScore(score);
				graphics.state = "END";
			}
			else {
				player.move();
			}
		}
		
	}
	
	private boolean wallCollision() {
		if(player.getBody().get(0).x < 0 || player.getBody().get(0).x >= (width-1) * sizemultiplier 
				|| player.getBody().get(0).y < 0|| player.getBody().get(0).y >= (height-2) * sizemultiplier) {
			return true;
		}
		return false;
	}
	

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(graphics.state == "RUNNING") {
			if(keyCode == KeyEvent.VK_W && player.getMove() != "DOWN") {
				player.state="UP";
			}
		
			if(keyCode == KeyEvent.VK_S && player.getMove() != "UP") {
				player.state="DOWN";
			}
		
			if(keyCode == KeyEvent.VK_A && player.getMove() != "RIGHT") {
				player.state="LEFT";
			}
		
			if(keyCode == KeyEvent.VK_D && player.getMove() != "LEFT") {
				player.state="RIGHT";
			}
		}
		else if(graphics.state=="END")
		{ 

		}
		else 
		{
				this.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	}

	public Snake getSnake() {
		return player;
	}

	public Food getFood() {
		return food;
	}

}
