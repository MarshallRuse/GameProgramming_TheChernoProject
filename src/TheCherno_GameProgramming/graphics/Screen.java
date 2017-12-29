package TheCherno_GameProgramming.graphics;

import java.util.Random;

import TheCherno_GameProgramming.levels.tile.Tile;

public class Screen {
	
	private int width, height;
	private final int MAP_SIZE = 64;
	private final int MAP_SIZE_MASK = 63;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; //how many tiles our map in the game is going to be
	private int xOffset, yOffset;
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff); // give each tile of map a random color between black (0x000000) and white
		}
		
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	
	
	public void renderTile(int xp, int yp, Tile tile) { 
		//xp, yp == xposition, yposition
		xp -= xOffset; // "-" b/c we want the map to move in the opposite direction as the player character
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) { //Offsetting
			int ya = y + yp; //ya == yabsolute
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
					// the first conditional is so that it can render the left side of screen off-screen
					// apparently cant do for right and bottom for technical reasons. See Level.render for fix
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) {break;} 
				if (xa < 0) { xa = 0;}
				if (ya < 0) { ya = 0;}
					//NOTE: LHS is where on map the tile is rendered (ie. with Offset), 
					// RHS is just the rendering of the sprite image itself
				pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE]; 
			}
		}
	}
	
		// Called in Level class, with xOffset, yOffset being xScroll, yScroll, the position of the player
	public void setOffset(int xOffset, int yOffset) { 
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
