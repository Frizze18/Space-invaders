package se.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import se.tutorial.main.Game.STATE;

public class Shop extends MouseAdapter {

	Handler handler;
	HUD hud;
	private int B1 = 1000;
	private int B2 = 1000;
	private int B3 = 500;

	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void render(Graphics g) {

		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH / 2 - 100, 50);

		// box1
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade health: ", 110, 120);
		g.drawString("Cost: " + B1, 110, 140);
		g.drawRect(100, 100, 100, 80);

		// box2
		g.drawString("Upgrade Speed: ", 260, 120);
		g.drawString("Cost: " + B2, 260, 140);
		g.drawRect(250, 100, 100, 80);

		// box3
		g.drawString("Refill health: ", 410, 120);
		g.drawString("Cost: " + B3, 410, 140);
		g.drawRect(400, 100, 100, 80);

		g.setFont(new Font("arial", 0, 50));
		g.drawString("Score: " + hud.getScore(), 150, 300);
		g.setFont(new Font("arial", 0, 30));
		g.drawString("Press space to go back", 150, 380);

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.gameState == STATE.Shop) {
			// box1
			if (mx >= 100 && mx <= 200) {
				if (my >= 100 && my <= 180) {
					if (hud.getScore() >= B1) {
						hud.setScore(hud.getScore() - B1);
						B1 += 1000;
						hud.bounds += 20;
						hud.HEALTH = (100 + hud.bounds / 2);
						return;
					}
				}
			}
			// box2
			if (mx >= 250 && mx <= 350) {
				if (my >= 100 && my <= 180) {
					if (hud.getScore() >= B2) {
						hud.setScore(hud.getScore() - B2);
						B2 += 1000;
						handler.spd++;
						return;
					}
				}
			}
			// box3
			if (mx >= 400 && mx <= 500) {
				if (my >= 100 && my <= 180) {
					if (hud.getScore() >= B3) {
						hud.setScore(hud.getScore() - B3);
						hud.HEALTH = (100 + hud.bounds / 2);
						return;
					}
				}
			}
		}
	}
}
