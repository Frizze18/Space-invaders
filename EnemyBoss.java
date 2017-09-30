package se.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {

	private Handler handler;
	Random r = new Random();

	private int timer = 20;
	private int timer2 = 25;

	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = 0;
		velY = 2;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 24, 24);
	}

	public void tick() {

		x += velX;
		y += velY;

		if (timer <= 0)
			velY = 0;
		else
			timer--;

		if (timer <= 0)
			timer2--;
		if (timer2 <= 0) {
			if (velX == 0)
				velX = 3;
			int spawn = r.nextInt(10);
			if (spawn == 0)
				handler.addObject(new EnemyBossBullet((int) x, (int) y, ID.EnemyBossBullet, handler));

		}
		if (x <= 0 || x >= Game.WIDTH - 32) {
			velX *= -1;
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, 24, 24);
	}

}
