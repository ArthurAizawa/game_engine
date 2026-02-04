package game_engine_zelda.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game_engine_zelda.main.Game;
import game_engine_zelda.world.World;

public class Enemy extends Entity {

	private double speed = 1.0;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void tick() {
		if (Game.rand.nextInt(100) < 50) {

			if ((int) x < Game.player.getX() && World.isFree((int) (x + speed), getY(), this) 
					&& isColidding((int) (x + speed), getY())) {
				x += speed;
			} else if ((int) x > Game.player.getX() && World.isFree((int) (x - speed), getY(), this) 
					&& isColidding((int) (x - speed), getY())) {
				x -= speed;
			} else if ((int) y < Game.player.getY() && World.isFree(getX(), (int) (y + speed), this)
					&& isColidding(getX(), (int) (y + speed))) {
				y += speed;
			} else if ((int) y > Game.player.getY() && World.isFree(getX(), (int) (y - speed), this)
					&& isColidding(getX(), (int) (y + speed))){
				y -= speed;
			}
		}
	}

	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext, ynext, World.TILE_SIZE, World.TILE_SIZE);
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			
			if (e == this) {
				continue;
			}
			
			Rectangle targetEnemy = new Rectangle(xnext, ynext, World.TILE_SIZE, World.TILE_SIZE);
			if (enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		return false;
	}
}
