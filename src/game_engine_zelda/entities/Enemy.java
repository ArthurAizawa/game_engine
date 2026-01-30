package game_engine_zelda.entities;

import java.awt.image.BufferedImage;

import game_engine_zelda.main.Game;
import game_engine_zelda.world.World;

public class Enemy extends Entity{
	
	private double speed = 0.8;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), getY())) {
			x+=speed;
		} else if((int)x > Game.player.getX() && World.isFree((int)(x-speed), getY())) {
			x-=speed;
		}
		if((int)y < Game.player.getY() && World.isFree(getX(), (int)(y+speed))) {
			y+=speed;
		} else if((int)y >Game.player.getY() && World.isFree(getX(), (int)(y-speed))) {
			y-=speed;
		}
	}
}
