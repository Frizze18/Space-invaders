package se.tutorial.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	private Game game;

	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick() {
		scoreKeep++;

		if (scoreKeep >= 200) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			if (game.diff == 0) {
				if (hud.getLevel() >= 1 && hud.getLevel() <= 5) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.addObject(new SmartEnemy(Game.WIDTH / 2, Game.HEIGHT / 2, ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss(Game.WIDTH / 2, Game.HEIGHT - 500, ID.EnemyBoss, handler));
				}
				if(hud.getLevel() == 14){
					handler.clearEnemys();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy, handler));
				}
				if (hud.getLevel() >= 15 && hud.getLevel() <= 20) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 21) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 22) {
					handler.addObject(new SmartEnemy(Game.WIDTH / 2, Game.HEIGHT / 2, ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 25) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss(Game.WIDTH / 2, Game.HEIGHT - 500, ID.EnemyBoss, handler));
				}

			} else if (game.diff == 1) {
				if (hud.getLevel() >= 1 && hud.getLevel() <= 5) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.addObject(new SmartEnemy(Game.WIDTH / 2, Game.HEIGHT / 2, ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss(Game.WIDTH / 2, Game.HEIGHT - 500, ID.EnemyBoss, handler));
				}

			}

		}

	}

}
