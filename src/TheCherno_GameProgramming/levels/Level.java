package TheCherno_GameProgramming.levels;

import TheCherno_GameProgramming.graphics.Screen;
import TheCherno_GameProgramming.graphics.Sprite;
import TheCherno_GameProgramming.levels.tile.Tile;

public class Level {

	int width, height; //for use with random level generation
	protected int[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
	}

	private void loadLevel(String path) {
		
	}

	protected void generateLevel() {
		
	}
	
	private void time() {
		
	}
	
	public void update() {
		
	}
	
	
	
	public void render(int xScroll, int yScroll, Screen screen) {
			// changes offset of map in screen, with xOffset, yOffset being xScroll, yScroll, the position of the player
		screen.setOffset(xScroll, yScroll);
			//sets "corner pins", the top left and bottom-right of the screen, define the area to render
		int x0 = xScroll >> 4; //same as /16, so were moving at the tile level (multiples of 16), not the pixel level
		int x1 = (xScroll + screen.getWidth() + 16) >> 4; //the +16 to render one tile-width beyond edge of screen
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.getHeight() + 16) >> 4;
		
			//Now the actual rendering of the level based on those corner pins. Note the values of y, x
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
					//the tile at the current rendering position. REMEMBER: x, y are TILE-PRECISION due to >> 4 above
					// So a bug here would be calling Tile.render with those x, y, as that method renders at pixel
					// precision, so each tile is a pixel. Bug is resolved in that method with << 4 to get back to pixels
				getTile(x, y).render(x, y, screen); 
			}
		}
	}
	
	public Tile getTile(int x, int y) { //parameters are the map position of the tile, already in place
										// needing to return the Tile to render to be rendered
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.voidTile;
		}
		switch (tiles[x+y*width]) {
			case 0:
				return Tile.grass;
		default:
				return Tile.voidTile;	
		}

	}
	
}
