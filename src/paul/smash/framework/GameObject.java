package paul.smash.framework;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected ObjectType type;
	private double velY = 0;
	private double velX = 0;
	private boolean falling = true;
	private boolean jumping = false;
	private int height;
	private int width;
	private boolean rightFacing;
	private boolean leftFacing;
	protected ObjectAction action;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	
	public abstract void draw(Graphics g);

	public abstract Rectangle getBounds();
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public ObjectType getType() {
		return type;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public boolean falling() {
		return falling;
	}
	
	public boolean jumping() {
		return jumping;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public boolean rightFacing() {
		return rightFacing;
	}

	public boolean leftFacing() {
		return leftFacing;
	}
	
	public void setLeftFacing(boolean leftFacing) {
		this.leftFacing = leftFacing;
	}
	
	
	public void setRightFacing(boolean rightFacing) {
		this.rightFacing = rightFacing;
	}

	public ObjectAction getAction() {
		return action;
	}

	public void setAction(ObjectAction action) {
		this.action = action;
	}
	
}

