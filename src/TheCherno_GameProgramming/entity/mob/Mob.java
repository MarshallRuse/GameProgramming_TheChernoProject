package TheCherno_GameProgramming.entity.mob;

import TheCherno_GameProgramming.entity.Entity;
import TheCherno_GameProgramming.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0; //direction, 0=N (default), 1=E, 2=S, 3=W 
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision()) {
			x += xa;
			y += ya;
		}
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	private boolean collision() {
		return false;
	}

}
