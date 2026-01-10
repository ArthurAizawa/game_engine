package game_engine_zelda;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static JFrame frame;
	public final int WIDTH = 160;
	public final int HEIGHT = 120;
	public final int SCALE = 3;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame = new JFrame();	

		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void initFrame() {
	}
	
	public static void main(String args[]) {
		Game game = new Game();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

