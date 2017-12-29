package TheCherno_GameProgramming.levels;

import java.util.Random;

public class RandomLevel extends Level{

	private static final Random random = new Random(); //to be used to generate a random tile type (ex. grass, water) on our map
	
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	@Override
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x+(y*width)] = random.nextInt(4);
			}
		}
	}
	

}
