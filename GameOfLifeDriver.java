import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameOfLifeDriver {
	public static void main(String[] arg0) {
		JFrame frame = new JFrame("Conway's Game of Life");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		GameOfLifePanel panel = new GameOfLifePanel(screenSize.getWidth(), screenSize.getHeight());
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
}
