package se.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -6294589025485536944L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;
	public int diff = 0;
	// 0 = normal
	// 1= hard

	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	private Highscore highscore;

	public enum STATE {
		Menu, Help, Game, End, Shop, Select, Highscore

	};

	public static STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		highscore = new Highscore(this, handler, hud);

		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		this.addMouseListener(highscore);

		new Window(WIDTH, HEIGHT, "Space-Invaders", this);
		r = new Random();

		spawner = new Spawn(handler, hud, this);

		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.player, handler));

			handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuPartical(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuPartical, handler));
			}
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {

		if (gameState == STATE.Game) {

			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				if (HUD.HEALTH <= 1) { //DET ÄR HÄR SOM MAN DÖR!!!
					HUD.HEALTH = 100;

					gameState = STATE.End;
					handler.clearEnemys();
					for (int i = 0; i < 5; i++) {
						handler.addObject(
								new MenuPartical(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuPartical, handler));
					}
				/*	try {
						highscore.writeToFile();
					} catch (IOException e) {
						e.printStackTrace();
					}*/
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select
				|| gameState == STATE.Highscore) {
			menu.tick();
			handler.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (paused) {

			Font fnt5 = new Font("arial", 1, 50);
			g.setFont(fnt5);
			g.setColor(Color.red);
			g.drawString("PAUSED", 200, 200);
			Font font = new Font("Arial", 1, 15);
			g.setFont(font);
		}

		if (gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);

		} else if (gameState == STATE.Shop) {
			shop.render(g);

		} else if (gameState == STATE.Highscore) {
			highscore.render(g);
			handler.render(g);

		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
				|| gameState == STATE.Select) {
			handler.render(g);
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {

		new Game();

	}

}
