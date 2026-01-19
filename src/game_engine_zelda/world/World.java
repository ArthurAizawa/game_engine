package game_engine_zelda.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {

	private Tile[] tiles;
	public static int WIDTH, HEIGHT;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getWidth()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getWidth()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

			for (int i = 0; i < pixels.length; i++) {
				for (int xx = 0; xx < map.getWidth(); xx++) {
					for (int yy = 0; yy < map.getHeight(); yy++) {
						int pixelAtual = pixels[xx + (yy * map.getHeight())];

						if (pixelAtual == 0xFF000000) {
							tiles[xx+(yy*WIDTH)] = new Floor(xx*16, yy*16, Tile.TILE_FLOOR);
						} else if (pixelAtual == 0xFFFFFFFF) {
							tiles[xx+(yy*WIDTH)] = new Wall(xx*16, yy*16, Tile.TILE_WALL);
						} else if(pixelAtual == 0x1f1fcb) {
							tiles[xx+(yy*WIDTH)] = new Floor(xx*16, yy*16, Tile.TILE_FLOOR);
						} else {
							tiles[xx+(yy*WIDTH)] = new Floor(xx*16, yy*16, Tile.TILE_FLOOR);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		for (int xx = 0; xx < WIDTH; xx++) {
			for (int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
