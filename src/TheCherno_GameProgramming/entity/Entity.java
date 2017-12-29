package TheCherno_GameProgramming.entity;

import java.util.Random;

import TheCherno_GameProgramming.graphics.Screen;
import TheCherno_GameProgramming.levels.Level;

public abstract class Entity {
	
	public int x, y;
	private boolean removed = false; //whether the entity's been removed from the level
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
			//Remove entity from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}

}
