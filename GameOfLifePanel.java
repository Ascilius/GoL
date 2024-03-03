import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class GameOfLifePanel extends JPanel {

	private int width = 192, height = 108;
	private boolean[][] board = new boolean[width][height];
	private double screenWidth, screenHeight, cellWidth;
	private ArrayList<Point> toSwitch = new ArrayList<Point>();

	private InputHandler inputHandler;

	GameOfLifePanel(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.cellWidth = screenHeight / height;
		if (cellWidth * width > screenWidth) {
			cellWidth = screenWidth / width;
		}
		this.inputHandler = new InputHandler();
		addKeyListener(this.inputHandler);
		setFocusable(true);

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (Math.random() > 0.5) {
					board[i][j] = true;
				} else {
					board[i][j] = false;
				}
			}
		}
	}

	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int) screenWidth, (int) screenHeight);

		g.translate(screenWidth / 2, screenHeight / 2);

		g.setColor(Color.BLACK);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == true) {
					g.fillRect((int) (cellWidth * width / 2.0 * -1 + cellWidth * i), (int) (cellWidth * height / 2.0 * -1 + j * cellWidth), (int) cellWidth, (int) cellWidth);
				}
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == true) {
					if (neighbors(i, j) < 2) {
						toSwitch.add(new Point(i, j));
					} else if (neighbors(i, j) > 3) {
						toSwitch.add(new Point(i, j));
					}
				} else if (board[i][j] == false) {
					if (neighbors(i, j) == 3) {
						toSwitch.add(new Point(i, j));
					}
				}
			}
		}

		for (int i = 0; i < toSwitch.size(); i++) {
			switching((int) toSwitch.get(i).getX(), (int) toSwitch.get(i).getY());
		}
		toSwitch.clear();

		try {
			// TimeUnit.SECONDS.sleep(1);
			TimeUnit.NANOSECONDS.sleep(16666667);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		repaint();
	}

	public int neighbors(int i, int j) {
		int liveNeighbors = 0;
		if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == true) {
			liveNeighbors++;
		}
		if (i - 1 >= 0 && board[i - 1][j] == true) {
			liveNeighbors++;
		}
		if (i - 1 >= 0 && j + 1 < height && board[i - 1][j + 1] == true) {
			liveNeighbors++;
		}
		if (j - 1 >= 0 && board[i][j - 1] == true) {
			liveNeighbors++;
		}
		if (j + 1 < height && board[i][j + 1] == true) {
			liveNeighbors++;
		}
		if (i + 1 < width && j - 1 >= 0 && board[i + 1][j - 1] == true) {
			liveNeighbors++;
		}
		if (i + 1 < width && board[i + 1][j] == true) {
			liveNeighbors++;
		}
		if (i + 1 < width && j + 1 < height && board[i + 1][j + 1] == true) {
			liveNeighbors++;
		}
		return liveNeighbors;
	}

	public void switching(int i, int j) {
		if (board[i][j] == true) {
			board[i][j] = false;
		} else if (board[i][j] == false) {
			board[i][j] = true;
		}
	}

	class InputHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (Math.random() > 0.5) {
							board[i][j] = true;
						} else {
							board[i][j] = false;
						}
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
