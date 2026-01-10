package game_engine_zelda;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	public final int WIDTH = 160;
	public final int HEIGHT = 120;
	public final int SCALE = 3;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();	
	}
	
	public void initFrame() {
		frame = new JFrame("Game Ingine");
		frame.add(this);
		frame.setResizable(false);
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
		
	}
	
	public void render() {
		
	}
	
	@Override
	public void run() {
		long lasTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lasTime) / ns;
			lasTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		} // fim while
		
	}

}

