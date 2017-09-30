package se.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 1027193372573448563L;

	public Window(int Width, int height, String title, Game game) {

		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(Width, height));
		frame.setMaximumSize(new Dimension(Width, height));
		frame.setMinimumSize(new Dimension(Width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();

	}

}
