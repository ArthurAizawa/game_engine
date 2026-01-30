package game_engine_zelda.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game_engine_zelda.main.Game;
import game_engine_zelda.world.Camera;

public class Entity {
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(0, 59, 13, 13);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(0, 34, 15, 14);
	protected double x;
	protected double y;
	protected int width, height;
	public int maskX, maskY, maskW, maskH;
	
	protected BufferedImage sprite;
	
	public Entity(int x, int y,int width, int height, BufferedImage sprite)  {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskX = 0;
		this.maskY = 0;
		this.maskW = width;
		this.maskH = height;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX()-Camera.x, this.getY() - Camera.y, null);
	}
	
	public void tick() {
		
	}
}
