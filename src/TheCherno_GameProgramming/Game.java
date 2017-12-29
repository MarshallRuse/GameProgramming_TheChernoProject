package TheCherno_GameProgramming;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import TheCherno_GameProgramming.graphics.Screen;
import TheCherno_GameProgramming.input.Keyboard;
import TheCherno_GameProgramming.levels.Level;
import TheCherno_GameProgramming.levels.RandomLevel;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static int width = 300;
	public static int height = 300 / 16* 9;
	public static int scale = 3; //scaled so you only have to calculate 1/3 of pixel data for display size
	private static String title = "Rain";
	
	private Thread thread;
	private Screen screen;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private boolean running = false;
	
	//Create the image
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	//write data to the image
		//get raster returns the writable raster version of the image, which is associated with the 
		//data buffer for the image through getDataBuffer, which getData returns to us as an int data array,
		// all casted to a DataBufferInt, stored as an array of pixels
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new RandomLevel(64, 64); //64x64 TILES
		
		addKeyListener(key); 
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display"); //this - thread runs this Game class, with "Display" as name
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
		thread.join(); // waits for the threads to conclude and joins them
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() { //called from thread.start() b/c Game implements runnable, and thread is "this"
		//Timer functionality to set update to run 60/sec
		long lastTime = System.nanoTime();
		final double ns = 1_000_000_000.0 / 60;
		double delta = 0;
		
		//1 second time 
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		requestFocus(); // the window takes input as soon as opening
		
		while (running) { //keeps the game loop going, vs just exiting after one iteration
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) { // if more than 1/60th of a second has elapsed
				update(); //logical part of game. Note: divorced from render so that the user's computer
						// speed doesn't affect the game logic (ex. moving 1px per frame, and user's computer
						// can do 1000 FPS, character doesnt move at 1000px/sec). Update will be 60 FPS.
				updates++;
				delta --;
			}
			
			render(); //graphics part of game. Render will be unlimited FPS
			frames++;
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				//System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + " |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	int xScroll = 0, yScroll = 0; // variables incremented in update to scroll the map, parameters of Screen.render
	
	public void update() {
		key.update();
		if (key.up) yScroll--;
		if (key.down) yScroll++;
		if (key.left) xScroll--;
		if(key.right) xScroll++;
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy(); //gets BufferStrategy object of Canvas class to store updated screen image
		if (bs == null) { //don't want to create each call of render (in running), so check if its been created 
							// (getBufferStrategy returns null if buffer strategy hasn't been created)
			createBufferStrategy(3); // 3 means Triple buffering, allowing two frames to be generated in the background
									// which improves speed, as the third screen doesn't have to wait for the second
									// to be pushed to display before it can be created.
									// #s higher than 3 can be used, but don't offer any real benefit
			return;
		}
		
		
		screen.clear();
		level.render(xScroll, yScroll, screen);
		
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics(); //connects the graphics to the buffer essentially
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); // clears the data resources being used to draw the graphics for this frame iteration
		bs.show(); //necessary to show the next buffered frame
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		//the reason game can be added to frame with frame.add() is because class Game extends canvas, so is a subclass
		game.frame.add(game);
		//frame.pack() sets the frame to the size specified by setPreferredSize and the Dimension object in Games constructor
		game.frame.pack();
		//if setDefaultCloseOperation wasnt set, its only the window that would close, but the Game process would keep on running
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null); //centers the window in the screen
		game.frame.setVisible(true);
		game.start();
	}

}
