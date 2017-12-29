package TheCherno_GameProgramming.levels.tile;

import TheCherno_GameProgramming.graphics.Screen;
import TheCherno_GameProgramming.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		
	}

	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() { //unless overridden in a subclass, not solid by default
		return false;
	}
}
