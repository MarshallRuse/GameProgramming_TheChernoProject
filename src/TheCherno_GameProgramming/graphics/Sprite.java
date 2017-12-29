package TheCherno_GameProgramming.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x, y; //the x, y position of the sprite on the spritesheet
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE]; //the size of our sprite
		this.x = x * SIZE; //to access sprite on sheet. Ex. 5th sprite on 2nd row would be x=4, y=1
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int colour) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}
	
	private void setColour(int colour) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = colour;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
			}
		}
	}

}
