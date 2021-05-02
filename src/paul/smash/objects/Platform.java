package paul.smash.objects;
import java.awt.Graphics;
import java.awt.Rectangle;

import paul.smash.framework.GameObject;
import paul.smash.framework.ObjectType;

public class Platform extends GameObject{
	private int width;
	private int height;

	public Platform(int x, int y, int width, int height) {
		super(x, y, width, height);	
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = ObjectType.PLATFORM;
	}

	@Override
	public void tick() {
		return;
	}

	@Override
	public void draw(Graphics g) {
		return;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, width, height);
	}

}
