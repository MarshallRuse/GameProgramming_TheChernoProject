package TheCherno_GameProgramming.levels.tile;

import TheCherno_GameProgramming.graphics.Screen;
import TheCherno_GameProgramming.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
