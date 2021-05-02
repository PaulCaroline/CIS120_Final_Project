package paul.smash.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	private int speed;
	private int numFrames;
	private int index = 0;
	private int count = 0;

	private BufferedImage[] frames;
	private BufferedImage current;
	
	public Animation(int speed, BufferedImage...args) {
		this.speed = speed;
		frames = new BufferedImage[args.length];
		
		for(int i = 0; i < args.length; i++) {
			frames[i] = args[i];
		}
		
		numFrames = args.length;
	}
	
	public void runAnimation() {
		index++;
		
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame() {
		for (int i = 0; i < numFrames; i++) {
			if (count == i) {
				current = frames[i];
			}
		}
		count++;
		if (count > numFrames) {
			count = 0;
		}
	}

	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(current, x, y, null);
	}

	public void drawAnimation(Graphics g, int x, int y, int width, int height) {
		g.drawImage(current, x, y, width, height, null);
	}
}
