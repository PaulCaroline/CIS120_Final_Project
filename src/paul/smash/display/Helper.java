package paul.smash.display;

import java.awt.Graphics;
import java.util.LinkedList;

import paul.smash.framework.*;

/*
 * This class serves to consolidate all of the functionality of updating and repainting every 
 * object in the game.
 */

public class Helper {

	public LinkedList<GameObject> objects = new LinkedList<GameObject>();

	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.tick();
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.draw(g);
		}
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void removeObject(GameObject o) {
		objects.remove(o);
	}

	
}
