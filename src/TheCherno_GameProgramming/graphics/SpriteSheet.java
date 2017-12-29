package TheCherno_GameProgramming.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpriteSheet {
	private String path;
	public final int SIZE; //size of the spritesheet itself, not the sprites
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);

	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE*SIZE]; //can now access every pixel of the spritesheet
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			// Because we want to deal with the pixels making up the image rather than 
			// the image itself...
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w); //last parameter, w, because scans horizontally
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
