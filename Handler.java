package se.tutorial.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Handler {

	// List<GameObject> object = new ArrayList<GameObject>();
	List<GameObject> object = Collections.synchronizedList(new ArrayList<GameObject>());

	public int spd = 5;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.tick();
		}
	}

	public void render(Graphics g) {

		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			try {
				tempObject.render(g);
			} catch (Exception e) {
				System.out.println(object.size());
				System.out.println(i);
				throw e;
			}
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);

	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemys() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			if (tempObject.getId() != ID.player) { // removeObject(object.get(i));
				object.clear();
				if (Game.gameState != Game.STATE.End) {
					addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.player, this));
				}
			}
		}

	}
}
