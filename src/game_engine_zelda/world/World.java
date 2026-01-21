package game_engine_zelda.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game_engine_zelda.entities.Enemy;
import game_engine_zelda.entities.Entity;
import game_engine_zelda.entities.LifePack;
import game_engine_zelda.main.Game;

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
						tiles[xx+(yy*WIDTH)] = new Floor(xx*16, yy*16, Tile.TILE_FLOOR);

						if (pixelAtual == 0xFF000000) {
							tiles[xx+(yy*WIDTH)] = new Floor(xx*16, yy*16, Tile.TILE_FLOOR);
						} else if (pixelAtual == 0xFFFFFFFF) { 
							tiles[xx+(yy*WIDTH)] = new Wall(xx*16, yy*16, Tile.TILE_WALL);
						} else if(pixelAtual == 0xFF1f1fcb) {
							//player
							Game.player.setX(xx*18);
							Game.player.setY(yy*20);
						} else if(pixelAtual == 0xFFff0000){
							Game.entities.add(new Enemy(xx*15, yy*14, 15, 14, Entity.ENEMY_EN));
						} else if(pixelAtual == 0xFFec9411 ) {
							Game.entities.add(new LifePack(xx*9, yy*9, 18, 18, Entity.LIFEPACK_EN));
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
