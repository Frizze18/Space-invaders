package se.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import se.tutorial.main.Game.STATE;

public class Highscore extends MouseAdapter implements Serializable  {

	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;

	private int[]  highscore = new int[3];

	public Highscore(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}

	public void tick() {

	}
	
	public void writeToFile (HUD hud)throws IOException{
		
		ObjectOutputStream utström = new ObjectOutputStream(new FileOutputStream("C:\\Users\\patri\\workspace\\first game\\highscore.txt"));
		int score = hud.getScore();
		int level = hud.getLevel();
		
		//HUR SKA JAG FÅ ATT LEVEL ÄR VÄRT MER ÄN SCORE PÅ hIGHSCOREN?
		// KANSKE OM VARJE LVL OMVANDLAS TILL 1000 I SCORE.
		
		

		utström.writeInt(score);
		utström.writeInt(level);
		
		//if(score >highscore){
			
		//}
		
		utström.close();
	}
	public void readToFile() throws IOException{
		ObjectInputStream inström = new ObjectInputStream(new FileInputStream("C:\\Users\\patri\\workspace\\first game\\highscore.txt"));
		
		inström.close();
	}
	
	public void render(Graphics g) {

		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);

		g.setFont(fnt);
		g.setColor(Color.WHITE);
		g.drawString("Highscore", 190, 75);

		g.drawRect(200, 300, 200, 64);
		g.setFont(fnt2);
		g.drawString("Menu", 235, 340);
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Highscore) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
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
}
