package se.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import se.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Menu) {
			// Play button
			if (mouseOver(mx, my, 200, 100, 200, 64)) {
				game.gameState = STATE.Select;
				return;
			}
			// Help button
			if (mouseOver(mx, my, 200, 200, 200, 64)) {
				game.gameState = STATE.Help;
			}
			// Quit button;
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				System.exit(1);
			}
		}
		if (game.gameState == STATE.Select) {
			// Normal button
			if (mouseOver(mx, my, 200, 100, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.player, handler));
				handler.clearEnemys();
				handler.addObject(
						new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				game.diff = 0;
			}
			// hard button
			if (mouseOver(mx, my, 200, 200, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.player, handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				game.diff = 1;
			}
			// back button;
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		// Back button in help
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		// Try again button
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 85, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
		//HIGHSCORE BUTTON, IS STILL IN DEVELOPMENT...
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 385, 300, 200, 64)) {
				game.gameState = STATE.Highscore;
//				hud.setLevel(1);
//				hud.setScore(0);
			}
		}
		//BACK BUTTON IN HIGHSCORE MODE, IS STILL IN DEVELOPMENT...
		if (game.gameState == STATE.Highscore) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {

		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Invaders", 205, 75);

			g.setFont(fnt2);
			g.drawRect(200, 100, 200, 64);
			g.drawString("Play", 265, 140);

			g.setColor(Color.WHITE);
			g.drawRect(200, 200, 200, 64);
			g.drawString("Help", 265, 240);

			g.setColor(Color.WHITE);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Quit", 265, 340);
		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 225, 75);

			g.setFont(fnt3);
			g.drawString("Use wsad to move player and dodge the enemies.", 100, 150);

			g.drawRect(200, 300, 200, 64);
			g.setFont(fnt2);
			g.drawString("Back", 265, 340);

		} else if (game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 190, 75);

			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 100, 150);
			g.drawString("You died at level: " + hud.getLevel(), 100, 250);

			g.drawRect(50, 300, 200, 64);
			g.setFont(fnt2);
			g.drawString("Try Again", 85, 340);
			
			g.drawRect(350, 300, 200, 64);
			g.setFont(fnt2);
			g.drawString("Highscore", 385, 340);

		}else if (game.gameState == STATE.Highscore) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Highscore", 190, 75);
			
			g.drawRect(200, 300, 200, 64);
			g.setFont(fnt2);
			g.drawString("Menu", 235, 340);

		}
		else if (game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Select difficulty", 150, 75);

			g.setFont(fnt2);
			g.drawRect(200, 100, 200, 64);
			g.drawString("Normal", 255, 140);

			g.setColor(Color.WHITE);
			g.drawRect(200, 200, 200, 64);
			g.drawString("Hard", 255, 240);

			g.setColor(Color.WHITE);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Back", 255, 340);
		}

	}

}
