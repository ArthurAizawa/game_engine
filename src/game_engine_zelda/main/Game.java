package game_engine_zelda.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import game_engine_zelda.entities.Entity;
import game_engine_zelda.entities.Player;
import game_engine_zelda.graficos.Spritesheet;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable, KeyListener {

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;

	private final int WIDTH = 340;
	private final int HEIGHT = 280;
	private final int SCALE = 3;

	private BufferedImage image;
	
	public static Spritesheet spritesheet;
	public List<Entity> entities;
	
	private Player player;

	public Game() {
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		
		player = new Player(21, 28, 18, 26, spritesheet.getSprite(0, 0, 18, 25));
		entities.add(player);
	}


	public void initFrame() {
		frame = new JFrame("Game Ingine");
		frame.add(this);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {

	}

	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}

	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); // sequencia de buffer para otimziar a renderização
			return;
		}
		// manipulando imagem
		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 255, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		g.dispose();
		g = bs.getDrawGraphics();
		// exibindo imagem na tela
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		bs.show();
	}

	@Override
	public void run() {
		long lasTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lasTime) / ns;
			lasTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		} // fim while

	}


	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP){
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP){
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){

			player.left = false;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
	}

}
